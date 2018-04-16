package isfaaghyth.app.mvp.network;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.jd.mrd.network.view.CommonLoadingDialog;

import isfaaghyth.app.mvp.App;
import isfaaghyth.app.mvp.base.BaseActivity;
import rx.Subscriber;

/**
 * Created by litao on 6/17/17.
 */

public abstract class RequestCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    protected Context context;
    protected View view;
    protected View[] views;

    public RequestCallback() {
        this.context = App.getContext();
    }

    public RequestCallback(View view) {
        this.view = view;
        this.context = view.getContext();
    }

    public RequestCallback(View view, View... views) {
        this.views = new View[views.length + 1];
        this.views[0] = view;
        System.arraycopy(views, 0, this.views, 1, views.length);
        this.context = view.getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.showDialog();
        this.setViewDisable();
    }

    @Override
    public void onError(Throwable e) {
        this.dismissDialog();
        this.setViewEnable();
        if(context instanceof BaseActivity){
            ((BaseActivity) context).toastShort(e.getMessage());
        }
    }

    @Override public void onNext(M model) {
        this.setViewEnable();
        onSuccess(model);
    }

    @Override public void onCompleted() {
        this.dismissDialog();
    }
    private void showDialog() {
        if(this.context instanceof Activity) {
            CommonLoadingDialog.getInstanceDialog().showDialog((Activity)this.context);
        }

    }

    private void dismissDialog() {
        if(this.context instanceof Activity) {
            CommonLoadingDialog.getInstanceDialog().dismissDialog((Activity)this.context);
        }
    }

    private void setViewDisable() {
        if(this.view != null && this.view.isEnabled()) {
            this.view.setEnabled(false);
        }
        if(this.views != null) {
            View[] var1 = this.views;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                View view1 = var1[var3];
                if(view1.isEnabled()) {
                    view1.setEnabled(false);
                }
            }
        }
    }

    protected void setViewEnable() {
        if(this.view != null && !this.view.isEnabled()) {
            this.view.setEnabled(true);
        }
        if(this.views != null) {
            View[] var1 = this.views;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                View view1 = var1[var3];
                if(!view1.isEnabled()) {
                    view1.setEnabled(true);
                }
            }
        }
    }
}
