package com.intecs.player;

import com.intecs.mab.MultiArm;
import com.intecs.mab.Username;
import com.intecs.mab.exception.LastRoundReachedException;

import math.VectorCalculus;

public class UpperConfidenceBound extends Player {


    public UpperConfidenceBound(Username username, String name, String born) {
        super(username, name, born, "UCB");
    }
    
    @Override
    public int[] playgame(MultiArm multiArm) {
    	int K = multiArm.getNumberOfBandits();
    	int T = multiArm.getCounterBound();
        double sampleMean[] = new double[K];
        double upperConfidenceBounds[] = new double[K];
        int[] pullSequence = new int[T];
        int numberOfPulls[] = new int[multiArm.getNumberOfBandits()];

    	double reward = 0;
        for (int j = 0; j < K; j++) {
            try {
                reward = multiArm.pullBandit(j).getIntegerValue();
                pullSequence[j] = j;
            } catch (LastRoundReachedException e) {
            }
            sampleMean[j] = reward;
            numberOfPulls[j] = numberOfPulls[j] + 1;
        }

        int remaingRounds = T - K;
        double radius;
        int bestBanditIdx;
        for (int j = 0; j < remaingRounds; j++) {
            for (int i = 0; i < K; i++) {
                radius = Math.sqrt(2 * Math.log(T) / (numberOfPulls[i]));
                upperConfidenceBounds[i] = sampleMean[i] + radius;
            }
            bestBanditIdx = VectorCalculus.argMax(upperConfidenceBounds);
            pullSequence[K+j] = bestBanditIdx;
            try {
                reward = multiArm.pullBandit(bestBanditIdx).getIntegerValue();
            } catch (LastRoundReachedException e) {
            }
            numberOfPulls[bestBanditIdx] = numberOfPulls[bestBanditIdx] + 1;
            sampleMean[bestBanditIdx] = sampleMean[bestBanditIdx] + (reward - sampleMean[bestBanditIdx]) / numberOfPulls[bestBanditIdx];
        }
        return pullSequence;
    }

}
