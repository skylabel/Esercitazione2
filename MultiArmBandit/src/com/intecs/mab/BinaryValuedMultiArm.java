package com.intecs.mab;

import java.util.Random;

public class BinaryValuedMultiArm extends MultiArm {

    public BinaryValuedMultiArm(BinaryBandit[] bandits, RoundCounter rounds) {
		super(bandits, rounds);
    }
    
	public static BinaryValuedMultiArm initialize(int K, int T) {
		double[] assignedProbs = new double[K];
		Random random = new Random(3);
		for (int i = 0; i < K; i++)
			assignedProbs[i] = random.nextDouble();
		BinaryBandit[] bandits = new BinaryBandit[assignedProbs.length];
		for (int i = 0; i < bandits.length; i++) 
			bandits[i] = new BinaryBandit(new WinProbability(assignedProbs[i]));
		RoundCounter rounds = new RoundCounter(T);
		return new BinaryValuedMultiArm(bandits, rounds);
	}

}
