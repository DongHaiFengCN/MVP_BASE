package com.example.mvplib.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author： 董海峰
 * @created: 2018-07-24
 * @description MVP基础Activity
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected BasePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter == null) {
             throw new RuntimeException("没有初始化当前activity的present对象！");
        }
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();

    }

    /**
     * 返回具体的业务 presenter,完成绑定视图等操作
     *
     * @return 具体视图
     */
    protected abstract T createPresenter();
}
