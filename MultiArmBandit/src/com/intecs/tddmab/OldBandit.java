package com.intecs.tddmab;

public class OldBandit {

	int count=0;

	public Reward pull() {
		Reward reward = null;
		if(count>=2)
			reward = new Reward(1);
		else 
			reward = new Reward(0);
		count++;
		return reward;
	}
	
}
