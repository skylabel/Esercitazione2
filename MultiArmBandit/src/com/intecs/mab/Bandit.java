package com.intecs.mab;

public class Bandit {

	private BernulliDistribution mu;
	
	
	public Bandit(BernulliDistribution mu) {
		this.mu=mu;
	}
	
	
	public Reward pull() {
		
		return new Reward(false);
	}
}
