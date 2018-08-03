package com.yh.ydd.database.config;

import android.content.Context;

/**
 * 具体数据库的创建，
 */
public class MyDatabaseFactory extends DataBaseFactory {

    private static DataBaseFactory myDatabaseFactory;
    private AbDataBase dataBase = null;
    private MyDatabaseFactory(){}
    @Override
    public <T extends AbDataBase> AbDataBase createDatabase(Class<T> clz, Context context) {

        try {
            dataBase = (AbDataBase) Class.forName(clz.getName())
                    .getConstructor(Context.class).newInstance(context);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBase;
    }

    /**
     * 返回工厂类型的单例
     * @return 当前工厂对象
     */
    public static DataBaseFactory getInstant(){

        if(myDatabaseFactory == null){

            myDatabaseFactory = new MyDatabaseFactory();
        }
        return myDatabaseFactory;
    }
}
