package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.tddmab.OldBandit;
import com.intecs.tddmab.Reward;

class testOldBandit {
	
	OldBandit bandit;
	
	@BeforeEach
	void initialize() {
		bandit = new OldBandit();
		
	}
	
	
	@Test
	void testOnePullLoose() {
		Reward expectedReward=new Reward(0);
		assertEquals(expectedReward,bandit.pull());
	}

   @Test
   void testLooseLooseWinPull() {
	  
		assertEquals(new Reward(0),bandit.pull());
		assertEquals(new Reward(0),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
	}


}
