package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.CumulativeReward;
import com.intecs.tddmab.Reward;

class TestCumulativeGain {

	@Test
	void testAddUnitReward1() {
		Reward reward = new Reward(1);
		CumulativeReward gain = new CumulativeReward();
		gain.addReward(reward);
		assertEquals((Integer) 1, gain.getValue());
	}

	@Test
	void testAddUnitReward2() {
		Reward reward = new Reward(1);
		CumulativeReward gain = new CumulativeReward(10);
		gain.addReward(reward);
		assertEquals(Integer.valueOf(11), gain.getValue());
	}

	@Test
	void testAddZeroReward1() {
		Reward reward = new Reward(0);
		CumulativeReward gain = new CumulativeReward(0);
		gain.addReward(reward);
		assertEquals((Integer) 0, gain.getValue());
	}

	@Test
	void testAddZeroReward2() {
		Reward reward = new Reward(0);
		CumulativeReward gain = new CumulativeReward(10);
		gain.addReward(reward);
		assertEquals((Integer) 10, gain.getValue());
	}

	@Test
	void testResetGain() {
		CumulativeReward gain = new CumulativeReward(10);
		assertEquals(Integer.valueOf(10), gain.getValue());
		gain.reset();
		assertEquals(Integer.valueOf(0), gain.getValue());
	}

}
