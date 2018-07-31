package com.example.administrator.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;
import com.example.mvplib.mvp.BasePresenter;
import com.example.mvplib.mvp.IPresenter;

import org.jetbrains.annotations.NotNull;

/**
 * @author： Administrator
 * @created: 2018-07-24
 * @description：
 */
class CustomPresenter extends BasePresenter implements IPresenter {

    public void out(){
        Log.e("DOAING","hello!");
        
    }

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {

        Log.e("DOAING","Presenter  onCreate");

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {

        Log.e("DOAING","Presenter  onDestroy");

    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {
        

    }
}
