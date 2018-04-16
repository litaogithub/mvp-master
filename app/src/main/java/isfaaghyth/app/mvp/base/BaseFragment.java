package isfaaghyth.app.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import isfaaghyth.app.mvp.R;
import isfaaghyth.app.mvp.util.CustomToast;

/**
 * author: litao29
 * date: 2018/2/8
 * mail: litao29@jd.com
 * desc: 请简单描述
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements View.OnClickListener {

    protected P present;
    protected abstract Object setLayout();
    protected abstract P initPresenter();
    protected abstract void initView(View rootView);
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if(setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        } else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of getLayoutId() must be int or View!");
        }
        ButterKnife.bind(getActivity());
        present = initPresenter();
        initView(rootView);
        initData();
        return rootView;
    }

    /**
     * 2秒钟显示
     * @param message
     */
    public void toastShort(String message){
        CustomToast.showToast(getActivity(),message, Toast.LENGTH_SHORT);
    }

    /**
     * 4秒钟显示
     * @param message
     */
    public void toastLong(String message){
        CustomToast.showToast(getActivity(),message,Toast.LENGTH_LONG);
    }

    /**
     * 普通跳转
     * @param clazz
     */
    public void openActivity(Class<? extends Activity> clazz){
        openActivity(clazz,null);
    }

    /**
     * 带参数跳转(带动画)
     * @param clazz
     * @param bundle
     */
    public void openActivity(Class<? extends Activity> clazz,Bundle bundle){
        Intent intent = new Intent(getActivity(),clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(present != null){
            present.dettachView();
        }
    }
}
