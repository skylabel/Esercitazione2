package com.intecs.mab;

import java.util.Random;

public class UniformDistribution {

    /*
     * Returns the next pseudorandom,
     * uniformly distributed float value between 0.0 and 1.0 from 
     * this random number generator's sequence.
     */
	
	private Random random;
	
	
	
	public UniformDistribution(Seed seed) {
		if(seed==null) throw new NullPointerException("Seed is null");
		random=new Random(seed.getSeed());   
	}
	
	
	
	
	
	
	public WinProbability sampling() {
	
		return new WinProbability();
		
	}
	
	
	public static UniformDistribution setManualRandom(Seed seed) {
		return new UniformDistribution(seed);
	}

	
	
}
