package com.intecs.mab.exception;

public class LastRoundReachedException extends Exception {

	public LastRoundReachedException() {
		super("All rounds already played. The game is over.");
	}
	
}
