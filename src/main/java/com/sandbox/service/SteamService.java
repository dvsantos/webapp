package com.sandbox.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import com.sandbox.service.result.MatchHistoryResult;

@Service
public class SteamService {

	private String baseURL = "https://api.steampowered.com/IDOTA2Match_570/";
	
	private String key = "81388AF4FDBC32329C1C657A8E11420F";
	
	public MatchHistoryResult getMatchHistory(long accountID){
		
		HttpClient httpclient = new DefaultHttpClient();
		
		
		return null;
	}
	
	
	
	
}
