package com.sandbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sandbox.test.CutBar;
import com.sandbox.test.CutItem;
import com.sandbox.test.CutPattern;
import com.sandbox.test.CutStockProblem;
import com.sandbox.test.CutStockProblemSolver;
import com.sandbox.test.CutStockSolution;

public class TestController {

	
	
	public static void main(String[] args) {
		
		List<CutBar> bars = new ArrayList<>();
		
		List<CutPattern> patterns = new ArrayList<>();
		
		Map<CutItem, Integer> demand = new HashMap<>();
		
		CutBar bar = new CutBar();
		bar.setSize(1700.0);
		
		CutItem itemA = new CutItem();
		itemA.setName("A");
		itemA.setSize(300.0);
		demand.put(itemA, 3);
		
		CutItem itemB = new CutItem();
		itemB.setName("B");
		itemB.setSize(600.0);
		demand.put(itemB, 3);
		
//		System.out.println(getBarPatterns(bar, demand));
		
		prettyPrint(bar, demand, getBarPatterns(bar, demand));
		
		CutStockProblem problem = new CutStockProblem();

		problem.getAvailableBars().add(bar);
		problem.setDemand(demand);
		

		CutStockProblemSolver solver = new CutStockProblemSolver();
		solver.setProblem(problem);
		CutStockSolution solution = solver.solve();
		
	}
	
	
	


	private static void prettyPrint(CutBar bar, Map<CutItem, Integer> demand, Set<CutPattern> barPatterns) {
		
		System.out.println("Bar size: " + bar.getSize());
		
		for(Entry<CutItem, Integer> itemDemand : demand.entrySet()) {
			CutItem item = itemDemand.getKey();
			Integer required = itemDemand.getValue();
			
			System.out.println("Item " + item.getName() + ", tam: " + item.getSize() + ", required: " + required);
		}
		
		System.out.println("");
		
		int i = 0;
		for(CutPattern pattern: barPatterns) {
			
			System.out.println("\tPattern " + (i++) + ", waste: " + pattern.getWaste());
			
			System.out.println("\tComposition:");
			
			for(Entry<CutItem, Integer> itemComposition : pattern.getCutComposition().entrySet()) {
				
				CutItem item = itemComposition.getKey();
				Integer used = itemComposition.getValue();
				
				System.out.println("\t\tItem " + item.getName() + ", tam: " + item.getSize() + ", qtd: " + used);
				
			}
			System.out.println("");
		}
		
		
	}



	//problemas de math com Double
	private static Set<CutPattern> getBarPatterns(CutBar bar, Map<CutItem, Integer> demand) {
		Double totalOccuppiedSize = 0.0;
		
		Map<CutItem, Integer> fullComposition = new HashMap<>();
		
		for (Entry<CutItem, Integer> entry : demand.entrySet()) {
			CutItem item = entry.getKey();
			
			Integer required = entry.getValue();
			
			totalOccuppiedSize = item.getSize() * required;
			
			fullComposition.put(item, required);
		}
		
		Set<CutPattern> patterns = new HashSet<>();
		
		Double barSize = bar.getSize();
		
		if(totalOccuppiedSize <= barSize) {
			
			CutPattern pattern = new CutPattern();
			
			pattern.setBar(bar);
			pattern.setWaste(barSize - totalOccuppiedSize);
			pattern.setCutComposition(fullComposition);
			patterns.add(pattern);
			return patterns;
		}
		
		for (Entry<CutItem, Integer> entry : demand.entrySet()) {

			CutItem item = entry.getKey();
			
			Integer required = entry.getValue();
			
			for(int s = required; s > 0; s--) {
				
				Double occupiedSize = s * item.getSize();
				
				if(occupiedSize.equals(barSize)) {
					// perfect fit
					CutPattern pattern = new CutPattern();
					pattern.setBar(bar);
					pattern.setWaste(0.0);
					pattern.getCutComposition().put(item, s);
					patterns.add(pattern);
					
				} else if((occupiedSize > barSize)) {
					// discard and continue
					
					//se tiver um item maior que a barra?
					if(s == 1) {
						CutPattern pattern = new CutPattern();
						pattern.setBar(bar);
						pattern.setWaste(barSize);
						patterns.add(pattern);
						
						if(demand.size() == 1) {
							return patterns;
						} else {
							CutBar remainingBar = new CutBar();
							remainingBar.setSize(barSize);
							
							Map<CutItem, Integer> remainingDemand = new HashMap<>(demand);
							remainingDemand.remove(item);
							
							if(remainingDemand.size() > 0) {
								//nao posso enviar um entry que ja foi?
								Set<CutPattern> remainingPatterns = getBarPatterns(remainingBar, remainingDemand);
								
								return remainingPatterns;
							}
							
						}
					}
					
				} else {
					// call recursion
					CutBar remainingBar = new CutBar();
					remainingBar.setSize(barSize - occupiedSize);
					
					//melhor fazer fora do loop?
					Map<CutItem, Integer> remainingDemand = new HashMap<>(demand);
					remainingDemand.remove(item);
					
					if(remainingDemand.size() > 0) {
						
						//nao posso enviar um entry que ja foi?
						Set<CutPattern> remainingPatterns = getBarPatterns(remainingBar, remainingDemand);
						
						for(CutPattern remainingPattern : remainingPatterns) {
							CutPattern pattern = new CutPattern();
							
							pattern.setBar(bar);
							pattern.setWaste(remainingPattern.getWaste());
							pattern.setCutComposition(remainingPattern.getCutComposition());
							pattern.getCutComposition().put(item, s);
							patterns.add(pattern);
						}
						
					} else {
						CutPattern pattern = new CutPattern();
						
						pattern.setBar(bar);
						pattern.setWaste(remainingBar.getSize());
						pattern.getCutComposition().put(item, s);
						patterns.add(pattern);
					}
					
				}
			}
		}
		
		return patterns;
	}
	
	
	
}
