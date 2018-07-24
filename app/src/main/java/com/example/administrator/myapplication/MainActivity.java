package com.example.administrator.myapplication;

import android.os.Bundle;
import android.util.Log;

import com.example.administrator.myapplication.mvp.BaseMvpActivity;
import com.example.administrator.myapplication.mvp.BasePresenter;

/**
 * @author Administrator
 */
public class MainActivity extends BaseMvpActivity {

    private Presenter myPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPresenter = ((Presenter) mPresenter);

        myPresenter.out();


    }

    @Override
    protected BasePresenter createPresenter() {
        return new Presenter();
    }
}
