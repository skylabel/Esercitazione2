package com.intecs.mab;

public class Reward {

	private Integer value;
	
	public Reward(Integer i) {
		if (i!=0 && i!=1)
			throw new IllegalArgumentException();
		
		value = i;
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

	public Integer getValue() {
		return value;
	}
	
	
}
