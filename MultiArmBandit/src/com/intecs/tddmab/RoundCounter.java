package com.intecs.tddmab;

public class RoundCounter {

	
	private Integer numberOfRounds;
	private Integer count;
	
	public RoundCounter(Integer i) {
		if(i < 0)
			throw new IllegalArgumentException("The selected bound is negative.");
		
		numberOfRounds = i;
		count = 0;
	}

	public RoundCounter(Integer initialValue, Integer bound) {
		numberOfRounds = bound;
		count = initialValue;
	}

	public int getCount() {
		return count;
	}
	
	public void increase() throws LastRoundReachedException {
		if(count==numberOfRounds) 
			throw new LastRoundReachedException();
		
		count++;
	}

	public void reset() {
		setValue(0);
	}

	public void setValue(Integer count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if(! (obj instanceof RoundCounter)) return false;
        
        boolean result = false;
        RoundCounter rc=(RoundCounter)obj;
        if(rc.count==this.count && rc.numberOfRounds==this.numberOfRounds) result=true;
        return result;
	}
	
}
