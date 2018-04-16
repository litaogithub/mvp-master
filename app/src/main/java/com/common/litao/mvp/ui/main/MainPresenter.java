package com.common.litao.mvp.ui.main;

import com.common.litao.mvp.base.BasePresenter;
import com.common.litao.mvp.model.MainModel;
import com.common.litao.mvp.util.Test;

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
