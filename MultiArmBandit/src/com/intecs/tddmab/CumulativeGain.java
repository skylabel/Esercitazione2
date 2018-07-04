package com.intecs.tddmab;

public class CumulativeGain {
	
	private Integer value;
	
	public CumulativeGain() {
		this(0);
	}

	public CumulativeGain(Integer initialValue) {
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
        if(! (obj instanceof CumulativeGain)) return false;
        
        boolean result = false;
        CumulativeGain re2=(CumulativeGain)obj;
        if(re2.value==this.value) result=true;
        return result;
	}

}
