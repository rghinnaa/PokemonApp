package com.assignment.pokemonapp.data.remote.model.mapper;

import java.util.List;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonPaging {

    public String pokemonUrl, pokemonName, pokemonImage;
    public int pokemonNumber;
    public List<String> pokemonType;
    public PokemonPaging(
            String pokemonUrl,
            String pokemonName,
            int pokemonNumber,
            String pokemonImage,
            List<String> pokemonType
    ) {
        this.pokemonUrl = pokemonUrl;
        this.pokemonName = pokemonName;
        this.pokemonNumber = pokemonNumber;
        this.pokemonImage = pokemonImage;
        this.pokemonType = pokemonType;
    }
}