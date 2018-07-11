package test;


import java.util.ArrayList;
import java.util.List;

import com.intecs.mab.Player;


public class Archive {

	private List<Player> players;
	
	public Archive() {
		players = new ArrayList<>();
	}
	
	
	
	public void register(Player player) throws PlayerIsAllReadyPresentException, NullPointerException {
		if(player.equals(null)) throw new NullPointerException();
	    if(!playerIsPresent(player)) {
	    	players.add(player);
	    } else throw new PlayerIsAllReadyPresentException();
	}
	
	private boolean playerIsPresent(Player player){	
		return  players.contains(player);
	}
	
	List<Player> getPlayers(){
		return this.players;
	}

	public void delete(Player player) throws PlayerIsNotPresentException {
		if(player.equals(null)) throw new NullPointerException();
		if(playerIsPresent(player)) {
		    	players.remove(player);
		} else throw new PlayerIsNotPresentException();		
	}

	

}
