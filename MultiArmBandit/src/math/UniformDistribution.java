package math;

import java.util.Random;

import com.intecs.mab.WinProbability;

public class UniformDistribution {

	private Random random;
	
	public UniformDistribution() {
		random= new Random();   
	}
	
	public WinProbability getWinProbability() {
		return new WinProbability(random.nextDouble());
	}

	public double getSample() {
		return random.nextDouble();
	}
}
