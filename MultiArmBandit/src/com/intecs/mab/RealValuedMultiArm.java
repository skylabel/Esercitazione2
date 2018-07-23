package com.intecs.mab;

import java.util.Random;

public class RealValuedMultiArm extends MultiArm {

	public RealValuedMultiArm(TruncatedGaussianBandit[] bandits, RoundCounter rounds) {
		super(bandits, rounds);
    }
    
	public static RealValuedMultiArm initialize(int K, int T) {
		double[] assignedMeans = new double[K];
		double[] assignedStds = new double[K];
		Random random = new Random(3);
		for (int i = 0; i < K; i++) {
			assignedMeans[i] = random.nextDouble();
			assignedStds[i] = random.nextDouble();
		}
		TruncatedGaussianBandit[] bandits = new TruncatedGaussianBandit[assignedMeans.length];
		for (int i = 0; i < bandits.length; i++) 
			bandits[i] = new TruncatedGaussianBandit(assignedMeans[i], assignedStds[i]);
		RoundCounter rounds = new RoundCounter(T);
		return new RealValuedMultiArm(bandits, rounds);
	}
	
}
