package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

import java.security.MessageDigest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PoolTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldCalculateSizeOfPool() throws Exception {
    	Pool pool = new Pool();
    	//System.out.println(Thread.currentThread().getId());
        assertEquals(0, pool.size());
        pool.add(255, "Hello".getBytes());
        assertEquals(7, pool.size());
    }
}
