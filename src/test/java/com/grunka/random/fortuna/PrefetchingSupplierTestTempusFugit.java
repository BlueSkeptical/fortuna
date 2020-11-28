package com.grunka.random.fortuna;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class PrefetchingSupplierTestTempusFugit {
	
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();
	
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private List<Integer> sleeps = new ArrayList<>(Arrays.asList(200, 150, 100, 50, 0));
/*
    @After
    public void tearDown() {
        executorService.shutdown();
    }
*/
    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldGetValues() {
    	System.out.println(Thread.currentThread().getId());
        AtomicInteger number = new AtomicInteger();
        PrefetchingSupplier<String> prefetcher = new PrefetchingSupplier<>(() -> "hello " + number.getAndIncrement(), executorService);
        assertEquals("hello 0", prefetcher.get());
        assertEquals("hello 1", prefetcher.get());
        assertEquals("hello 2", prefetcher.get());
    }

    private void sleep() {
        try {
            Thread.sleep(sleeps.remove(0));
        } catch (InterruptedException e) {
            throw new Error(e);
        }
    }
}
