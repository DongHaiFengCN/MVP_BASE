package com.yh.ydd.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mvplib.R;


public class LoadingView extends ProgressDialog {

    private TextView info;



    public LoadingView(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading);
        info = findViewById(R.id.tv_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {//开启
        super.show();
    }

    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
    public void setTitle(@NonNull String s){

        info.setText(s);
    }
}
