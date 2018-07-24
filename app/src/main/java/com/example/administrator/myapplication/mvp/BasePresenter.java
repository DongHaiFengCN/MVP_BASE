package com.example.administrator.myapplication.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author： DOAING
 * @created: 2018-07-24
 * @description：
 */
public abstract class BasePresenter<T> {

    /**
     * 接口类型的弱引用
     */
    protected Reference<T> mViewRef;

    /**
     * 关联视图
     *
     * @param view
     */
    public void attachView(T view) {

        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {

        return mViewRef.get();
    }

    public boolean isViewAttached() {

        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
