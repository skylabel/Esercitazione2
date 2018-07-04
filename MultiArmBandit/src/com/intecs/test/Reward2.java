package com.intecs.test;

public class Reward2 {

	
	private boolean value;
	
	public Reward2(boolean r) {
	value=r;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if(! (obj instanceof Reward2)) return false;
        
        boolean result = false;
        Reward2 re2=(Reward2)obj;
        if(re2.value==this.value) result=true;
        return result;
		
	}
	
	
}
