package com.bruce.others;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ThreadPoolUsageTest {
    final ExecutorService pool = Executors.newFixedThreadPool(2);

    private Runnable getTaskInstance(CountDownLatch latch) {
        Runnable taskWithExp;
        List<Integer> list = Lists.newArrayList(1, 2, 3, null);
        taskWithExp = () -> {
            try {
                list.parallelStream().map(a -> a.toString()).collect(Collectors.toList());
            } catch (Exception e) {
                System.out.println("内部 捕获到的异常: " + e.getMessage());
                e.printStackTrace();
                System.out.println("内部 异常打印结束, 重新抛出");
                latch.countDown();
                throw e;
            } finally {
                System.out.println("这里是finally, 即使抛出异常也会走了这里再抛出");
            }
        };
        return taskWithExp;
    }

    @Test
    public void executeTaskWithExp() {
        System.out.println("\n =========== executeTaskWithExp 外部 开始。。。。");

        try {
            CountDownLatch latch = new CountDownLatch(1);
            pool.execute(this.getTaskInstance(latch));
            latch.await();
        } catch (Exception e) {
            System.out.println("executeTaskWithExp 外部 使用线程池处理task异常: " + e.getMessage());
            e.printStackTrace();
            System.out.println("executeTaskWithExp 外部 异常打印结束");
        }

        System.out.println("executeTaskWithExp 外部 结束。。。。");
    }

    @Test
    public void submitTaskWithExp() {
        System.out.println("\n =========== submitTaskWithExp 外部 开始。。。。");

        try {
            CountDownLatch latch = new CountDownLatch(1);
            pool.submit(this.getTaskInstance(latch));
            latch.await();
        } catch (Exception e) {
            System.out.println("submitTaskWithExp 外部 使用线程池处理task异常: " + e.getMessage());
            e.printStackTrace();
            System.out.println("submitTaskWithExp 外部 异常打印结束");
        }

        System.out.println("submitTaskWithExp 外部 结束。。。。");
    }

    @Test
    public void submitTaskWithExpAndThenGet() {
        System.out.println("\n =========== submitTaskWithExpAndThenGet 外部 开始。。。。");

        try {
            CountDownLatch latch = new CountDownLatch(1);
            Future taskFuture = pool.submit(this.getTaskInstance(latch));
            latch.await();
            if (taskFuture.isDone()) {
                taskFuture.get();
            }
        } catch (Exception e) {
            System.out.println("submitTaskWithExpAndThenGet 外部 使用线程池处理task异常: " + e.getMessage());
            e.printStackTrace();
            System.out.println("submitTaskWithExpAndThenGet 外部 异常打印结束");
        }

        System.out.println("submitTaskWithExpAndThenGet 外部 结束。。。。");
    }

}
