package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Match {

	@Id
	private Long matchId;

	private Long matchSeqNum;

	private Long startTime;

	private Integer lobbyType;

	// cache?
	@OneToMany
	private List<PlayerInMatch> players = new ArrayList<>();

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getMatchSeqNum() {
		return matchSeqNum;
	}

	public void setMatchSeqNum(Long matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(Integer lobbyType) {
		this.lobbyType = lobbyType;
	}

	public List<PlayerInMatch> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerInMatch> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", matchSeqNum=" + matchSeqNum
				+ ", startTime=" + startTime + ", lobbyType=" + lobbyType
				+ ", players=" + players + "]";
	}

}
