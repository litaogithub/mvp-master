package isfaaghyth.app.mvp.model;

import isfaaghyth.app.mvp.bean.BusinessBean;
import isfaaghyth.app.mvp.base.BaseWgRequest;
import isfaaghyth.app.mvp.network.InWhParams;
import isfaaghyth.app.mvp.util.ParamUtil;
import rx.Observable;

/**
 * author: litao29
 * date: 2018/2/6
 * mail: litao29@jd.com
 * desc: 请简单描述
 */

public class MainModel extends BaseWgRequest {

    public MainModel() {
        super();
        this.service = InWhParams.PROTECT_BASE_API;
        this.alias = InWhParams.getServiceAlias(service);
    }

    public Observable<BusinessBean> getEmployee(String param){
        return wgRequest(service, alias, InWhParams.queryPackageLevelConfigsForApp,
                ParamUtil.getJsonParam(param));
    }
}
