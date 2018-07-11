package com.intecs.mab;

import java.util.GregorianCalendar;
import java.util.List;

import com.intecs.tddmab.MultiArm;

public abstract class Player {

	private String name;
	private GregorianCalendar born;
	
	public Player(String Name, GregorianCalendar born) {
       this.born=born;
       this.name=Name;
	}
	
	public abstract List<Integer>  playgame(MultiArm multiArm);
	public abstract void reset(MultiArm multiArm);
	
	public String getName() {
		return name;
	}

	public GregorianCalendar getBorn() {
		return born;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((born == null) ? 0 : born.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (born == null) {
			if (other.born != null)
				return false;
		} else if (!born.equals(other.born))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
}
