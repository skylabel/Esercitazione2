package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intecs.mab.*;

public class ArchiveDB implements IArchiveDB {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/multiarmbanditdb?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "a.12345678";
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public ArchiveDB() {
    }

    public void register(Player player) throws NullPointerException, SQLException, ClassNotFoundException, PlayerIsAlreadyPresentException {
        if (player.equals(null)) throw new NullPointerException();
        if (isPresent(player)) {
            throw new PlayerIsAlreadyPresentException();
        } else {
            int rate = 0;
            if (player instanceof UniformExplorationPlayer) rate = ((UniformExplorationPlayer) player).getRate();
            String DateToStr = player.getBorn();
            String query = "INSERT INTO player (username, name, strategy, rate, birthdate) VALUES ('" + player.getUserName()
                    + "','" + player.getName() + "','" + player.getStrategyType() + "','" + rate + "','" + DateToStr + "')";
            insertquery(query);
        }
    }

    public void delete(Player player) throws PlayerIsNotPresentException, SQLException, ClassNotFoundException {
        if (player.equals(null)) throw new NullPointerException();
        String query = "DELETE FROM player WHERE username = " + "'" + player.getUserName() + "';";
        if (!isPresent(player)) throw new PlayerIsNotPresentException();
        deleteQuery(query);
    }

    public Player getPlayer(String username) throws SQLException, ClassNotFoundException, PlayerDataCorruptionException, PlayerIsNotPresentException {
        Player player = null;
        String query = "SELECT * FROM player WHERE  username=" + "'" + username + "';";
        ResultSet result = executeQuery(query);
        String usname = result.getString("username");
        String name = result.getString("name");
        String strategy = result.getString("strategy");
        String birthdate = result.getString("birthdate");

        if (strategy.equals("UE")) {
            String rate = result.getString("rate");
            player = new UniformExplorationPlayer(usname, name, birthdate, new ExplorationRate(result.getInt("rate")));
        } else if (strategy.equals("UCB")) {
            player = new UpperConfidenceBound(username, name, birthdate);
        } else throw new PlayerDataCorruptionException();
        return player;
    }


    public boolean isPresent(Player player) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM player WHERE  username=" + "'" + player.getUserName() + "';";
        try {
            executeQuery(query);
        } catch (PlayerIsNotPresentException e) {
            return false;
        }
        return true;
    }

    public void cleanPlayerTable() throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM player";
        getDBConnection();
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
        //closeDbConnection();
    }

    private void closeDbConnection() throws SQLException {
        stmt.close();
        conn.close();
    }


    private void insertquery(String query) {
        try {
            getDBConnection();
            stmt = conn.prepareStatement(query);
            stmt.execute();
           // closeDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteQuery(String query) throws ClassNotFoundException, SQLException {
        getDBConnection();
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
       // closeDbConnection();
    }

    private void getDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ResultSet executeQuery(String query) throws PlayerIsNotPresentException, SQLException, ClassNotFoundException {
        getDBConnection();
        stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        resultIsEmpty(rs);
        rs.next();
        return rs;
    }

    private void resultIsEmpty(ResultSet rs) throws PlayerIsNotPresentException {
        try {
            if (!rs.next()) throw new PlayerIsNotPresentException();
            rs.previous();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
