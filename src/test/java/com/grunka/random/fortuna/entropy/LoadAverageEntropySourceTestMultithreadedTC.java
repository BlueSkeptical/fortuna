package com.grunka.random.fortuna.entropy;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class LoadAverageEntropySourceTestMultithreadedTC extends MultithreadedTestCase {

    private LoadAverageEntropySource target = new LoadAverageEntropySource();
    private int adds = 0;

    public void thread1() {
    	//System.out.println(Thread.currentThread().getId());
        target.event(event -> {
            Assert.assertEquals(2, event.length);
            adds++;
        });
        Assert.assertEquals(1, adds);
    }
    
    public void thread2() {
        target.event(event -> {
            Assert.assertEquals(2, event.length);
            adds++;
        });
        Assert.assertEquals(1, adds);
    }
    
    @Test
    public void shouldAddTwoBytesAndSchedule() throws Throwable {
    	TestFramework.runManyTimes(new LoadAverageEntropySourceTestMultithreadedTC(), 100);
    }
}
