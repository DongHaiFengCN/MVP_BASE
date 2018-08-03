package com.yh.ydd.base.print;

import android.content.Context;
import android.content.Intent;

/**
 * @author 董海峰
 * @date 2018-8-1
 * <p>
 * 这个类进行打印服务的开启和关闭，通过单例模式获取当前的对象。
 */

public class PrintManager implements IPrintConfig {

    private static PrintManager printManager;


    /**
     * 配置，初始化打印机的信息
     */
    @Override
    public void initPrint() {

    }

    /**
     * 启动打印监听
     *
     * @param context 上下文
     */
    @Override
    public void startPrintService(Context context) {

        context.startService(new Intent(context, PrintService.class));
    }

    /**
     * 关闭打印监听
     *
     * @param context 上下文
     */
    @Override
    public void stopPrintService(Context context) {

        context.stopService(new Intent(context, PrintService.class));

        printManager = null;

    }

    /**
     * 关闭打印服务
     *
     * @return 返回打印管理实例
     */
    public static PrintManager getInstance() {

        if (printManager == null) {

            printManager = new PrintManager();
        }
        return printManager;
    }


}
