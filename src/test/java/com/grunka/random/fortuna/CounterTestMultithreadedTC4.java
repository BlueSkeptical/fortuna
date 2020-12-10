package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.*;

import org.junit.Assert;

public class CounterTestMultithreadedTC4 extends MultithreadedTestCase {

    private Counter counter = new Counter(128);

    public void thread1() throws Exception {
        Assert.assertTrue(counter.isZero());
    }
    
    public void thread2() throws Exception {
        Assert.assertTrue(counter.isZero());
    }

    @Test
    public void shouldBeZero() throws Throwable {
    	TestFramework.runManyTimes(new CounterTestMultithreadedTC4(), 100);
    }
}
