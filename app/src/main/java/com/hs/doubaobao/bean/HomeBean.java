package com.hs.doubaobao.bean;

import com.google.gson.annotations.SerializedName;

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
     * resData : {"messageCount":{"1":1},"pageDataList":{"list":[],"page":{"currentPage":1,"currentPageSum":0,"end":0,"pages":0,"pernum":0,"start":0,"total":0},"type":0},"roleIdList":[1]}
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
         * messageCount : {"1":1}
         * pageDataList : {"list":[],"page":{"currentPage":1,"currentPageSum":0,"end":0,"pages":0,"pernum":0,"start":0,"total":0},"type":0}
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
             * 1 : 1
             */

            @SerializedName("1")
            private int _$1;

            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }
        }

        public static class PageDataListBean {
            /**
             * list : []
             * page : {"currentPage":1,"currentPageSum":0,"end":0,"pages":0,"pernum":0,"start":0,"total":0}
             * type : 0
             */

            private PageBean page;
            private int type;
            private List<?> list;

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

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }

            public static class PageBean {
                /**
                 * currentPage : 1
                 * currentPageSum : 0
                 * end : 0
                 * pages : 0
                 * pernum : 0
                 * start : 0
                 * total : 0
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
        }
    }
}
