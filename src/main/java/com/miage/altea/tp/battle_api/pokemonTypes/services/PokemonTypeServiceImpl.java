package com.miage.altea.tp.battle_api.pokemonTypes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.battle_api.pokemonTypes.bo.PokemonType;
@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${pokemonType.service.url}")
	private String url;

	@Override
	public PokemonType getPokemonTypeById(Integer identifier) {
		ResponseEntity<PokemonType> pokemon = this.restTemplate.getForEntity(this.url + "/pokemon-types/" + identifier,
				PokemonType.class);
		return pokemon.hasBody() ? pokemon.getBody() : null;
	}

}
