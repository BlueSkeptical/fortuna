package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.*;

import org.junit.Assert;

public class CounterTestMultithreadedTC5 extends MultithreadedTestCase {

    private Counter counter = new Counter(128);

    public void thread1() throws Exception {
        counter.increment();
        byte[] state = counter.getState();
        Assert.assertEquals(16, state.length);
        Assert.assertEquals(1, state[0]);
        Assert.assertFalse(counter.isZero());
    }
    
    public void thread2() throws Exception {
        counter.increment();
        byte[] state = counter.getState();
        Assert.assertEquals(16, state.length);
        Assert.assertEquals(1, state[0]);
        Assert.assertFalse(counter.isZero());
    }
    
    @Test
    public void shouldIncrementOneStep() throws Throwable {
    	TestFramework.runManyTimes(new CounterTestMultithreadedTC5(), 100);
    }

}
