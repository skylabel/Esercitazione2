package com.intecs.mab;

class Pair {
	private Username username;
	private Game game;
	public Pair(Username username, Game game) {
		super();
		this.username = username;
		this.game = game;
	}
	
	public Username getUsername() {
		return username;
	}
	
	public Game getGame() {
		return game;
	}

	
}