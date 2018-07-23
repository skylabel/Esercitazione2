package com.intecs.mab;

public class Reward {

	private double value;
	
	public Reward(double i) {
		value = i;
	}
	
	public static Reward createIntegerReward(int i) {
		if (i!=0 && i!=1)
			throw new IllegalArgumentException();
		return new Reward((double) i);
	}

	public static Reward createRealReward(double i) {
		if (i<0 || i>1)
			throw new IllegalArgumentException();
		return new Reward((double) i);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if(! (obj instanceof Reward)) return false;
        
        boolean result = false;
        Reward re2=(Reward)obj;
        if(re2.value==this.value) result=true;
        return result;
	}

	public Integer getIntegerValue() {
		return (int)value;
	}
}
