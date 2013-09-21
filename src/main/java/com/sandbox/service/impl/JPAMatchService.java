package com.sandbox.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.model.DotaMatch;
import com.sandbox.repository.MatchRepository;
import com.sandbox.service.MatchService;

@Service
public class JPAMatchService implements MatchService {

	@Resource
	private MatchRepository matchRepository;
	
	@Override
	@Transactional
	public DotaMatch create(DotaMatch match) {
		return matchRepository.save(match);
	}
	
	@Override
	@Transactional
	public DotaMatch findMatch(Long matchId) {
		DotaMatch match = matchRepository.findOne(matchId);
		
		return match;
	}
	
	

}
