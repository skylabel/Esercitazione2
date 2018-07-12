package com.intecs.mab;

public class MultiArm {

    private Bandit[] bandits;
    private CumulativeReward cumulativeReward;
    private RoundCounter roundCounter;

    public MultiArm() {
        UniformDistribution unif = new UniformDistribution();
        bandits = new Bandit[5];
        bandits[0] = new Bandit(unif.getSample());
        bandits[1] = new Bandit(unif.getSample());
        bandits[2] = new Bandit(unif.getSample());
        bandits[3] = new Bandit(unif.getSample());
        bandits[4] = new Bandit(unif.getSample());
        cumulativeReward = new CumulativeReward(0d);
        roundCounter = new RoundCounter(1000);
    }

    public MultiArm(Bandit[] bandits, RoundCounter rounds) {
        this.bandits = bandits;
        cumulativeReward = new CumulativeReward(0d);
        roundCounter = rounds;
    }

    double[] getProbabilityList() {
        double[] probabilities = new double[bandits.length];
        for (int i = 0; i < bandits.length; i++) {
            probabilities[i] = bandits[i].getWinProbability();
        }
        return probabilities;
    }

    public Integer getCounterValue() {
        return roundCounter.getCount();
    }

    public Integer getcounterBound() {
        return roundCounter.getCounterBound();
    }

    public void setNumberOfRound(Integer count) throws StillInGameException {
        if (!roundCounter.isZero())
            throw new StillInGameException();
        roundCounter.setBound(count);
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

    public Double getCumulativeRewardValue() {
        return this.cumulativeReward.getValue();
    }

    public void reset() {
        roundCounter.reset();
        cumulativeReward.reset();
    }

    public int getNumberOfBandits() {
        return this.bandits.length;
    }

}
