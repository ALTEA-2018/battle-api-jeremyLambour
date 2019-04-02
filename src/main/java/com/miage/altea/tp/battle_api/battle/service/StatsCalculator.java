package com.miage.altea.tp.battle_api.battle.service;

import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;

public interface StatsCalculator {

	public Integer calculateHp(Integer base,Integer level);
	public Integer calculateStat(Integer base,Integer level);
	public Integer calculateDamage(BattlePokemon attacker,BattlePokemon opponent);
}
