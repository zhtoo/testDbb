package com.hs.doubaobao.model.invalid;

import com.hs.doubaobao.base.BasePresenter;
import com.hs.doubaobao.base.BaseView;
import com.hs.doubaobao.bean.InvalidReasonBean;

/**
 * 作者：zhanghaitao on 2017/9/12 10:59
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public interface InvalidReasonContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void setData(InvalidReasonBean bean);
        void setError(String text);
    }

}
