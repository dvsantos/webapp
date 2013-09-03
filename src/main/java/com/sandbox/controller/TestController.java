package com.sandbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sandbox.test.CutBar;
import com.sandbox.test.CutItem;
import com.sandbox.test.CutPattern;

public class TestController {

	
	
	public static void main(String[] args) {
		
		List<CutBar> bars = new ArrayList<>();
		
		List<CutPattern> patterns = new ArrayList<>();
		
		Map<CutItem, Integer> demand = new HashMap<>();
		
		for(CutBar bar : bars) {
			//patterns.add(getBarPatterns(bar, demand));
			
			
			
		}
		
	}

	
	
	
	
	//problemas de math com Double
	private static List<CutPattern> getBarPatterns(CutBar bar, Map<CutItem, Integer> demand) {

		List<CutPattern> patterns = new ArrayList<>();
		
		Double barSize = bar.getSize();
		
		for (Entry<CutItem, Integer> entry : demand.entrySet()) {

			CutItem item = entry.getKey();
			
			Integer required = entry.getValue();
			
			for(int s = required; s >= 0; s--) {
				
				Double occupiedSize = s * item.getSize();
				
				if(occupiedSize == barSize) {
					// perfect fit
					
				} else if((occupiedSize > barSize)) {
					// discard and continue
					
				} else {
					// call recursion
					CutBar remainingBar = new CutBar();
					remainingBar.setSize(barSize - occupiedSize);
					
					Map<CutItem, Integer> remainingDemand = new HashMap<>(demand);
					remainingDemand.remove(item);
					
					patterns.addAll(getBarPatterns(remainingBar, remainingDemand));
				}
				
			}
			
		}

		return null;
	}
	
	
	
}
