package com.miage.altea.tp.battle_api.battle.bo;

import java.util.List;

public class BattleTrainer {
	
	private String name;
	private List<BattlePokemon> team;
	private Boolean nextTurn;
	public String getName() {
		return name;
	}
	public List<BattlePokemon> getTeam() {
		return team;
	}
	public Boolean getNextTurn() {
		return nextTurn;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTeam(List<BattlePokemon> team) {
		this.team = team;
	}
	public void setNextTurn(Boolean nextTurn) {
		this.nextTurn = nextTurn;
	}
	
	
	
}
