package com.intecs.mab;

import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CumulativeReward that = (CumulativeReward) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {

		return Objects.hash(value);
	}
	//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) return true;
//        if(! (obj instanceof CumulativeReward)) return false;
//
//        boolean result = false;
//        CumulativeReward re2=(CumulativeReward)obj;
//        if(re2.value==this.value) result=true;
//        return result;
//	}

	public boolean isZero() {
		return value == 0;
	}

}
