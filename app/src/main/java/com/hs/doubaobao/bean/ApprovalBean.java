package com.hs.doubaobao.bean;

/**
 * 作者：zhanghaitao on 2017/9/25 14:20
 * 邮箱：820159571@qq.com
 *
 * @describe:审批返回的BEAN
 */

public class ApprovalBean {

    /**
     * resCode : 1
     * resMsg :
     * resData : {}
     */

    private int resCode;
    private String resMsg;
    private ResDataBean resData;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public ResDataBean getResData() {
        return resData;
    }

    public void setResData(ResDataBean resData) {
        this.resData = resData;
    }

    public static class ResDataBean {
    }
}
