package com.sandbox.test;

import java.util.Map;

public class CutPattern {

	private Double waste;
	
	private CutBar bar;
	
	private Map<CutItem, Integer> cutComposition;

	public Double getWaste() {
		return waste;
	}

	public void setWaste(Double waste) {
		this.waste = waste;
	}

	public Map<CutItem, Integer> getCutComposition() {
		return cutComposition;
	}

	public void setCutComposition(Map<CutItem, Integer> cutComposition) {
		this.cutComposition = cutComposition;
	}

	public CutBar getBar() {
		return bar;
	}

	public void setBar(CutBar bar) {
		this.bar = bar;
	}
	
}
