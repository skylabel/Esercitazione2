package com.intecs.mab;

import java.util.Comparator;
import java.util.List;

public class Ranking {
	
	private List<Pair> rank;
	
	public Ranking(List<Pair> list){
		this.rank = list;
		sort();
	}
	
	private void sort() {
		rank.sort(new Comparator<Pair>() {
		    @Override
		    public int compare(Pair m1, Pair m2) {
		        if(m1.getGame().getScore() >= m2.getGame().getScore()){
		            return 1;
		        }
		        return -1;
		     }
		});
		
	}
	
	@Override
	public String toString(){
		StringBuilder result= new StringBuilder("Ranking \n");
		for (Pair entry: rank) {
			result.append(""+entry.getUsername().getValue() +"  "+entry.getGame().getScore()+"\n");
		}
		String s=result.toString();
		return s;
	}
	
	
	
	
}

