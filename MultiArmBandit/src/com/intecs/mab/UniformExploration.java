package com.intecs.mab;

import java.util.ArrayList;
import java.util.List;

import math.VectorCalculus;

public class UniformExploration extends Player {

    private ExplorationRate rate;

    public UniformExploration(Username username, String name, String born, ExplorationRate rate) {
        super(username, name, born, "UE");
        this.rate = rate;

    }
    
    public int getRateValue() {
        return rate.getRate();
    }

    public int[] playgame(MultiArm multiArm) {
    	int K = multiArm.getNumberOfBandits();
    	int T = multiArm.getcounterBound();
    	int R = getRateValue();
    	int[] pullSequence = new int[T];
    	double[] sampleMean = new double[K];
    	double[] reward = new double[R];
    	
    	for (int j = 0; j < K; j++) {
    		for (int i = 0; i < R; i++) {
    			try {
    				reward[i] = multiArm.pullBandit(j).getValue();
    				pullSequence[j*R + i] = j;
    			} catch (LastRoundReachedException e) {
    			}
    		}
    		sampleMean[j] = VectorCalculus.sampleMean(reward);
    	}
    	
    	int remainingRounds = T - K*R;
    	int selBandit = VectorCalculus.argMax(sampleMean);
    	for (int t = 0; t < remainingRounds; t++) {
    		pullSequence[K*R + t] = selBandit; 
    	}
    	return pullSequence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UniformExploration other = (UniformExploration) obj;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        return true;
    }

}
