package com.hs.doubaobao.model.invalid;

import com.hs.doubaobao.base.BasePresenter;
import com.hs.doubaobao.base.BaseView;
import com.hs.doubaobao.bean.HomeBean;

/**
 * 作者：zhanghaitao on 2017/9/12 13:57
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public interface InvalidListContract {

    interface Presenter extends BasePresenter {}

    interface View extends BaseView<Presenter> {
        void setData(HomeBean bean);
        void setError(String text);
    }

}
