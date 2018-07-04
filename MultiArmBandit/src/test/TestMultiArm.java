package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.CumulativeGain;
import com.intecs.tddmab.LastRoundReachedException;
import com.intecs.tddmab.MultiArm;
import com.intecs.tddmab.OldMultiArm;
import com.intecs.tddmab.Reward;
import com.intecs.tddmab.RoundCounter;

class TestMultiArm {

	@Test
	void testBandit1Loose() {
     OldMultiArm multi=new OldMultiArm();   
     
     Reward actualreward=multi.choseArm1();
     assertEquals(new Reward(0),actualreward);
	}
	
	@Test
	void testBandit1Win1() {
		OldMultiArm multi=new OldMultiArm();   
     
		assertEquals(new Reward(0),multi.choseArm1());
		assertEquals(new Reward(0),multi.choseArm1());
		assertEquals(new Reward(1),multi.choseArm1());
	}

	@Test
	void testBandit1Win2() {
		OldMultiArm multi=new OldMultiArm();   
		
		assertEquals(new Reward(0),multi.choseArm1());
		assertEquals(new Reward(0),multi.choseArm1());
		assertEquals(new Reward(1),multi.choseArm1());
	}

	@Test
	void testGainUpdate() {
		OldMultiArm multi=new OldMultiArm();   
		
		multi.choseArm1();
		assertEquals(new CumulativeGain(0), multi.getGain());
		multi.choseArm1();
		assertEquals(new CumulativeGain(0), multi.getGain());
		multi.choseArm1();
		assertEquals(new CumulativeGain(1), multi.getGain());
		multi.choseArm1();
		assertEquals(new CumulativeGain(2), multi.getGain());
	}
	
	@Test
	void testCounter() throws LastRoundReachedException {
		MultiArm multi = new MultiArm();   
		multi.choseArm1();
		assertEquals(new RoundCounter(1,10),multi.getCounter());
//		assertEquals(new Reward(0),multi.choseArm1());
//		assertEquals(new Reward(1),multi.choseArm1());
	}

	
}
