package com.intecs.tddmab;



public class WinProbability {

	private Double probability;
	
	public WinProbability() {
         this.probability=1d;
	}

	public WinProbability(Double p) {
		if(p<0 || p>1) throw new IllegalArgumentException();
	   
		this.probability=p;
	}	
	
	public Double getProbability() {
		return probability;
	}
	
}
