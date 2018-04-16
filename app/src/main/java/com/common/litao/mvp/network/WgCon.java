package isfaaghyth.app.mvp.network;


import isfaaghyth.app.mvp.App;
import isfaaghyth.app.mvp.base.BaseConstants;
import isfaaghyth.app.mvp.util.CommonUtil;
import isfaaghyth.app.mvp.util.SPUtil;

/**
 * Created by yanghao1 on 2016/8/9.
 */
public class WgCon {

    /**
     * 下面五个参数为OEM网关需求参数
     */
    public static String origin = "origin";
    public static String token = "token";

    // 登录平台权限码
    public static final String passportAppId = String.valueOf(NetworkParam.WJLoginAppId);
    // 应用平台
    public static final String client = "android";
    // 应用IP地址
    public static final String clientIp = "1";
    // 应用SDK版本
    public static final String osVersion = String.valueOf(CommonUtil.getAndroidSDKVersion());
    // 客户端版本
    public static final String clientVersion = CommonUtil.getAppVersion(App.getContext());
    // 客户端UUID
    public static final String uuid = CommonUtil.getRsn();
    // 应用名称
    public static final String appName = "CloudWarehouse";
    // 区域
    public static final String area = "8";
    // 屏幕
    public static final String screen = "1280720";
    // 应用权限码的值
    private static final String TEST_APPID_VALUE = BaseConstants.TEST_APPID_VALUE;
    // 生产（公有云网关）
    private static final String JCLOUD_WMS_MRD_GW = BaseConstants.JCLOUD_WMS_MRD_GW;
    // 生产（私有云网关）
    private static final String GATEWAY_MRD = BaseConstants.GATEWAY_MRD;

    // 应用JSF访问权限码
    public static final String appId = SPUtil.getDevelopMode() ? TEST_APPID_VALUE :
            NetworkParam.SERVICE_ALIAS.equals(NetworkParam.PUBLIC_CLOUD_SERVICE_ALIAS) ?
                    JCLOUD_WMS_MRD_GW : GATEWAY_MRD;
}
