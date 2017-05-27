package com.example.test.injector;

import android.content.Context;

import com.example.test.activity.IListActivity;
import com.example.test.activity.IMainActivity;
import com.example.test.activity.ListActivity;
import com.example.test.activity.MainActivity;
import com.example.test.adapter.RecyclerBooksAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liubin on 2017/5/23.
 */
@Module
public class ActivityModule {

    Context context;

    public ActivityModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }


    @Provides
    @Singleton
    public IMainActivity.IView provideMainView(){
        return (MainActivity)context;
    }

    @Provides
    @Singleton
    public IListActivity.IView provideListView(){
        return (ListActivity)context;
    }

//    @Provides
//    @Singleton
//    public RecyclerBooksAdapter provideAdapter(Context context){
//        return new RecyclerBooksAdapter(context);
//    }

}
