package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.CumulativeGain;
import com.intecs.tddmab.Reward;

class TestCumulativeGain {

	@Test
	void testAddUnitReward1() {
		Reward reward = new Reward(1);
		CumulativeGain gain = new CumulativeGain();
		gain.addReward(reward);
		assertEquals((Integer) 1, gain.getValue());
	}

	@Test
	void testAddUnitReward2() {
		Reward reward = new Reward(1);
		CumulativeGain gain = new CumulativeGain(10);
		gain.addReward(reward);
		assertEquals((Integer) 11, gain.getValue());
	}

	@Test
	void testAddZeroReward1() {
		Reward reward = new Reward(0);
		CumulativeGain gain = new CumulativeGain(0);
		gain.addReward(reward);
		assertEquals((Integer) 0, gain.getValue());
	}

	@Test
	void testAddZeroReward2() {
		Reward reward = new Reward(0);
		CumulativeGain gain = new CumulativeGain(10);
		gain.addReward(reward);
		assertEquals((Integer) 10, gain.getValue());
	}

	@Test
	void testResetGain() {
		CumulativeGain gain = new CumulativeGain(10);
		gain.reset();
		assertEquals((Integer) 0, gain.getValue());
	}

}
