package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.LastRoundReachedException;
import com.intecs.tddmab.RoundCounter;

class TestRoundCounter {


	@Test
	void testRoundLimitIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(5);
		
		for(int i=0;i<5;i++) {
			assertEquals(i, counter.getCount());
			counter.increase();
		}
		
	}
	
	@Test
	void testRoundZeroIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(0);
		assertThrows(LastRoundReachedException.class, ()->{counter.increase();});
	}
	
	@Test
	void testRoundIncrease() throws LastRoundReachedException {
		RoundCounter counter=new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		for(int i=0;i<IValues.ROUND_COUNTER_LIMIT; i++) 
			counter.increase();
		assertThrows(LastRoundReachedException.class, ()->{counter.increase();});
	}
	
	@Test
	void testNegativeBound()  {
		assertThrows(IllegalArgumentException.class, ()->{new RoundCounter(-3);});
	}

	@Test
	void testNullBound()  {
		assertThrows(NullPointerException.class, ()->{new RoundCounter(null);});
	}

	@Test
	void testResetCounter() throws LastRoundReachedException  {
		RoundCounter counter = new RoundCounter(IValues.ROUND_COUNTER_LIMIT);
		for(int i=0; i<5; i++) counter.increase();
		assertEquals(5, counter.getCount());
		
		counter.reset();
		assertEquals(0, counter.getCount());
	}
	
}
