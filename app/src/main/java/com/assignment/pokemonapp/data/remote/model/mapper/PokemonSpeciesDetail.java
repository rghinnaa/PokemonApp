package com.assignment.pokemonapp.data.remote.model.mapper;

import java.util.List;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonSpeciesDetail {

    public String pokemonColor, pokemonGrowth, pokemonHabitat, pokemonShape;
    public int pokemonHatch, pokemonCatchRate;
    public List<String> pokemonEgg;

    public PokemonSpeciesDetail(
            String pokemonColor,
            List<String> pokemonEgg,
            int pokemonHatch,
            int pokemonCatchRate,
            String pokemonGrowth,
            String pokemonHabitat,
            String pokemonShape
    ) {
        this.pokemonColor = pokemonColor;
        this.pokemonEgg = pokemonEgg;
        this.pokemonHatch = pokemonHatch;
        this.pokemonCatchRate = pokemonCatchRate;
        this.pokemonGrowth = pokemonGrowth;
        this.pokemonHabitat = pokemonHabitat;
        this.pokemonShape = pokemonShape;
    }
}
