package com.common.litao.mvp.util;

import com.alibaba.fastjson.JSONArray;
import com.jd.mrd.network.bean.WGRequestBean;

import java.util.Arrays;

import com.common.litao.mvp.bean.BusinessBean;
import com.common.litao.mvp.network.WgCon;

/**
 * 参数组装类
 * Created by litao on 2018/2/8.
 */
public class ParamUtil {
    /**
     * 组合网关请求参数Bean
     *
     * @param service 服务名
     * @param alias   别名
     * @param method  方法
     * @param param   参数
     * @return 请求结果
     */
    public static WGRequestBean getWGRequestBean(String service, String alias, String method, String param) {
        // 构造网关访问参数
        return new WGRequestBean.Builder(service, alias, method, param)
                // OEM网关接口参数（2017/5/12）authType不用传
                .userId(SPUtil.getKeyPin()).origin(WgCon.origin).authType("authType").token(WgCon.token)
                // 网关通用参数
                .appId(WgCon.appId).passportAppId(WgCon.passportAppId).client(WgCon.client)
                .clientIp(WgCon.clientIp).osVersion(WgCon.osVersion).clientVersion(WgCon.clientVersion)
                .uuid(WgCon.uuid).appName(WgCon.appName).networkType("")
                .area(WgCon.area).screen(WgCon.screen).build();
    }

    /**
     * 组装BusinessBean
     *
     * @param code    状态码
     * @param message 信息
     * @return BusinessBean对象
     */
    public static BusinessBean getBusinessBean(String code, String message) {
        BusinessBean businessBean = new BusinessBean();
        businessBean.setCode(code);
        businessBean.setMessage(message);
        return businessBean;
    }

    /**
     * 组合网关的param字段
     *
     * @param param 只有一个参数
     * @return 参数Json串
     */
    public static String getJsonParam(Object param) {
        JSONArray json = new JSONArray();
        json.add(param);
        return json.toJSONString();
    }

    /**
     * 组合网关的param字段（重载）
     *
     * @param param 可变参数列表，避免每次调用产生的参数数组创建
     * @return 参数Json串
     */
    public static String getJsonParam(Object param, Object... more) {
        JSONArray json = new JSONArray();
        json.add(param);
        json.addAll(Arrays.asList(more));
        return json.toJSONString();
    }
}
