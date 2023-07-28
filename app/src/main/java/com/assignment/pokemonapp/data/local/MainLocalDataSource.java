package com.assignment.pokemonapp.data.local;

import androidx.lifecycle.LiveData;

import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;

import java.util.List;

/**
 * Created by rizka on 28/07/2023
 */

public class MainLocalDataSource {

    private final PokemonCaughtDao pokemonCaughtDao;

    public MainLocalDataSource(PokemonCaughtDao pokemonCaughtDao) {
        this.pokemonCaughtDao = pokemonCaughtDao;
    }

    public LiveData<List<PokemonCaught>> getCaught() {
        return pokemonCaughtDao.getCaught();
    }

    public void insertCaught(PokemonCaught pokemonCaught) {
        pokemonCaughtDao.insertCaught(pokemonCaught);
    }

}
