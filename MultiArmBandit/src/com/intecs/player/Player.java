package com.intecs.player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.intecs.mab.Game;
import com.intecs.mab.MultiArm;
import com.intecs.mab.Username;
import com.intecs.mab.exception.ElementIsAlreadyPresentException;
import com.intecs.mab.exception.IllegalUsernameException;
import com.intecs.mab.exception.PlayerIsNotPresentException;

import db.ArchiveDB;
import db.PlayerDataCorruptionException;

public abstract class Player {

	private Username username;
	private String name;
	private String birthdate;
	private String strategyType;

	public Player(Username username, String playerName, String birthdate, String strategyType) {
		if (playerName.equals(null) || birthdate.equals(null) || strategyType.equals(null))
			throw new NullPointerException();
		this.username = username;
		this.birthdate = birthdate;
		this.name = playerName;
		this.strategyType = strategyType;
	}

	public abstract int[] playgame(MultiArm multiArm);

	public String getUserName() {
		return username.getValue();
	}

	public String getName() {
		return name;
	}

	public String getStrategyType() {
		return strategyType;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void register() throws ElementIsAlreadyPresentException {
		int rate = 0;
		if (this instanceof UniformExploration)
			rate = ((UniformExploration) this).getRateValue();
		String DateToStr = getBirthdate();
		String query = "INSERT INTO player (username, name, strategy, rate, birthdate) VALUES ('" + getUserName()
				+ "','" + getName() + "','" + getStrategyType() + "','" + rate + "','" + DateToStr + "')";
		try {
			ArchiveDB.getInstance().insert(query);
		} catch (SQLIntegrityConstraintViolationException e) {
			
			throw new ElementIsAlreadyPresentException("Utente già registrato. Error code " + e.getErrorCode());
		} catch (SQLException e) {

		}
	}

	public void delete(String username) throws PlayerIsNotPresentException {
		if (username.equals(null))
			throw new NullPointerException();
		String query = "DELETE FROM player WHERE username = " + "'" + username + "';";
		if (!ArchiveDB.getInstance().isPresent(username))
			throw new PlayerIsNotPresentException();
		ArchiveDB.getInstance().deleteQuery(query);
	}

	public List<Game> getPlayedGameByUser() throws IllegalUsernameException {
		List<Game> games = new ArrayList<>();
		String query = new String("SELECT * FROM game WHERE id_player='" + username.getValue() + "'");
		ResultSet records = ArchiveDB.getInstance().executeQuery(query);
		double score ;
		String date ;
		try {
			while (records.next()) {
				String id_player;
				try {
					id_player = records.getString("id_player");
					 score = records.getDouble("score");
					 date = records.getString("date");
				}  catch (SQLException e) {
					throw new IllegalStateException("Invalid Column name");
				}
				Game game = new Game(Username.createUserNameByUser(id_player), score, date);
				games.add(game);
			}
		} catch (SQLException e) {
			throw new IllegalStateException();
		}
		return games;
	}

	public Game getBetterGame() throws IllegalUsernameException {
		List<Game> games = getPlayedGameByUser();
		Game bettergame = games.stream().min(Comparator.comparing(Game::getScore))
				.orElseThrow(NoSuchElementException::new);
		return bettergame;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player) o;
		return Objects.equals(username, player.username) && Objects.equals(name, player.name)
				&& Objects.equals(birthdate, player.birthdate) && Objects.equals(strategyType, player.strategyType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, name, birthdate, strategyType);
	}

	private static Player buildPlayer(ResultSet result) throws IllegalUsernameException {
		Player player = null;
		String username;
		try {
			username = result.getString("username");
			String name = result.getString("name");
			String strategy = result.getString("strategy");
			String birthdate = result.getString("birthdate");
			Username user = Username.getUserNamefromDB(username);

			if (strategy.equals("UE")) {
				player = new UniformExploration(user, name, birthdate, new ExplorationRate(result.getInt("rate")));

			} else if (strategy.equals("UCB")) {
				player = new UpperConfidenceBound(user, name, birthdate);

			} else if (strategy.equals("EpsG")) {
				player = new EpsilonGreedy(user, name, birthdate);

			} else
				throw new PlayerDataCorruptionException();
		} catch (SQLException e) {
			throw new IllegalStateException("Invalid Column name");
		}
		return player;
	}

	public static Player findPlayer_(Username username)
			throws PlayerIsNotPresentException, IllegalUsernameException {
		String query = "SELECT * FROM player WHERE  username=" + "'" + username.getValue() + "';";
		ResultSet result;
		if (ArchiveDB.getInstance().isPresent(username.getValue())) {
			 result = ArchiveDB.getInstance().executeQuery(query);
			return buildPlayer(result); 
		} else throw new PlayerIsNotPresentException();
	}

	public static Player findPlayer(Username username) throws PlayerIsNotPresentException, IllegalUsernameException {
		String query = "SELECT * FROM player WHERE username=" + "'" + username.getValue() + "';";
		ResultSet resultSet;
		Player result = null;
		try {
			resultSet = ArchiveDB.getInstance().executeQuery(query);
			if (!resultSet.next())
				throw new PlayerIsNotPresentException();
			result = buildPlayer(resultSet);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		return result;
	}

	public static List<Player> findAllPlayers() throws IllegalUsernameException {
		List<Player> players = new ArrayList<>();
		String query = new String("SELECT * FROM player");
		ResultSet result = ArchiveDB.getInstance().executeQuery(query);
		try {
			while (result.next())
				players.add(buildPlayer(result));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

}
