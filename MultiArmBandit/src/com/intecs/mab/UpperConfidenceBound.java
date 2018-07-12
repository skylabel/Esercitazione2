package com.intecs.mab;

import java.util.ArrayList;

import java.util.List;

public class UpperConfidenceBound extends Player {


    public UpperConfidenceBound(String username, String name, String born) {
        super(username, name, born, "UCB");
    }

    @Override
    public List<Integer> playgame(MultiArm multiArm) {

        double probabilityEstimation[] = new double[multiArm.getNumberOfBandits()];
        double upperConfidenceBounds[] = new double[multiArm.getNumberOfBandits()];
        List<Integer> pullSequence = new ArrayList<>();

        int numberOfPulls[] = new int[multiArm.getNumberOfBandits()];

        Reward reward = null;
        for (int j = 0; j < multiArm.getNumberOfBandits(); j++) {
            try {
                reward = multiArm.pullBandit(j);
                pullSequence.add(j);
            } catch (LastRoundReachedException e) {
            }
            probabilityEstimation[j] = (double) reward.getValue();
            numberOfPulls[j] = numberOfPulls[j] + 1;
        }

        int remaingRounds = multiArm.getcounterBound() - multiArm.getNumberOfBandits();
        double radius;
        int bestBanditIdx;
        for (int j = 0; j < remaingRounds; j++) {
            for (int i = 0; i < multiArm.getNumberOfBandits(); i++) {
                radius = Math.sqrt(2 * Math.log(multiArm.getcounterBound()) / (numberOfPulls[i]));
                upperConfidenceBounds[i] = probabilityEstimation[i] + radius;
            }
            bestBanditIdx = argMax(upperConfidenceBounds);
            pullSequence.add(bestBanditIdx);
            try {
                reward = multiArm.pullBandit(bestBanditIdx);
            } catch (LastRoundReachedException e) {
            }
            numberOfPulls[bestBanditIdx] = numberOfPulls[bestBanditIdx] + 1;
            probabilityEstimation[bestBanditIdx] = probabilityEstimation[bestBanditIdx] + ((double) reward.getValue() - probabilityEstimation[bestBanditIdx]) / numberOfPulls[bestBanditIdx];
        }
        return pullSequence;
    }

    @Override
    public void reset(MultiArm multiArm) {
    }

    private int argMax(double[] v) {
        int max = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[max] < v[i]) {
                max = i;
            }
        }
        return max;
    }


}
