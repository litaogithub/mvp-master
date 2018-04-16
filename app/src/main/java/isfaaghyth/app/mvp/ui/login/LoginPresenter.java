package isfaaghyth.app.mvp.ui.login;

import java.util.HashMap;
import java.util.Map;

import isfaaghyth.app.mvp.base.BasePresenter;
import isfaaghyth.app.mvp.bean.BusinessBean;
import isfaaghyth.app.mvp.network.RequestCallback;
import isfaaghyth.app.mvp.ui.login.LoginContract;
import isfaaghyth.app.mvp.model.LoginModel;
import isfaaghyth.app.mvp.util.Const;

/**
 * Created by isfaaghyth on 6/17/17.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Present {

    private LoginModel mLoginModel;

    public LoginPresenter(LoginContract.View view) {
        super.attachView(view);
        mLoginModel = new LoginModel();
    }
    /**
     * 请求登录
     */
    @Override
    public void onRequestLogin() {
        Map<String,String> param = new HashMap<>();
        param.put("email",baseView.getEmail());
        param.put("pass",baseView.getPass());
        addSubscription(mLoginModel.postLogin(param), new RequestCallback<BusinessBean>(baseView.getEnbleView()) {
            @Override
            public void onSuccess(BusinessBean model) {
                if(model.getCode().equals(Const.SUCCESS_CODE)){
                    baseView.onSuccess(model.getData(),1);
                }else{
                    baseView.onFailure(model.getMessage());
                }
            }
        });
    }
}
