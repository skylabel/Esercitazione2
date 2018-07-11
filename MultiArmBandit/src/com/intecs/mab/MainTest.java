package com.intecs.mab;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import com.intecs.tddmab.Bandit;
import com.intecs.tddmab.MultiArm;
import com.intecs.tddmab.RoundCounter;
import com.intecs.tddmab.WinProbability;

public class MainTest {

	public static void main(String[] args) {
		
		List<Double> assignedProbs;
		int numberOfRounds=1000;
		int numberOfBandits=10;
		assignedProbs = initializeProbablity(numberOfBandits);
		
		MultiArm multiarm = initializeMultiArm(numberOfRounds,assignedProbs);
		
		Double bestProbability = max(assignedProbs);
		
		ExplorationRate rate=new ExplorationRate(50);
		GregorianCalendar birthdate=new GregorianCalendar(1987,12,24);
		Player uniformPlayer=new UniformExplorationPlayer("Pippo",birthdate,rate);
		Player upperConfidenceBound = new UpperConfidenceBound("Pluto", birthdate);
		

		int rep = 50;
		computeRegret(assignedProbs, multiarm, bestProbability, rep, uniformPlayer);
		computeRegret(assignedProbs, multiarm, bestProbability, rep, upperConfidenceBound);
		

	}

	public static double[][] computeRegret(List<Double> assignedProbs, MultiArm multiarm, Double bestProbability,int rep, Player player) {
		int T = multiarm.getcounterBound();
		double[][] regret=new double[rep][T];
	    for(int r=0; r<rep; r++) {
	    	regret[r][0]=0d;
		
	    	List<Integer> indexBandit =	player.playgame(multiarm);
	    
	    	double temp = 0;
	    	for(int i=1;i<T;i++) {
	    		temp = temp + assignedProbs.get(indexBandit.get(i));
	    		regret[r][i] = bestProbability*i - temp;
	    	}
	    }
	    
	    double[] meanRegret=new double[T];
	    double[] xDat =new double[T] ;
    	for(int i=0;i<T;i++) {
    		xDat[i] = i;
    		for(int r=0; r<rep; r++) {
	    		meanRegret[i] = meanRegret[i] + regret[r][i];
	    	}
	    	meanRegret[i] = meanRegret[i]/rep;
	    }
	      
		XYChart chart = QuickChart.getChart("Mean Regret", "T", "E[R(T)]", "r(t)", xDat, meanRegret);
	    new SwingWrapper<XYChart>(chart).displayChart();
	    
		return regret;
	}



	public static List<Bandit> initializeBandits(List<Double> assignedProbs) {
		List<Bandit> bandits;
		bandits=new ArrayList<>();
		for (Double prob : assignedProbs) {
			bandits.add(new Bandit(new WinProbability(prob)));
		}
		return bandits;
	}



	public static List<Double> initializeProbablity(int K) {
		List<Double> assignedProbs=new ArrayList<>();
		Random random=new Random(3);
		for(int i=0; i<K; i++) 			
			assignedProbs.add(random.nextDouble());

		return assignedProbs;
	}
	
	public static List<Double> initializeHardProbablity(int K) {
		List<Double> assignedProbs=new ArrayList<>();
		assignedProbs.add(0.3);
		assignedProbs.add(0.5);
		assignedProbs.add(0.4);
		assignedProbs.add(0.45);
		assignedProbs.add(0.4);
		assignedProbs.add(0.43);
		assignedProbs.add(0.42);
		assignedProbs.add(0.48);
		assignedProbs.add(0.51);
		assignedProbs.add(0.52);
		return assignedProbs;
	}
	
	private static MultiArm initializeMultiArm(int T,List<Double> assignedProbs) {
	
		List<Bandit> bandits;
		bandits = initializeBandits(assignedProbs);
		RoundCounter rounds = new RoundCounter(T);
		return new MultiArm(bandits, rounds);
	}
	
	
	private static Double max(List<Double> v) {
		 int max=0;
			
		    for(int i=0; i<v.size();i++) {
		    	if (v.get(max)<v.get(i)) {
		    		max=i;
		    	}
		    }
			return v.get(max);
		
	}
}
