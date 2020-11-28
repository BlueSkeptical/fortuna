package com.grunka.random.fortuna.entropy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

import static org.junit.Assert.assertEquals;

public class LoadAverageEntropySourceTestTempusFugit {
	
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

    private LoadAverageEntropySource target;
    private int adds;

    @Before
    public void before() {
        target = new LoadAverageEntropySource();
        adds = 0;
    }

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldAddTwoBytesAndSchedule() {
    	//System.out.println(Thread.currentThread().getId());
        target.event(event -> {
            assertEquals(2, event.length);
            adds++;
        });
        assertEquals(1, adds);
    }
}
