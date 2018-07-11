package com.intecs.tddmab;



public class WinProbability {

	public static WinProbability ZERO = new WinProbability(0d);
	public static WinProbability ONE = new WinProbability(1d);
	
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
