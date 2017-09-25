package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/18 15:38
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class HomeBean {


    /**
     * resCode : 1
     * resData : {"messageCount":{"messageRole1":1,"messageRole2":1,"messageRole3":1,"messageRole4":1,"messageRole5":1,"messageRole6":1,"messageRole7":1,"messageRole8":1},"pageDataList":{"list":[{"account":20000,"applydate":"2017-09-06","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":1,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"旅游","status":"30","type":"3"},{"account":30000,"applydate":"2017-09-11","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"李四","id":2,"mobilephone":"15555555555","operName":"平台","period":2,"purpose":"环球","status":"30","type":"2"},{"account":1000,"applydate":"2017-9-18","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":3,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"玩","status":"30","type":"3"}],"page":{"currentPage":1,"currentPageSum":0,"end":0,"pages":2147483647,"pernum":0,"start":0,"total":3},"type":0},"roleIdList":[1]}
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
         * messageCount : {"messageRole1":1,"messageRole2":1,"messageRole3":1,"messageRole4":1,"messageRole5":1,"messageRole6":1,"messageRole7":1,"messageRole8":1}
         * pageDataList : {"list":[{"account":20000,"applydate":"2017-09-06","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":1,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"旅游","status":"30","type":"3"},{"account":30000,"applydate":"2017-09-11","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"李四","id":2,"mobilephone":"15555555555","operName":"平台","period":2,"purpose":"环球","status":"30","type":"2"},{"account":1000,"applydate":"2017-9-18","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":3,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"玩","status":"30","type":"3"}],"page":{"currentPage":1,"currentPageSum":0,"end":0,"pages":2147483647,"pernum":0,"start":0,"total":3},"type":0}
         * roleIdList : [1]
         */

        private MessageCountBean messageCount;
        private PageDataListBean pageDataList;
        private List<Integer> roleIdList;

        public MessageCountBean getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(MessageCountBean messageCount) {
            this.messageCount = messageCount;
        }

        public PageDataListBean getPageDataList() {
            return pageDataList;
        }

        public void setPageDataList(PageDataListBean pageDataList) {
            this.pageDataList = pageDataList;
        }

        public List<Integer> getRoleIdList() {
            return roleIdList;
        }

        public void setRoleIdList(List<Integer> roleIdList) {
            this.roleIdList = roleIdList;
        }



        public static class MessageCountBean {
            /**
             * messageRole1 : 1   平台角色（超级管理）
             * messageRole2 : 1
             * messageRole3 : 1
             * messageRole4 : 1
             * messageRole5 : 1
             * messageRole6 : 1
             * messageRole7 : 1  风控角色
             * messageRole8 : 1  总经理角色
             */

            private int messageRole1;
            private int messageRole2;
            private int messageRole3;
            private int messageRole4;
            private int messageRole5;
            private int messageRole6;
            private int messageRole7;
            private int messageRole8;

            public int getMessageRole1() {
                return messageRole1;
            }

            public void setMessageRole1(int messageRole1) {
                this.messageRole1 = messageRole1;
            }

            public int getMessageRole2() {
                return messageRole2;
            }

            public void setMessageRole2(int messageRole2) {
                this.messageRole2 = messageRole2;
            }

            public int getMessageRole3() {
                return messageRole3;
            }

            public void setMessageRole3(int messageRole3) {
                this.messageRole3 = messageRole3;
            }

            public int getMessageRole4() {
                return messageRole4;
            }

            public void setMessageRole4(int messageRole4) {
                this.messageRole4 = messageRole4;
            }

            public int getMessageRole5() {
                return messageRole5;
            }

            public void setMessageRole5(int messageRole5) {
                this.messageRole5 = messageRole5;
            }

            public int getMessageRole6() {
                return messageRole6;
            }

            public void setMessageRole6(int messageRole6) {
                this.messageRole6 = messageRole6;
            }

            public int getMessageRole7() {
                return messageRole7;
            }

            public void setMessageRole7(int messageRole7) {
                this.messageRole7 = messageRole7;
            }

            public int getMessageRole8() {
                return messageRole8;
            }

            public void setMessageRole8(int messageRole8) {
                this.messageRole8 = messageRole8;
            }
        }

        public static class PageDataListBean {
            /**
             * list : [{"account":20000,"applydate":"2017-09-06","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":1,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"旅游","status":"30","type":"3"},{"account":30000,"applydate":"2017-09-11","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"李四","id":2,"mobilephone":"15555555555","operName":"平台","period":2,"purpose":"环球","status":"30","type":"2"},{"account":1000,"applydate":"2017-9-18","approveStatus":0,"auditor":"","auditorTime":"","content":"","cusName":"张三","id":3,"mobilephone":"13033333333","operName":"平台","period":1,"purpose":"玩","status":"30","type":"3"}]
             * page : {"currentPage":1,"currentPageSum":0,"end":0,"pages":2147483647,"pernum":0,"start":0,"total":3}
             * type : 0
             */

            private PageBean page;
            private int type;
            private List<ListBean> list;

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class PageBean {
                /**
                 * currentPage : 1
                 * currentPageSum : 0
                 * end : 0
                 * pages : 2147483647
                 * pernum : 0
                 * start : 0
                 * total : 3
                 */

                private int currentPage;
                private int currentPageSum;
                private int end;
                private int pages;
                private int pernum;
                private int start;
                private int total;

                public int getCurrentPage() {
                    return currentPage;
                }

                public void setCurrentPage(int currentPage) {
                    this.currentPage = currentPage;
                }

                public int getCurrentPageSum() {
                    return currentPageSum;
                }

                public void setCurrentPageSum(int currentPageSum) {
                    this.currentPageSum = currentPageSum;
                }

                public int getEnd() {
                    return end;
                }

                public void setEnd(int end) {
                    this.end = end;
                }

                public int getPages() {
                    return pages;
                }

                public void setPages(int pages) {
                    this.pages = pages;
                }

                public int getPernum() {
                    return pernum;
                }

                public void setPernum(int pernum) {
                    this.pernum = pernum;
                }

                public int getStart() {
                    return start;
                }

                public void setStart(int start) {
                    this.start = start;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }
            }

            public static class ListBean {
                /**
                 * account : 20000
                 * applydate : 2017-09-06
                 * approveStatus : 0
                 * auditor :
                 * auditorTime :
                 * content :
                 * cusName : 张三
                 * id : 1
                 * mobilephone : 13033333333
                 * operName : 平台
                 * period : 1
                 * purpose : 旅游
                 * riskControl : 0
                 * managerRation : 0
                 * status : 30
                 * type : 3
                 */

                private double account;
                private String applydate;
                private int approveStatus;
                private String auditor;
                private String auditorTime;
                private String content;
                private String cusName;
                private int id;
                private String mobilephone;
                private String operName;
                private int period;
                private String purpose;
                private String status;
                private String type;
                private double riskControl;
                private double managerRation ;

                public double getAccount() {
                    return account;
                }

                public void setAccount(double account) {
                    this.account = account;
                }

                public String getApplydate() {
                    return applydate;
                }

                public void setApplydate(String applydate) {
                    this.applydate = applydate;
                }

                public int getApproveStatus() {
                    return approveStatus;
                }

                public void setApproveStatus(int approveStatus) {
                    this.approveStatus = approveStatus;
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

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCusName() {
                    return cusName;
                }

                public void setCusName(String cusName) {
                    this.cusName = cusName;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getMobilephone() {
                    return mobilephone;
                }

                public void setMobilephone(String mobilephone) {
                    this.mobilephone = mobilephone;
                }

                public String getOperName() {
                    return operName;
                }

                public void setOperName(String operName) {
                    this.operName = operName;
                }

                public int getPeriod() {
                    return period;
                }

                public void setPeriod(int period) {
                    this.period = period;
                }

                public String getPurpose() {
                    return purpose;
                }

                public void setPurpose(String purpose) {
                    this.purpose = purpose;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
                public double getRiskControl() {
                    return riskControl;
                }

                public void setRiskControl(double riskControl) {
                    this.riskControl = riskControl;
                }

                public double getManagerRation() {
                    return managerRation;
                }

                public void setManagerRation(double managerRation) {
                    this.managerRation = managerRation;
                }
            }
        }
    }
}
