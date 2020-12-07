package utcec;

import com.grunka.random.fortuna.PrefetchingSupplier;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class PrefetchingSupplierTest {
    private Boolean ifEqualActor1, ifEqualActor2;
    private List<Integer> sleeps;
    private ExecutorService executorService;

    public PrefetchingSupplierTest () {
        sleeps = new ArrayList<>(Arrays.asList(200, 150, 100, 50, 0));
        ifEqualActor1 = true;
		ifEqualActor2 = true;
		executorService = Executors.newFixedThreadPool(5);
    }
    @Actor
    public void actor1() {
        AtomicInteger number = new AtomicInteger();
        PrefetchingSupplier<String> prefetcher = new PrefetchingSupplier<>(() -> "hello " + number.getAndIncrement(), executorService);
        if (!"hello 0".equals(prefetcher.get())) {
            ifEqualActor1 = false;
        }
        if (!"hello 1".equals(prefetcher.get())) {
            ifEqualActor1 = false;
        }
        if (!"hello 2".equals(prefetcher.get())) {
            ifEqualActor1 = false;
        }
    }
    @Actor
    public void actor2() {
        AtomicInteger number = new AtomicInteger();
        PrefetchingSupplier<String> prefetcher = new PrefetchingSupplier<>(() -> "hello " + number.getAndIncrement(), executorService);
        if (!"hello 0".equals(prefetcher.get())) {
            ifEqualActor2 = false;
        }
        if (!"hello 1".equals(prefetcher.get())) {
            ifEqualActor2 = false;
        }
        if (!"hello 2".equals(prefetcher.get())) {
            ifEqualActor2 = false;
        }
    }
    @Arbiter
	public void arbiter(ZZ_Result r) {
        executorService.shutdown();
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
