package isfaaghyth.app.mvp.ui.main;

import isfaaghyth.app.mvp.base.BasePresenter;
import isfaaghyth.app.mvp.model.MainModel;
import isfaaghyth.app.mvp.util.Test;

/**
 * Created by isfaaghyth on 6/17/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Present {

    private MainModel mMainModel;

    public MainPresenter(MainContract.View view) {
        super.attachView(view);
        mMainModel = new MainModel();
    }

    @Override
    public void loadEmployees() {
        baseView.onSuccess(Test.getData(),1);
//        addSubscription(mMainModel.getEmployee(""), new RequestCallback<BusinessBean>() {
//            @Override public void onSuccess(BusinessBean model) {
//                if(model.getCode().equals(Const.SUCCESS_CODE)){
//                    baseView.onSuccess(Test.getData(),1);
//                }else{
//                    baseView.onFailure(model.getMessage());
//                }
//            }
//        });
    }
}
