package utcec;

import com.grunka.random.fortuna.entropy.MemoryPoolEntropySource;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;


//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class MemoryPoolEntropySourceTest {
    private Boolean ifEqualActor1, ifEqualActor2;
    private MemoryPoolEntropySource target;
    private int adds;
    public MemoryPoolEntropySourceTest () {
        ifEqualActor1 = true;
		ifEqualActor2 = true;
        target = new MemoryPoolEntropySource();
        adds = 0;
    }
    @Actor
    public void actor1() {
        target.event(event -> {
            if (2 != event.length) {
                ifEqualActor1 = false;
            }
            adds++;
        });
        if (1 != adds) {
            ifEqualActor1 = false;
        }
    }
    @Actor
    public void actor2() {
        target.event(event -> {
            if (2 != event.length) {
                ifEqualActor2 = false;
            }
            adds++;
        });
        if (1 != adds) {
            ifEqualActor2 = false;
        }
    }
    @Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}


