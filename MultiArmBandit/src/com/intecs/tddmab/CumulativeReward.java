package com.intecs.tddmab;

public class CumulativeReward {
	
	private Double value;
	
	public CumulativeReward() {
		this(0d);
	}

	public CumulativeReward(Double initialValue) {
		if(initialValue==null) throw new NullPointerException();
		if(initialValue<0) throw new IllegalArgumentException("Negative Construcctor argumet");
		value = initialValue;
	}
	
	public void addReward(Reward reward) {
		value = value + reward.getValue();
	}

	public Double getValue() {
		return value;
	}

	public void reset() {
		value = 0d;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if(! (obj instanceof CumulativeReward)) return false;
        
        boolean result = false;
        CumulativeReward re2=(CumulativeReward)obj;
        if(re2.value==this.value) result=true;
        return result;
	}

	public boolean isZero() {
		return value == 0;
	}

}
