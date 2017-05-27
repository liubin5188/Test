package com.example.test.injector;

import android.content.Context;

import com.example.test.activity.ListActivity;
import com.example.test.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liubin on 2017/5/23.
 */
@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Context getContext();

    void inject(MainActivity mainActivity);

    void inject(ListActivity listActivity);

}
