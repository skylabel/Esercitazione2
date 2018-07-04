package com.intecs.tddmab;

import java.util.ArrayList;
import java.util.List;

public class MultiArm {

	private List<Bandit> bandits;
	private CumulativeGain currentGain;
	private RoundCounter counter;
	private boolean inGame;
	
	public MultiArm() {
		UniformDistribution unif = new UniformDistribution();
		bandits=new ArrayList<>();
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		bandits.add(new Bandit(unif.getSample()));
		currentGain = new CumulativeGain(0);
		counter = new RoundCounter(10);
		inGame = true;
	}
	
	public RoundCounter getCounter() {
		return counter;
	}
	
	public void setCounter(Integer count) throws StillInGameException {
		if (inGame)
			throw new StillInGameException();
		counter.setValue(count);
	}
	
	private Reward pullSelectedArm(Bandit bandit) {
		Reward reward = null;
		try {
			counter.increase();
			reward = bandit.pull();
			currentGain.addReward(reward);
		} catch (LastRoundReachedException e) {
			inGame = false;
		}
		return reward;
	}
	
	public Reward choseArm1() throws LastRoundReachedException {
		return pullSelectedArm(bandits.get(0));
	}
	
	public Reward choseArm2() throws LastRoundReachedException {
		return pullSelectedArm(bandits.get(1));
	}
	
	public Reward choseArm3() throws LastRoundReachedException {
		return pullSelectedArm(bandits.get(2));
	}
	
	public Reward choseArm4() throws LastRoundReachedException {
		return pullSelectedArm(bandits.get(3));
	}
	
	public Reward choseArm5() throws LastRoundReachedException {
		return pullSelectedArm(bandits.get(4));
	}

	public CumulativeGain getGain() {
		return currentGain;
	}

}
