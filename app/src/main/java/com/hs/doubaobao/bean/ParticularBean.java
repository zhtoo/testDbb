package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/20 11:13
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ParticularBean {


    /**
     * resCode : 1
     * resData : {"approves":[{"content":"是否梵蒂冈梵蒂冈更广泛的风格","id":1,"managerRation":500000,"riskControl":400000,"type":1}],"borrow":{"account":20000,"applydate":"2017-09-06","finishdate":"2017-09-06","fundStatus":1,"grantdate":"2017-09-06","id":1,"operatorName":"admin123","period":1,"purpose":"旅游","status":"30","type":"01"},"borrowContants":[{"id":1,"name":"1","notice":1,"phone":"1","relation":"1","type":1}],"carInfo":{"brand":"1","buyDate":"2017-09-06","cardid":"1","color":"1","id":1,"monthlyMoney":1,"otherInfo":"1","owner":"1","price":1,"status":1},"coborrow":{"birth":"2017-09-06","cardid":"1","coname":"1","crelationship":"1","domicile":"1","exitingBuildAddr":"1","extPhone":"1","id":1,"isBusinessOwner":1,"mobilephone":"1","monthlyIncome":1,"phone":"1","sex":1,"workunitAddr":"","workunitAge":"1","workunitDepartment":"1","workunitName":"1","workunitNatureString":"事业单位/政府机关"},"customerInfo":{"alipay":"1","birth":"2017-09-06","buildStauts":"1","carStauts":"1","cardId":"1","cname":"张三","domicile":"1","exitingBuildAcreage":1,"exitingBuildAddr":"1","exitingBuildLivetime":"1","id":1,"isBusinessOwner":1,"jobdepartment":"1","jobdepartmentCount":"1","marriageString":"未婚","mobilephone":"13033333333","monthlyWage":1,"opinion":"1","otherBuildAcreage":1,"otherBuildInfo":"111","otherBuildProperty":"1","ownBuildAcreage":1,"ownBuildAddr":"1","ownBuildPropertyString":"有房无贷款","qq":"11","sexString":"男","workunitAddr":"","workunitAge":"1","workunitExtPhone":"1","workunitName":"1","workunitNatureString":"事业单位/政府机关","workunitPhone":"1"}}
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
         * approves : [{"content":"是否梵蒂冈梵蒂冈更广泛的风格","id":1,"managerRation":500000,"riskControl":400000,"type":1}]
         * borrow : {"account":20000,"applydate":"2017-09-06","finishdate":"2017-09-06","fundStatus":1,"grantdate":"2017-09-06","id":1,"operatorName":"admin123","period":1,"purpose":"旅游","status":"30","type":"01"}
         * borrowContants : [{"id":1,"name":"1","notice":1,"phone":"1","relation":"1","type":1}]
         * carInfo : {"brand":"1","buyDate":"2017-09-06","cardid":"1","color":"1","id":1,"monthlyMoney":1,"otherInfo":"1","owner":"1","price":1,"status":1}
         * coborrow : {"birth":"2017-09-06","cardid":"1","coname":"1","crelationship":"1","domicile":"1","exitingBuildAddr":"1","extPhone":"1","id":1,"isBusinessOwner":1,"mobilephone":"1","monthlyIncome":1,"phone":"1","sex":1,"workunitAddr":"","workunitAge":"1","workunitDepartment":"1","workunitName":"1","workunitNatureString":"事业单位/政府机关"}
         * customerInfo : {"alipay":"1","birth":"2017-09-06","buildStauts":"1","carStauts":"1","cardId":"1","cname":"张三","domicile":"1","exitingBuildAcreage":1,"exitingBuildAddr":"1","exitingBuildLivetime":"1","id":1,"isBusinessOwner":1,"jobdepartment":"1","jobdepartmentCount":"1","marriageString":"未婚","mobilephone":"13033333333","monthlyWage":1,"opinion":"1","otherBuildAcreage":1,"otherBuildInfo":"111","otherBuildProperty":"1","ownBuildAcreage":1,"ownBuildAddr":"1","ownBuildPropertyString":"有房无贷款","qq":"11","sexString":"男","workunitAddr":"","workunitAge":"1","workunitExtPhone":"1","workunitName":"1","workunitNatureString":"事业单位/政府机关","workunitPhone":"1"}
         */

        private BorrowBean borrow;
        private CarInfoBean carInfo;
        private CoborrowBean coborrow;
        private CustomerInfoBean customerInfo;
        private List<ApprovesBean> approves;
        private List<BorrowContantsBean> borrowContants;

        public BorrowBean getBorrow() {
            return borrow;
        }

        public void setBorrow(BorrowBean borrow) {
            this.borrow = borrow;
        }

        public CarInfoBean getCarInfo() {
            return carInfo;
        }

        public void setCarInfo(CarInfoBean carInfo) {
            this.carInfo = carInfo;
        }

        public CoborrowBean getCoborrow() {
            return coborrow;
        }

        public void setCoborrow(CoborrowBean coborrow) {
            this.coborrow = coborrow;
        }

        public CustomerInfoBean getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(CustomerInfoBean customerInfo) {
            this.customerInfo = customerInfo;
        }

        public List<ApprovesBean> getApproves() {
            return approves;
        }

        public void setApproves(List<ApprovesBean> approves) {
            this.approves = approves;
        }

        public List<BorrowContantsBean> getBorrowContants() {
            return borrowContants;
        }

        public void setBorrowContants(List<BorrowContantsBean> borrowContants) {
            this.borrowContants = borrowContants;
        }

        public static class BorrowBean {
            /**
             * account : 20000
             * applydate : 2017-09-06
             * finishdate : 2017-09-06
             * fundStatus : 1
             * grantdate : 2017-09-06
             * id : 1
             * operatorName : admin123
             * period : 1
             * purpose : 旅游
             * status : 30
             * type : 01
             */

            private int account;
            private String applydate;
            private String finishdate;
            private int fundStatus;
            private String grantdate;
            private int id;
            private String operatorName;
            private int period;
            private String purpose;
            private String status;
            private String type;

            public int getAccount() {
                return account;
            }

            public void setAccount(int account) {
                this.account = account;
            }

            public String getApplydate() {
                return applydate;
            }

            public void setApplydate(String applydate) {
                this.applydate = applydate;
            }

            public String getFinishdate() {
                return finishdate;
            }

            public void setFinishdate(String finishdate) {
                this.finishdate = finishdate;
            }

            public int getFundStatus() {
                return fundStatus;
            }

            public void setFundStatus(int fundStatus) {
                this.fundStatus = fundStatus;
            }

            public String getGrantdate() {
                return grantdate;
            }

            public void setGrantdate(String grantdate) {
                this.grantdate = grantdate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
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
        }

        public static class CarInfoBean {
            /**
             * brand : 1
             * buyDate : 2017-09-06
             * cardid : 1
             * color : 1
             * id : 1
             * monthlyMoney : 1
             * otherInfo : 1
             * owner : 1
             * price : 1
             * status : 1
             */

            private String brand;
            private String buyDate;
            private String cardid;
            private String color;
            private int id;
            private int monthlyMoney;
            private String otherInfo;
            private String owner;
            private int price;
            private int status;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getBuyDate() {
                return buyDate;
            }

            public void setBuyDate(String buyDate) {
                this.buyDate = buyDate;
            }

            public String getCardid() {
                return cardid;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMonthlyMoney() {
                return monthlyMoney;
            }

            public void setMonthlyMoney(int monthlyMoney) {
                this.monthlyMoney = monthlyMoney;
            }

            public String getOtherInfo() {
                return otherInfo;
            }

            public void setOtherInfo(String otherInfo) {
                this.otherInfo = otherInfo;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class CoborrowBean {
            /**
             * birth : 2017-09-06
             * cardid : 1
             * coname : 1
             * crelationship : 1
             * domicile : 1
             * exitingBuildAddr : 1
             * extPhone : 1
             * id : 1
             * isBusinessOwner : 1
             * mobilephone : 1
             * monthlyIncome : 1
             * phone : 1
             * sex : 1
             * workunitAddr :
             * workunitAge : 1
             * workunitDepartment : 1
             * workunitName : 1
             * workunitNatureString : 事业单位/政府机关
             */

            private String birth;
            private String cardid;
            private String coname;
            private String crelationship;
            private String domicile;
            private String exitingBuildAddr;
            private String extPhone;
            private int id;
            private int isBusinessOwner;
            private String mobilephone;
            private int monthlyIncome;
            private String phone;
            private int sex;
            private String workunitAddr;
            private String workunitAge;
            private String workunitDepartment;
            private String workunitName;
            private String workunitNatureString;

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getCardid() {
                return cardid;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public String getConame() {
                return coname;
            }

            public void setConame(String coname) {
                this.coname = coname;
            }

            public String getCrelationship() {
                return crelationship;
            }

            public void setCrelationship(String crelationship) {
                this.crelationship = crelationship;
            }

            public String getDomicile() {
                return domicile;
            }

            public void setDomicile(String domicile) {
                this.domicile = domicile;
            }

            public String getExitingBuildAddr() {
                return exitingBuildAddr;
            }

            public void setExitingBuildAddr(String exitingBuildAddr) {
                this.exitingBuildAddr = exitingBuildAddr;
            }

            public String getExtPhone() {
                return extPhone;
            }

            public void setExtPhone(String extPhone) {
                this.extPhone = extPhone;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsBusinessOwner() {
                return isBusinessOwner;
            }

            public void setIsBusinessOwner(int isBusinessOwner) {
                this.isBusinessOwner = isBusinessOwner;
            }

            public String getMobilephone() {
                return mobilephone;
            }

            public void setMobilephone(String mobilephone) {
                this.mobilephone = mobilephone;
            }

            public int getMonthlyIncome() {
                return monthlyIncome;
            }

            public void setMonthlyIncome(int monthlyIncome) {
                this.monthlyIncome = monthlyIncome;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getWorkunitAddr() {
                return workunitAddr;
            }

            public void setWorkunitAddr(String workunitAddr) {
                this.workunitAddr = workunitAddr;
            }

            public String getWorkunitAge() {
                return workunitAge;
            }

            public void setWorkunitAge(String workunitAge) {
                this.workunitAge = workunitAge;
            }

            public String getWorkunitDepartment() {
                return workunitDepartment;
            }

            public void setWorkunitDepartment(String workunitDepartment) {
                this.workunitDepartment = workunitDepartment;
            }

            public String getWorkunitName() {
                return workunitName;
            }

            public void setWorkunitName(String workunitName) {
                this.workunitName = workunitName;
            }

            public String getWorkunitNatureString() {
                return workunitNatureString;
            }

            public void setWorkunitNatureString(String workunitNatureString) {
                this.workunitNatureString = workunitNatureString;
            }
        }

        public static class CustomerInfoBean {
            /**
             * alipay : 1
             * birth : 2017-09-06
             * buildStauts : 1
             * carStauts : 1
             * cardId : 1
             * cname : 张三
             * domicile : 1
             * exitingBuildAcreage : 1
             * exitingBuildAddr : 1
             * exitingBuildLivetime : 1
             * id : 1
             * isBusinessOwner : 1
             * jobdepartment : 1
             * jobdepartmentCount : 1
             * marriageString : 未婚
             * mobilephone : 13033333333
             * monthlyWage : 1
             * opinion : 1
             * otherBuildAcreage : 1
             * otherBuildInfo : 111
             * otherBuildProperty : 1
             * ownBuildAcreage : 1
             * ownBuildAddr : 1
             * ownBuildPropertyString : 有房无贷款
             * qq : 11
             * sexString : 男
             * workunitAddr :
             * workunitAge : 1
             * workunitExtPhone : 1
             * workunitName : 1
             * workunitNatureString : 事业单位/政府机关
             * workunitPhone : 1
             */

            private String alipay;
            private String birth;
            private String buildStauts;
            private String carStauts;
            private String cardId;
            private String cname;
            private String domicile;
            private int exitingBuildAcreage;
            private String exitingBuildAddr;
            private String exitingBuildLivetime;
            private int id;
            private int isBusinessOwner;
            private String jobdepartment;
            private String jobdepartmentCount;
            private String marriageString;
            private String mobilephone;
            private int monthlyWage;
            private String opinion;
            private int otherBuildAcreage;
            private String otherBuildInfo;
            private String otherBuildProperty;
            private int ownBuildAcreage;
            private String ownBuildAddr;
            private String ownBuildPropertyString;
            private String qq;
            private String sexString;
            private String workunitAddr;
            private String workunitAge;
            private String workunitExtPhone;
            private String workunitName;
            private String workunitNatureString;
            private String workunitPhone;

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getBuildStauts() {
                return buildStauts;
            }

            public void setBuildStauts(String buildStauts) {
                this.buildStauts = buildStauts;
            }

            public String getCarStauts() {
                return carStauts;
            }

            public void setCarStauts(String carStauts) {
                this.carStauts = carStauts;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getDomicile() {
                return domicile;
            }

            public void setDomicile(String domicile) {
                this.domicile = domicile;
            }

            public int getExitingBuildAcreage() {
                return exitingBuildAcreage;
            }

            public void setExitingBuildAcreage(int exitingBuildAcreage) {
                this.exitingBuildAcreage = exitingBuildAcreage;
            }

            public String getExitingBuildAddr() {
                return exitingBuildAddr;
            }

            public void setExitingBuildAddr(String exitingBuildAddr) {
                this.exitingBuildAddr = exitingBuildAddr;
            }

            public String getExitingBuildLivetime() {
                return exitingBuildLivetime;
            }

            public void setExitingBuildLivetime(String exitingBuildLivetime) {
                this.exitingBuildLivetime = exitingBuildLivetime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsBusinessOwner() {
                return isBusinessOwner;
            }

            public void setIsBusinessOwner(int isBusinessOwner) {
                this.isBusinessOwner = isBusinessOwner;
            }

            public String getJobdepartment() {
                return jobdepartment;
            }

            public void setJobdepartment(String jobdepartment) {
                this.jobdepartment = jobdepartment;
            }

            public String getJobdepartmentCount() {
                return jobdepartmentCount;
            }

            public void setJobdepartmentCount(String jobdepartmentCount) {
                this.jobdepartmentCount = jobdepartmentCount;
            }

            public String getMarriageString() {
                return marriageString;
            }

            public void setMarriageString(String marriageString) {
                this.marriageString = marriageString;
            }

            public String getMobilephone() {
                return mobilephone;
            }

            public void setMobilephone(String mobilephone) {
                this.mobilephone = mobilephone;
            }

            public int getMonthlyWage() {
                return monthlyWage;
            }

            public void setMonthlyWage(int monthlyWage) {
                this.monthlyWage = monthlyWage;
            }

            public String getOpinion() {
                return opinion;
            }

            public void setOpinion(String opinion) {
                this.opinion = opinion;
            }

            public int getOtherBuildAcreage() {
                return otherBuildAcreage;
            }

            public void setOtherBuildAcreage(int otherBuildAcreage) {
                this.otherBuildAcreage = otherBuildAcreage;
            }

            public String getOtherBuildInfo() {
                return otherBuildInfo;
            }

            public void setOtherBuildInfo(String otherBuildInfo) {
                this.otherBuildInfo = otherBuildInfo;
            }

            public String getOtherBuildProperty() {
                return otherBuildProperty;
            }

            public void setOtherBuildProperty(String otherBuildProperty) {
                this.otherBuildProperty = otherBuildProperty;
            }

            public int getOwnBuildAcreage() {
                return ownBuildAcreage;
            }

            public void setOwnBuildAcreage(int ownBuildAcreage) {
                this.ownBuildAcreage = ownBuildAcreage;
            }

            public String getOwnBuildAddr() {
                return ownBuildAddr;
            }

            public void setOwnBuildAddr(String ownBuildAddr) {
                this.ownBuildAddr = ownBuildAddr;
            }

            public String getOwnBuildPropertyString() {
                return ownBuildPropertyString;
            }

            public void setOwnBuildPropertyString(String ownBuildPropertyString) {
                this.ownBuildPropertyString = ownBuildPropertyString;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getSexString() {
                return sexString;
            }

            public void setSexString(String sexString) {
                this.sexString = sexString;
            }

            public String getWorkunitAddr() {
                return workunitAddr;
            }

            public void setWorkunitAddr(String workunitAddr) {
                this.workunitAddr = workunitAddr;
            }

            public String getWorkunitAge() {
                return workunitAge;
            }

            public void setWorkunitAge(String workunitAge) {
                this.workunitAge = workunitAge;
            }

            public String getWorkunitExtPhone() {
                return workunitExtPhone;
            }

            public void setWorkunitExtPhone(String workunitExtPhone) {
                this.workunitExtPhone = workunitExtPhone;
            }

            public String getWorkunitName() {
                return workunitName;
            }

            public void setWorkunitName(String workunitName) {
                this.workunitName = workunitName;
            }

            public String getWorkunitNatureString() {
                return workunitNatureString;
            }

            public void setWorkunitNatureString(String workunitNatureString) {
                this.workunitNatureString = workunitNatureString;
            }

            public String getWorkunitPhone() {
                return workunitPhone;
            }

            public void setWorkunitPhone(String workunitPhone) {
                this.workunitPhone = workunitPhone;
            }
        }

        public static class ApprovesBean {
            /**
             * content : 是否梵蒂冈梵蒂冈更广泛的风格
             * id : 1
             * managerRation : 500000
             * riskControl : 400000
             * type : 1
             */

            private String content;
            private int id;
            private int managerRation;
            private int riskControl;
            private int type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getManagerRation() {
                return managerRation;
            }

            public void setManagerRation(int managerRation) {
                this.managerRation = managerRation;
            }

            public int getRiskControl() {
                return riskControl;
            }

            public void setRiskControl(int riskControl) {
                this.riskControl = riskControl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class BorrowContantsBean {
            /**
             * id : 1
             * name : 1
             * notice : 1
             * phone : 1
             * relation : 1
             * type : 1
             */

            private int id;
            private String name;
            private int notice;
            private String phone;
            private String relation;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNotice() {
                return notice;
            }

            public void setNotice(int notice) {
                this.notice = notice;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
