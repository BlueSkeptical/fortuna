package com.grunka.random.fortuna.entropy;

import com.grunka.random.fortuna.Util;
import com.grunka.random.fortuna.accumulator.EntropySource;
import com.grunka.random.fortuna.accumulator.EventAdder;
import com.grunka.random.fortuna.accumulator.EventScheduler;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferPoolEntropySource implements EntropySource {

    @Override
    public void schedule(EventScheduler scheduler) {
        scheduler.schedule(5, TimeUnit.SECONDS);
    }

    @Override
    public void event(EventAdder adder) {
        long sum = 0;
        List<BufferPoolMXBean> bufferPoolMXBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
        for (BufferPoolMXBean bufferPoolMXBean : bufferPoolMXBeans) {
            sum += bufferPoolMXBean.getMemoryUsed();
        }
        adder.add(Util.twoLeastSignificantBytes(sum));
    }
}
