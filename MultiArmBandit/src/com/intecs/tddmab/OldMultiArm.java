package com.intecs.tddmab;

import java.util.ArrayList;
import java.util.List;

public class OldMultiArm {
	
	private List<OldBandit> OldBandits;
	private CumulativeReward currentCumulativeReward;
	
	public OldMultiArm() {
		OldBandits=new ArrayList<>();
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		OldBandits.add(new OldBandit());
		currentCumulativeReward = new CumulativeReward(0);
	}
	
	private Reward pullSelectedArm(OldBandit OldBandit) {
		
		Reward reward = OldBandit.pull();
		currentCumulativeReward.addReward(reward);
		return reward;
	}
	
	public Reward pullBandit1() {
		return pullSelectedArm(OldBandits.get(0));
	}
	
	public Reward pullBandit2() {
		return pullSelectedArm(OldBandits.get(1));
	}
	
	public Reward pullBandit3() {
		return pullSelectedArm(OldBandits.get(2));
	}
	
	public Reward pullBandit4() {
		return pullSelectedArm(OldBandits.get(3));
	}
	
	public Reward pullBandit5() {
		return pullSelectedArm(OldBandits.get(4));
	}

	public CumulativeReward getCumulativeReward() {
		return currentCumulativeReward;
	}
	
}
