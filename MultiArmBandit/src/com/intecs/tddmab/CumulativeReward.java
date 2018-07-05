package com.intecs.tddmab;

public class CumulativeReward {
	
	private Integer value;
	
	public CumulativeReward() {
		this(0);
	}

	public CumulativeReward(Integer initialValue) {
		value = initialValue;
	}
	
	public void addReward(Reward reward) {
		value = value + reward.getValue();
	}

	public Integer getValue() {
		return value;
	}

	public void reset() {
		value = 0;
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

}
