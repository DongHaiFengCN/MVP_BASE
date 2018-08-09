package com.yh.ydd.server.print;

import android.content.Context;

public interface IPrintConfig {

    void initPrint();

    void startPrintService(Context context);

    void stopPrintService(Context context);

}
