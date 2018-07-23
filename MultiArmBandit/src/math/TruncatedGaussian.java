package math;
import org.apache.commons.math3.distribution.NormalDistribution;

import com.intecs.mab.Reward;

public class TruncatedGaussian extends NormalDistribution{

	private TruncatedGaussian(double mean, double std) { 
		super(mean, std);
	}
	
	public static TruncatedGaussian createTruncatedGaussion(double mean, double std) throws InvalidMeanException {
		if (mean <0 || mean > 1)
			throw new InvalidMeanException();
		return new TruncatedGaussian(mean, std);
	}
	
	public Reward getSample() {
		double sample = super.sample();
		if (sample < 0) 
			sample = 0;
		else if(sample > 1)
			sample = 1;
		return Reward.createRealReward(sample);
	}
	
}
