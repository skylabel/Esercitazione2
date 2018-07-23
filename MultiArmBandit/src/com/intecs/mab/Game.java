package com.intecs.mab;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import db.ArchiveDB;

public class Game   {

    private Username id_player;
    private double score;
    private String date;

    public Game(Username id_player, double score, String date) {
        if(id_player.equals(null) || date.equals(null)) throw new NullPointerException();
        this.id_player = id_player;
        this.score = score;
        this.date = date;
    }

    public Username getId_player() {
        return id_player;
    }

    public double getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    
    public void saveGame() {
		if (this.equals(null))
			throw new NullPointerException();
		String query = "INSERT INTO game (id_player, score, date) VALUES ('" + getId_player().getValue() + "','"
				+ ((int) getScore()) + "','" + getDate() + "')";
		try {
			ArchiveDB.getInstance().insert(query);
		} catch (SQLIntegrityConstraintViolationException e) {
		} catch (SQLException e) {
		}
	}
    
    
	@Override
	public String toString() {
		return "Game [id_player=" + id_player.getValue()+ ", score=" + score + ", date=" + date + "]";
	}

}
