package com.hs.doubaobao.bean;

import android.text.TextUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/19 10:25
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ListBean {

    //客户姓名
    private String name;
    //借款时间
    private String time;
    //借款用途
    private String purpose;
    //借款金额
    private double loanAmount;
    //客户电话
    private String customPhone;
    //借款期数
    private String loanPeriods;
    //客户经理
    private String customManager;
    //审批状态
    private String status;
    //列表显示类型
    private int showType = 0;//0: 首页  ； 1 风控  2 总经理
    //按钮的状态
    private int approveStatus = 0;//0:审批;1:继续;2:等待上级审核

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

    public String getLoanPeriods() {
        return loanPeriods;
    }

    public void setLoanPeriods(int loanPeriods) {
        //1：12期，2：18期，3：24期，4：36期，5：48期，6：60期
        String[] aar = {"", "12期", "18期", "24期", "36期", "48期", "60期"};
        if (loanPeriods > 0 && loanPeriods < 7) {
            this.loanPeriods = aar[loanPeriods];
        }else {
            this.loanPeriods = "";
        }
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
        Map<String, String> map = new LinkedHashMap<>();
        map.put("00", "待提交");
        map.put("01", "待初审");
        map.put("02", "初审通过(待家访)");
        map.put("03", "初审不通过");
        map.put("10", "家访待提交");
        map.put("11", "待填写风控额度");
        map.put("12", "家访通过");
        map.put("13", "家访不通过");
        map.put("20", "待风控审批");
        map.put("21", "待总经理审批");
        map.put("22", "风控审批不通过");
        map.put("30", "总经理审批通过");
        map.put("31", "总经理审批不通过");
        for (String key : map.keySet()) {
            if (status.equals(key)) {
                this.status = map.get(key);
            }
        }
        if (TextUtils.isEmpty(this.status)) {
            this.status = "总经理审批通过";
        }
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
