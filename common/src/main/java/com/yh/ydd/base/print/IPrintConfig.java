package com.yh.ydd.base.print;

import android.content.Context;

public interface IPrintConfig {

    void initPrint();

    void startPrintService(Context context);

    void stopPrintService(Context context);

}
