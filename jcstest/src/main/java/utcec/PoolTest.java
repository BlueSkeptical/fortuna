package utcec;

import com.grunka.random.fortuna.Pool;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class PoolTest {
    private Boolean ifEqualActor1, ifEqualActor2;
    private Pool pool;
    public PoolTest () {
        pool = new Pool();
        ifEqualActor1 = true;
		ifEqualActor2 = true;
    }
    @Actor
    public void actor1() {
        if (pool.size() != 0) {
            ifEqualActor1 = false;
        }
        pool.add(255, "Hello".getBytes());
        if (pool.size() != 7) {
            ifEqualActor1 = false;
        }
    }
    @Actor
    public void actor2() {
        if (pool.size() != 0) {
            ifEqualActor2 = false;
        }
        pool.add(255, "Hello".getBytes());
        if (pool.size() != 7) {
            ifEqualActor2 = false;
        }
    }
    @Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
