package com.grunka.random.fortuna;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import java.security.MessageDigest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PoolTestMultithreadedTC extends MultithreadedTestCase {

    public void thread1() throws Exception {
    	//System.out.println(Thread.currentThread().getId());
    	Pool pool = new Pool();
        Assert.assertEquals(0, pool.size());
        pool.add(255, "Hello".getBytes());
        Assert.assertEquals(7, pool.size());
    }
    
    public void thread2() throws Exception {
    	Pool pool = new Pool();
        Assert.assertEquals(0, pool.size());
        pool.add(255, "Hello".getBytes());
        Assert.assertEquals(7, pool.size());
    }

    @Test
    public void shouldCalculateSizeOfPool() throws Throwable {
    	TestFramework.runManyTimes(new PoolTestMultithreadedTC(), 100);
    }
    
}
