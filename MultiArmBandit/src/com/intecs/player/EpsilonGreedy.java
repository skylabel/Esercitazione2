package com.intecs.player;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.distribution.BinomialDistribution;

import com.intecs.mab.MultiArm;
import com.intecs.mab.Username;
import com.intecs.mab.exception.LastRoundReachedException;

import math.VectorCalculus;

public class EpsilonGreedy extends Player {

    public EpsilonGreedy(Username username, String name, String born) {
        super(username, name, born, "EpsG");
    }
    
    @Override
    public int[] playgame(MultiArm multiArm) {
    	int K = multiArm.getNumberOfBandits();
    	int T = multiArm.getCounterBound();
        double sampleMean[] = new double[K];
        int[] pullSequence = new int[T];
        int numberOfPulls[] = new int[multiArm.getNumberOfBandits()];
        double epsilon;
    	int armIdx;
    	int reward = 0;
        for (int i = 0; i < T; i++) {
        	epsilon = (Math.pow((double) i, (double)-1/3) * Math.pow((double)K * Math.log(i),(double) 1/3));
        	if (epsilon > 1) 
        		epsilon = 1;
        	BinomialDistribution ber = new BinomialDistribution(1, epsilon);
			if (ber.sample() == 1)
        		armIdx = ThreadLocalRandom.current().nextInt(0, K-1);
			else 
        		armIdx = VectorCalculus.argMax(sampleMean);
			pullSequence[i] = armIdx;
			try {
				reward = multiArm.pullBandit(armIdx).getIntegerValue();
			} catch (LastRoundReachedException e) {
			}
            numberOfPulls[armIdx] = numberOfPulls[armIdx] + 1;
            sampleMean[armIdx] = sampleMean[armIdx] + (reward - sampleMean[armIdx]) / numberOfPulls[armIdx];
        }
        return pullSequence;
    }        
    
}
