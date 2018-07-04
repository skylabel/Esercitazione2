package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.OldBandit;
import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.Reward;
import com.intecs.tddmab.WinProbability;

class testBandit {

	@Test
	void testOnePullLoose() {
		OldBandit bandit=new OldBandit();
		Reward expectedReward=new Reward(0);
		
		assertEquals(expectedReward,bandit.pull());
	}

   @Test
   void test3Pull() {
	    OldBandit bandit=new OldBandit();
		assertEquals(new Reward(0),bandit.pull());
		assertEquals(new Reward(0),bandit.pull());
		assertEquals(new Reward(1),bandit.pull());
	}

   @Test
   void testDefaultBandit() {
	   Bandit bandit=new Bandit();
	   assertEquals(new Reward(1),bandit.pull());
	   assertEquals(new Reward(1),bandit.pull());
	   assertEquals(new Reward(1),bandit.pull());
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
