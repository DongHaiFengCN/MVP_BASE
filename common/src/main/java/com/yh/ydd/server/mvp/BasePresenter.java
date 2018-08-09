package com.yh.ydd.server.mvp;

import android.app.Activity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author： 董海峰
 * @created: 2018-07-24
 * @description：
 */
public  class BasePresenter<T> {

    /**
     * 接口类型的弱引用
     */
    private Reference<T> mViewRef;

    public void attachView(T view) {

        mViewRef = new WeakReference<>(view);
    }

    protected T getView() {

        return mViewRef.get();
    }

    /**
     *
     * @return 获取基础的application
     */
    protected BaseApplication getBaseApplication(){

        Activity activity = (Activity) mViewRef.get();

        return (BaseApplication) activity.getApplication();
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
