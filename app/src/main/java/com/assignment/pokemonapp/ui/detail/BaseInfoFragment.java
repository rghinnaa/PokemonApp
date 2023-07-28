package com.assignment.pokemonapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;
import com.assignment.pokemonapp.databinding.FragmentBaseInfoBinding;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rizka on 28/07/2023
 */

public class BaseInfoFragment extends Fragment {

    FragmentBaseInfoBinding binding;

    Gson gson = new Gson();

    private static final String POKEMON_DETAIL = "pokemon_detail";
    private static final String POKEMON_SPECIES = "pokemon_species";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentBaseInfoBinding.inflate(inflater, parent, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDetail();
        setSpecies();
    }

    public BaseInfoFragment() {
        // Empty constructor is required in Java for fragments.
    }

    public void setDetail() {
        if(getArguments() != null) {
            String detail = getArguments().getString(POKEMON_DETAIL);
            PokemonDetail item = gson.fromJson(detail, PokemonDetail.class);

            double height = item.pokemonHeight;
            double weight = item.pokemonHeight;

            binding.tvHeight.setText(binding.getRoot().getContext()
                    .getString(R.string.heightCm, String.valueOf(height * 10.0))
            );
            binding.tvWeight.setText(binding.getRoot().getContext()
                    .getString(R.string.weightKg, String.valueOf(weight / 10.0))
            );

            if(item.pokemonAbility.size() > 1) {
                List<String> data = item.pokemonAbility.stream().map(name ->
                        name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
                ).collect(Collectors.toList());
                binding.tvAbilities.setText(String.join(", ", data));
            } else {
                String data = item.pokemonAbility.get(0).substring(0, 1)
                        .toUpperCase() + item.pokemonAbility.get(0).substring(1).toLowerCase();
                binding.tvAbilities.setText(data);
            }
        }
    }

    public void setSpecies() {
        if(getArguments() != null) {
            String species = getArguments().getString(POKEMON_SPECIES);
            PokemonSpeciesDetail item = gson.fromJson(species, PokemonSpeciesDetail.class);

            String color = item.pokemonColor.substring(0, 1)
                    .toUpperCase() + item.pokemonColor.substring(1).toLowerCase();
            String habitat = item.pokemonHabitat.substring(0, 1)
                    .toUpperCase() + item.pokemonHabitat.substring(1).toLowerCase();
            binding.tvColor.setText(color);
            binding.tvEggCycle.setText(String.valueOf(item.pokemonHatch));
            binding.tvHabitat.setText(habitat);

            if(item.pokemonEgg.size() > 1) {
                List<String> data = item.pokemonEgg.stream().map(name ->
                        name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
                ).collect(Collectors.toList());
                binding.tvEggGroup.setText(String.join(", ", data));
            } else {
                String data = item.pokemonEgg.get(0).substring(0, 1)
                        .toUpperCase() + item.pokemonEgg.get(0).substring(1).toLowerCase();
                binding.tvEggGroup.setText(data);
            }
        }
    }

    public static BaseInfoFragment getInstance(String detail, String species) {
        BaseInfoFragment baseInfoFragment = new BaseInfoFragment();

        Bundle arguments = new Bundle();
        arguments.putString(POKEMON_DETAIL, detail);
        arguments.putString(POKEMON_SPECIES, species);

        baseInfoFragment.setArguments(arguments);

        return baseInfoFragment;
    }

}
