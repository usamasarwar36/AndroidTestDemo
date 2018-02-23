package com.futureuniverse.fgandroidtest.base.executor.implementation;

import com.futureuniverse.fgandroidtest.base.executor.interfaces.Executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Usama Sarwar on 4/1/2017.
 */

public class ExecutorImpl implements Executor {

    public static Executor executor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();
    protected ThreadPoolExecutor threadPoolExecutor;

    public static Executor getInstance(){
        if(executor == null) {
            executor = new ExecutorImpl();
        }
        return executor;
    }

    private ExecutorImpl() {
        int corePoolSize = CORE_POOL_SIZE;
        int maxPoolSize = MAX_POOL_SIZE;
        int keepAliveTime = KEEP_ALIVE_TIME;
        TimeUnit timeUnit = TIME_UNIT;
        BlockingQueue<Runnable> workQueue = WORK_QUEUE;
        threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
    }

    @Override
    public void run(final Runnable runnable) {
        if(runnable == null) {
            throw new IllegalArgumentException("Executor runnable cannot be null");
        }
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
