package com.assignment.pokemonapp.data.remote.source;

import static android.text.TextUtils.isEmpty;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.PagingRx;
import androidx.paging.rxjava3.RxPagingSource;

import com.assignment.pokemonapp.data.remote.api.ApiCallback;
import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonList;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.data.remote.model.mapper.mapper;
import com.assignment.pokemonapp.ui.main.adapter.PokemonPagingAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonListPagingSource extends RxPagingSource<Integer, PokemonPaging> {
    private final ApiCallback apiCallback;

    public PokemonListPagingSource(ApiCallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public Single<LoadResult<Integer, PokemonPaging>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 0;
            Single<PokemonList> response = apiCallback.requestPokemonList(25, page);
            return response.subscribeOn(Schedulers.io())
                    .map(PokemonList::getResults).map(result -> toLoadResult(
                            result.stream().map(it -> {
                                Pokemon data = new Pokemon();
                                try {
                                    data = apiCallback.requestPokemon(
                                            Integer.parseInt(substringAfterLast(it.url.substring(0, it.url.length() - 1), "/"))
                                    ).execute().body();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                assert data != null;
                                return mapper.mapPaging(
                                        substringAfterLast(it.url.substring(0, it.url.length() - 1), "/"), data);
                            }).collect(Collectors.toList()),
                            page
                    ));

        } catch (Exception e) {
            return Single.just(new LoadResult.Error<>(e));
        }
    }

    private LoadResult<Integer, PokemonPaging> toLoadResult(List<PokemonPaging> data, int page) {
        return new LoadResult.Page<>(
                data,
                page == 0 ? null : page - 25,
                page + 25);
    }

    public static String substringAfterLast(final String str, final String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(separator)) {
            return "";
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == str.length() - separator.length()) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, PokemonPaging> pagingState) {
        return null;
    }

}
