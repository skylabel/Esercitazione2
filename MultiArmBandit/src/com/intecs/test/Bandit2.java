package com.intecs.test;

import java.util.function.Supplier;

public class Bandit2 {

	
	int count=0;
	
	public Reward2 pull() {
		Reward2 reward = null;
		if(count==2)
			reward = new Reward2(true);
		else 
			reward = new Reward2(false);
		count++;
		return reward;
	}
}
