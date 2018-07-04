package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.mab.Bandit;
import com.intecs.test.Bandit2;
import com.intecs.test.Reward2;

class testBandit {

	@Test
	void testOnePullLoose() {
		Bandit2 bandit=new Bandit2();
		Reward2 expectedReward=new Reward2(false);
		
		assertEquals(expectedReward,bandit.pull());
	}

   @Test
   void test3Pull() {
	    Bandit2 bandit=new Bandit2();
		assertEquals(new Reward2(false),bandit.pull());
		assertEquals(new Reward2(false),bandit.pull());
		assertEquals(new Reward2(true),bandit.pull());
	}
   
	
	
}
