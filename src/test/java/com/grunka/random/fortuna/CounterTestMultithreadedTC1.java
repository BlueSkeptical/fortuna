package com.grunka.random.fortuna;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.*;

public class CounterTestMultithreadedTC1 extends MultithreadedTestCase {

    private Counter counter = new Counter(128);

    public void thread1() throws Exception {
        new Counter(127);
    }

    public void threadw() throws Exception {
        new Counter(127);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForInvalidNumberOfBits() throws Throwable {
    	TestFramework.runManyTimes(new CounterTestMultithreadedTC1(), 100);
    }
}
