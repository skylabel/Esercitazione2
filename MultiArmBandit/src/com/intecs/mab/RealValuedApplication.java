package com.intecs.mab;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import com.intecs.mab.exception.IllegalUsernameException;
import com.intecs.mab.exception.LoginRequiredException;
import com.intecs.mab.exception.PlayerIsNotPresentException;
import com.intecs.player.Player;

import db.ArchiveDB;
import math.VectorCalculus;

public class RealValuedApplication {

	private RealValuedMultiArm multiArm;
	private Player currentPlayer;

	public RealValuedApplication() {
		int numberOfRounds = 10000;
		int numberOfBandits = 10;
		multiArm = RealValuedMultiArm.initialize(numberOfBandits, numberOfRounds);
		currentPlayer = null;
	}

	
	public void cleanDB() {
		ArchiveDB.getInstance().cleanPlayerTable();
	}
	
	public Player login(Username username) throws 
		PlayerIsNotPresentException,
		IllegalUsernameException {
		
		this.currentPlayer = Player.findPlayer(username);
		Player player = this.currentPlayer;
		return player;
	}

	public void play() throws LoginRequiredException, IllegalUsernameException {
		if (currentPlayer == null) {
			throw new LoginRequiredException();
		} else {
			int repetition = 50;
			computeRegret(multiArm, repetition, currentPlayer);
		}
	}
	
	public Ranking getRanking() throws IllegalUsernameException  {
		return new Ranking(getPlayerBestGames());
	}

	private double[][] computeRegret(MultiArm multiarm, int rep, Player player) throws IllegalUsernameException {
		Double bestProbability = VectorCalculus.max(multiArm.getMeanList());
		double[] assignedProbs = multiarm.getMeanList();
		int T = multiarm.getCounterBound();
		double[][] regret = new double[rep][T];
		for (int r = 0; r < rep; r++) {
			regret[r][0] = 0d;
			int[] indexBandit = player.playgame(multiarm);
			double temp = 0;
			for (int i = 1; i < T; i++) {
				temp = temp + assignedProbs[indexBandit[i]];
				regret[r][i] = bestProbability * i - temp;
			}
		}
		double[] meanRegret = new double[T];
		double[] xDat = new double[T];
		for (int i = 0; i < T; i++) {
			xDat[i] = i;
			for (int r = 0; r < rep; r++) {
				meanRegret[i] = meanRegret[i] + regret[r][i];
			}
			meanRegret[i] = meanRegret[i] / rep;
		}
		XYChart chart = QuickChart.getChart("Average Regret", "T", "E[R(T)]", "r(t)", xDat, meanRegret);
		new SwingWrapper<XYChart>(chart).displayChart();
		Username username =Username.createUserNameByUser(currentPlayer.getUserName());
		Game game = new Game(username, meanRegret[T - 1], String.valueOf(System.currentTimeMillis()));
		game.saveGame();
		return regret;
	}
	
	private List<Pair> getPlayerBestGames() throws IllegalUsernameException{
		List<Pair> rank = new ArrayList<>();
		Player.findAllPlayers().forEach((Player player)->{
			try {
				rank.add(new Pair(Username.createUserNameByUser(player.getUserName()), player.getBetterGame()));
			} catch (IllegalUsernameException e) {
			}
		});
		
		return rank;
	}

}