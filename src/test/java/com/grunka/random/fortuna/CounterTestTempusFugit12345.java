package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

import static org.junit.Assert.*;

public class CounterTestTempusFugit12345 {

    private Counter counter = new Counter(128);
    @Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

    @Test(expected = IllegalArgumentException.class)
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldFailForInvalidNumberOfBits() throws Exception {
        new Counter(127);
    }

    @Test(expected = IllegalArgumentException.class)
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldFailForTooFewBits() throws Exception {
        new Counter(0);
    }

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldRollOverBackToZero() throws Exception {
        Counter smallCounter = new Counter(8);
        for (int i = 0; i < 256; i++) {
            smallCounter.increment();
        }
        assertTrue(smallCounter.isZero());
    }

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldBeZero() throws Exception {
        assertTrue(counter.isZero());
    }

    @Test
    @Concurrent(count = 2)
	@Repeating(repetition = 100)
    public void shouldIncrementOneStep() throws Exception {
        counter.increment();
        byte[] state = counter.getState();
        assertEquals(16, state.length);
        assertEquals(1, state[0]);
        assertFalse(counter.isZero());
    }
}
