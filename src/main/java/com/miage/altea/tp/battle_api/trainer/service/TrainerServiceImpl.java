package com.miage.altea.tp.battle_api.trainer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.battle_api.trainer.bo.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	@Qualifier("trainerApiRestTemplate")
	RestTemplate template;
	
	@Value("${trainer.service.url}")
	private String url;
	
	@Override
	public Trainer getTrainerByName(String trainerName) {
		ResponseEntity<Trainer> finded = this.template.getForEntity(this.url+"/trainers/"+trainerName, Trainer.class);
		return finded.hasBody() ? finded.getBody() : null;
	}

}
