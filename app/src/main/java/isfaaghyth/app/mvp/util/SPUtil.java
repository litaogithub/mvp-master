package isfaaghyth.app.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import isfaaghyth.app.mvp.App;
import isfaaghyth.app.mvp.base.BaseConstants;

public class SPUtil {

    public static SharedPreferences sharedPreferences;

    /**
     * 获取主配置文件
     */
    public static SharedPreferences getJdSharedPreferences() {
        if (null == sharedPreferences) {
            sharedPreferences = App.getContext().getSharedPreferences(BaseConstants.JD_CW_ANDROID_CLIENT, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    /**
     * 保存自定义String值
     *
     * @param key   键
     * @param value 值
     */
    public static void setCustomizePara(String key, String value) {
        getJdSharedPreferences().edit().putString(key, value).commit();
    }

    public static String getCustomizePara(String key) {
        return getJdSharedPreferences().getString(key, "");
    }

    public static String getCustomizePara(String key, String defValue) {
        return getJdSharedPreferences().getString(key, defValue);
    }

    /**
     * 保存环境模式
     */
    public static void setDevelopMode(boolean mode) {
        getJdSharedPreferences().edit().putBoolean("developMode", mode).commit();
    }

    /**
     * 获取环境模式
     *
     * @return
     */
    public static boolean getDevelopMode() {
        return getJdSharedPreferences().getBoolean("developMode", false);
    }

    /**
     * 获取存储pin值
     *
     * @return
     */
    public static String getKeyPin() {
        return getJdSharedPreferences().getString("keyPin", "");
    }

    /**
     * 存储pin值
     *
     * @param keyPin
     */
    public static void setKeyPin(String keyPin) {
        getJdSharedPreferences().edit().putString("keyPin", keyPin).commit();
    }

    /**
     * 获取存储A2值
     *
     * @return
     */
    public static String getA2() {
        return getJdSharedPreferences().getString("A2", "");
    }

    /**
     * A2
     *
     * @param a2
     */
    public static void setA2(String a2) {
        getJdSharedPreferences().edit().putString("A2", a2).commit();
    }

    /**
     * 保存用户名
     *
     * @param userNames
     */
    public static void setUserNames(Set<String> userNames) {
        getJdSharedPreferences().edit().putStringSet("userNames", userNames).commit();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static Set<String> getUserNames() {
        return getJdSharedPreferences().getStringSet("userNames", null);
    }

    /**
     * 保存最近用户名
     *
     * @param userName
     */
    public static void setLastUserNames(String userName) {
        getJdSharedPreferences().edit().putString("lastUserName", userName).commit();
    }

    /**
     * 获取最近用户名
     *
     * @return
     */
    public static String getLastUserName() {
        return getJdSharedPreferences().getString("lastUserName", "");
    }

    /**
     * 获取当前账户的nickname
     *
     * @return
     */
    public static String getNickname() {
        return getJdSharedPreferences().getString(getKeyPin() + "_nickname", "");
    }

    /**
     * 保存当前账户的nickname
     *
     * @param nickname
     */
    public static void setNickname(String nickname) {
        getJdSharedPreferences().edit().putString(getKeyPin() + "_nickname", nickname).apply();
    }

    /**
     * 获取机构号
     *
     * @return
     */
    public static String getOrgNo() {
        return getJdSharedPreferences().getString("orgNo", "");
    }

    /**
     * 设置机构号
     *
     * @param orgNo
     */
    public static void setOrgNo(String orgNo) {
        getJdSharedPreferences().edit().putString("orgNo", orgNo).commit();
    }

    /**
     * 获取机构名称
     *
     * @return
     */
    public static String getOrgName() {
        return getJdSharedPreferences().getString("orgName", "");
    }

    /**
     * 设置机构名称
     *
     * @param orgName
     */
    public static void setOrgName(String orgName) {
        getJdSharedPreferences().edit().putString("orgName", orgName).commit();
    }

    /**
     * 获取配送中心号
     *
     * @return
     */
    public static String getDistributeNo() {
        return getJdSharedPreferences().getString("distributeNo", "");
    }

    /**
     * 设置配送中心号
     *
     * @param distributeNo
     */
    public static void setDistributeNo(String distributeNo) {
        getJdSharedPreferences().edit().putString("distributeNo", distributeNo).commit();
    }

    /**
     * 获取配送中心名称
     *
     * @return
     */
    public static String getDistributeName() {
        return getJdSharedPreferences().getString("distributeName", "");
    }

    /**
     * 设置配送中心名称
     *
     * @param distributeName
     */
    public static void setDistributeName(String distributeName) {
        getJdSharedPreferences().edit().putString("distributeName", distributeName).commit();
    }

    /**
     * 获取库房号
     *
     * @return
     */
    public static String getWarehouseNo() {
        return getJdSharedPreferences().getString("warehouseNo", "");
    }

    /**
     * 设置库房号
     *
     * @param warehouseNo
     */
    public static void setWarehouseNo(String warehouseNo) {
        getJdSharedPreferences().edit().putString("warehouseNo", warehouseNo).commit();
    }

    /**
     * 获取库房名
     *
     * @return
     */
    public static String getWarehouseName() {
        return getJdSharedPreferences().getString("warehouseName", "");
    }

    /**
     * 设置库房名
     *
     * @param warehouseName
     */
    public static void setWarehouseName(String warehouseName) {
        getJdSharedPreferences().edit().putString("warehouseName", warehouseName).commit();
    }

    /**
     * 获取租户ID
     *
     * @return
     */
    public static String getTenantId() {
        return getJdSharedPreferences().getString("tenantId", "");
    }

    /**
     * 设置租户ID
     *
     * @param tenantId
     */
    public static void setTenantId(String tenantId) {
        getJdSharedPreferences().edit().putString("tenantId", tenantId).commit();
    }
    /**
     *  设置第一次打开app
     *
     * @param
     */
    public static void setFirstStartFlag(boolean isFirst) {
        getJdSharedPreferences().edit().putBoolean("firstStart", isFirst).apply();
    }

    /**
     * 获取是否为第一次打开
     *
     * @return
     */
    public static boolean getFirstStartFlag() {
        return getJdSharedPreferences().getBoolean("firstStart",true);
    }
}
