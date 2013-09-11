package com.sandbox.service.result;

import java.util.List;

public class MatchDetailsResult {

	private long matchId;
	
	private List<Player> players;
	
	private boolean radiantWin;
	
	private int duration;
	
	private long startTime;
	
	private long matchSeqNum;
	
//	"tower_status_radiant": 1828,
//	"tower_status_dire": 0,
//	"barracks_status_radiant": 63,
//	"barracks_status_dire": 0,
//	"cluster": 200,
	
	private int firstBloodTime;
	
	private int lobbyType;
	
	private int humanPlayers;
	
	private int leagueId;

	private int gameMode;
	
	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isRadiantWin() {
		return radiantWin;
	}

	public void setRadiantWin(boolean radiantWin) {
		this.radiantWin = radiantWin;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getMatchSeqNum() {
		return matchSeqNum;
	}

	public void setMatchSeqNum(long matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	public int getFirstBloodTime() {
		return firstBloodTime;
	}

	public void setFirstBloodTime(int firstBloodTime) {
		this.firstBloodTime = firstBloodTime;
	}

	public int getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(int lobbyType) {
		this.lobbyType = lobbyType;
	}

	public int getHumanPlayers() {
		return humanPlayers;
	}

	public void setHumanPlayers(int humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	
//	"positive_votes": 0,
//	"negative_votes": 0,
	
}
