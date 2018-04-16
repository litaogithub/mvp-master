package isfaaghyth.app.mvp.ui.main;

import isfaaghyth.app.mvp.adapter.CommonAdapterRv;
import isfaaghyth.app.mvp.base.IPresent;
import isfaaghyth.app.mvp.base.IView;
import isfaaghyth.app.mvp.bean.MainDto;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: P 和 V 的合体
 */

public interface MainContract {
    interface View extends IView{
        CommonAdapterRv<MainDto> getAdapter();
    }
    interface Present extends IPresent{
        void loadEmployees();
    }
}
