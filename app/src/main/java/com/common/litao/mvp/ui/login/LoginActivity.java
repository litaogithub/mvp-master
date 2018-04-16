package com.common.litao.mvp.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.common.litao.mvp.R;
import com.common.litao.mvp.base.BaseActivity;
import com.common.litao.mvp.ui.main.MainActivity;
import com.common.litao.mvp.util.IosAlertDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override protected void initialize(Bundle savedInstanceState) {
        setBackView(View.GONE);
        setBarTitle("登录");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        new IosAlertDialog(this)
                .builder()
                .setTitle("退出当前账号")
                .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                presenter.onRequestLogin();
                                openMain();
                            }
                        },2000);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    @Override
    public void onSuccess(String result, int type) {
        switch (type){
            case 1:
                openMain();
                break;
        }
    }

    @Override
    public void onFailure(String failMsg) {
        showAlertMessage(failMsg);
    }

    @Override
    public void openMain() {
        openActivity(MainActivity.class);
        finish();
    }

    @Override
    public String getEmail() {
        return edtEmail.getText().toString();
    }

    @Override
    public String getPass() {
        return edtPassword.getText().toString();
    }

    @Override
    public View getEnbleView() {
        return btnLogin;
    }
}
