package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.intecs.tddmab.BernulliDistribution;
import com.intecs.tddmab.WinProbability;

class TestBernulliDistribution {

	@Test
	void testHypothesisTesting() {
		WinProbability p = new WinProbability(0.8);
		BernulliDistribution ber = new BernulliDistribution(p);
	}

}
