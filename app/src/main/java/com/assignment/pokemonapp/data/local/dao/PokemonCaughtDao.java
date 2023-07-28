package com.assignment.pokemonapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.pokemonapp.data.local.entity.PokemonCaught;

import java.util.List;

/**
 * Created by rizka on 28/07/2023
 */

@Dao
public interface PokemonCaughtDao {
    @Query("SELECT * FROM POKEMON_CAUGHT_ENTITY ORDER BY id DESC")
    LiveData<List<PokemonCaught>> getCaught();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCaught(PokemonCaught caught);
}
