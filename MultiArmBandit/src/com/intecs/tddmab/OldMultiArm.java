package com.intecs.tddmab;

import java.util.ArrayList;
import java.util.List;

public class OldMultiArm {
	
	private List<OldBandit> OldBandits;
	private CumulativeGain currentGain;
	
	public OldMultiArm() {
		OldBandits=new ArrayList<>();
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		currentGain = new CumulativeGain(0);
	}
	
	private Reward pullSelectedArm(OldBandit OldBandit) {
		
		Reward reward = OldBandit.pull();
		currentGain.addReward(reward);
		return reward;
	}
	
	public Reward choseArm1() {
		return pullSelectedArm(OldBandits.get(0));
	}
	
	public Reward choseArm2() {
		return pullSelectedArm(OldBandits.get(1));
	}
	
	public Reward choseArm3() {
		return pullSelectedArm(OldBandits.get(2));
	}
	
	public Reward choseArm4() {
		return pullSelectedArm(OldBandits.get(3));
	}
	
	public Reward choseArm5() {
		return pullSelectedArm(OldBandits.get(4));
	}

	public CumulativeGain getGain() {
		return currentGain;
	}
	
}
