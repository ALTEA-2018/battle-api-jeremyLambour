package com.miage.altea.tp.battle_api.battle.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miage.altea.tp.battle_api.battle.service.StatsCalculator;
import com.miage.altea.tp.battle_api.pokemonTypes.bo.PokemonType;

@Component
public class BattlePokemonFactory {

	@Autowired
	private StatsCalculator calculator;

	public BattlePokemon createBattlePokemon(PokemonType pokemonType, Integer level) {
		BattlePokemon battlePokemon = new BattlePokemon();
		battlePokemon.setType(pokemonType);
		battlePokemon.setHp(calculator.calculateHp(pokemonType.getStats().getHp(), level));
		battlePokemon.setMaxHp(calculator.calculateHp(pokemonType.getStats().getHp(), level));
		battlePokemon.setAlive(true);
		battlePokemon.setKo(false);
		battlePokemon.setAttack(calculator.calculateStat(pokemonType.getStats().getAttack(), level));
		battlePokemon.setDefense(calculator.calculateStat(pokemonType.getStats().getDefense(), level));
		battlePokemon.setSpeed(calculator.calculateStat(pokemonType.getStats().getSpeed(), level));
		battlePokemon.setLevel(level);


		return battlePokemon;

	}

}
