package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.intecs.mab.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		   Bandit[] stubBandits=new Bandit[5];
		   for(int i=0; i<5; i++)  stubBandits[i] = new StubBandit(i+1);
		   RoundCounter rounds=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		   multi=new MultiArm(stubBandits,rounds);
	}
	
//	void initialize2() {
//		List<Bandit> stubBandits=new ArrayList<>();
//
//		for(int i=0; i<5; i++) {
//			StubBandit stubBandit = new StubBandit();
//			stubBandit.setFirstWinRound(i+1);
//
//			stubBandits.add(stubBandit);
//		}
//
//		RoundCounter rounds=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
//		multi=new MultiArm(stubBandits,rounds);
//	}
	
	@Test
	void testCounter() throws LastRoundReachedException {
		assertTrue(0 == multi.getCounterValue());
		for(int i=0; i<IValues.ROUND_COUNTER_LIMIT; i++) {
			multi.pullBandit(1);
		}
		assertTrue(IValues.ROUND_COUNTER_LIMIT == multi.getCounterValue());

	}
	
	@Test
	void testSetNumberofRoundsInGame() throws LastRoundReachedException {
		assertTrue(0 == multi.getCounterValue());
		multi.pullBandit(1);
		assertTrue(1 == multi.getCounterValue());
		assertThrows(StillInGameException.class, ()->{multi.setNumberOfRound(IValues.ROUND_COUNTER_LIMIT);});	
	}

	@Test
	void testSetNumberofRoundsBeforeStartingToPlay() throws StillInGameException {
		assertTrue(IValues.ROUND_COUNTER_LIMIT == multi.getcounterBound());
		multi.setNumberOfRound(100);
		assertTrue(100 ==  multi.getcounterBound());
	}
	
//	@Test
//	void testGainUpdateWithMultiBandit() throws LastRoundReachedException {
//	   assertTrue(0d == multi.getCumulativeRewardValue());
//	   multi.pullBandit(1);
//	   multi.pullBandit(2);
//	   multi.pullBandit(1);
//	   multi.pullBandit(2);
//	   multi.pullBandit(2);
//	   assertTrue(4d == multi.getCumulativeRewardValue());
//	}
	
	@Test
	void testResetMultiArm() throws LastRoundReachedException {  

		assertTrue(multi.getCounterValue() == 0);
		assertTrue(multi.getCumulativeRewardValue() == 0);
		multi.pullBandit(1);
		multi.pullBandit(1);
		multi.pullBandit(1);
		multi.pullBandit(1);
		multi.pullBandit(1);
		assertTrue(multi.getCounterValue() > 0);
		assertTrue(multi.getCumulativeRewardValue() > 0);
		multi.reset();
		assertTrue(multi.getCounterValue() == 0);
		assertTrue(multi.getCumulativeRewardValue() == 0);
	}
	
//	@Test
//	void testRestBeforeStartingToPlay() throws LastRoundReachedException {
//		assertTrue(IValues.ROUND_COUNTER_LIMIT== multi.getcounterBound());
//		multi.reset();
//		assertTrue(0 == multi.getCounterValue());
//	}
	
	@Test
	void testRestAtTheEnd() throws LastRoundReachedException{  
		assertTrue(0 == multi.getCounterValue());
		for(int i=0; i<IValues.ROUND_COUNTER_LIMIT;i++) {
				multi.pullBandit(1);
		}
		assertThrows(LastRoundReachedException.class, ()->{multi.pullBandit(1);});
		multi.reset();
		assertTrue(0 == multi.getCounterValue());
		
	}
	
}
