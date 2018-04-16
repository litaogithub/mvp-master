package com.common.litao.mvp.ui.login;

import com.common.litao.mvp.base.IPresent;
import com.common.litao.mvp.base.IView;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: P 和 V 的合体
 */

public interface LoginContract {
    interface View extends IView{
        void openMain();
        String getEmail();
        String getPass();
        android.view.View getEnbleView();
    }
    interface Present extends IPresent{
        void onRequestLogin();
    }
}
