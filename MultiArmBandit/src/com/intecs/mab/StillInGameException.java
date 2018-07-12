package com.intecs.mab;

public class StillInGameException extends Exception {
	
	public StillInGameException() {
		super("There are still other rounds to play. The game is not over.");
	}

}
