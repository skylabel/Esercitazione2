package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.CumulativeReward;
import com.intecs.tddmab.Reward;

class TestCumulativeReward {

	@Test
	void testAddUnitReward1() {
		Reward reward = new Reward(1);
		CumulativeReward cumulativeReward = new CumulativeReward();
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(1), cumulativeReward.getValue());
	}

	@Test
	void testAddUnitReward2() {
		Reward reward = new Reward(1);
		CumulativeReward cumulativeReward = new CumulativeReward(10);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(11), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward1() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(0);
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward2() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(10);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
	}

	@Test
	void testResetReward() {
		CumulativeReward cumulativeReward = new CumulativeReward(10);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		cumulativeReward.reset();
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
	}
	
	@Test
	void testNegativeConstructorArgument() {
		assertThrows(IllegalArgumentException.class, () -> {new CumulativeReward(-3);});
	}

}
