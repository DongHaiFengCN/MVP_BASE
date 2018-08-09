package com.yh.ydd.server.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchedulerUntils {
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     *
     * @return 返回一个线程池
     */
    public static ExecutorService getExecutorService() {

        return executorService;

    }


}
