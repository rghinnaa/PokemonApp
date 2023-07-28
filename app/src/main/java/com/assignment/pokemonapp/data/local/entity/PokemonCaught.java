package com.assignment.pokemonapp.data.local.entity;

import static com.assignment.pokemonapp.utils.Const.POKEMON_CAUGHT;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by rizka on 28/07/2023
 */

@Entity(tableName = POKEMON_CAUGHT)
public class PokemonCaught implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String nameGiven;
    public int number;
    public String image;
    public String type1 = "";
    public String type2 = "";

    public PokemonCaught(String name, String nameGiven, int number, String image, String type1, String type2) {
        this.name = name;
        this.nameGiven = nameGiven;
        this.number = number;
        this.image = image;
        this.type1 = type1;
        this.type2 = type2;
    }
}
