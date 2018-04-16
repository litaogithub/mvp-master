package com.common.litao.mvp.ui.main;

import com.common.litao.mvp.adapter.BaseRecyclerAdapter;
import com.common.litao.mvp.base.IPresent;
import com.common.litao.mvp.base.IView;
import com.common.litao.mvp.bean.MainDto;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: P 和 V 的合体
 */

public interface MainContract {
    interface View extends IView{
        BaseRecyclerAdapter<MainDto> getAdapter();
    }
    interface Present extends IPresent{
        void loadEmployees();
    }
}
