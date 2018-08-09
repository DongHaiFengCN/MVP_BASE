package com.yh.ydd.database.config;

import android.content.Context;

public abstract class AbDataBase {

    /**
     * 初始化数据库
     * @param context 传入上下文
     */
    public abstract void initDataBase(Context context);

    /**
     *
     * @return 返回当前数据的操作对象
     */
    public abstract AbDatabaseUtils getDatabaseUtilsInstant();





}
