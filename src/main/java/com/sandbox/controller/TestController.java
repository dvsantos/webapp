package com.sandbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sandbox.test.CutBar;
import com.sandbox.test.CutItem;
import com.sandbox.test.CutPattern;

public class TestController {

	
	
	public static void main(String[] args) {
		
		List<CutBar> bars = new ArrayList<>();
		
		List<CutPattern> patterns = new ArrayList<>();
		
		Map<CutItem, Integer> demand = new HashMap<>();
		
		for(CutBar bar : bars) {
			patterns.add(getBarPatterns(bar, demand));
		}
		
	}

	private static CutPattern getBarPatterns(CutBar bar, Map<CutItem, Integer> demand) {
		
		
		
		
		return null;
	}
	
	
	
}
