package com.assignment.pokemonapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rizka on 26/07/2023
 */

public class Pokemon {
    @SerializedName("abilities")
    public List<Ability> abilities;
    @SerializedName("base_experience")
    public int baseExperience;
    @SerializedName("forms")
    public List<Form> forms;
    @SerializedName("game_indices")
    public List<GameIndice> gameIndices;
    @SerializedName("height")
    public int height;
    @SerializedName("id")
    public int id;
    @SerializedName("is_default")
    public Boolean isDefault;
    @SerializedName("location_area_encounters")
    public String locationAreaEncounters;
    @SerializedName("moves")
    public List<Move> moves;
    @SerializedName("name")
    public String name;
    @SerializedName("order")
    public int order;
    @SerializedName("species")
    public Species species;
    @SerializedName("sprites")
    public Sprites sprites;
    @SerializedName("stats")
    public List<Stat> stats;
    @SerializedName("types")
    public List<Type> types;
    @SerializedName("weight")
    public int weight;

    public static class Ability {
        @SerializedName("ability")
        public AbilitySub ability;
        @SerializedName("is_hidden")
        public Boolean isHidden;
        @SerializedName("slot")
        public int slot;

        public static class AbilitySub {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Form {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class GameIndice {
        @SerializedName("game_index")
        public int gameIndex;
        @SerializedName("version")
        public Version version;

        public static class Version {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Move {
        @SerializedName("move")
        public MoveSub move;
        @SerializedName("version_group_details")
        public List<VersionGroupDetail> versionGroupDetails;

        public static class MoveSub {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }

        public static class VersionGroupDetail {
            @SerializedName("level_learned_at")
            public int levelLearnedAt;
            @SerializedName("move_learn_method")
            public MoveLearnMethod moveLearnMethod;
            @SerializedName("version_group")
            public VersionGroup versionGroup;

            public static class MoveLearnMethod {
                @SerializedName("name")
                public String name;
                @SerializedName("url")
                public String url;
            }

            public static class VersionGroup {
                @SerializedName("name")
                public String name;
                @SerializedName("url")
                public String url;
            }
        }
    }

    public static class Species {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String url;
    }

    public static class Sprites {
        @SerializedName("back_default")
        public String backDefault;
        @SerializedName("back_female")
        public String backFemale;
        @SerializedName("back_shiny")
        public String backShiny;
        @SerializedName("back_shiny_female")
        public String backShinyFemale;
        @SerializedName("front_default")
        public String frontDefault;
        @SerializedName("front_female")
        public String frontFemale;
        @SerializedName("front_shiny")
        public String frontShiny;
        @SerializedName("front_shiny_female")
        public String frontShinyFemale;
        @SerializedName("other")
        public Other other;
        @SerializedName("versions")
        public Versions version;

        public static class Other {
            @SerializedName("dream_world")
            public DreamWorld dreamWorld;
            @SerializedName("home")
            public Home home;
            @SerializedName("official-artwork")
            public OfficialArtwork officialArtwork;

            public static class DreamWorld {
                @SerializedName("front_default")
                public String frontDefault;
                @SerializedName("front_female")
                public String frontFemale;
            }

            public static class Home {
                @SerializedName("front_default")
                public String frontDefault;
                @SerializedName("front_female")
                public String frontFemale;
                @SerializedName("front_shiny")
                public String frontShiny;
                @SerializedName("front_shiny_female")
                public String frontShinyFemale;
            }

            public static class OfficialArtwork {
                @SerializedName("front_default")
                public String frontDefault;
            }
        }

        public static class Versions {
            @SerializedName("generation-i")
            public GenerationI generationI;
            @SerializedName("generation-ii")
            public GenerationIi generationIi;
            @SerializedName("generation-iii")
            public GenerationIii generationIii;
            @SerializedName("generation-iv")
            public GenerationIv generationIv;
            @SerializedName("generation-v")
            public GenerationV generationV;
            @SerializedName("generation-vi")
            public GenerationVi generationVi;
            @SerializedName("generation-vii")
            public GenerationVii generationVii;
            @SerializedName("generation-viii")
            public GenerationViii generationViii;

            public static class GenerationI {
                @SerializedName("red-blue")
                public RedBlue redBlue;
                @SerializedName("yellow")
                public Yellow yellow;

                public static class RedBlue {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_gray")
                    public String backGray;
                    @SerializedName("back_transparent")
                    public String backTransparent;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_gray")
                    public String frontGray;
                    @SerializedName("front_transparent")
                    public String frontTransparent;
                }

                public static class Yellow {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_gray")
                    public String backGray;
                    @SerializedName("back_transparent")
                    public String backTransparent;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_gray")
                    public String frontGray;
                    @SerializedName("front_transparent")
                    public String frontTransparent;
                }
            }

            public static class GenerationIi {
                @SerializedName("crystal")
                public Crystal crystal;
                @SerializedName("gold")
                public  Gold gold;
                @SerializedName("silver")
                public Silver silver;

                public static class Crystal {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_transparent")
                    public String backShinyTransparent;
                    @SerializedName("back_transparent")
                    public String backTransparent;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_transparent")
                    public String frontShinyTransparent;
                    @SerializedName("front_transparent")
                    public String frontTransparent;
                }

                public static class Gold {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_transparent")
                    public String frontTransparent;
                }

                public static class Silver {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_transparent")
                    public String frontTransparent;
                }
            }

            public static class GenerationIii {
                @SerializedName("emerald")
                public Emerald emerald;
                @SerializedName("firered-leafgreen")
                public  FireredLeafgreen fireredLeafgreen;
                @SerializedName("ruby-sapphire")
                public RubySapphire rubySapphire;

                public static class Emerald {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                }

                public static class FireredLeafgreen {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                }

                public static class RubySapphire {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                }
            }

            public static class GenerationIv {
                @SerializedName("diamond-pearl")
                public DiamondPearl diamondPearl;
                @SerializedName("heartgold-soulsilver")
                public HeartgoldSoulsilver heartgoldSoulsilver;
                @SerializedName("platinum")
                public Platinum platinum;

                public static class DiamondPearl {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_female")
                    public String backFemale;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_female")
                    public String backShinyFemale;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }

                public static class HeartgoldSoulsilver {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_female")
                    public String backFemale;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_female")
                    public String backShinyFemale;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }

                public static class Platinum {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_female")
                    public String backFemale;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_female")
                    public String backShinyFemale;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }
            }

            public static class GenerationV {
                @SerializedName("black-white")
                public BlackWhite blackWhite;

                public static class BlackWhite {
                    @SerializedName("animated")
                    public Animated animated;
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_female")
                    public String backFemale;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_female")
                    public String backShinyFemale;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }

                public static class Animated {
                    @SerializedName("back_default")
                    public String backDefault;
                    @SerializedName("back_female")
                    public String backFemale;
                    @SerializedName("back_shiny")
                    public String backShiny;
                    @SerializedName("back_shiny_female")
                    public String backShinyFemale;
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }
            }

            public static class GenerationVi {
                @SerializedName("omegaruby-alphasapphire")
                public OmegarubyAlphasapphire omegarubyAlphasapphire;
                @SerializedName("x-y")
                public XY xY;

                public static class OmegarubyAlphasapphire {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }

                public static class XY {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }
            }

            public static class GenerationVii {
                @SerializedName("icons")
                public Icons icons;
                @SerializedName("ultra-sun-ultra-moon")
                public UltraSunUltraMoon ultraSunUltraMoon;

                public static class Icons {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                }

                public static class UltraSunUltraMoon {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                    @SerializedName("front_shiny")
                    public String frontShiny;
                    @SerializedName("front_shiny_female")
                    public String frontShinyFemale;
                }
            }

            public static class GenerationViii {
                @SerializedName("icons")
                public Icons icons;

                public static class Icons {
                    @SerializedName("front_default")
                    public String frontDefault;
                    @SerializedName("front_female")
                    public String frontFemale;
                }
            }
        }
    }

    public static class Stat {
        @SerializedName("base_stat")
        public int baseStat;
        @SerializedName("effort")
        public int effort;
        @SerializedName("stat")
        public StatSub stat;

        public static class StatSub {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public static class Type {
        @SerializedName("slot")
        public int slot;
        @SerializedName("type")
        public TypeSub typeSub;

        public static class TypeSub {
            @SerializedName("name")
            public String name;
            @SerializedName("url")
            public String url;
        }
    }

    public Pokemon getResults() {
        return this;
    }
}