package com.example.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.yh.ydd.server.mvp.BaseMvpActivity;
import com.yh.ydd.server.mvp.BasePresenter;
import com.yh.ydd.server.print.PrintManager;
import com.yh.ydd.server.scheduler.SchedulerUntils;
import com.yh.ydd.view.LoadingView;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends BaseMvpActivity {

    private  List<String> title = new ArrayList<>();

    private LoadingView loadingView;
    protected LoginPresenter loginPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        loginPresenter = (LoginPresenter) mPresenter;

        getLifecycle().addObserver(loginPresenter);

        findViewById(R.id.bt_trun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });

    }


    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresenter();
    }

    /**
     * 开启进度dialog
     */
    public void showLoadingDialog() {

        if (loadingView == null) {

            loadingView = new LoadingView(LoginActivity.this, R.style.CustomDialog);
        }
        loadingView.show();

    }

    /**
     * 为dialog设置文字
     *
     * @param s 信息
     */
    public void setLoadingViewTitle(@NonNull final String s) {

        title.add(s);
    }

    /**
     * 关闭进度dialog
     */
    public void closeLoadingDialog() {

        SchedulerUntils.getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < title.size(); i++) {
                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loadingView.setTitle(title.get(finalI)+"..");
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


                loadingView.dismiss();

            }
        });





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PrintManager.getInstance().stopPrintService(getApplicationContext());

    }
}
