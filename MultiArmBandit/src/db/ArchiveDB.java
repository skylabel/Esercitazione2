package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intecs.mab.IArchiveDB;

public class ArchiveDB implements IArchiveDB {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/multiarmbanditdb?autoReconnect=true&useSSL=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "a.12345678";
	private Connection conn = null;
	private PreparedStatement stmt = null;

	private static ArchiveDB instance = null;

	private ArchiveDB() {
	}

	public static ArchiveDB getInstance() {
		if (instance == null)
			instance = new ArchiveDB();
		return instance;
	}

	@Override
	public void insert(String query) throws SQLException {
		getDBConnection();
		stmt = conn.prepareStatement(query);
		stmt.execute();

	}

	@Override
	public void deleteQuery(String query) {
		getDBConnection();
		try {
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException();
		}
	}

	private void getDBConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Invalid DB connection: " + JDBC_DRIVER);

		} catch (SQLException e) {
			throw new IllegalStateException("Invalid DB connection: " + DB_URL + " " + "USER" + "/" + "PASS");
		}
	}

	@Override
	public ResultSet executeQuery(String query) {
		getDBConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new IllegalStateException();
		}

		return rs;
	}

	@Override
	public void updateQuery(String query) {

	}

	@Override
	public boolean isPresent(String username) {
		String query = "SELECT * FROM player WHERE  username=" + "'" + username + "';";
		ResultSet result = executeQuery(query);
		boolean res=false;
		try {
			if (result.next())
			    res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void cleanPlayerTable() {
		String query = "DELETE FROM player";
		getDBConnection();
		try {
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
