package com.sandbox.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.service.SteamService;
import com.sandbox.service.repository.MatchHistoryResultRepository;
import com.sandbox.service.result.Hero;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.MatchHistoryResultKey;
import com.sandbox.service.result.PlayerSummary;

@Service(value="persistedService")
public class PersistedSteamService implements SteamService{

	@Autowired
	@Qualifier(value="simpleService")
	private SteamService steamService;
	
	@Autowired
	@Resource
	private MatchHistoryResultRepository matchHistoryResultRepository;
	
	@Override
	@Transactional
	public MatchHistoryResult getMatchHistory(long accountID) {
		MatchHistoryResultKey key = new MatchHistoryResultKey();
		key.setAccountId(accountID);
		key.setStartAtMatchId(-1l);
		
		MatchHistoryResult matchHistoryResult = null;
		try {
			matchHistoryResult = matchHistoryResultRepository.findOne(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		if(matchHistoryResult == null) {
			matchHistoryResult = steamService.getMatchHistory(accountID);
			
			if(matchHistoryResult != null) {
				matchHistoryResult.setMatchHistoryResultKey(key);
				matchHistoryResultRepository.save(matchHistoryResult);
			}
			
		}
		
		return matchHistoryResult;
	}

	@Override
	public MatchDetailsResult getMatchDetails(long matchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hero> getHeroes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerSummary> getPlayerSummaries(Set<Long> steamids) {
		// TODO Auto-generated method stub
		return null;
	}

}
