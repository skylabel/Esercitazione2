package com.intecs.mab;

public class Bandit {

	private WinProbability prob;
	
	
	public Bandit(WinProbability probability) {
		prob=probability;
	}
	
	
	public Reward pull() {
		
		return new Reward();
	}
}
