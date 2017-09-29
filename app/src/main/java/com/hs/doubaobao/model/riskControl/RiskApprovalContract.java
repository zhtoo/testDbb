package com.hs.doubaobao.model.riskControl;

import com.hs.doubaobao.base.BasePresenter;
import com.hs.doubaobao.base.BaseView;
import com.hs.doubaobao.bean.HomeBean;

/**
 * 作者：zhanghaitao on 2017/9/12 13:53
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public interface RiskApprovalContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void setData(HomeBean bean);
        void setError(String text);
    }


}
