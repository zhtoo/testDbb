package com.hs.doubaobao.model.main;

import com.hs.doubaobao.base.BasePresenter;
import com.hs.doubaobao.base.BaseView;
import com.hs.doubaobao.bean.HomeBean;

/**
 * 作者：zhanghaitao on 2017/9/12 14:10
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public interface MainContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void setData(HomeBean bean);
        void setError(String text);
    }



}
