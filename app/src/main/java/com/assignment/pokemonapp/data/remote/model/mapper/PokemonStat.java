package com.assignment.pokemonapp.data.remote.model.mapper;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonStat {

    public String name;
    public int baseStat;

    public PokemonStat(
            String name,
            int baseStat
    ) {
        this.name = name;
        this.baseStat = baseStat;
    }
}
