package isfaaghyth.app.mvp.base;

/**
 * author: litao29
 * date: 2018/4/13
 * mail: litao29@jd.com
 * desc: 请简单描述
 */

public interface IView {
    //考虑单个页面有多个接口数据返回，特增加type参数区分
    void onSuccess(String data,int type);
    void onFailure(String failMsg);
}
