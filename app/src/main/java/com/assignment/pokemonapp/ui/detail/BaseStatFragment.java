package com.assignment.pokemonapp.ui.detail;

import static com.assignment.pokemonapp.utils.Const.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonStat;
import com.assignment.pokemonapp.databinding.FragmentBaseStatBinding;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rizka on 28/07/2023
 */

public class BaseStatFragment extends Fragment {

    FragmentBaseStatBinding binding;

    Gson gson = new Gson();

    private static final String POKEMON_DETAIL = "detail";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentBaseStatBinding.inflate(inflater, parent, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDetail();
    }

    public BaseStatFragment() {

    }

    public void setDetail() {
        if(getArguments() != null) {
            String detail = getArguments().getString(POKEMON_DETAIL);
            List<PokemonStat> item = gson.fromJson(detail, PokemonDetail.class).pokemonStats;

            item.forEach(element -> {
                int baseStat = element.baseStat;

                switch (element.name) {
                    case HP:
                        binding.pgHP.setProgress(baseStat);
                        binding.tvHpNumber.setText(String.valueOf(baseStat));
                        break;
                    case ATTACK:
                        binding.pgAttack.setProgress(baseStat);
                        binding.tvAttackNumber.setText(String.valueOf(baseStat));
                        break;
                    case DEFENSE:
                        binding.pgDefense.setProgress(baseStat);
                        binding.tvDefenseNumber.setText(String.valueOf(baseStat));
                        break;
                    case SPECIAL_ATTACK:
                        binding.pgSpecialAttack.setProgress(baseStat);
                        binding.tvSpecialAttackNumber.setText(String.valueOf(baseStat));
                        break;
                    case SPECIAL_DEFENSE:
                        binding.pgSpecialDefense.setProgress(baseStat);
                        binding.tvSpecialDefenseNumber.setText(String.valueOf(baseStat));
                        break;
                    case SPEED:
                        binding.pgSpeed.setProgress(baseStat);
                        binding.tvSpeedNumber.setText(String.valueOf(baseStat));
                        break;
                }

            });
        }
    }

    public static BaseStatFragment getInstance(String detail) {
        BaseStatFragment baseStatFragment = new BaseStatFragment();

        Bundle arguments = new Bundle();
        arguments.putString(POKEMON_DETAIL, detail);

        baseStatFragment.setArguments(arguments);

        return baseStatFragment;
    }
}
