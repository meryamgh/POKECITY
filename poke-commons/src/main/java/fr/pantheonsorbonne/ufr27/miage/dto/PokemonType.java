package fr.pantheonsorbonne.ufr27.miage.dto;

public enum PokemonType {

    FEU,

    EAU,

    PLANTE;

    public boolean isStrongAgainst(PokemonType opponentType) {
        return switch (this) {
            case FEU -> opponentType == PokemonType.PLANTE;
            case EAU -> opponentType == PokemonType.FEU;
            case PLANTE -> opponentType == PokemonType.EAU;
            default -> false;
        };
    }

    public boolean isWeakAgainst(PokemonType opponentType) {
        return switch (this) {
            case FEU -> opponentType == PokemonType.EAU;
            case EAU -> opponentType == PokemonType.PLANTE;
            case PLANTE -> opponentType == PokemonType.FEU;
            default -> false;
        };
    }


}
