package com.sandbox.service.result;

public class Player {

	private long accountId;
	
	private int playerSlot;
	
	private int heroId;

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
