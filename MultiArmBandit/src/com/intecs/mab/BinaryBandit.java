package com.intecs.mab;

import math.BernulliDistribution;

public class BinaryBandit extends Bandit {
	
	private BernulliDistribution ber;
	
	public BinaryBandit() {
		this(new WinProbability(1d));
	}

	public BinaryBandit(WinProbability p) {
		if(p==null) throw new NullPointerException("Bandit con argomento NULL");
		ber = new BernulliDistribution(p);
	}

	public Reward pull() {
		return ber.getSample();
	}

	double getMean() {
		return ber.getProbabilityOfSuccess();
	}

	
}
