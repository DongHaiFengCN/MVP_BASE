package com.yh.ydd.database.config;

import android.content.Context;

public abstract class DataBaseFactory {

    public abstract <T extends AbDataBase> AbDataBase createDatabase(Class<T> clz, Context context);
}
