package com.sandbox.service.result;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MatchHistoryBySequenceNumResult {

	@Id
	private Long matchId;
	
	private Integer status;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DotaMatch> matches = new ArrayList<>();
	
	private Boolean radiantWin;
	
	private Integer duration;
	
	private Long startTime;
	
	private Long matchSeqNum;
	
//	"tower_status_radiant": 1828,
//	"tower_status_dire": 0,
//	"barracks_status_radiant": 63,
//	"barracks_status_dire": 0,
//	"cluster": 200,
	
	private Integer firstBloodTime;
	
	private Integer lobbyType;
	
	private Integer humanPlayers;
	
	private Integer leagueId;

	private Integer gameMode;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}


	public Boolean isRadiantWin() {
		return radiantWin;
	}

	public void setRadiantWin(Boolean radiantWin) {
		this.radiantWin = radiantWin;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getMatchSeqNum() {
		return matchSeqNum;
	}

	public void setMatchSeqNum(Long matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	public Integer getFirstBloodTime() {
		return firstBloodTime;
	}

	public void setFirstBloodTime(Integer firstBloodTime) {
		this.firstBloodTime = firstBloodTime;
	}

	public Integer getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(Integer lobbyType) {
		this.lobbyType = lobbyType;
	}

	public Integer getHumanPlayers() {
		return humanPlayers;
	}

	public void setHumanPlayers(Integer humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getGameMode() {
		return gameMode;
	}

	public void setGameMode(Integer gameMode) {
		this.gameMode = gameMode;
	}

	@Override
	public String toString() {
		return "MatchDetailsResult [matchId=" + matchId + ", players="
				 + ", radiantWin=" + radiantWin + ", duration="
				+ duration + ", startTime=" + startTime + ", matchSeqNum="
				+ matchSeqNum + ", firstBloodTime=" + firstBloodTime
				+ ", lobbyType=" + lobbyType + ", humanPlayers=" + humanPlayers
				+ ", leagueId=" + leagueId + ", gameMode=" + gameMode + "]";
	}
	
//	"positive_votes": 0,
//	"negative_votes": 0,
}
