package com.sandbox.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandbox.service.SteamService;

@Controller
public class SampleController {

	@Autowired
	private SteamService steamService;
	
    @RequestMapping("/path")
    public String helloWorld(Model model) {
    	long accountID = 103229594;
    	
        model.addAttribute("matchHistoryResult", steamService.getMatchHistory(accountID));
        
        
        
        return "path";
    }
    
    @RequestMapping("/path2")
    public String path2(Model model) {
    	long matchId = 283971180;
    	
        model.addAttribute("matchDetailResult", steamService.getMatchDetails(matchId));
        
        
        
        return "path2";
    }

    public static void main(String[] args) {

    	String epochString = "1377236371";
    	long epoch = Long.parseLong( epochString );
    	Date expiry = new Date( epoch * 1000 );
    	System.out.println(expiry);
	}
    
}
