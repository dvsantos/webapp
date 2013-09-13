package com.sandbox.service.result;

public class PlayerSummary {

	//remaining params here: http://wiki.teamfortress.com/wiki/WebAPI/GetPlayerSummaries
	
	private long steamId;
	
	private int communityVisibilityState;
	
	private String personaname;
	
	private long lastLogOff;
	
	private String profileurl;
	
	private String avatar;
	
	private String avatarMedium;
	
	private String avatarFull;
	
	private int personastate;

	public long getSteamId() {
		return steamId;
	}

	public void setSteamId(long steamId) {
		this.steamId = steamId;
	}

	public int getCommunityVisibilityState() {
		return communityVisibilityState;
	}

	public void setCommunityVisibilityState(int communityVisibilityState) {
		this.communityVisibilityState = communityVisibilityState;
	}

	public String getPersonaname() {
		return personaname;
	}

	public void setPersonaname(String personaname) {
		this.personaname = personaname;
	}

	public long getLastLogOff() {
		return lastLogOff;
	}

	public void setLastLogOff(long lastLogOff) {
		this.lastLogOff = lastLogOff;
	}

	public String getProfileurl() {
		return profileurl;
	}

	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarMedium() {
		return avatarMedium;
	}

	public void setAvatarMedium(String avatarMedium) {
		this.avatarMedium = avatarMedium;
	}

	public String getAvatarFull() {
		return avatarFull;
	}

	public void setAvatarFull(String avatarFull) {
		this.avatarFull = avatarFull;
	}

	public int getPersonastate() {
		return personastate;
	}

	public void setPersonastate(int personastate) {
		this.personastate = personastate;
	}

	@Override
	public String toString() {
		return "PlayerSummary [steamId=" + steamId
				+ ", communityVisibilityState=" + communityVisibilityState
				+ ", personaname=" + personaname + ", lastLogOff=" + lastLogOff
				+ ", profileurl=" + profileurl + ", avatar=" + avatar
				+ ", avatarMedium=" + avatarMedium + ", avatarFull="
				+ avatarFull + ", personastate=" + personastate + "]";
	}
	
}
