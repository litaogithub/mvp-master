package isfaaghyth.app.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import isfaaghyth.app.mvp.App;

/**
 * 工具类
 */
public class CommonUtil {

    private static String TAG = "CommonUtil";

    // 上一次的联网方式
    public static String preNetApn;

    /**
     * 检查字符串为Null或者为空
     *
     * @param args 字符串
     * @return 如果为Null或者为空，返回true，否则返回false
     */
    public static boolean checkNullAndEmpty(String... args) {
        for (String str : args) {
            if (str == null || str.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static int dip2px(Context context, float dipValue) {
        return (int) (0.5F + dipValue
                * context.getResources().getDisplayMetrics().density);
    }

    public static int px2dip(Context context, float pxValue) {
        return (int) (0.5F + pxValue
                / context.getResources().getDisplayMetrics().density);
    }

    public static float getDensity(Context context) {
        float desity = context.getResources().getDisplayMetrics().density;
        Log.v("lyb", "desity =" + desity);
        return desity;
    }

    /**
     * 根据用户的数据类型打开相应的Activity。比如 tel:13400010001打开拨号程序，http://www.g.cn则会打开浏览器等。
     *
     * @param activity
     * @param url
     */
    public static void openURL(Activity activity, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        activity.startActivity(intent);
    }

    /**
     * 调用系统中的打电话功能 (需要添加打电话权限：<uses-permission
     * android:name="android.permission.CALL_PHONE" />)
     *
     * @param activity
     * @param phoneNumber 你要拨打的电话号码
     */
    public static void call(Activity activity, String phoneNumber) {
        if (activity != null && phoneNumber != null && !phoneNumber.equals("")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + phoneNumber));
            activity.startActivity(intent);
        }
    }

    /**
     * 调用系统中的打电话功能
     *
     * @param context
     * @param phoneNumber 你要拨打的电话号码
     */
    public static void call(Context context, String phoneNumber) {
        if (context != null && phoneNumber != null && !phoneNumber.equals("")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v
     * @param context
     */
    public static void hideSoftInput(View v, Context context) {
        final InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 显示键盘
     *
     * @param v
     * @param context
     */
    public static void showSoftInput(View v, Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
    }

    /**
     * 判断字符串是否是纯数字串
     *
     * @param content
     * @return
     */
    public static boolean isAllNum(String content) {
        for (int i = 0; i < content.length(); i++) {
            int cAscii = (int) content.charAt(i);
            char charZero = '0';
            char char9 = '9';
            if (cAscii < (int) charZero || cAscii > (int) char9) {
                return false;
            }
        }
        return true;
    }

    // 转码
    public static String toUrlEncode(String str) {
        try {
            if (str == null)
                return null;
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 解码
    public static String formUrlEncode(String urlEncodeStr) {
        try {
            if (urlEncodeStr == null)
                return null;
            return URLDecoder.decode(urlEncodeStr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 地球半径（米）
    private static final Integer Radius = 6370856;

    /**
     * 用于计算两个点之间的距离公式
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static float getDistance(float lat1, float lng1, float lat2,
                                    float lng2) {
        float x, y, distance = 0;
        try {
            x = (float) ((lng2 - lng1) * Math.PI * Radius
                    * Math.cos(((lat1 + lat2) / 2) * Math.PI / 180) / 180);
            y = (float) ((lat2 - lat1) * Math.PI * Radius / 180);
            distance = (float) Math.hypot(x, y);
        } catch (Exception e) {

        }
        return distance;
    }

    /**
     * 用于计算两个点之间的距离公式
     *
     * @param strLat1
     * @param strLng1
     * @param strLat2
     * @param strLng2
     * @return
     */
    public static int getDistance(String strLat1, String strLng1,
                                  String strLat2, String strLng2) {
        float distance = 0;
        try {
            float lat1 = Float.parseFloat(strLat1);
            float lng1 = Float.parseFloat(strLng1);
            float lat2 = Float.parseFloat(strLat2);
            float lng2 = Float.parseFloat(strLng2);
            float x, y;
            x = (float) ((lng2 - lng1) * Math.PI * Radius
                    * Math.cos(((lat1 + lat2) / 2) * Math.PI / 180) / 180);
            y = (float) ((lat2 - lat1) * Math.PI * Radius / 180);
            distance = (float) Math.hypot(x, y);
        } catch (Exception e) {

        }
        return (int) distance;
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public static String bitmap2Base64String(Bitmap bm) {
        return Base64.encodeToString(bitmap2Bytes(bm), Base64.DEFAULT);
    }

    public static Bitmap base64StringToBitmap(String base64String) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64String, Base64.DEFAULT);
            bitmap = bytes2Bimap(bitmapArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * MD5加密 yaogubin 2014/07/16
     *
     * @param str
     * @return
     */
    public static String EncryptPassword2(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "EncryptPassword2-->>" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "EncryptPassword2-->>" + e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString().toUpperCase();
    }

    // *********************图片尺寸
    // n0: 608x608
    // n1: 350x350
    // n2： 160x160
    // n3: 130x130
    // n4: 100x100
    // n5: 50x50
    // 图片前缀。用于订单列表跟pic字段拼接 (如需要不同的尺寸,替换n2)
    public static String PIC_PREFIX = "http://img10.360buyimg.com/n2";

    // 屏幕的宽度
    public static int screen_width;
    // 屏幕的高度
    public static int screen_height;

    /**
     * 获得屏幕尺寸
     *
     * @param act
     */
    public static void getScreenMetrics(Activity act) {
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        CommonUtil.screen_width = dm.widthPixels;
    }

    public static String getScreenWH(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth() + "*" + display.getHeight();
    }

    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getClientIpAddress() {
        String ipAddress = null;
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            if (null == networks) {
                return ipAddress;
            }
            while (networks.hasMoreElements()) {
                NetworkInterface networkInterface = networks.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 对ipv6的地址做特殊处理
        if (!TextUtils.isEmpty(ipAddress)) {
            int index = ipAddress.indexOf('%');
            if (index > 0) {
                ipAddress = ipAddress.substring(0, index);
            }
        }
        return ipAddress;
    }

    public interface BrowserUrlListener {
        void onComplete(String url);
    }

    public static String mergerUrlAndParams(String urlStr,
                                            Map<String, String> params) {

        if (null == params) {
            return urlStr;
        }

        Set<String> keySet = params.keySet();
        if (null == keySet || keySet.isEmpty()) {
            return urlStr;
        }

        StringBuilder url = new StringBuilder(urlStr);
        int i = urlStr.indexOf("?");
        if (i == -1) {
            url.append("?");
        } else {
            String queryString = urlStr.substring(i + 1);
            if (!TextUtils.isEmpty(queryString) && !queryString.endsWith("&")) {
                url.append("&");
            }
        }

        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            String value = params.get(key);
            url.append(key).append("=").append(value);
            if (iterator.hasNext()) {
                url.append("&");
            }
        }

        return url.toString();

    }

    public static Intent newBrowserIntent(Uri uri, boolean force) {
        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        // 由于外部浏览器可能无法准确跳转回来，所以现在只调用系统的浏览器
        if (force) {
            i.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return i;
    }

    /**
     * 判断intent是否有效
     */
    public static boolean isIntentAvailable(Intent intent) {
        PackageManager packageManager = App.getContext().getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return null != list && list.size() > 0;
    }

    /**
     * 对象NULL转换成""
     *
     * @param obj
     * @return
     */
    public static String tranString(Object obj) {
        String result = "";
        if (obj != null && !"null".equals(obj.toString())) {
            result = String.valueOf(obj);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * 格式化两位小数
     */
    public static String toMoneyString(String money) {

        if (money != null && !"".equals(money)) {
            DecimalFormat f = new DecimalFormat("###,###,##0.00");
            return f.format(Double.parseDouble(money));
        } else {
            return "";
        }
    }

    /**
     * 自定义格式化
     */
    public static String toMoneyString(String money, String pattern) {

        if (money != null && !"".equals(money)) {
            DecimalFormat f = new DecimalFormat(pattern);
            return f.format(Double.parseDouble(money));
        } else {
            return "";
        }
    }

    /**
     * 保存文本信息到本地文件
     *
     * @param context
     * @param scontent 要保存的文本内容
     * @param filename 本地文件名 （保存在app私有目录）
     * @return
     */
    public static boolean savePrivateFileToLocal(Context context, String scontent, String filename) {
        boolean ret = false;
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(scontent.getBytes());
            fos.close();
            ret = true;
        } catch (Exception e) {
        }

        return ret;
    }

    /**
     * 消息提示框
     *
     * @param context
     * @param showText
     */
//	public static void showDialogComfirm(Context context, String showText) {
//		Handler handler = new Handler();
//		Dialog_Confirm dialogConfirm = new Dialog_Confirm(context, R.style.dialog_style, handler , showText);
//		Window win = dialogConfirm.getWindow();
//		LayoutParams params = new LayoutParams();
//		params.gravity = Gravity.CENTER;
//		win.setAttributes(params);
//		dialogConfirm.setCanceledOnTouchOutside(true);
//		dialogConfirm.show();
//	}

    public static boolean isUseTestData = false;
    //联网请求的key （key+pin）
    public static String KEY = "24C75DB056286C370DFF14D556D5191C";

    /**
     * 获取当前版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = null;
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获得手机厂商
     *
     * @return
     */
    public static String getPhoneManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取GUID
     *
     * @return
     */
    public static String getRsn() {
        return UUID.randomUUID().toString();
    }

    public static String dateTimeToString(Long time) {
        if (time != null) {
            Date date = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd HH:mm");
            String tempString = sf.format(date);
            return tempString.replace(" ", "\n");
        }
        return "";
    }

    /**
     * 时间转成yyyy.MM.dd HH:mm
     *
     * @param time
     * @return
     */
    public static String dateToString(Long time) {
        if (time != null) {
            Date date = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            return sf.format(date);
        }
        return "";
    }

    /**
     * 获取sdk版本号
     *
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
        }
        return version;
    }

    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 1000;

    /**
     * 判定按钮是否重复点击
     *
     * @return
     */
    public static boolean isNotDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isNotDoubleClick = currentTime - lastClickTime > SPACE_TIME;
        lastClickTime = currentTime;
        return isNotDoubleClick;
    }
}
