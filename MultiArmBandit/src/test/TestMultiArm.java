package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.mab.BinaryBandit;
import com.intecs.mab.BinaryValuedMultiArm;
import com.intecs.mab.Reward;
import com.intecs.mab.RoundCounter;
import com.intecs.mab.exception.LastRoundReachedException;

class TestMultiArm {

	private BinaryValuedMultiArm multi;
	
	class StubBandit extends BinaryBandit{
		
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
		   BinaryBandit[] stubBandits=new BinaryBandit[5];
		   for(int i=0; i<5; i++)  stubBandits[i] = new StubBandit(i+1);
		   RoundCounter rounds=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		   multi=new BinaryValuedMultiArm(stubBandits,rounds);
	}
		
	@Test
	void testCounter() throws LastRoundReachedException {
		assertTrue(0 == multi.getCounterValue());
		for(int i=0; i<IValues.ROUND_COUNTER_LIMIT; i++) {
			multi.pullBandit(1);
		}
		assertTrue(IValues.ROUND_COUNTER_LIMIT == multi.getCounterValue());

	}
	
}
