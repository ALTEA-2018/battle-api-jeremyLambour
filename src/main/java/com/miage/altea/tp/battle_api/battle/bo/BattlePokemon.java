package com.miage.altea.tp.battle_api.battle.bo;

import com.miage.altea.tp.battle_api.pokemonTypes.bo.PokemonType;

public class BattlePokemon {

	private Integer id;
	private Integer hp;
	private Integer maxHp;
	private PokemonType type;
	private Integer attack;
	private Integer defense;
	private Integer speed;
	private Integer level;
	private Boolean alive;
	private Boolean ko;	

	public Integer getHp() {
		return hp;
	}

	public Integer getMaxHp() {
		return maxHp;
	}

	public PokemonType getType() {
		return type;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	public void setType(PokemonType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public Integer getAttack() {
		return attack;
	}

	public Integer getDefense() {
		return defense;
	}

	public Integer getSpeed() {
		return speed;
	}

	public Integer getLevel() {
		return level;
	}

	public Boolean getAlive() {
		return alive;
	}

	public Boolean getKo() {
		return ko;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public void setKo(Boolean ko) {
		this.ko = ko;
	}

	
}
