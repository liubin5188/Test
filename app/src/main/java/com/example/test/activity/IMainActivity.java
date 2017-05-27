package com.example.test.activity;

import com.example.test.models.BaseResponse;

import java.util.HashMap;

/**
 * Created by liubin on 2017/5/23.
 */

public interface IMainActivity {

    interface IView{
        HashMap<String,String> getNewsParams(HashMap<String, String> params);

        void showError();

        void showSuccess(BaseResponse data);
    }

    interface IPresenter{
        void releaseNews(HashMap<String,String> params);
    }

}
