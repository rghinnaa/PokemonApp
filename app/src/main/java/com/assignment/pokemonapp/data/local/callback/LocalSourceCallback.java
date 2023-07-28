package com.assignment.pokemonapp.data.local.callback;

import androidx.lifecycle.LiveData;

import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;

import java.util.List;

/**
 * Created by rizka on 28/07/2023
 */

public interface LocalSourceCallback {


    void insertPokemon(PokemonCaught pokemonCaught);

    LiveData<List<PokemonCaught>> getCaughtPokemon();

}
