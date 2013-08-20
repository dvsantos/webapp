package com.sandbox.controller;

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
        model.addAttribute("message", "asas");
        
        return "path";
    }
	
}
