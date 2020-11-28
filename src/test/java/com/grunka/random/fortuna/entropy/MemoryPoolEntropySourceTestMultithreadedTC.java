package com.grunka.random.fortuna.entropy;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class MemoryPoolEntropySourceTestMultithreadedTC extends MultithreadedTestCase {

    private MemoryPoolEntropySource target = new MemoryPoolEntropySource();
    private int adds = 0;

    public void thread1() {
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
    public void shouldGetMemoryPoolData() throws Throwable {
    	TestFramework.runManyTimes(new MemoryPoolEntropySourceTestMultithreadedTC(), 100);
    }

}
