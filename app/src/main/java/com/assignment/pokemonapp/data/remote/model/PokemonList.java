package com.assignment.pokemonapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rizka on 26/07/2023
 */

public class PokemonList {
    @SerializedName("count")
    public int count;
    @SerializedName("next")
    public String next;
    @SerializedName("previous")
    public String previous;
    @SerializedName("results")
    public List<Result> results;

    public static class Result {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public List<Result> getResults() {
        return results;
    }
}