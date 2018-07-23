package com.intecs.mab;

import com.intecs.mab.exception.LastRoundReachedException;

public abstract class MultiArm {
    
    private Bandit[] bandits;
    private CumulativeReward cumulativeReward;
    private RoundCounter roundCounter;
       
    public MultiArm(Bandit[] bandits, RoundCounter rounds) {
        this.bandits = bandits;
        cumulativeReward = new CumulativeReward(0d);
        roundCounter = rounds;    	
    }
    
    public int getNumberOfBandits() {
        return this.bandits.length;
    }

    public Integer getCounterValue() {
        return roundCounter.getCount();
    }

    public Integer getCounterBound() {
        return roundCounter.getCounterBound();
    }
    
    double[] getMeanList() {
        double[] means = new double[bandits.length];
        for (int i = 0; i < bandits.length; i++) {
            means[i] = bandits[i].getMean();
        }
        return means;
    }

    private Reward pullSelectedBandit(Bandit bandit) throws LastRoundReachedException {
        Reward reward = null;
        roundCounter.increase();
        reward = bandit.pull();
        cumulativeReward.addReward(reward);
        return reward;
    }

    public Reward pullBandit(Integer idx) throws LastRoundReachedException {
        return pullSelectedBandit(bandits[idx]);
    }

}
