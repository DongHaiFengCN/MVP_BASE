package com.example.administrator.myapplication;

import android.os.Bundle;

import com.example.mvplib.mvp.BaseMvpActivity;
import com.example.mvplib.mvp.BasePresenter;

/**
 * @author Administrator
 */
public class MainActivity extends BaseMvpActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        CustomPresenter customPresenter = (CustomPresenter) mPresenter;

        customPresenter.out();

        //如果使用LifecycleObserver
         getLifecycle().addObserver(customPresenter);


    }

    @Override
    protected BasePresenter createPresenter() {
        return new CustomPresenter();
    }


}
