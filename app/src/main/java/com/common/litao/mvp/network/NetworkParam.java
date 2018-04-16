package com.common.litao.mvp.network;

import java.util.HashMap;
import java.util.Map;

import com.common.litao.mvp.util.SPUtil;

/**
 * 网络请求服务名，方法名
 * <p/>
 * Created by yanghao1 on 2016/5/20.
 */
public class NetworkParam {

    // 上海统一登录AppId
    public static final short WJLoginAppId = 230;
    // Application初始化赋值
    public static String PUBLIC_CLOUD_SERVICE_ALIAS;
    // Application初始化赋值
    public static String SERVICE_ALIAS;

    // 别名缓存
    public static final Map<String, String> mAliasCache = new HashMap<>();

    static {
        mAliasCache.put("com.jd.jcloud.wms.stock", "jcloud-stock-ceshi");
        mAliasCache.put("com.jd.jcloud.wms.masterbasic", "jcloud-master-ceshi");
        mAliasCache.put("com.jd.jcloud.wms.authority", "jcloud-authority-test");
        mAliasCache.put("com.jd.jcloud.wms.inventory", "test");
        mAliasCache.put("com.jd.jcloud.wms.inbound", "jcloud-inbound-test");
        mAliasCache.put("com.jd.jcloud.wms.picking", "jcloud-picking-test");
        mAliasCache.put("com.jd.jcloud.wms.afterpicking", "jcloud-afterpicking-test");
        mAliasCache.put("com.jd.jcloud.wms.pickingplan", "test");
        mAliasCache.put("com.jd.jcloud.wms.master", "jcloud-master-ceshi");
        mAliasCache.put("com.jd.jcloud.wms.center", "jcloud-center-test");
        mAliasCache.put("com.jd.jwms.picking.b2b", "picking-b2b-test");
        mAliasCache.put("com.jd.jwms.afterpicking.b2b", "b2b-test");
        mAliasCache.put("com.jd.jwms.pickingplan.b2b", "pickingplan-b2b-test");
        mAliasCache.put("com.jd.jcloud.report.api", "jcloud-report-ceshi");
    }
    /**
     * 获取服务对应的别名
     *
     * @param serviceName 服务名
     * @return
     */
    public static String getServiceAlias(String serviceName) {
        if (!SPUtil.getDevelopMode()) {
            return SERVICE_ALIAS;
        }
        String[] services = serviceName.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(services[i]);
            sb.append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return mAliasCache.get(sb.toString());
    }
}
