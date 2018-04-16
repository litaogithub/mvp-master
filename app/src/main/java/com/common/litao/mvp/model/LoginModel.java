package isfaaghyth.app.mvp.model;

import java.util.Map;

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

public class LoginModel extends BaseWgRequest{
    /**
     * 区分具体服务
     */
    public LoginModel() {
        super();
        this.service = InWhParams.PROTECT_BASE_API;
        this.alias = InWhParams.getServiceAlias(service);
    }

    public Observable<BusinessBean> postLogin(Map<String,String> param){
        return wgRequest(service, alias, InWhParams.queryPackageLevelConfigsForApp,
                ParamUtil.getJsonParam(param));
    }
}
