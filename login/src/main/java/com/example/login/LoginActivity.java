package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.yh.ydd.base.config.BaseMvpActivity;
import com.yh.ydd.base.config.BasePresenter;
import com.yh.ydd.base.print.PrintManager;


public class LoginActivity extends BaseMvpActivity {


    protected LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginPresenter = (LoginPresenter) mPresenter;

        getLifecycle().addObserver(loginPresenter);

        findViewById(R.id.bt_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PrintManager.getInstance().startPrintService(getApplicationContext());


            }
        });
        findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PrintManager.getInstance().stopPrintService(getApplicationContext());


            }
        });
        findViewById(R.id.bt_trun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,SecondActivity.class));

            }
        });
        findViewById(R.id.bt_database).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginPresenter.submit("董海峰","222");
            }
        });
        findViewById(R.id.bt_http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginPresenter.httpTest();
            }
        });

    }


    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresenter();
    }


}
