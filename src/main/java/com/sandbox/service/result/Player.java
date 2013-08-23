package com.sandbox.service.result;

import java.util.List;

public class Player {

	private long accountId;
	
	private int playerSlot;
	
	private int heroId;
	
	private List<Item> items;
	
	private int kills;
	
	private int deaths;
	
	private int assists;
	
	
	
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

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(int playerSlot) {
		this.playerSlot = playerSlot;
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	@Override
	public String toString() {
		return "Player [accountId=" + accountId + ", playerSlot=" + playerSlot
				+ ", heroId=" + heroId + "]";
	}
	
}
