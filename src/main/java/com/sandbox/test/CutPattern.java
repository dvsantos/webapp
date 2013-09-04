package com.sandbox.test;

import java.util.HashMap;
import java.util.Map;

public class CutPattern {

	private Double waste;
	
	private CutBar bar;
	
	private Map<CutItem, Integer> cutComposition = new HashMap<>();

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bar == null) ? 0 : bar.hashCode());
		result = prime * result
				+ ((cutComposition == null) ? 0 : cutComposition.hashCode());
		result = prime * result + ((waste == null) ? 0 : waste.hashCode());
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
		CutPattern other = (CutPattern) obj;
		if (bar == null) {
			if (other.bar != null)
				return false;
		} else if (!bar.equals(other.bar))
			return false;
		if (cutComposition == null) {
			if (other.cutComposition != null)
				return false;
		} else if (!cutComposition.equals(other.cutComposition))
			return false;
		if (waste == null) {
			if (other.waste != null)
				return false;
		} else if (!waste.equals(other.waste))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CutPattern [waste=" + waste + ", bar=" + bar
				+ ", cutComposition=" + cutComposition + "]";
	}
	
}
