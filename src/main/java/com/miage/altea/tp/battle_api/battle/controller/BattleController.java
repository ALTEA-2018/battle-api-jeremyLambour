package com.miage.altea.tp.battle_api.battle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattleException;
import com.miage.altea.tp.battle_api.battle.service.BattleService;
import com.miage.altea.tp.battle_api.trainer.bo.Trainer;
import com.miage.altea.tp.battle_api.trainer.service.TrainerService;

@RestController
@RequestMapping("/battles")
public class BattleController {

	private HashMap<UUID, Battle> currentBattle = new HashMap<UUID, Battle>();

	@Autowired
	private BattleService service;

	@Autowired
	private TrainerService trainerService;

	@PostMapping("/")
	public Battle createBattle(@RequestParam("trainer") String trainer, @RequestParam("opponent") String opponent) {
		Trainer battleTrainer = trainerService.getTrainerByName(trainer);
		Trainer battleOpponent = trainerService.getTrainerByName(opponent);
		Battle battle = service.createBattle(battleTrainer, battleOpponent);
		currentBattle.put(battle.getUuid(), battle);
		return battle;
	}

	@GetMapping("/{idBattle}")
	public Battle getBattleFromUuid(@PathVariable UUID idBattle) {
		return currentBattle.get(idBattle);
	}

	@GetMapping("/")
	public List<Battle> getAllBattles() {
		return currentBattle.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
	}

	@PostMapping("/{idBattle}/{trainer}/attack")
	public ResponseEntity<?> attack(@PathVariable UUID idBattle, @PathVariable String trainer) {
		Battle battle = currentBattle.get(idBattle);

		try {
			Battle newStateBattle = service.attack(currentBattle.get(idBattle), trainer);
			currentBattle.replace(battle.getUuid(), newStateBattle);
			return new ResponseEntity<Battle>(newStateBattle, HttpStatus.ACCEPTED);
		} catch (BattleException e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
