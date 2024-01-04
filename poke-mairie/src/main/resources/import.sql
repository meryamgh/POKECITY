REPLACE INTO `Dresseur` (`bannedStatus`, `idDresseur`, `name`) VALUES
                                                                  (b'0', 1, 'Sacha'),
                                                                  (b'0', 2, 'Jonny');


REPLACE INTO `Pokemon` (`idPokemon`, `isAdopted`, `pokeScore`, `name`, `type`, `localisation`)
VALUES
       (1, true ,200, 'ghulamo', 'feu', 'mairie'),
        (2, false, 80 ,'nabilo', 'plante', 'store'),
        (3, false, 90, 'lilio', 'eau', 'store'),
        (4, false, 95,'naloufio', 'eau', 'store'),
        (5, false, 70,'paudo', 'feu', 'store'),
        (6, false, 50,'nherbo', 'plante', 'store'),
        (7, false, 100,'carlo', 'feu', 'store'),
        (8, false, 50,'ounissio', 'eau', 'store'),
        (9, false, 110,'chenno', 'plante', 'store'),
        (10, false, 110,'olivio', 'plante', 'store'),
        (11, false, 50,'chiriaco', 'eau', 'store'),
        (12, false, 110,'clavelo', 'feu', 'store');

REPLACE INTO `Dresseur_Pokemon` (`Dresseur_idDresseur`, `pokedex_idPokemon`) VALUES ('1', '1');
REPLACE INTO `BankAccount` (`balance`, `dresseur_idDresseur`, `idBankAccount`) VALUES
                                                                                  (200, 2, 11122334),
                                                                                  (150, 1, 12345678);