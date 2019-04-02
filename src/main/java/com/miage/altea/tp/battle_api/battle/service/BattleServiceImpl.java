package com.miage.altea.tp.battle_api.battle.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattleException;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemonFactory;
import com.miage.altea.tp.battle_api.battle.bo.BattleTrainer;
import com.miage.altea.tp.battle_api.pokemonTypes.services.PokemonTypeService;
import com.miage.altea.tp.battle_api.trainer.bo.Trainer;

@Service
public class BattleServiceImpl implements BattleService {

	@Autowired
	private BattlePokemonFactory factory;

	@Autowired
	private PokemonTypeService pokemonTypeService;

	@Autowired
	private StatsCalculator calculator;

	@Override
	public Battle createBattle(Trainer trainer, Trainer opponent) {
		Battle newBattle = new Battle();
		BattleTrainer battleTrainer = new BattleTrainer();
		battleTrainer.setName(trainer.getName());
		battleTrainer.setTeam(trainer.getTeam().parallelStream()
				.map(pokemon -> factory.createBattlePokemon(
						pokemonTypeService.getPokemonTypeById(pokemon.getPokemonType()), pokemon.getLevel()))
				.collect(Collectors.toList()));
		battleTrainer.setNextTurn(true);
		BattleTrainer opponentTrainer = new BattleTrainer();
		opponentTrainer.setName(opponent.getName());
		opponentTrainer.setTeam(opponent.getTeam().parallelStream()
				.map(pokemon -> factory.createBattlePokemon(
						pokemonTypeService.getPokemonTypeById(pokemon.getPokemonType()), pokemon.getLevel()))
				.collect(Collectors.toList()));
		opponentTrainer.setNextTurn(false);
		newBattle.setTrainer(battleTrainer);
		newBattle.setOpponent(opponentTrainer);
		newBattle.setUuid(UUID.randomUUID());
		return newBattle;
	}

	@Override
	public Battle attack(Battle current, String trainerName) throws BattleException {
		BattleTrainer attacker = current.getTrainer().getName().equalsIgnoreCase(trainerName) ? current.getTrainer()
				: current.getOpponent();
		BattleTrainer opponent = current.getTrainer().getName().equalsIgnoreCase(attacker.getName())
				? current.getOpponent()
				: current.getTrainer();
		if (attacker.getNextTurn()) {
			BattlePokemon attackerPokemon = attacker.getTeam().stream().filter(pokemon -> pokemon.getAlive() == true)
					.findFirst().get();
			BattlePokemon opponentPokemon = opponent.getTeam().stream().filter(pokemon -> pokemon.getAlive() == true)
					.findFirst().get();
			Integer newOpponentHp = opponentPokemon.getHp()
					- calculator.calculateDamage(attackerPokemon, opponentPokemon) <= 0 ? 0
							: opponentPokemon.getHp() - calculator.calculateDamage(attackerPokemon, opponentPokemon);
			opponentPokemon.setHp(newOpponentHp);
			opponentPokemon.setAlive(opponentPokemon.getHp() == 0 ? false : true);
			opponentPokemon.setKo(!opponentPokemon.getAlive());
			attacker.setNextTurn(false);
			opponent.setNextTurn(true);
		} else {
			throw new BattleException("Not your turn!");

		}
		return current;
	}

}
