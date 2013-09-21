package com.sandbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayerMatchResult {

	@Id
	@GeneratedValue
	private Long playerMatchResultId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Player player;
	
	private PlayerSlot playerSlot;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Hero hero;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Item> items = new ArrayList<>();
	
	private Integer kills;
	
	private Integer deaths;
	
	private Integer assists;

	public Long getPlayerMatchResultId() {
		return playerMatchResultId;
	}

	public void setPlayerMatchResultId(Long playerMatchResultId) {
		this.playerMatchResultId = playerMatchResultId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerSlot getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(PlayerSlot playerSlot) {
		this.playerSlot = playerSlot;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}
	
}
