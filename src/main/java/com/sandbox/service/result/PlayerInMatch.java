package com.sandbox.service.result;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayerInMatch {

	@Id
	private Long accountId;
	
	private Integer playerSlot;
	
	private Integer heroId;
	
	@ManyToMany
	private List<Item> items;
	
	private Integer kills;
	
	private Integer deaths;
	
	private Integer assists;
	
	
//	"leaver_status": 0,
//	"gold": 247,
//	"last_hits": 123,
//	"denies": 1,
//	"gold_per_min": 457,
//	"xp_per_min": 567,
//	"gold_spent": 18988,
//	"hero_damage": 7369,
//	"tower_damage": 1085,
//	"hero_healing": 462,
//	"level": 21,
//	"ability_upgrades": [
//		{
//			"ability": 5116,
//			"time": 185,
//			"level": 1
//		},
	
	
	@Override
	public String toString() {
		return "Player [accountId=" + accountId + ", playerSlot=" + playerSlot
				+ ", heroId=" + heroId + "]";
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(Integer playerSlot) {
		this.playerSlot = playerSlot;
	}

	public Integer getHeroId() {
		return heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
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
