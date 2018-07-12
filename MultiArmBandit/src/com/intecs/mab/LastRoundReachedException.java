package com.intecs.mab;

public class LastRoundReachedException extends Exception {

	public LastRoundReachedException() {
		super("All rounds already played. The game is over.");
	}
	
}
