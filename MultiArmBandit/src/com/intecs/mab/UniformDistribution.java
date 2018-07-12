package com.intecs.mab;

import java.util.Random;

public class UniformDistribution {

	private Random random;
	
	public UniformDistribution() {
		random= new Random();   
	}

	public UniformDistribution(Seed seed) {
		if(seed==null) throw new NullPointerException("Seed is null");
		random= new Random(seed.getValue());   
	}
	
	public WinProbability getSample() {
		return new WinProbability(random.nextDouble());
	}
}
