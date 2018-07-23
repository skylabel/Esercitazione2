package com.intecs.mab;

import math.InvalidMeanException;
import math.TruncatedGaussian;

public class TruncatedGaussianBandit extends Bandit {
	
	private TruncatedGaussian tn;
	
	public TruncatedGaussianBandit() {
	}

	public TruncatedGaussianBandit(double mean, double std) {
		try {
			tn = TruncatedGaussian.createTruncatedGaussion(mean, std);
		} catch (InvalidMeanException e) {
			throw new IllegalStateException("Negative values for the std are not admitted.");
		}
	}

	public Reward pull() {
		return tn.getSample();
	}

	double getMean() {
		return tn.getMean();
	}

}
