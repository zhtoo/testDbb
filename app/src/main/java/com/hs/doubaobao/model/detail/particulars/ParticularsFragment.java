package com.hs.doubaobao.model.detail.particulars;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.ParticularBean;
import com.hs.doubaobao.model.detail.DetailActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 15:11
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ParticularsFragment extends BaseFragment implements ParticularsContract.View {
    private ParticularsContract.Presenter presenter;
    private RecyclerView mRecycler;
    private ParticularsAdapter adapter;
    private List<String> mTitles;
    private List<Map> mMap;

    @Override
    protected int setLayout() {
        return R.layout.fragment_particulars;
    }

    @Override
    protected void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.particulars_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);

        mRecycler.setLayoutManager(llm);

        mTitles = new ArrayList<>();
        mMap = new ArrayList<>();
        adapter = new ParticularsAdapter(getContext(), mTitles, mMap);
        mRecycler.setAdapter(adapter);

        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new ParticularsPresener(this, getContext());
        //获取数据

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);

    }

    @Override
    public void setData(ParticularBean bean) {

        ParticularBean.ResDataBean.BorrowBean borrow = bean.getResData().getBorrow();
        ParticularBean.ResDataBean.CustomerInfoBean customerInfo = bean.getResData().getCustomerInfo();
        ParticularBean.ResDataBean.CoborrowBean coborrow = bean.getResData().getCoborrow();
        ParticularBean.ResDataBean.CarInfoBean carInfo = bean.getResData().getCarInfo();
        List<ParticularBean.ResDataBean.BorrowContantsBean> borrowContants = bean.getResData().getBorrowContants();
        ParticularBean.ResDataBean.ApprovesBean approves = bean.getResData().getApproves();

        mTitles.add("贷款类别");
        Map<String, String> map1 = new LinkedHashMap<>();
        //01:汇民消费贷，02：汇民经营贷，03：汇业贷，04：汇车贷，05：汇农贷
        String type = borrow.getType();
        String[] aarType = {"", "汇民消费贷", "汇民经营贷", "汇业贷", "汇车贷", "汇农贷"};
        for (int i = 1; i <= aarType.length; i++) {
            if (type.equals("0" + i)) {
                type = aarType[i];
            }
        }
        map1.put("贷款类别", type);
        mMap.add(map1);

        mTitles.add("借款人贷款事项");
        Map<String, String> map2 = new LinkedHashMap<>();

        //贷款期限：1:12期;2:18期;3:24期;4:36期;5:48期;6:60期)
        String period = "";
        int periodInt = borrow.getPeriod();
        String[] aarPeriod = {"", "12期", "18期", "24期", "36期", "48期", "60期"};
        for (int i = 1; i <= aarPeriod.length; i++) {
            if (periodInt == i) {
                period = aarPeriod[i];
            }
        }
        map2.put("申请贷款金额", borrow.getAccount() + "万元");
        map2.put("申请期限", period);
        map2.put("借款用途", borrow.getPurpose());
        map2.put("客户经理名称/机构名称", borrow.getOperatorName());
        mMap.add(map2);

        mTitles.add("借款人信息");
        Map<String, String> map3 = new LinkedHashMap<>();
        map3.put("姓名", customerInfo.getCname());
        map3.put("性别", customerInfo.getSexString());
        map3.put("婚姻状态", customerInfo.getMarriageString());
        map3.put("出生日期", customerInfo.getBirth());
        map3.put("户籍所在地", customerInfo.getDomicile());
        map3.put("身份证号码", customerInfo.getCardId());
        map3.put("移动电话", customerInfo.getMobilephone());
        map3.put("line1", "line");
        map3.put("居住地址", customerInfo.getExitingBuildAddr());
        map3.put("居住面积", customerInfo.getExitingBuildAcreage() + "m²");
        map3.put("供养人数", customerInfo.getJobdepartmentCount()+"人");
        map3.put("自有房产地址", customerInfo.getOwnBuildAddr());
        map3.put("自有房产面积", customerInfo.getOwnBuildAcreage() + "");
        map3.put("现住房居住时间", customerInfo.getExitingBuildLivetime());
        map3.put("其他房产信息", customerInfo.getOtherBuildInfo());
        map3.put("其他房产面积", customerInfo.getOtherBuildAcreage() + "m²");
        map3.put("其他房产性质", customerInfo.getOtherBuildProperty());
        map3.put("line2", "line");
        map3.put("单位名称", customerInfo.getWorkunitName());
        map3.put("部门及职务", customerInfo.getJobdepartment());
        map3.put("工资月收入", customerInfo.getMonthlyWage() + "元");
        //是否是企业主/个体户 1:否 2：是
        int isBusinessOwner = customerInfo.getIsBusinessOwner();
        if (isBusinessOwner == 1) {
            map3.put("是否企业主/个体户", "否");
        } else if (isBusinessOwner == 2) {
            map3.put("是否企业主/个体户", "是");
        } else {
            map3.put("是否企业主/个体户", "");
        }
        map3.put("单位性质", customerInfo.getWorkunitNatureString());
        map3.put("单位电话", customerInfo.getWorkunitPhone());
        map3.put("分机", customerInfo.getWorkunitExtPhone());
        map3.put("累计工作年限", customerInfo.getWorkunitAge()+"年");
        map3.put("单位地址", customerInfo.getWorkunitAddr());
        mMap.add(map3);

        mTitles.add("共同借款人信息");
        Map<String, String> map4 = new LinkedHashMap<>();
        map4.put("姓名", coborrow.getConame());
        //性别 1:男  0:女）
        int sex = coborrow.getSex();
        if (sex == 1) {
            map4.put("性别", "男");
        } else if (sex == 0) {
            map4.put("性别", "女");
        } else {
            map4.put("性别", "");
        }
        map4.put("与借款人关系", coborrow.getCrelationship());
        map4.put("出生日期", coborrow.getBirth());
        map4.put("身份证号码", coborrow.getCardid());
        map4.put("户籍所在地", coborrow.getDomicile());
        map4.put("移动电话", coborrow.getMobilephone());
        map4.put("居住地址", coborrow.getExitingBuildAddr());
        map4.put("line", "line");
        map4.put("单位名称", coborrow.getWorkunitName());
        map4.put("部门及职务", coborrow.getWorkunitDepartment());
        map4.put("工资月收入", coborrow.getMonthlyIncome() + "元");
        //是否是企业主/个体户 1:否 2：是
        int isBusinessOwner1 = coborrow.getIsBusinessOwner();
        if (isBusinessOwner1 == 1) {
            map4.put("是否企业主/个体户", "否");
        } else if (isBusinessOwner1 == 2) {
            map4.put("是否企业主/个体户", "是");
        } else {
            map4.put("是否企业主/个体户", "");
        }
        map4.put("单位性质", coborrow.getWorkunitNatureString());
        map4.put("单位电话", coborrow.getPhone());
        map4.put("分机", coborrow.getExtPhone());
        map4.put("累计工作年限", coborrow.getWorkunitAge() +"年");
        map4.put("单位地址", coborrow.getWorkunitAddr());
        mMap.add(map4);

        mTitles.add("借款车辆信息");
        Map<String, String> map5 = new LinkedHashMap<>();
        map5.put("车辆所有人", carInfo.getOwner());
        map5.put("车辆品牌", carInfo.getBrand());
        map5.put("车辆颜色", carInfo.getColor());
        map5.put("车辆号码", carInfo.getCardid());
        String status = "";
        if (carInfo.getStatus() >= 0 && carInfo.getStatus() <= 1) {
            status = carInfo.getStatus() == 0 ? "有车无贷款" : "有车有贷款";
        }
        map5.put("车辆状况", status);
        map5.put("裸车价", carInfo.getPrice() == 0 ? "" : (carInfo.getPrice() + "元"));
        map5.put("车辆购买日期", carInfo.getBuyDate());
        map5.put("其他车辆信息", carInfo.getOtherInfo());
        mMap.add(map5);


        mTitles.add("借款人联系人信息");
        Map<String, String> map6 = new LinkedHashMap<>();

        //与借款人关系:0:配偶 1：父母 2：子女 3：兄弟姐妹 4:亲戚 5：朋友 6：其他）
        String[] aarRelation = {"配偶", "父母", "子女", "兄弟姐妹", "亲戚", "朋友", "其他"};
        int ContantTimes = 0;
        map6.put("空1", "直属亲戚联系人");// 0 1
        for (int i = 0; i < 2; i++) {
            map6.put("姓名" + i, "");
            map6.put("关系" + i, "");
            map6.put("手机号码" + i, "");
            map6.put("是否知晓贷款" + i, "");
            map6.put("line" + i, "line");
        }
        for (int i = 0; i < borrowContants.size(); i++) {
            if (borrowContants.get(i).getType() == 0) {
                map6.put("姓名" + ContantTimes, borrowContants.get(i).getName());
                map6.put("关系" + ContantTimes, borrowContants.get(i).getRelation());
                map6.put("手机号码" + ContantTimes, borrowContants.get(i).getPhone());
                //是否知晓贷款  0：是 1：否
                String notice = "";
                if (borrowContants.get(i).getNotice() >= 0 && borrowContants.get(i).getNotice() <= 1) {
                    notice = borrowContants.get(i).getNotice() == 0 ? "是" : "否";
                }
                map6.put("是否知晓贷款" + ContantTimes, notice);
                ContantTimes++;
            }
        }

        ContantTimes = 2;
        map6.put("空2", "一般联系人");//3 4
        for (int i = 2; i < 4; i++) {
            map6.put("姓名" + i, "");
            map6.put("关系" + i, "");
            map6.put("手机号码" + i, "");
            map6.put("是否知晓贷款" + i, "");
            if (i != 3) {
                map6.put("line" + i, "line");
            }
        }
        for (int i = 0; i < borrowContants.size(); i++) {
            if (borrowContants.get(i).getType() == 1) {
                map6.put("姓名" + ContantTimes, borrowContants.get(i).getName());
                map6.put("关系" + ContantTimes, borrowContants.get(i).getRelation());
                map6.put("手机号码" + ContantTimes, borrowContants.get(i).getPhone());
                //TODO:
                //是否知晓贷款  0：是 1：否
                String notice = "";
                if (borrowContants.get(i).getNotice() >= 0 && borrowContants.get(i).getNotice() <= 1) {
                    notice = borrowContants.get(i).getNotice() == 0 ? "是" : "否";
                }
                map6.put("是否知晓贷款" + ContantTimes, notice);
                ContantTimes++;
            }
        }
        mMap.add(map6);

        mTitles.add("借款人互联网信息");
        Map<String, String> map7 = new LinkedHashMap<>();
        map7.put("腾讯QQ/微信号码", customerInfo.getQq());
        map7.put("淘宝网/支付宝账号", customerInfo.getAlipay());
        mMap.add(map7);

        mTitles.add("客户详情调查表");
        Map<String, String> map8 = new LinkedHashMap<>();
        map8.put("房产情况", customerInfo.getBuildStauts());
        map8.put("line", "line");
        map8.put("车辆情况", customerInfo.getCarStauts());
        map8.put("line1", "line");
        map8.put("面审情况及意见", customerInfo.getOpinion());
        map8.put("line2", "line");

        map8.put("资料", approves.getBorrowData());
        map8.put("line3", "line");
        map8.put("家访客户情况汇总", approves.getHomeVisitContent());
        map8.put("line4", "line");
        map8.put("风控意见", approves.getRiskControlContent());
        map8.put("风控定额", approves.getRiskControl() > 0 ? (approves.getRiskControl() + "元") : "");
        map8.put("line5", "line");
        map8.put("总经理意见", approves.getManagerContent());
        map8.put("总经理定额", approves.getManagerRation() > 0 ? (approves.getManagerRation() + "元") : "");
        mMap.add(map8);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(ParticularsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
