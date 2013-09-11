package com.sandbox.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.javailp.Linear;
import net.sf.javailp.OptType;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryGLPK;

public class CutStockProblemSolver {

	private CutStockProblem problem;
	
	private Map<CutPattern, String> variableAssigned = new HashMap<>();
	
	public CutStockSolution solve() {

		Set<CutPattern> cutPatterns = problem.getPatterns();
		Map<CutItem, Integer> demand = problem.getDemand();
		
		for(CutBar availableBar : problem.getAvailableBars()) {
			cutPatterns.addAll(getBarPatterns(availableBar, demand));
		}
		
		SolverFactory factory = new SolverFactoryGLPK(); // use lp_solve
    	factory.setParameter(Solver.VERBOSE, 9); 
    	factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds
		
    	Problem ilpProblem = new Problem();
    	
    	Linear linear = new Linear();
    	
    	int i = 1;
    	for(CutPattern pattern: cutPatterns) {
    		linear.add(pattern.getWaste(), "x" + i);
    		ilpProblem.setVarType("x" + i, Integer.class);
    		ilpProblem.setVarLowerBound("x" + i, 0);
    		
    		variableAssigned.put(pattern, "x" + i);
    		
    		i++;
    	}
    	ilpProblem.setObjective(linear, OptType.MIN);
    	
    	
		for (Entry<CutItem, Integer> itemDemand : demand.entrySet()) {

			linear = new Linear();
			for(CutPattern pattern: cutPatterns) {
				Integer used = pattern.getCutComposition().get(itemDemand.getKey());
				
				if(used == null) {
					used = 0;
				}
				
				linear.add(used, variableAssigned.get(pattern));
			}
			
			ilpProblem.add(linear, "=", itemDemand.getValue());
		}
		
    	Solver solver = factory.get(); // you should use this solver only once for one problem
    	Result result = solver.solve(ilpProblem);
    	
    	System.out.println(result);
    	
		return null;
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
	
	public CutStockProblem getProblem() {
		return problem;
	}

	public void setProblem(CutStockProblem problem) {
		this.problem = problem;
	}
	
}
