package isfaaghyth.app.mvp.base;

import com.alibaba.fastjson.JSON;
import com.jd.mrd.network.bean.WGResponseBean;
import com.jd.mrd.network.wg.WgReqAsync;

import isfaaghyth.app.mvp.bean.BusinessBean;
import isfaaghyth.app.mvp.util.CommonUtil;
import isfaaghyth.app.mvp.util.ParamUtil;
import rx.Observable;
import rx.functions.Func1;

/**
 * 基础网关请求类（异步）
 *
 * @author yanghao1
 */
public class BaseWgRequest implements Func1<WGResponseBean, BusinessBean> {

    // JSF接口全名（带包名）
    protected String service;
    // JSF接口别名
    protected String alias;

    // 网关请求Helper类
    private WgReqAsync<BusinessBean> wgReqAsync;

    /**
     * 默认根据别名选择网关
     */
    public BaseWgRequest() {
        // 网关地址
//        String url="";
//        // 是否测试环境
//        boolean developMode = BuildConfig.DEBUG;
////        // 是否使用共有云环境
////        boolean publicCloud = NetworkParam.SERVICE_ALIAS
////                .equals(NetworkParam.PUBLIC_CLOUD_SERVICE_ALIAS);
////        // 用户来源，是否京东自有用户
////        boolean origin = WgCon.origin.equals(WgCon.ORIGIN_JD);
//        if (developMode) {
////            url = origin ? WgCon.TEST_WG_URL : WgCon.OEM_TEST_WG_URL;
//        } else {
//            // 别名为jcloud-wms-v0.1，选择公有云网关，否则根据用户来源选择是否私有云网关或者OEM网关
////            url = publicCloud ? WgCon.ON_LINE_WG_URL : origin ? WgCon.JSF_WG_URL : WgCon.OEM_JSF_WG_URL;
//        }
//        wgReqAsync = new WgReqAsync<>(url);
//        wgReqAsync.setResultFunc(this);
    }

    /**
     * 业务数据解析
     *
     * @param wgResponse 网关返回数据
     * @return 解析业务数据
     */
    @Override
    public BusinessBean call(WGResponseBean wgResponse) {
        // 判断网关访问是否成功
        if (wgResponse.getCode() != 0 || wgResponse.getData() == null) {
            // 访问失败返回给业务层网关错误码和网关错误信息
            return ParamUtil.getBusinessBean(String.valueOf(wgResponse.getCode()), wgResponse.getMsg());
        }
        // 解析业务数据
        try {
            return JSON.parseObject(wgResponse.getData(), BusinessBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            // 访问失败返回给业务层网关错误码和网关错误信息
            return ParamUtil.getBusinessBean(String.valueOf(wgResponse.getCode()), wgResponse.getMsg());
        }
    }

    /**
     * 异步网关请求
     *
     * @param service 服务名
     * @param alias   别名
     * @param method  方法名
     * @param param   参数
     * @return 请求结果
     */
    protected Observable<BusinessBean> wgRequest(String service, String alias, String method, String param) {
        if (CommonUtil.checkNullAndEmpty(service, alias, method, param)) {
            throw new IllegalArgumentException("网关请求参数错误");
        }
        // 请求
        return wgReqAsync.wgReq(ParamUtil.getWGRequestBean(service, alias, method, param),
                BaseConstants.getHeaderMap());
    }
}
