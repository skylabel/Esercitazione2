package com.intecs.mab;

import org.apache.commons.math3.distribution.BinomialDistribution;

public class BernulliDistribution extends BinomialDistribution {

	public BernulliDistribution() {
		this(1, 1);
	}
	
	public BernulliDistribution(WinProbability p) {
		this(1, p.getProbability());
	}
	
	private BernulliDistribution(int trials, double p) {
		super(trials, p);
	}

	public Reward getSample() {
		Reward reward = new Reward(super.sample());
		return reward;
	}
	
}
