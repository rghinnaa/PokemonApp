package com.assignment.pokemonapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rizka on 26/07/2023
 */

public class PokemonSpecies {
    @SerializedName("base_happiness")
    public int baseHappiness;
    @SerializedName("capture_rate")
    public int captureRate;
    @SerializedName("color")
    public Color color;
    @SerializedName("egg_groups")
    public List<EggGroup> eggGroups;
    @SerializedName("evolution_chain")
    public EvolutionChain evolutionChain;
    @SerializedName("evolves_from_species")
    public Object evolvesFromSpecies;
    @SerializedName("flavor_text_entries")
    public List<FlavorTextEntry> flavorTextEntries;
    @SerializedName("form_descriptions")
    public List<Object> formDescriptions;
    @SerializedName("forms_switchable")
    public Boolean formsSwitchable;
    @SerializedName("gender_rate")
    public int genderRate;
    @SerializedName("genera")
    public List<Genera> genera;
    @SerializedName("generation")
    public Generation generation;
    @SerializedName("growth_rate")
    public GrowthRate growthRate;
    @SerializedName("habitat")
    public Habitat habitat;
    @SerializedName("has_gender_differences")
    public Boolean hasGenderDifferences;
    @SerializedName("hatch_counter")
    public int hatchCounter;
    @SerializedName("id")
    public int id;
    @SerializedName("is_baby")
    public Boolean isBaby;
    @SerializedName("is_legendary")
    public Boolean isLegendary;
    @SerializedName("is_mythical")
    public Boolean isMythical;
    @SerializedName("name")
    public String name;
    @SerializedName("names")
    public List<Name> names;
    @SerializedName("order")
    public int order;
    @SerializedName("pal_park_encounters")
    public List<PalParkEncounter> palParkEncounters;
    @SerializedName("pokedex_numbers")
    public List<PokedexNumber> pokedexNumbers;
    @SerializedName("shape")
    public Shape shape;
    @SerializedName("varieties")
    public List<Variety> varieties;

    public static class Color {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class EggGroup {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class EvolutionChain {
        @SerializedName("url")
        public String url;
    }

    public static class FlavorTextEntry {
        @SerializedName("flavor_text")
        public String flavorText;
        @SerializedName("language")
        public Language language;
        @SerializedName("version")
        public Version version;

        public static class Language {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }

        public static class Version {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Genera {
        @SerializedName("genus")
        public String genus;
        @SerializedName("language")
        public Language language;

        public static class Language {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Generation {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class GrowthRate {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class Habitat {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class Name {
        @SerializedName("language")
        public Language language;
        @SerializedName("name")
        public String name;

        public static class Language {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class PalParkEncounter {
        @SerializedName("area")
        public Area area;
        @SerializedName("base_score")
        public int baseScore;
        @SerializedName("rate")
        public int rate;

        public static class Area {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class PokedexNumber {
        @SerializedName("entry_number")
        public int entryNumber;
        @SerializedName("pokedex")
        public Pokedex pokedex;

        public static class Pokedex {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Shape {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class Variety {
        @SerializedName("is_default")
        public Boolean isDefault;
        @SerializedName("pokemon")
        public Pokemon pokemon;

        public static class Pokemon {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }
}
