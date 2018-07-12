package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.mab.CumulativeReward;
import com.intecs.mab.Reward;

class TestCumulativeReward {
	private CumulativeReward cumulativeReward() {
		CumulativeReward cumulativeReward = new CumulativeReward();
		return cumulativeReward;
	}

	private void assertRewardValue(int i, CumulativeReward cumulativeReward) {
		assertEquals(Double.valueOf(i), cumulativeReward.getValue());
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
		assertEquals(Double.valueOf(0), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Double.valueOf(1), cumulativeReward.getValue());
	}
	
	@Test
	void testAddUnitReward2() {
		
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Double.valueOf(10), cumulativeReward.getValue());
		
		cumulativeReward.addReward(new Reward(1));
		assertEquals(Double.valueOf(11), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward1() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(0d);
		assertEquals(Double.valueOf(0), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Double.valueOf(0), cumulativeReward.getValue());
	}

	@Test
	void testAddZeroReward2() {
		Reward reward = new Reward(0);
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Double.valueOf(10), cumulativeReward.getValue());
		cumulativeReward.addReward(reward);
		assertEquals(Double.valueOf(10), cumulativeReward.getValue());
	}

	@Test
	void testResetReward() {
		CumulativeReward cumulativeReward = new CumulativeReward(10d);
		assertEquals(Double.valueOf(10d), cumulativeReward.getValue());
		
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
