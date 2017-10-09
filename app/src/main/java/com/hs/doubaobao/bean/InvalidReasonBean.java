package com.hs.doubaobao.bean;

/**
 * 作者：zhanghaitao on 2017/9/20 17:49
 * 邮箱：820159571@qq.com
 *
 * @describe:无效列表的BEAN
 */

public class InvalidReasonBean {

    /**
     * resCode : 1
     * resData : {"disableDetail":{"auditor":"admin123","auditorTime":"2017-09-19","cname":"张三","operatorName":"admin123","remark":""}}
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
         * disableDetail : {"auditor":"admin123","auditorTime":"2017-09-19","cname":"张三","operatorName":"admin123","remark":""}
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
             * auditor : admin123
             * auditorTime : 2017-09-19
             * cname : 张三
             * operatorName : admin123
             * remark :
             */

            private String auditor;
            private String auditorTime;
            private String cname;
            private String operatorName;
            private String remark;

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

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
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
