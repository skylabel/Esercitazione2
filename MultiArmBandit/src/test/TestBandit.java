package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.Reward;
import com.intecs.tddmab.WinProbability;

class TestBandit {

	
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
	


}
