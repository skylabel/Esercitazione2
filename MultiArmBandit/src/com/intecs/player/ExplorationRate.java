package com.intecs.player;

public class ExplorationRate {

	private Integer rate;
	
	public ExplorationRate(int rate) {
		if(rate<=0) new IllegalArgumentException();
		this.rate=rate;
	}
	
	public Integer getRate() {
		return rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExplorationRate other = (ExplorationRate) obj;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

	
	
	
	
}
