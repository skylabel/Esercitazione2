package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.mab.Reward;

class TestReward {

	@Test
	void testNegativeValue() {
		assertThrows(IllegalArgumentException.class, () -> {new Reward(-3);});
	}
	
	@Test
	void testNullValue() {
		assertThrows(NullPointerException.class, () -> {new Reward(null);});
	}

}
