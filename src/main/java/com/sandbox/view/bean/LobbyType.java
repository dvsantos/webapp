package com.sandbox.view.bean;

public enum LobbyType {
	
	INVALID, PUBLIC_MATCHMAKING, PRACTISE, TOURNAMENT, TUTORIAL, CO_OP_WITH_BOTS, TEAM_MATCH, SOLO_QUEUE;
	
	
	public static LobbyType fromInt(int type) {
		
		if(type == -1) {
			return INVALID;
		}
		
		if(type >= 0 && type <= 6) {
			return LobbyType.values()[type + 1];
		}
		
		return null;
	}
	
//	-1 - Invalid
//	0 - Public matchmaking
//	1 - Practise
//	2 - Tournament
//	3 - Tutorial
//	4 - Co-op with bots.
//	5 - Team match
//	6 - Solo Queue

}
