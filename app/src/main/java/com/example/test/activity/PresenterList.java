package com.example.test.activity;

import com.example.test.models.Book;
import com.example.test.models.Database;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liubin on 2017/5/23.
 */

public class PresenterList implements IListActivity.IPresenter {

    IListActivity.IView iView;

    @Inject
    public PresenterList(IListActivity.IView iView){
        this.iView = iView;
    }


    @Override
    public void loadData(HashMap<String, String> params) {
        Observable.create(new Observable.OnSubscribe<ArrayList<Book>>() {
            @Override
            public void call(Subscriber<? super ArrayList<Book>> subscriber) {
                subscriber.onNext(Database.ALL_BOOKS);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<ArrayList<Book>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        iView.onError();
                    }

                    @Override
                    public void onNext(ArrayList<Book> books) {
                        iView.onSuccess(books);
                    }
                });

    }
}
