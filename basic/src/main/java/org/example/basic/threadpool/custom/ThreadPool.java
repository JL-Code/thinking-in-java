package org.example.basic.threadpool.custom;

/**
 * 自定义线程池接口
 *
 * @param <Job> 任务类型
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
