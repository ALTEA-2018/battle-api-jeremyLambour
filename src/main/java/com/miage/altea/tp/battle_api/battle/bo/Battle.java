package com.miage.altea.tp.battle_api.battle.bo;

import java.util.UUID;

public class Battle {

	private UUID uuid;
	private BattleTrainer trainer;
	private BattleTrainer opponent;
	
	
	public BattleTrainer getTrainer() {
		return trainer;
	}
	public BattleTrainer getOpponent() {
		return opponent;
	}
	public void setTrainer(BattleTrainer trainer) {
		this.trainer = trainer;
	}
	public void setOpponent(BattleTrainer opponent) {
		this.opponent = opponent;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	
	
}
