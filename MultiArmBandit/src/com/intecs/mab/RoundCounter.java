package com.intecs.mab;

public class RoundCounter {
	
	private Integer numberOfRounds;
	private Integer count;
	
	public RoundCounter(Integer i) {
		if(i < 0)
			throw new IllegalArgumentException("The selected bound is negative.");
		
		numberOfRounds = i;
		count = 0;
	}


	public int getCount() {
		return count;
	}
	
	public void increase() throws LastRoundReachedException {
		if(count==numberOfRounds) 
			throw new LastRoundReachedException();
		
		count++;
	}

	public boolean isZero() {
		return (count==0);
	}
	
	public void reset() {
		this.count=0;
	}

	public void setBound(Integer count) {
		this.numberOfRounds = count;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	    result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((numberOfRounds == null) ? 0 : numberOfRounds.hashCode()) ;
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)return false;
        if(! (obj instanceof RoundCounter)) return false;
        
        boolean result = false;
        RoundCounter rc=(RoundCounter)obj;
        boolean t = (rc.count.equals(this.count));
        boolean m = (rc.numberOfRounds.equals(this.numberOfRounds));
        if( t && m) result=true;
        return result;
	}


	public Integer getCounterBound() {
		return numberOfRounds;
	}
	
}
