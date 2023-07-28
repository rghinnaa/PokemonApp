package com.assignment.pokemonapp.data.remote.model.mapper;

import java.util.List;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonDetail {

    public String pokemonName, pokemonImage;
    public int pokemonNumber, pokemonWeight, pokemonHeight;
    public List<String> pokemonType, pokemonAbility;
    public List<PokemonStat> pokemonStats;

    public PokemonDetail(
            String pokemonName,
            int pokemonNumber,
            String pokemonImage,
            List<String> pokemonType,
            List<String> pokemonAbility,
            int pokemonWeight,
            int pokemonHeight,
            List<PokemonStat> pokemonStats
    ) {
        this.pokemonName = pokemonName;
        this.pokemonNumber = pokemonNumber;
        this.pokemonImage = pokemonImage;
        this.pokemonType = pokemonType;
        this.pokemonAbility = pokemonAbility;
        this.pokemonHeight = pokemonHeight;
        this.pokemonWeight = pokemonWeight;
        this.pokemonStats = pokemonStats;
    }

}
