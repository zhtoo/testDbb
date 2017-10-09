package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/25 17:49
 * 邮箱：820159571@qq.com
 *
 * @describe:图片bean
 */

public class PictrueBean {


    /**
     * resCode : 1
     * resData : {"picList":[{"addTime":"","addUser":"","borrowId":5,"id":19,"isDelete":1,"name":"50wd.png","path":"http://192.168.1.246:8085/borrow/1709250677620443-89504E47/view.html","size":0,"type":"1","updateTime":"","updateUser":""},{"addTime":"","addUser":"","borrowId":5,"id":20,"isDelete":1,"name":"dekz.png","path":"http://192.168.1.246:8085/borrow/1709251308708970-89504E47/view.html","size":0,"type":"1","updateTime":"","updateUser":""},{"addTime":"","addUser":"","borrowId":5,"id":21,"isDelete":1,"name":"P2????????.png","path":"http://192.168.1.246:8085/borrow/1709250039722262-89504E47/view.html","size":0,"type":"1","updateTime":"","updateUser":""},{"addTime":"","addUser":"","borrowId":5,"id":22,"isDelete":1,"name":"QQ??20170518175757.jpg","path":"http://192.168.1.246:8085/borrow/1709250072776189-FFD8FF/view.html","size":0,"type":"1","updateTime":"","updateUser":""}]}
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
        private List<PicListBean> picList;

        public List<PicListBean> getPicList() {
            return picList;
        }

        public void setPicList(List<PicListBean> picList) {
            this.picList = picList;
        }

        public static class PicListBean {
            /**
             * addTime :
             * addUser :
             * borrowId : 5
             * id : 19
             * isDelete : 1
             * name : 50wd.png
             * path : http://192.168.1.246:8085/borrow/1709250677620443-89504E47/view.html
             * size : 0
             * type : 1
             * updateTime :
             * updateUser :
             */

            private String addTime;
            private String addUser;
            private int borrowId;
            private int id;
            private int isDelete;
            private String name;
            private String path;
            private int size;
            private String type;
            private String updateTime;
            private String updateUser;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getAddUser() {
                return addUser;
            }

            public void setAddUser(String addUser) {
                this.addUser = addUser;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }
    }
}
