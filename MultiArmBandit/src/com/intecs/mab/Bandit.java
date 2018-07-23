package com.intecs.mab;

public abstract class Bandit {
	
	public abstract Reward pull();

	abstract double getMean();

}
