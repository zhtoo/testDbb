package com.hs.doubaobao.http;

import android.os.Looper;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 作者：zhanghaitao on 2017/9/29 11:48
 * 邮箱：820159571@qq.com
 * @describe:拦截器----用于联网请求信息打印类。
 */

public class HttpLogging implements Interceptor {

    private final Logger logger;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public HttpLogging() {
        this(Logger.DEFAULT);
    }
    public HttpLogging(Logger logger) {
        this.logger = logger;
    }

    public interface Logger {
        void log(String message);

        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                com.hs.doubaobao.utils.log.Logger.i("HSClient", message);
            }
        };
    }


    public enum Level {
        /**
         * 不打印日志
         */
        NONE,
        /**
         * 日志请求和响应行。
         * <p/>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * 日志请求和响应行和它们各自的头信息
         * <p/>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * 日志请求和响应行以及它们各自的头信息和主体(如果存在的话)。
         * <p/>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    private volatile Level level = Level.NONE;

    /**
     * 设置拦截器日志的级别。
     */
    public HttpLogging setLevel(Level level) {
        if (level == null)
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody    = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody    = request.body();
        boolean     hasRequestBody = requestBody != null;

        Connection connection          = chain.connection();
        Protocol   protocol            = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String     requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logger.log(requestStartMessage);

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor.
                // Force them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    logger.log("Content-Type: " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    logger.log("Content-Length: " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    logger.log(name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
                logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset   charset     = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                logger.log("请求参数：");
                if (isPlaintext(buffer)) {
                    String requestParams = buffer.readString(charset);
                    requestParams = requestParams.replaceAll("&","\n");
                    logger.log(requestParams);
                    logger.log("--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
                } else {
                    logger.log("--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }
        //记录打印的当前时间
        long     startNs  = System.nanoTime();
        //继续请求网络（可以理解为该方法拦截了网络请求，在此处释放）
        Response response = chain.proceed(request);
        //计算请求回来之后的时间和请求之前的时间，即请求时间
        long     tookMs   = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        //响应体
        ResponseBody responseBody  = response.body();
        long         contentLength = responseBody.contentLength();
        String       bodySize      = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        logger.log("<-- " + response.code() + ' ' + response.message() + ' ' + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", " +
                bodySize + " body" : "") + ')');

        if (response.code() >= 400) {
            Looper.prepare();
            Looper.loop();
        }

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                logger.log(headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpEngine.hasBody(response)) {
                logger.log("<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                logger.log("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset   charset     = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        logger.log("");
                        logger.log("Couldn't decode the response body; charset is likely malformed.");
                        logger.log("<-- END HTTP");
                        return response;
                    }
                }

                if (!isPlaintext(buffer)) {
                    logger.log("");
                    logger.log("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                    return response;
                }

                if (contentLength != 0) {
                    logger.log("");
                    logger.log(buffer.clone().readString(charset));
                }

                logger.log("<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     * 如果问题主体可能包含可读的文本，则返回true。使用一小段代码点来检测常用的二进制文件签名中的unicode控制字符。
     */
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix    = new Buffer();
            long   byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                if (Character.isISOControl(prefix.readUtf8CodePoint())) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }




}
