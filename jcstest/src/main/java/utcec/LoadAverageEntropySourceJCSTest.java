package utcec;
import com.grunka.random.fortuna.entropy.LoadAverageEntropySource;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class LoadAverageEntropySourceJCSTest {
	private LoadAverageEntropySource target;
    private int adds;
	private Boolean ifEqualActor1, ifEqualActor2;

	public LoadAverageEntropySourceJCSTest() {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
		this.target = new LoadAverageEntropySource();
        this.adds = 0;
	}

	@Actor
	public void actor1() {
		this.target.event(event -> {
			if (2 != event.length) {
				this.ifEqualActor1 = false;
			}
            this.adds++;
        });
		if (1 != adds) {
			this.ifEqualActor1 = false;
		}
	}

	@Actor
	public void actor2() {
		this.target.event(event -> {
			if (2 != event.length) {
				this.ifEqualActor2 = false;
			}
            this.adds++;
        });
		if (1 != adds) {
			this.ifEqualActor2 = false;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
