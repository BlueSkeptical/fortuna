package com.grunka.random.fortuna;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class PrefetchingSupplierTestMultithreadedTC extends MultithreadedTestCase {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private List<Integer> sleeps = new ArrayList<>(Arrays.asList(200, 150, 100, 50, 0));

    @org.junit.After
    public void tearDown() {
        executorService.shutdown();
    }

    public void thread1() {
    	//System.out.println(Thread.currentThread().getId());
        AtomicInteger number = new AtomicInteger();
        PrefetchingSupplier<String> prefetcher = new PrefetchingSupplier<>(() -> "hello " + number.getAndIncrement(), executorService);
        Assert.assertEquals("hello 0", prefetcher.get());
        Assert.assertEquals("hello 1", prefetcher.get());
        Assert.assertEquals("hello 2", prefetcher.get());
    }

    public void thread2() {
        AtomicInteger number = new AtomicInteger();
        PrefetchingSupplier<String> prefetcher = new PrefetchingSupplier<>(() -> "hello " + number.getAndIncrement(), executorService);
        Assert.assertEquals("hello 0", prefetcher.get());
        Assert.assertEquals("hello 1", prefetcher.get());
        Assert.assertEquals("hello 2", prefetcher.get());
    }
    
    @Test
    public void shouldGetValues() throws Throwable {
    	TestFramework.runManyTimes(new PrefetchingSupplierTestMultithreadedTC(), 100);
    }
    
    private void sleep() {
        try {
            Thread.sleep(sleeps.remove(0));
        } catch (InterruptedException e) {
            throw new Error(e);
        }
    }
}
