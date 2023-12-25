package com.bruce.others;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUsageTest {
    @Test
    public void executeTaskWithExp() {
        Lock lock = new ReentrantLock();
        Thread.interrupted();
        /**
         * 阻塞等待获取锁
         */
        lock.lock();
        // 这里能不能写可能抛异常的语句？不能，因为可能导致没法往下走，没法解锁
        try {
            // 不能在此加锁，否则不管是否加锁成功，finally都会执行，导致出错
        } finally {
            lock.unlock();
        }

        /**
         * 尝试获取锁
         */
        boolean isLock = lock.tryLock();
        if (isLock) {
            try {
                // do someThing
            } finally {
                lock.unlock();
            }
        }


        /**
         * 在并发修改同一记录时，为避免数据丢失，需要加锁
         * 应用层加锁
         * 缓存层加锁
         * 数据库加锁，version 作为更新依据
         *
         * 冲突概率小于20% 推荐使用乐观锁，重试3次及以上。
         * 冲突频繁则使用悲观锁 synchronized lock等
         *
         *
         * 布隆过滤器
         */
    }
}
