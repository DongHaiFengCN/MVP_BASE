package com.yh.ydd.base.config;

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
     * @return 是否绑定视图
     */
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
