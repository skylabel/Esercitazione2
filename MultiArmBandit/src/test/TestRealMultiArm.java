package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import com.intecs.mab.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRealMultiArm {

	MultiArm multiarm;
	Bandit[] bandits;
	@BeforeEach
	void initialize() {
		bandits = new Bandit[5];
		bandits[0] = new Bandit(new WinProbability(0.3));
		bandits[1] = new Bandit(new WinProbability(0.5));
		bandits[2] = new Bandit(new WinProbability(0.4));
		bandits[3] = new Bandit(new WinProbability(0.7));
		bandits[4] = new Bandit(new WinProbability(0.9));
		RoundCounter rounds = new RoundCounter(1000);
		multiarm=new MultiArm(bandits, rounds);
	}

	@Test
	void testCounter() throws LastRoundReachedException {
		for(int i=0; i<bandits.length; i++) {
			multiarm.pullBandit(i);
		}
		assertTrue(5 == multiarm.getCounterValue());
	}
	
	@Test
	void testSetNumberofRoundsInGame() throws LastRoundReachedException {
		multiarm.pullBandit(1);
		assertTrue(1 ==  multiarm.getCounterValue());
		assertThrows(StillInGameException.class, ()->{multiarm.setNumberOfRound(IValues.ROUND_COUNTER_LIMIT);});	
	}
	
	@Test
	void testSetNumberofRoundsBeforeStartingToPlay() throws StillInGameException {
		assertTrue(1000 == multiarm.getcounterBound());
		multiarm.setNumberOfRound(100);
		assertTrue(100 == multiarm.getcounterBound());
	}

	@Test
	void testResetMultiArm() throws LastRoundReachedException {
		for(int i=0; i<bandits.length; i++) {
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
		RoundCounter rounds = new RoundCounter(bandits.length);
		multiarm=new MultiArm(bandits, rounds);
		assertTrue(multiarm.getCounterValue()==0);
		for(int i=0; i<bandits.length;i++) {
				multiarm.pullBandit(i);
		}
		assertThrows(LastRoundReachedException.class, ()->{multiarm.pullBandit(1);});
		multiarm.reset();
		assertTrue(multiarm.getCounterValue()==0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
