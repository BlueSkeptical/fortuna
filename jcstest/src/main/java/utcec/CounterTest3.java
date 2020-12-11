package utcec;

import com.grunka.random.fortuna.Counter;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class CounterTest3 {
    private Boolean ifEqualActor1, ifEqualActor2;
    public CounterTest3 () {
        ifEqualActor1 = true;
		ifEqualActor2 = true;
    }
    @Actor
    public void actor1() {
        Counter smallCounter = new Counter(8);
        for (int i = 0; i < 256; i++) {
            smallCounter.increment();
        }
        if (!smallCounter.isZero()) {
            ifEqualActor1 = false;
        }
    }
    @Actor
    public void actor2() {
        Counter smallCounter = new Counter(8);
        for (int i = 0; i < 256; i++) {
            smallCounter.increment();
        }
        if (!smallCounter.isZero()) {
            ifEqualActor2 = false;
        }
    }
    @Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}