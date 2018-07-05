package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.Reward;
import com.intecs.tddmab.WinProbability;

class testBandit {

	
	@Test
	void testDefaultWinnerBandit() {
		Bandit bandit=new Bandit();
		assertEquals(new Reward(1),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
	}

	
	@Test
	void testBanditNullArgument() {
		assertThrows(NullPointerException.class, ()->{new Bandit(null);});
	}
	
	@Test
	void testRealBandit() {
		WinProbability p = new WinProbability(0.8d);
		Bandit bandit=new Bandit(p);
		Reward reward = bandit.pull();
		System.out.println(reward.getValue());
		reward = bandit.pull();
		System.out.println(reward.getValue());
	}

}
