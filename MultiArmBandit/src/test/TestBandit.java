package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.intecs.mab.BinaryBandit;
import com.intecs.mab.Reward;

class TestBandit {

	
	@Test
	void testDefaultWinnerBandit() {
		BinaryBandit bandit=new BinaryBandit();
		assertEquals(new Reward(1),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
	}

	
	@Test
	void testBanditNullArgument() {
		assertThrows(NullPointerException.class, ()->{new BinaryBandit(null);});
	}
	


}
