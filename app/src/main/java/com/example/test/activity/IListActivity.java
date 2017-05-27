package com.example.test.activity;

import com.example.test.models.Book;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liubin on 2017/5/23.
 */

public interface IListActivity {
    interface IView{
        void onSuccess(ArrayList<Book> arrayList);
        void onError();
    }

    interface IPresenter{
        void loadData(HashMap<String,String> params);
    }
}
