package com.grunka.random.fortuna.entropy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

import static org.junit.Assert.assertEquals;

public class MemoryPoolEntropySourceTestTempusFugit {
	
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

    private MemoryPoolEntropySource target;
    private int adds;

    @Before
    public void before() {
        target = new MemoryPoolEntropySource();
        adds = 0;
    }

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldGetMemoryPoolData() {
        target.event(event -> {
            assertEquals(2, event.length);
            adds++;
        });
        assertEquals(1, adds);
    }


}
