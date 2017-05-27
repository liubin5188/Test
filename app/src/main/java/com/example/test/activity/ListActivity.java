package com.example.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.adapter.RecyclerBooksAdapter;
import com.example.test.R;
import com.example.test.injector.ActivityModule;
import com.example.test.injector.DaggerActivityComponent;
import com.example.test.models.Book;
import com.example.test.models.Database;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;


public class ListActivity extends AppCompatActivity implements IListActivity.IView {

    @Inject
    PresenterList presenterList;

    @Inject
    RecyclerBooksAdapter booksAdapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.rlv);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(booksAdapter);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pg","0");
        hashMap.put("sz","20");
        presenterList.loadData(hashMap);

    }


    @Override
    public void onSuccess(ArrayList<Book> arrayList) {
        booksAdapter.setAdapterData(arrayList);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        System.out.println("onError");
    }
}
