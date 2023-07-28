package com.assignment.pokemonapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.pokemonapp.R;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by rizka on 26/07/2023
 */

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}