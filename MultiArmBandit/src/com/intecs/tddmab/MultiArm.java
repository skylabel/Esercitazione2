package com.intecs.tddmab;

import java.util.ArrayList;
import java.util.List;

public class MultiArm {

	private List<Bandit> bandits;
	private CumulativeReward cumulativeReward;
	private RoundCounter roundCounter;
	
	public MultiArm() {
		UniformDistribution unif = new UniformDistribution();
		bandits=new ArrayList<>();
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		cumulativeReward = new CumulativeReward(0);
		roundCounter = new RoundCounter(10);
	}
	
	public MultiArm(List<Bandit> bandits,RoundCounter rounds) {
		this.bandits = bandits;
		cumulativeReward = new CumulativeReward(0);
		roundCounter = rounds;
	}

	public RoundCounter getCounter() {
		return roundCounter;
	}
	
	public void setNumberOfRound(Integer count) throws StillInGameException {
		if (!roundCounter.isZero())
			throw new StillInGameException();
		roundCounter.setValue(count);
	}
	
	private Reward pullSelectedBandit(Bandit bandit) throws LastRoundReachedException {
		Reward reward = null;
			roundCounter.increase();
			reward = bandit.pull();
			cumulativeReward.addReward(reward);
	
		return reward;
	}
	
	public Reward pullBandit1() throws LastRoundReachedException {
		return pullSelectedBandit(bandits.get(0));
	}
	
	public Reward pullBandit2() throws LastRoundReachedException {
		return pullSelectedBandit(bandits.get(1));
	}
	
	public Reward pullBandit3() throws LastRoundReachedException {
		return pullSelectedBandit(bandits.get(2));
	}
	
	public Reward pullBandit4() throws LastRoundReachedException {
		return pullSelectedBandit(bandits.get(3));
	}
	
	public Reward pullBandit5() throws LastRoundReachedException {
		return pullSelectedBandit(bandits.get(4));
	}

	public CumulativeReward getCumulativeReward() {
		return cumulativeReward;
	}

	public void reset() {
		roundCounter.reset();
		cumulativeReward.reset();
	}

}
