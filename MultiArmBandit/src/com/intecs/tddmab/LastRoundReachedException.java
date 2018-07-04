package com.intecs.tddmab;

public class LastRoundReachedException extends Exception {

	public LastRoundReachedException() {
		super("All rounds already played. The game is over.");
	}
	
}
