package com.intecs.mab;

import java.util.ArrayList;
import java.util.List;

public class UniformExplorationPlayer extends Player {


    private ExplorationRate rate;

    public UniformExplorationPlayer(Username username, String name, String born, ExplorationRate rate) {
        super(username, name, born, "UE");
        this.rate = rate;

    }

    @Override
    public List<Integer> playgame(MultiArm multiArm) {
        List<Double> sampleMean = new ArrayList<>();
        List<Integer> pullSequence = new ArrayList<>();


        for (int j = 0; j < multiArm.getNumberOfBandits(); j++) {

            List<Reward> rewards = new ArrayList<>();
            for (int i = 0; i < rate.getRate(); i++) {
                try {
                    rewards.add(multiArm.pullBandit(j));
                    pullSequence.add(j);
                } catch (LastRoundReachedException e) {
                }
            }
            sampleMean.add(cumputeSampleMean(rewards));

        }

        int selBandit = argMax(sampleMean);

        int remainingRounds = multiArm.getcounterBound() - (multiArm.getNumberOfBandits() * rate.getRate());

        for (int t = 0; t < remainingRounds; t++) {
            try {
                multiArm.pullBandit(selBandit);
                pullSequence.add(selBandit);
            } catch (LastRoundReachedException e) {
            }
        }

        return pullSequence;

    }


    @Override
    public void reset(MultiArm multiArm) {
        multiArm.reset();
    }

    private Double cumputeSampleMean(List<Reward> rewards) {
        Double sum, mean;
        sum = 0d;
        for (Reward reward : rewards) {
            sum = reward.getValue() + sum;
        }
        mean = sum / rewards.size();
        return mean;
    }


    private int argMax(List<Double> v) {
        int max = 0;

        for (int i = 0; i < v.size(); i++) {
            if (v.get(max) < v.get(i)) {
                max = i;
            }
        }
        return max;

    }

    public int getRate() {
        return rate.getRate();
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
        UniformExplorationPlayer other = (UniformExplorationPlayer) obj;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        return true;
    }


}
