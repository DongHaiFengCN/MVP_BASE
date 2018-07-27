package com.example.administrator.myapplication;
import android.os.Bundle;
import com.example.administrator.myapplication.mvp.BaseMvpActivity;
import com.example.administrator.myapplication.mvp.BasePresenter;

/**
 * @author Administrator
 */
public class MainActivity extends BaseMvpActivity {

    private CustomPresenter myPresenter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPresenter = (CustomPresenter) mPresenter;

        //如果使用LifecycleObserver
        getLifecycle().addObserver(myPresenter);


    }


    @Override
    protected BasePresenter createPresenter() {
        return new CustomPresenter();
    }

}
