package com.sandbox.controller;

import java.util.Date;

import net.sf.javailp.Linear;
import net.sf.javailp.OptType;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryGLPK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.service.SteamService;

@Controller
public class SampleController {

	@Autowired
	@Qualifier(value="persistedService")
	private SteamService steamService;
	
    @RequestMapping("/path")
    public String helloWorld(Model model) {
    	long accountID = 103229594;
    	
        model.addAttribute("matchHistoryResult", steamService.getMatchHistory(accountID));
        
        
        
        return "path";
    }
    
    @RequestMapping("/path2")
    public String path2(Model model) {
    	long matchId = 305007174;
    	
        model.addAttribute("matchDetailResult", steamService.getMatchDetails(matchId));
        
        
        
        return "path2";
    }

    
    
    
    
    
    public static void main(String[] args) {

    	String epochString = "1379273345";
    	long epoch = Long.parseLong( epochString );
    	Date expiry = new Date( epoch * 1000 );
    	System.out.println(expiry);
    	
    	System.exit(-1);

		SolverFactory factory = new SolverFactoryGLPK(); // use lp_solve
    	factory.setParameter(Solver.VERBOSE, 9); 
    	factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds
    	
    	Problem problem = new Problem();
    	
    	Linear linear = new Linear();
    	linear.add(0, "a");
    	linear.add(0, "b");
    	linear.add(0, "c");
    	linear.add(1, "d");
    	linear.add(0, "e");
    	linear.add(0, "f");
    	problem.setObjective(linear, OptType.MIN);
    	
    	
    	linear = new Linear();
    	linear.add(1, "a");
    	//linear.add(0, "b");
    	linear.add(2, "c");
    	linear.add(1, "d");
    	linear.add(2, "e");
    	linear.add(3, "f");

    	problem.add(linear, "=", 3);
    	
    	
    	linear = new Linear();
    	linear.add(1, "a");
    	linear.add(3, "b");
    	linear.add(2, "c");
    	linear.add(3, "d");
    	linear.add(2, "e");
    	//linear.add(0, "f");

    	problem.add(linear, "=", 3);
    	
    	problem.setVarType("a", Integer.class);
    	problem.setVarType("b", Integer.class);
    	problem.setVarType("c", Integer.class);
    	problem.setVarType("d", Integer.class);
    	problem.setVarType("e", Integer.class);
    	problem.setVarType("f", Integer.class);

    	problem.setVarLowerBound("a", 0);
    	problem.setVarLowerBound("b", 0);
    	problem.setVarLowerBound("c", 0);
    	problem.setVarLowerBound("d", 0);
    	problem.setVarLowerBound("e", 0);
    	problem.setVarLowerBound("f", 0);
    	
    	Solver solver = factory.get(); // you should use this solver only once for one problem
    	Result result = solver.solve(problem);

    	System.out.println(result);
    
    	
	}

	private static void ilp() {
		SolverFactory factory = new SolverFactoryGLPK(); // use lp_solve
    	factory.setParameter(Solver.VERBOSE, 0); 
    	factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds

    	/**
    	* Constructing a Problem: 
    	* Maximize: 143x+60y 
    	* Subject to: 
    	* 120x+210y <= 15000 
    	* 110x+30y <= 4000 
    	* x+y <= 75
    	* 
    	* With x,y being integers
    	* 
    	*/
    	Problem problem = new Problem();

    	Linear linear = new Linear();
    	linear.add(143, "x");
    	linear.add(60, "y");

    	problem.setObjective(linear, OptType.MAX);

    	linear = new Linear();
    	linear.add(120, "x");
    	linear.add(210, "y");

    	problem.add(linear, "<=", 15000);

    	linear = new Linear();
    	linear.add(110, "x");
    	linear.add(30, "y");

    	problem.add(linear, "<=", 4000);

    	linear = new Linear();
    	linear.add(1, "x");
    	linear.add(1, "y");

    	problem.add(linear, "<=", 75);

    	problem.setVarType("x", Integer.class);
    	problem.setVarType("y", Integer.class);

    	Solver solver = factory.get(); // you should use this solver only once for one problem
    	Result result = solver.solve(problem);

    	System.out.println(result);

    	/**
    	* Extend the problem with x <= 16 and solve it again
    	*/
    	problem.setVarUpperBound("x", 16);

    	solver = factory.get();
    	result = solver.solve(problem);

    	System.out.println(result);
	}
    
}
