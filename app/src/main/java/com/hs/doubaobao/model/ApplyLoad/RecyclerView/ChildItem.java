package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

/**
 * 作者：zhanghaitao on 2017/11/20 17:21
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ChildItem {

    private String loansUse;
    private String loanCategories;
    private String telephone;
    private String applicationPeriod;
    private String storeName;
    private String loanAmount;

    public ChildItem() {}

    public ChildItem(String loansUse, String loanCategories, String telephone, String applicationPeriod, String storeName, String loanAmount) {
        this.loansUse = loansUse;
        this.loanCategories = loanCategories;
        this.telephone = telephone;
        this.applicationPeriod = applicationPeriod;
        this.storeName = storeName;
        this.loanAmount = loanAmount;
    }

    public String getLoansUse() {
        return loansUse;
    }

    public void setLoansUse(String loansUse) {
        this.loansUse = loansUse;
    }

    public String getLoanCategories() {
        return loanCategories;
    }

    public void setLoanCategories(String loanCategories) {
        this.loanCategories = loanCategories;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getApplicationPeriod() {
        return applicationPeriod;
    }

    public void setApplicationPeriod(String applicationPeriod) {
        this.applicationPeriod = applicationPeriod;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }
}
