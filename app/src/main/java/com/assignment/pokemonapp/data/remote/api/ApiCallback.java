package com.assignment.pokemonapp.data.remote.api;

import static com.assignment.pokemonapp.utils.Const.*;

import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonList;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rizka on 26/07/2023
 */

public interface ApiCallback {

    @GET(POKEMON_LIST)
    Single<PokemonList> requestPokemonList(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET(POKEMON)
    Call<Pokemon> requestPokemon(
            @Path("id") int id
    );

    @GET(POKEMON_SPECIES)
    Call<PokemonSpecies> requestPokemonSpecies(
            @Path("id") int id
    );

}
