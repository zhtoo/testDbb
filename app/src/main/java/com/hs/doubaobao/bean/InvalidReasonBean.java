package com.hs.doubaobao.bean;

/**
 * 作者：zhanghaitao on 2017/9/20 17:49
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvalidReasonBean {


    /**
     * resCode : 1
     * resData : {"disableDetail":{"account":0,"auditor":"admin123","auditorTime":"2017-09-19","operatorName":"admin123","remark":""}}
     * resMsg : SUCCESS
     */

    private int resCode;
    private ResDataBean resData;
    private String resMsg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public ResDataBean getResData() {
        return resData;
    }

    public void setResData(ResDataBean resData) {
        this.resData = resData;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public static class ResDataBean {
        /**
         * disableDetail : {"account":0,"auditor":"admin123","auditorTime":"2017-09-19","operatorName":"admin123","remark":""}
         */

        private DisableDetailBean disableDetail;

        public DisableDetailBean getDisableDetail() {
            return disableDetail;
        }

        public void setDisableDetail(DisableDetailBean disableDetail) {
            this.disableDetail = disableDetail;
        }

        public static class DisableDetailBean {
            /**
             * account : 0
             * auditor : admin123
             * auditorTime : 2017-09-19
             * operatorName : admin123
             * remark :
             */

            private int account;
            private String auditor;
            private String auditorTime;
            private String operatorName;
            private String remark;

            public int getAccount() {
                return account;
            }

            public void setAccount(int account) {
                this.account = account;
            }

            public String getAuditor() {
                return auditor;
            }

            public void setAuditor(String auditor) {
                this.auditor = auditor;
            }

            public String getAuditorTime() {
                return auditorTime;
            }

            public void setAuditorTime(String auditorTime) {
                this.auditorTime = auditorTime;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
