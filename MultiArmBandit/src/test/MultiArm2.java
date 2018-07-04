package test;

import java.util.ArrayList;
import java.util.List;

import com.intecs.test.Bandit2;
import com.intecs.test.Reward2;

public class MultiArm2 {

	
	private List<Bandit2> bandits;
	
	public MultiArm2() {
		bandits=new ArrayList<>();
		bandits.add(new Bandit2());
		bandits.add(new Bandit2());
		bandits.add(new Bandit2());
		bandits.add(new Bandit2());
		bandits.add(new Bandit2());
	}
	
	
	private Reward2 pullSelectedArm(Bandit2 bandit) {
        
		return bandit.pull();
	}
	public Reward2 choseArm1() {
		
		return pullSelectedArm(bandits.get(0));
	}
	public Reward2 choseArm2() {
		
		return pullSelectedArm(bandits.get(1));
	}
	public Reward2 choseArm3() {
		
		return pullSelectedArm(bandits.get(2));
	}
	public Reward2 choseArm4() {
		
		return pullSelectedArm(bandits.get(3));
	}
	public Reward2 choseArm() {
		
		return pullSelectedArm(bandits.get(4));
	}

	
}
