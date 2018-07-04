package com.intecs.mab;



public class WinProbability {


	
	private Float probability;
	
	
	public WinProbability() {
         this.probability=1f;
	}

	public WinProbability(float nextFloat) {
		if(nextFloat<0 || nextFloat>1) throw new IllegalArgumentException();
	   
		this.probability=nextFloat;
	}	
	
	public Float getProbability() {
		return probability;
	}
	
	
	
	
}
