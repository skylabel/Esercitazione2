package com.intecs.mab;

public class UniformDistribution extends Distribution{

    public UniformDistribution(Seed seed) {
		super(seed);
	}
	
	public WinProbability sampling() {
		
		return new WinProbability(getRandom().nextFloat());
		
	}

	
}
