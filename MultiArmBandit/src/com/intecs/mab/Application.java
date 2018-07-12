package com.intecs.mab;

import db.PlayerDataCorruptionException;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import db.ArchiveDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class Application {

    private MultiArm multiArm;
    private IArchiveDB archive;
    private Player currentPlayer;

    public Application(IArchiveDB archive) {
        int numberOfRounds = 10000;
        int numberOfBandits = 50;
        double[] assignedProbs = initializeProbablity(numberOfBandits);
        multiArm = initializeMultiArm(numberOfRounds, assignedProbs);
        this.archive = archive;
        currentPlayer = null;
    }

    public Player login(String username) throws SQLException, PlayerDataCorruptionException,
            ClassNotFoundException, PlayerIsNotPresentException {
        this.currentPlayer = archive.getPlayer(username);
        Player player = this.currentPlayer;
        return player;
    }

    public void registerUser(Player player) throws PlayerIsAlreadyPresentException, SQLException, ClassNotFoundException {
        archive.register(player);
    }

    public void play() throws LoginRequiredException {
        if (currentPlayer == null) {
            throw new LoginRequiredException();
        } else {
            int repetition = 50;
            computeRegret(multiArm, repetition, currentPlayer);
        }
    }

    public void unregisterPlayer(Player player) throws PlayerIsNotPresentException, SQLException, ClassNotFoundException {
        archive.delete(player);
    }

    private double[][] computeRegret(MultiArm multiarm, int rep, Player player) {
        Double bestProbability = max(multiArm.getProbabilityList());
        double[] assignedProbs = multiarm.getProbabilityList();
        int T = multiarm.getcounterBound();
        double[][] regret = new double[rep][T];
        for (int r = 0; r < rep; r++) {
            regret[r][0] = 0d;
            List<Integer> indexBandit = player.playgame(multiarm);
            double temp = 0;
            for (int i = 1; i < T; i++) {
                temp = temp + assignedProbs[indexBandit.get(i)];
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
        XYChart chart = QuickChart.getChart("Mean Regret", "T", "E[R(T)]", "r(t)", xDat, meanRegret);
        new SwingWrapper<XYChart>(chart).displayChart();
        return regret;
    }

    private double[] initializeProbablity(int K) {
        double[] assignedProbs = new double[K];
        Random random = new Random(3);
        for (int i = 0; i < K; i++)
            assignedProbs[i] = random.nextDouble();
        return assignedProbs;
    }

    private double max(double[] v) {
        int max = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[max] < v[i]) {
                max = i;
            }
        }
        return v[max];
    }

    private MultiArm initializeMultiArm(int T, double[] assignedProbs) {
        Bandit[] bandits;
        bandits = initializeBandits(assignedProbs);
        RoundCounter rounds = new RoundCounter(T);
        return new MultiArm(bandits, rounds);
    }

    private Bandit[] initializeBandits(double[] assignedProbs) {
        Bandit[] bandits = new Bandit[assignedProbs.length];
        for (int i = 0; i < bandits.length; i++) {
            bandits[i] = new Bandit(new WinProbability(assignedProbs[i]));
        }
        return bandits;
    }

}
