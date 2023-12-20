REPLACE INTO `Dresseur` (`idDresseur`, `name`) VALUES
                                                  (1, 'Sacha'),
                                                  (2, 'Thomas');


REPLACE INTO `Dresseur_Pokemon` (`Dresseur_idDresseur`, `pokedex_idPokemon`) VALUES
    (1, 2);


REPLACE INTO `Pokemon` (`dresseur_idDresseur`, `idPokemon`, `pokeScore`, `prix`, `name`) VALUES
    (1, 2, 0, 54, 'Pickachu');
