package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.CumulativeReward;
import com.intecs.tddmab.LastRoundReachedException;
import com.intecs.tddmab.MultiArm;
import com.intecs.tddmab.RoundCounter;
import com.intecs.tddmab.StillInGameException;
import com.intecs.tddmab.WinProbability;

class TestRealMultiArm {

	MultiArm multiarm;
	List<Bandit> bandits;
	@BeforeEach
	void initialize() {
		bandits=new ArrayList<>();
		bandits.add(new Bandit(new WinProbability(0.3)));
		bandits.add(new Bandit(new WinProbability(0.5)));
		bandits.add(new Bandit(new WinProbability(0.4)));
		bandits.add(new Bandit(new WinProbability(0.7)));
		bandits.add(new Bandit(new WinProbability(0.9)));
		
		RoundCounter rounds = new RoundCounter(1000);
		multiarm=new MultiArm(bandits, rounds);
		
	}
	
	
	
	@Test
	void testCounter() throws LastRoundReachedException { 
		RoundCounter extepctedRoundCounter= new RoundCounter(1000);
		for(int i=0; i<bandits.size(); i++) {
			multiarm.pullBandit(i);
			extepctedRoundCounter.increase();
		}
		assertEquals(extepctedRoundCounter,multiarm.getCounter());

	}
	
	
	@Test
	void testSetNumberofRoundsInGame() throws LastRoundReachedException {
		multiarm.pullBandit(1);
		RoundCounter extepctedRoundCounter= new RoundCounter(1000);
		extepctedRoundCounter.increase();
		assertEquals(extepctedRoundCounter, multiarm.getCounter());
		
		assertThrows(StillInGameException.class, ()->{multiarm.setNumberOfRound(IValues.ROUND_COUNTER_LIMIT);});	
	}
	
	@Test
	void testSetNumberofRoundsBeforeStartingToPlay() throws StillInGameException {
		assertEquals(new RoundCounter(1000), multiarm.getCounter());
		multiarm.setNumberOfRound(100);
		RoundCounter extepctedRoundCounter = new RoundCounter(100);
		assertEquals(extepctedRoundCounter, multiarm.getCounter());
	}

	
	
	@Test
	void testResetMultiArm() throws LastRoundReachedException {  
		
		for(int i=0; i<bandits.size(); i++) {
			multiarm.pullBandit(i);	
		}
		
		assertTrue(multiarm.getCumulativeRewardValue()>0);
		assertTrue(multiarm.getCounterValue()>0);
		multiarm.reset();
		
		assertTrue(multiarm.getCumulativeRewardValue()==0d);
		assertTrue(multiarm.getCounterValue()==0);
		
	}
	
	@Test
	void testRestBeforeStartingToPlay() throws LastRoundReachedException {  
		assertTrue(multiarm.getCounterValue()==0);
		
		multiarm.reset();
		assertTrue(multiarm.getCounterValue()==0);
	}
	
	@Test
	void testRestAtTheEnd() throws LastRoundReachedException{  
		
		RoundCounter rounds = new RoundCounter(bandits.size());
		multiarm=new MultiArm(bandits, rounds);
		assertTrue(multiarm.getCounterValue()==0);
		for(int i=0; i<bandits.size();i++) {
				multiarm.pullBandit(i);
		}
		assertThrows(LastRoundReachedException.class, ()->{multiarm.pullBandit(1);});
	
		multiarm.reset();
		
		assertTrue(multiarm.getCounterValue()==0);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
