package com.sandbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DotaMatch {

	@Id
	private Long matchId;
	
	private Long matchSequenceNumber;
	
	private Date startTime;
	
	private LobbyType lobbyType;
	
	private Faction winningFaction;
	
	// cache?
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PlayerMatchResult> playerMatchResults = new ArrayList<>();

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getMatchSequenceNumber() {
		return matchSequenceNumber;
	}

	public void setMatchSequenceNumber(Long matchSequenceNumber) {
		this.matchSequenceNumber = matchSequenceNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public LobbyType getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(LobbyType lobbyType) {
		this.lobbyType = lobbyType;
	}

	public List<PlayerMatchResult> getPlayerMatchResults() {
		return playerMatchResults;
	}

	public void setPlayerMatchResults(List<PlayerMatchResult> playerMatchResults) {
		this.playerMatchResults = playerMatchResults;
	}

	public Faction getWinningFaction() {
		return winningFaction;
	}

	public void setWinningFaction(Faction winningFaction) {
		this.winningFaction = winningFaction;
	}
	
}
