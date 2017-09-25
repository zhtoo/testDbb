package com.hs.doubaobao.model.main;

/**
 * 作者：zhanghaitao on 2017/9/19 10:25
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ListBean {

    //客户姓名
    private String name ;
    //借款时间
    private String time;
    //借款用途
    private String purpose ;
    //借款金额
    private double loanAmount ;
    //客户电话
    private String customPhone ;
    //借款期数
    private int loanPeriods;
    //客户经理
    private String customManager ;
    //审批状态
    private String status ;
    //列表显示类型
    private int showType = 0 ;//0: 首页  ； 1 风控  2 总经理
    //按钮的状态
    private int approveStatus  = 0 ;//0:审批;1:继续;2:等待上级审核

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public int getLoanPeriods() {
        return loanPeriods;
    }

    public void setLoanPeriods(int loanPeriods) {
        this.loanPeriods = loanPeriods;
    }

    public String getCustomManager() {
        return customManager;
    }

    public void setCustomManager(String customManager) {
        this.customManager = customManager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(int approveStatus) {
        this.approveStatus = approveStatus;
    }


}
