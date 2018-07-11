package com.intecs.tddmab;

public class Seed {
	
private Integer value;
	
	public Seed(Integer s) {
		if(s<0) throw new IllegalArgumentException();
		value=s;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
}
