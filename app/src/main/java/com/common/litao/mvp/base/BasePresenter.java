package isfaaghyth.app.mvp.base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by litao on 6/18/17.
 */

public class BasePresenter<V>{
    public V baseView;
    private CompositeSubscription compositeSubscription;

    public void attachView(V view) {
        this.baseView = view;
    }

    public void dettachView() {
        this.baseView = null;
        //RXjava取消注册，以避免内存泄露
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    protected void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
