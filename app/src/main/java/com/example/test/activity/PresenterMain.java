package com.example.test.activity;

import com.example.test.models.BaseResponse;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Created by liubin on 2017/5/23.
 */

public class PresenterMain implements IMainActivity.IPresenter {

    IMainActivity.IView iView;

    @Inject
    public PresenterMain(IMainActivity.IView iView){
        this.iView = iView;
    }

    @Override
    public void releaseNews(HashMap<String,String> params) {

        HashMap<String, String> newsParams = iView.getNewsParams(params);

        Observable.create(new OnSubscribe<BaseResponse>() {
            @Override
            public void call(Subscriber<? super BaseResponse> subscriber) {
                try {
                    Thread.sleep(1000);
                    BaseResponse baseResponse = new BaseResponse();
                    baseResponse.setCode(1);
                    baseResponse.setMsg("ok");
                    subscriber.onNext(baseResponse);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                }
            }

        }).subscribe(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                iView.showError();
            }


            @Override
            public void onNext(BaseResponse data) {
                iView.showSuccess(data);
            }
        });
    }




}
