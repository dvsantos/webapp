package com.sandbox.view.bean;

import java.util.ArrayList;
import java.util.List;

public class PlayerMatch {

	private Hero hero;
	
	private Long matchId;
	
	private LobbyType lobbyType;
	
	private boolean wonMatch;
	
	private int duration;
	
	private int kills;
	
	private int deaths;
	
	private int assists;
	
	private List<Item> items = new ArrayList<>();

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public LobbyType getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(LobbyType lobbyType) {
		this.lobbyType = lobbyType;
	}

	public boolean isWonMatch() {
		return wonMatch;
	}

	public void setWonMatch(boolean wonMatch) {
		this.wonMatch = wonMatch;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	 
}
