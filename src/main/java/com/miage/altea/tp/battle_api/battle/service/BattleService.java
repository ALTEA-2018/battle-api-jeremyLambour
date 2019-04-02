package com.miage.altea.tp.battle_api.battle.service;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattleException;
import com.miage.altea.tp.battle_api.trainer.bo.Trainer;

public interface BattleService {

	public Battle createBattle(Trainer trainer,Trainer opponent);
	public Battle attack(Battle current,String trainerName) throws BattleException;
}
