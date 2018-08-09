package com.example.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.couchbase.lite.ReplicatorChange;
import com.yh.ydd.server.mvp.BasePresenter;
import com.yh.ydd.server.mvp.ILifecyclePresenter;
import com.yh.ydd.server.httpservice.RetrofitServiceFactory;
import com.yh.ydd.database.config.AbDatabaseUtils;
import com.yh.ydd.database.config.MyReplicatorChangeListener;
import com.yh.ydd.database.factory.couchebase.CoucheBaseInstant;
import com.yh.ydd.server.print.PrintManager;
import com.yh.ydd.server.scheduler.SchedulerUntils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter extends BasePresenter implements ILifecyclePresenter {

    private LoginActivity loginActivity;
    private static final String FIRST_START = "1";
    private AbDatabaseUtils currentDatabaseUtils;

    private SharedPreferences setting;

    boolean startReplication = true;

    /**
     * 打开数据库和开启同步
     *
     * @param name
     * @param age
     */
    private void createDatabaseAndReplication(String name, String age) {



        //这里开启并注册指定的数据库，例如couchebase，如果已经开启就直接返回当前数据库的操作工具。
        if (currentDatabaseUtils == null) {

            currentDatabaseUtils = getBaseApplication().createCurrentDatabase(CoucheBaseInstant.class);


        }

        if (startReplication) {



            startReplicationBySession(name, age);
        }

        //监听同步状态
        currentDatabaseUtils.openReplicatorChangeListener(new MyReplicatorChangeListener() {
            @Override
            public void changed(Object change) {
                ReplicatorChange replicatorChange = (ReplicatorChange) change;
                Log.e("DOAING",replicatorChange.getStatus().getProgress().toString());

                if (replicatorChange.getStatus().getActivityLevel().name().equals("STOPPED")) {
                    loginActivity.closeLoadingDialog();

                }
            }
        });

    }

    @SuppressLint("CheckResult")
    private void startReplicationBySession(String name, String psw) {

        final String[] sessionInfo = new String[1];
        Retrofit retrofit = RetrofitServiceFactory.createRetrofitByBaseUrl("http://60.208.74.139:4984/kitchen/");
        final Session session = retrofit.create(Session.class);
        SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.name = name;
        sessionRequest.password = psw;
        Call<ResponseBody> call = session.getSession(sessionRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String info = response.headers().get("Set-Cookie");

                sessionInfo[0] = Objects.requireNonNull(info,"空的？？？").substring(19, 59);

                currentDatabaseUtils.startReplication(sessionInfo[0]);

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });

    }

    /**
     * 监听生命周期，可以初始化一下东西
     */
    @Override
    public void onCreate() {

        loginActivity = (LoginActivity) getView();

        if (isFirstStart()) {

            //1 开启设备绑定 2 绑定成功执行——》initConfig

            AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity);

            builder.setTitle("请输入设备注册码");
            builder.setMessage("为了用户数据安全，请先登录网站xxxx，注册/登录平台账号，在设备管理中点击生成设备码" +
                    "，在当前输入框填入设备码进行绑定，绑定完成后就可以安全使用此设备了");

            builder.setPositiveButton("提交验证", null);
            final AlertDialog alertDialog = builder.show();
            alertDialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //网络校验设备码的验证返回
                    boolean registration_code = setting.edit().putString("registration_code", "2").commit();

                    if (registration_code) {

                        initConfig();

                    }

                    alertDialog.dismiss();

                }
            });


        } else {

            initConfig();
        }


    }

    @Override
    public void onDestroy() {


    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }

    /**
     * 判断是不是第一次安装
     */
    private boolean isFirstStart() {
        setting = loginActivity.getSharedPreferences("SHARE_APP_TAG", 0);
        String RegistrationCode = setting.getString("registration_code", FIRST_START);
        return FIRST_START.equals(RegistrationCode);

    }

    /**
     * 初始化配置
     * 1 打开打印机服务
     * 2 打开/创建数据库
     * 3 开启数据库同步
     */
    private void initConfig() {

        loginActivity.showLoadingDialog();

        PrintManager.getInstance().startPrintService(getBaseApplication());
        loginActivity.setLoadingViewTitle("开启订单监听");

        if (startReplication) {
            loginActivity.setLoadingViewTitle("检查同步");
            createDatabaseAndReplication("8c183213","123456");

        }

    }
}
