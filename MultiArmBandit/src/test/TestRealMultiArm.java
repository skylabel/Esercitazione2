package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.mab.BinaryBandit;
import com.intecs.mab.BinaryValuedMultiArm;
import com.intecs.mab.RoundCounter;
import com.intecs.mab.WinProbability;
import com.intecs.mab.exception.LastRoundReachedException;

class TestRealMultiArm {

	BinaryValuedMultiArm multiarm;
	BinaryBandit[] bandits;
	@BeforeEach
	void initialize() {
		bandits = new BinaryBandit[5];
		bandits[0] = new BinaryBandit(new WinProbability(0.3));
		bandits[1] = new BinaryBandit(new WinProbability(0.5));
		bandits[2] = new BinaryBandit(new WinProbability(0.4));
		bandits[3] = new BinaryBandit(new WinProbability(0.7));
		bandits[4] = new BinaryBandit(new WinProbability(0.9));
		RoundCounter rounds = new RoundCounter(1000);
		multiarm=new BinaryValuedMultiArm(bandits, rounds);
	}

	@Test
	void testCounter() throws LastRoundReachedException {
		for(int i=0; i<bandits.length; i++) {
			multiarm.pullBandit(i);
		}
		assertTrue(5 == multiarm.getCounterValue());
	}

}