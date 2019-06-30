package com.ibm.mq.jms.customthread;

import java.util.concurrent.*;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor
{
    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                    TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    RejectedExecutionHandler handler)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override protected void afterExecute(Runnable r, Throwable t)
    {
        super.afterExecute(r, t);
        if (r == null) {
            System.out.println("Runnable r = NULL");
        }
        else {
            System.out.println("Runnable r <> NULL");
        }

        if (t == null) {
            System.out.println("Throwable t = NULL");
        }
        else {
            System.out.println("t = " + t +
                    "Cause = " + t.getCause() +
                    "Message = " + t.getMessage() +
                    "LocalizedMessage = " + t.getLocalizedMessage() +
                    "StackTrace = " + t.getStackTrace() +
                    "Suppressed = " + t.getSuppressed() +
                    "initCause = " + t.initCause(t));
        }
    }
}
