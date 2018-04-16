package isfaaghyth.app.mvp.base;

import java.util.HashMap;

/**
 * 网络请求地址
 * @author litao
 */
public class BaseConstants {

    /**
     * 默认shared
     */
    public final static String JD_CW_ANDROID_CLIENT = "JdCwAndroidClient";

    /**
     * 测试应用编号
     */
    public static final String TEST_APPID_VALUE = "4556";

    /**
     * 部署应用编号：jcloud_wms_mrd_gw（公有云）
     */
    public static final String JCLOUD_WMS_MRD_GW = "12885";

    /**
     * 部署应用编号：gateway.mrd
     */
    public static final String GATEWAY_MRD = "8167";

    /**
     * 请求网关的请求头信息
     */
    public static HashMap<String, String> headerMap = new HashMap<String, String>();

    public static HashMap<String, String> getHeaderMap() {
        return headerMap;
    }

    public static void setHeaderMap(HashMap<String, String> header) {
        headerMap.putAll(header);
    }
}
