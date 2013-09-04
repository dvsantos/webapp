package com.sandbox.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CutStockProblem {

	private Set<CutPattern> patterns = new HashSet<>();
	
	private Map<CutItem, Integer> demand = new HashMap<CutItem, Integer>();
	
	private Set<CutBar> availableBars = new HashSet<CutBar>();

	public Set<CutPattern> getPatterns() {
		return patterns;
	}

	public void setPatterns(Set<CutPattern> patterns) {
		this.patterns = patterns;
	}

	public Map<CutItem, Integer> getDemand() {
		return demand;
	}

	public void setDemand(Map<CutItem, Integer> demand) {
		this.demand = demand;
	}

	public Set<CutBar> getAvailableBars() {
		return availableBars;
	}

	public void setAvailableBars(Set<CutBar> availableBars) {
		this.availableBars = availableBars;
	}
	
}
