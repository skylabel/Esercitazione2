package com.intecs.test;

public class RoundCounter {

	
	private int countLimit;
	private int count;
	
	public RoundCounter(int i) {
      if()
		
		countLimit=i;
		count=0;
	}

	
	public int getCount() {
		return count;
	}
	
	
	public void increase() throws LastRoundReachedException {
		
		if(count==countLimit) throw new LastRoundReachedException();
		count++;
	}
	
}
