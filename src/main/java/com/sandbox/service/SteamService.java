package com.sandbox.service;

import java.util.List;
import java.util.Set;

import com.sandbox.service.result.Hero;
import com.sandbox.service.result.MatchDetailsResult;
import com.sandbox.service.result.MatchHistoryResult;
import com.sandbox.service.result.PlayerSummary;

public interface SteamService {

	public abstract MatchHistoryResult getMatchHistory(long accountID);

	public abstract MatchDetailsResult getMatchDetails(long matchId);

	public abstract List<Hero> getHeroes();

	public abstract List<PlayerSummary> getPlayerSummaries(Set<Long> steamids);

}