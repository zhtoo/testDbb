package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

/**
 * 作者：zhanghaitao on 2017/11/23 17:30
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class FilloutLenderInformationActivity extends AppBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("贷款人信息");
        isShowRightView(false);
        setContentView(R.layout.activity_fillout_lender_information);



    }
}
