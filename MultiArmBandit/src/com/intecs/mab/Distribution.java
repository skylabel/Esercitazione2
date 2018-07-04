package com.intecs.mab;

import java.util.Random;

public abstract class Distribution {

	private Random random;
	
	
	public Distribution(Seed seed) {
		if(seed==null) throw new NullPointerException("Seed is null");
		random= new Random(seed.getValue());   
	}
	
	abstract public WinProbability sampling();
		
	protected Random getRandom() {
		return random;
	}
	
}
