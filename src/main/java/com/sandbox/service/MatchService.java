package com.sandbox.service;

import com.sandbox.model.DotaMatch;

public interface MatchService {

	public DotaMatch create(DotaMatch match);
	
	public DotaMatch findMatch(Long matchId);
	
}
