package com.sandbox.service.result;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayerSummary {

	//remaining params here: http://wiki.teamfortress.com/wiki/WebAPI/GetPlayerSummaries
	
	@Id
	private Long steamId;
	
	private Integer communityVisibilityState;
	
	private String personaname;
	
	private Long lastLogOff;
	
	private String profileurl;
	
	private String avatar;
	
	private String avatarMedium;
	
	private String avatarFull;
	
	private Integer personastate;

	public Long getSteamId() {
		return steamId;
	}

	public void setSteamId(Long steamId) {
		this.steamId = steamId;
	}

	public Integer getCommunityVisibilityState() {
		return communityVisibilityState;
	}

	public void setCommunityVisibilityState(Integer communityVisibilityState) {
		this.communityVisibilityState = communityVisibilityState;
	}

	public String getPersonaname() {
		return personaname;
	}

	public void setPersonaname(String personaname) {
		this.personaname = personaname;
	}

	public Long getLastLogOff() {
		return lastLogOff;
	}

	public void setLastLogOff(Long lastLogOff) {
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

	public Integer getPersonastate() {
		return personastate;
	}

	public void setPersonastate(Integer personastate) {
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
