package com.assignment.pokemonapp.data.remote.model.mapper;

import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;

import java.util.stream.Collectors;

/**
 * Created by rizka on 27/07/2023
 */

public class mapper {

    public static PokemonPaging mapPaging(String url, Pokemon pokemon) {
        return new PokemonPaging(
                url,
                pokemon.name,
                pokemon.id,
                pokemon.sprites.other.officialArtwork.frontDefault,
                pokemon.types.stream().map(it -> it.typeSub.name).collect(Collectors.toList())
        );
    }

    public static PokemonDetail mapDetail(Pokemon pokemon) {
        return new PokemonDetail(
                pokemon.name,
                pokemon.id,
                pokemon.sprites.other.officialArtwork.frontDefault,
                pokemon.types.stream().map(it -> it.typeSub.name).collect(Collectors.toList()),
                pokemon.abilities.stream().map(it -> it.ability.name).collect(Collectors.toList()),
                pokemon.weight,
                pokemon.height,
                pokemon.stats.stream().map(it -> new PokemonStat(it.stat.name, it.baseStat)).collect(Collectors.toList())
        );
    }

    public static PokemonSpeciesDetail mapSpecies(PokemonSpecies pokemonSpecies) {
        String habitat, shape;
        if(pokemonSpecies.habitat == null) habitat = "";
        else habitat = pokemonSpecies.habitat.name;

        if(pokemonSpecies.shape == null) shape = "";
        else shape = pokemonSpecies.shape.name;

        return new PokemonSpeciesDetail(
                pokemonSpecies.color.name,
                pokemonSpecies.eggGroups.stream().map(it -> it.name).collect(Collectors.toList()),
                pokemonSpecies.hatchCounter,
                pokemonSpecies.captureRate,
                pokemonSpecies.growthRate.name,
                habitat,
                shape
        );
    }
}
