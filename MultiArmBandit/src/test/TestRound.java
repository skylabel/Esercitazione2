package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.test.LastRoundReachedException;
import com.intecs.test.RoundCounter;

class TestRound {

	@Test
	void testRoundSingleIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(10);
		counter.increase();

		assertEquals(1, counter.getCount());
	}
	@Test
	void testRoundLimitIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(5);
		counter.increase();
		assertEquals(1, counter.getCount());
		counter.increase();
		assertEquals(2, counter.getCount());
		counter.increase();
		assertEquals(3, counter.getCount());
		counter.increase();
		assertEquals(4, counter.getCount());
		counter.increase();
		assertEquals(5, counter.getCount());
	}
	
	@Test
	void testRoundZeroIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(0);
		assertThrows(LastRoundReachedException.class, ()->{counter.increase();});
	}
	
	
	@Test
	void testNegativeBound()  {
		RoundCounter counter=new RoundCounter(-3);
		assertThrows(IllegalArgumentException.class, ()->{counter.increase();});
	}
	
	

}
