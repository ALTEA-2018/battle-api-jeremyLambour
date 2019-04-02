package com.miage.altea.tp.battle_api.battle.service;

import org.springframework.stereotype.Service;

import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;

@Service
public class StatsCalculatorImpl implements StatsCalculator {

	@Override
	public Integer calculateHp(Integer base, Integer level) {

		Double value = (10 + level + (base * ((Double.valueOf(level) / Double.valueOf(50)))));
		return value.intValue();
	}

	@Override
	public Integer calculateStat(Integer base, Integer level) {

		Double value = 5 + (base * (Double.valueOf(level) / Double.valueOf(50)));
		return value.intValue();
	}

	@Override
	public Integer calculateDamage(BattlePokemon attacker, BattlePokemon opponent) {
		Double value = ((Double.valueOf(2 * attacker.getLevel()) / Double.valueOf(5))
				+ 2 * (Double.valueOf(attacker.getAttack()) / Double.valueOf(opponent.getDefense())) + 2);
		return value.intValue();
	}

}
