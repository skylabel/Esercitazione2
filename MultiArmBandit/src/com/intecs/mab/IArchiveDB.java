package com.intecs.mab;

import db.PlayerDataCorruptionException;

import java.sql.SQLException;

public interface IArchiveDB {

    public void register(Player player) throws SQLException, ClassNotFoundException, PlayerIsAlreadyPresentException;
    public void delete(Player player) throws PlayerIsNotPresentException, SQLException, ClassNotFoundException;
    public boolean isPresent(Player player) throws SQLException, ClassNotFoundException;
    public void cleanPlayerTable() throws ClassNotFoundException, SQLException;
    public Player getPlayer(String username) throws SQLException, ClassNotFoundException, PlayerDataCorruptionException, PlayerIsNotPresentException;

}
