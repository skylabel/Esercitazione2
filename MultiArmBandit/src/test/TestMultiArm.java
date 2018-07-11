package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.CumulativeReward;
import com.intecs.tddmab.LastRoundReachedException;
import com.intecs.tddmab.MultiArm;
import com.intecs.tddmab.Reward;
import com.intecs.tddmab.RoundCounter;
import com.intecs.tddmab.StillInGameException;

class TestMultiArm {

	private MultiArm multi;
	
	
	class StubBandit extends Bandit{
		
		private int firstWinRound;
		private int count;
		
		public StubBandit(int firstWinRound) {
			this.firstWinRound=firstWinRound;
			count=0;
		}
		
		public StubBandit() {
			this(1);
		}

		@Override
		public Reward pull() {
			Reward reward = null;
			count++;
			if(count>=firstWinRound)
				reward = new Reward(1);
			else 
				reward = new Reward(0);
			
			return reward;
		}

		public void setFirstWinRound(int i) {
			this.firstWinRound = i;
			
		}
	}
	
	
	@BeforeEach
	void initialize() {
		   List<Bandit> stubBandits=new ArrayList<>();
		   for(int i=0; i<5; i++)  stubBandits.add(new StubBandit(i+1));
		   
		   RoundCounter rounds=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		   multi=new MultiArm(stubBandits,rounds);
	}
	
	void initialize2() {
		List<Bandit> stubBandits=new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			StubBandit stubBandit = new StubBandit();
			stubBandit.setFirstWinRound(i+1);
			
			stubBandits.add(stubBandit);
		}
		
		RoundCounter rounds=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		multi=new MultiArm(stubBandits,rounds);
	}
	
	
	@Test
	void testCounter() throws LastRoundReachedException { 
		RoundCounter extepctedRoundCounter= new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		for(int i=0; i<IValues.ROUND_COUNTER_LIMIT; i++) {
			multi.pullBandit1();
			extepctedRoundCounter.increase();
		}
		assertEquals(extepctedRoundCounter,multi.getCounter());

	}
	
	@Test
	void testSetNumberofRoundsInGame() throws LastRoundReachedException {
		multi.pullBandit1();
		RoundCounter extepctedRoundCounter= new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		extepctedRoundCounter.increase();
		assertEquals(extepctedRoundCounter, multi.getCounter());
		
		assertThrows(StillInGameException.class, ()->{multi.setNumberOfRound(IValues.ROUND_COUNTER_LIMIT);});	
	}

	
	@Test
	void testSetNumberofRoundsBeforeStartingToPlay() throws StillInGameException {
		assertEquals(new RoundCounter(IValues.ROUND_COUNTER_LIMIT), multi.getCounter());
		multi.setNumberOfRound(100);
		RoundCounter extepctedRoundCounter = new RoundCounter(100);
		assertEquals(extepctedRoundCounter, multi.getCounter());
	}
	
	
	@Test
	void testGainUpdateWithMultiBandit() throws LastRoundReachedException {
		
	   assertEquals(new CumulativeReward(0d), multi.getCumulativeReward());
			multi.pullBandit1();	
			multi.pullBandit2();
			multi.pullBandit1();
			multi.pullBandit2();
			multi.pullBandit2();
			
		assertEquals(new CumulativeReward(4d), multi.getCumulativeReward());

	}
	
	@Test
	void testResetMultiArm() throws LastRoundReachedException {  

		assertTrue(multi.getCounter().getCount() == 0);
		assertTrue(multi.getCumulativeReward().getValue() == 0);
		
		multi.pullBandit1();
		multi.pullBandit1();
		multi.pullBandit1();
		multi.pullBandit1();
		multi.pullBandit1();
		
		assertTrue(multi.getCounter().getCount() > 0);
		assertTrue(multi.getCumulativeReward().getValue() > 0);
		
		multi.reset();

		assertTrue(multi.getCounter().getCount() == 0);
		assertTrue(multi.getCumulativeReward().getValue() == 0);
	}
	
	@Test
	void testRestBeforeStartingToPlay() throws LastRoundReachedException {  
		RoundCounter extepctedRoundCounter= new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		assertEquals(extepctedRoundCounter, multi.getCounter());
		
		multi.reset();
		assertEquals(extepctedRoundCounter, multi.getCounter());
		
	}
	
	@Test
	void testRestAtTheEnd() throws LastRoundReachedException{  
		RoundCounter extepctedRoundCounter = new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		assertEquals(extepctedRoundCounter, multi.getCounter());
		for(int i=0; i<IValues.ROUND_COUNTER_LIMIT;i++) {
				extepctedRoundCounter.increase();
				multi.pullBandit1();
			
		}
		assertThrows(LastRoundReachedException.class, ()->{multi.pullBandit1();});
	
		multi.reset();
		extepctedRoundCounter.reset();
		assertEquals(extepctedRoundCounter, multi.getCounter());
		
	}
	
}
