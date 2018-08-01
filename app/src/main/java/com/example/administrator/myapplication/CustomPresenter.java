package com.example.administrator.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.yh.ydd.base.config.BasePresenter;
import com.yh.ydd.base.config.ILifecyclePresenter;

import org.jetbrains.annotations.NotNull;

/**
 * @author： 董海峰
 * @created: 2018-07-24
 * @description： 如果需要感知生命周期，需要实现接口ILifecyclePresenter
 */
class CustomPresenter extends BasePresenter implements ILifecyclePresenter {

    public void out(){
        Log.e("DOAING","hello!");

    }


    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
        Log.e("DOAING","onCreate!");
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        Log.e("DOAING","onDestroy!");
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
