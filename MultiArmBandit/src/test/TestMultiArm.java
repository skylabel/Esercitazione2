package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

	MultiArm multi;
	
	
	class StubBandit extends Bandit{
		
		private int sequence;
		private int count;
		public StubBandit(int i) {
			sequence=i;
			count=0;
		}
		
		@Override
		public Reward pull() {
			Reward reward = null;
			count++;
			if(count>=sequence)
				reward = new Reward(1);
			else 
				reward = new Reward(0);
			
			return reward;
		}
	}
	
	
	@BeforeEach
	void initialize() {
		   List<Bandit> stubBandits=new ArrayList<>();
		   for(int i=1; i<6; i++)  stubBandits.add(new StubBandit(i));
		   
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
		
	   assertEquals(new CumulativeReward(0), multi.getCumulativeReward());
			multi.pullBandit1();	
			multi.pullBandit2();
			multi.pullBandit1();
			multi.pullBandit2();
			multi.pullBandit2();
			
		assertEquals(new CumulativeReward(4), multi.getCumulativeReward());

	}
	
	@Test
	void testRestMultiArm() throws LastRoundReachedException {  
				
		RoundCounter extepctedRoundCounter= new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		for(int i=1; i<=5;i++) {
			extepctedRoundCounter.increase();
			multi.pullBandit1();	
		}
		
		assertEquals(new CumulativeReward(5), multi.getCumulativeReward());
		assertEquals(extepctedRoundCounter, multi.getCounter());
		multi.reset();
		
		assertEquals(new CumulativeReward(0), multi.getCumulativeReward());
		extepctedRoundCounter.reset();
		assertEquals(extepctedRoundCounter, multi.getCounter());
		
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
