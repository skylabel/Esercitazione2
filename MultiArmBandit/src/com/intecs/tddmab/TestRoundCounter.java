package com.intecs.tddmab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestRoundCounter {

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
		assertThrows(IllegalArgumentException.class, ()->{new RoundCounter(-3);});
	}

	@Test
	void testNullBound()  {
		assertThrows(NullPointerException.class, ()->{new RoundCounter(null);});
	}

	@Test
	void testResetCounter()  {
		RoundCounter counter = new RoundCounter(3, 10);
		counter.reset();
		assertEquals(0, counter.getCount());
	}
	
}
