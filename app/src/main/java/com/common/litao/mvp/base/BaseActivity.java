package com.common.litao.mvp.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import com.common.litao.mvp.R;
import com.common.litao.mvp.util.CustomToast;

/**
 * Created by litao on 6/17/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener{

    protected P presenter;
    private TextView barTitleTitleTv;
    private LinearLayout barTitleBackLl;
    protected abstract P createPresenter();
    protected abstract void initialize(Bundle savedInstanceState);
    protected abstract void initData();
    //获取页面传值数据
    protected abstract void getBundleExtras(Bundle extras);
    protected abstract @LayoutRes int setLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent().getExtras()) {
            getBundleExtras(getIntent().getExtras());
        }
        setContentView(setLayout());
        ButterKnife.bind(this);
        presenter = createPresenter();
        initialize(savedInstanceState);
        initData();
    }

    public void setBarTitle(String title) {
        this.barTitleTitleTv = (TextView)this.findViewById(R.id.tv_bar_title_title);
        if(barTitleTitleTv == null)
            throw new NullPointerException("Please add baseTitleLayout");
        this.barTitleTitleTv.setText(title);
    }

    public void setBackView(int visible) {
        this.barTitleBackLl = (LinearLayout)this.findViewById(R.id.lv_bar_title_back);
        if(barTitleBackLl == null)
            throw new NullPointerException("Please add baseTitleLayout");
        this.barTitleBackLl.setVisibility(visible);
    }

    public void setBackBtn() {
        this.barTitleBackLl = (LinearLayout)this.findViewById(R.id.lv_bar_title_back);
        if(barTitleBackLl == null)
            throw new NullPointerException("Please add baseTitleLayout");
        this.barTitleBackLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(this.barTitleBackLl != null){
            if(this.barTitleBackLl == v){
                this.finish();
            }
        }else{
            throw new NullPointerException("Please init setBackBtn method");
        }
    }

    /**
     * 2秒钟显示
     * @param message
     */
    public void toastShort(String message){
        CustomToast.showToast(this,message, Toast.LENGTH_SHORT);
    }

    /**
     * 4秒钟显示
     * @param message
     */
    public void toastLong(String message){
        CustomToast.showToast(this,message,Toast.LENGTH_LONG);
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
        Intent intent = new Intent(this,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.dettachView();
    }

    protected void showAlertMessage(String message) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message);
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {}
        });
        alert.show();
    }
}
