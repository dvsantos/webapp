package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private long matchId;
	
	private long matchSeqNum;
	
	private long startTime;

	private int lobbyType;
	
	private List<Player> players = new ArrayList<>();
	
	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public long getMatchSeqNum() {
		return matchSeqNum;
	}

	public void setMatchSeqNum(long matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(int lobbyType) {
		this.lobbyType = lobbyType;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", matchSeqNum=" + matchSeqNum
				+ ", startTime=" + startTime + ", lobbyType=" + lobbyType
				+ ", players=" + players + "]";
	}
	
}
