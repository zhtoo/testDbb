package com.hs.doubaobao.model.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.LoginBean;
import com.hs.doubaobao.model.main.MainActivity;
import com.hs.doubaobao.utils.Base64Util;
import com.hs.doubaobao.utils.SPHelp;
import com.hs.doubaobao.utils.log.LogWrap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/11 17:43
 * 邮箱：820159571@qq.com
 *
 * @describe: 登录界面
 */

public class LoginActivity extends AppBarActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    private LoginContract.Presenter presenter;

    private ImageView appLogo;
    //用户名和密码
    private EditText loginUsername;
    private EditText loginPwd;
    //登录
    private Button loginBtnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        hideTitleBar();

        initView();
        initListener();

        //将Presenter和View进行绑定
        new LoginPresener(this,this);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        appLogo = (ImageView) findViewById(R.id.app_logo);
        loginPwd = (EditText) findViewById(R.id.login_pwd);
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginBtnSure = (Button) findViewById(R.id.login_btn_sure);
        loginBtnSure.setOnClickListener(this);

        //初始化数据（不想单独写一个方法）
        loginUsername.setText(SPHelp.getData("name"));
        loginPwd.setText(SPHelp.getData("password"));
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        loginUsername.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    //处理事件
                    loginPwd.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 请求成功返回
     * @param bean
     */
    @Override
    public void setData(LoginBean bean) {

        BaseParams.USER_ID = String.valueOf(bean.getResData().getId());
        BaseParams.OPERATOR_NAME= bean.getResData().getName();

        //跳转到主界面
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("name", BaseParams.OPERATOR_NAME);
        startActivity(intent);
        finish();
    }

    /**
     * 请求失败返回
     * @param text
     */
    @Override
    public void setError(String text) {
        if(text.startsWith("提示")){
            text = text.substring(2,text.length());
            Toast.makeText(MyApplication.getContext(),text, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MyApplication.getContext(), "网络不给力", Toast.LENGTH_SHORT).show();
        }
        LogWrap.e(TAG,text);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 点击登录
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn_sure) {
            String name = loginUsername.getText().toString().trim();
            String password = loginPwd.getText().toString().trim();
            //保存用户名和密码
            SPHelp.setData("name", name);
            SPHelp.setData("password", password);
            //存放参数
            Map<String, String> map = new LinkedHashMap<>();
            map.put("id", name);
            map.put("pwd", Base64Util.encode(password));
            //获取数据
            presenter.getData(map);
        }
    }


}
