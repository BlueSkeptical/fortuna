package utcec;

import com.grunka.random.fortuna.Counter;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class CounterTest4 {
    private Boolean ifEqualActor1, ifEqualActor2;
    public CounterTest4 () {
        ifEqualActor1 = true;
		ifEqualActor2 = true;
    }
    @Actor
    public void actor1() {
        Counter counter = new Counter(128);
        if (!counter.isZero()) {
            ifEqualActor1 = false;
        }
    }
    @Actor
    public void actor2() {
        Counter counter = new Counter(128);
        if (!counter.isZero()) {
            ifEqualActor2 = false;
        }
    }
    @Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}