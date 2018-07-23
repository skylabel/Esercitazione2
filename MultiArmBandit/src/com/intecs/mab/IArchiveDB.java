package com.intecs.mab;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intecs.mab.exception.PlayerIsNotPresentException;

public interface IArchiveDB {

    public boolean isPresent(String username) throws SQLException, ClassNotFoundException;
    public void cleanPlayerTable();

    void insert(String query) throws SQLException;
    void deleteQuery(String query) throws ClassNotFoundException, SQLException;
    ResultSet executeQuery(String query) throws PlayerIsNotPresentException, SQLException, ClassNotFoundException;
    void updateQuery(String query);
}
