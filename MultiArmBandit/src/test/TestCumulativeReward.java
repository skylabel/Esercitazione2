package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.CumulativeReward;
import com.intecs.tddmab.Reward;

class TestCumulativeReward {
	private CumulativeReward cumulativeReward() {
		CumulativeReward cumulativeReward = new CumulativeReward();
		return cumulativeReward;
	}

	private void assertRewardValue(int i, CumulativeReward cumulativeReward) {
		assertEquals(Integer.valueOf(i), cumulativeReward.getValue());
	}

	private Reward reward(int i) {
		return new Reward(i);
	}

	@Test
	void test() {
		CumulativeReward cumulativeReward = cumulativeReward();

		assertRewardValue(0, cumulativeReward);
		
		cumulativeReward.addReward(reward(1));
		
		assertRewardValue(1, cumulativeReward);
	}

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
		
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		
		cumulativeReward.addReward(new Reward(1));
		assertEquals(Integer.valueOf(11), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward1() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(0d);
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(0), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward2() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
	}

	@Test
	void testResetReward() {
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Integer.valueOf(10), cumulativeReward.getValue());
		
		cumulativeReward.reset();
		assertTrue(cumulativeReward.isZero());
	}
	
	@Test
	void testNegativeConstructorArgument() {
		assertThrows(IllegalArgumentException.class, () -> {new CumulativeReward(-3d);});
	}
	
	@Test
	void testNullConstructorArgument() {
		assertThrows(NullPointerException.class, () -> {new CumulativeReward(null);});
	}

}
