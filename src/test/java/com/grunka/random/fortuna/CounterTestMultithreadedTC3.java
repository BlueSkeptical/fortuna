package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.*;

import org.junit.Assert;

public class CounterTestMultithreadedTC3 extends MultithreadedTestCase {

    private Counter counter = new Counter(128);

    public void thread1() throws Exception {
        Counter smallCounter = new Counter(8);
        for (int i = 0; i < 256; i++) {
            smallCounter.increment();
        }
        Assert.assertTrue(smallCounter.isZero());
    }
    
    public void thread2() throws Exception {
        Counter smallCounter = new Counter(8);
        for (int i = 0; i < 256; i++) {
            smallCounter.increment();
        }
        Assert.assertTrue(smallCounter.isZero());
    }

    @Test
    public void shouldRollOverBackToZero() throws Throwable {
    	TestFramework.runManyTimes(new CounterTestMultithreadedTC3(), 100);
    }
}
