package isfaaghyth.app.mvp.network;

/**
 * Created by yanghao1 on 2017/11/27.
 */

public class InWhParams extends NetworkParam {

    /**
     * 在2.0版本上新增服务、方法，或者重写getServiceAlias
     */
    //养护模块API
    public static final String PROTECT_BASE_API = "com.jd.jcloud.wms.inventory.maintain.service.MaintainAppService";
    //包装规格详情
    public static final String queryPackageLevelConfigsForApp = "queryPackageLevelConfigsForApp";
}
