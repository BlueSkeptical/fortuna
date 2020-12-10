package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.*;

public class CounterTestMultithreadedTC2 extends MultithreadedTestCase {

    private Counter counter = new Counter(128);

    public void thread1() throws Exception {
        new Counter(0);
    }
    
    public void thread2() throws Exception {
        new Counter(0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForTooFewBits() throws Throwable {
    	TestFramework.runManyTimes(new CounterTestMultithreadedTC2(), 100);
    }
}
