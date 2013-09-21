package com.sandbox.model;

public enum PlayerSlot {

	RADIANT_0, RADIANT_1, RADIANT_2, RADIANT_3, RADIANT_4, DIRE_0, DIRE_1, DIRE_2, DIRE_3, DIRE_4;
	
	public Faction getFaction() {
		
		if(this.ordinal() < 5) {
			return Faction.RADIANT;
		}
		
		return Faction.DIRE;
	}
	
	public Integer getFactionSlotNumber() {
		return (this.ordinal() % 5);
	}
	
	public Integer getSlotNumber() {
		return this.ordinal();
	}
	
	public static PlayerSlot fromUnsignedInteger(int slot) {
		if((slot & 0x80) == 128) {
			//was on dire
			if(slot - 128 <= 4) {
				return PlayerSlot.values()[slot - 128];
			}
		} else {
			//was on radiant
			if(slot <= 4) {
				return PlayerSlot.values()[slot];
			}
		}
		
		return null;
	}
	
}


