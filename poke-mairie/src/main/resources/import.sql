REPLACE INTO `Dresseur` (`bannedStatus`, `idDresseur`, `name`) VALUES
                                                                  (b'0', 1, 'Sacha'),
                                                                  (b'0', 2, 'Jonny');


REPLACE INTO `Pokemon` (`idPokemon`, `isAdopted`, `pokeScore`, `name`, `type`, `localisation`)
VALUES
        (1, false, 200,'ghulamo', 'FEU', 'mairie'),
        (2, false, 80 ,'nabilo', 'PLANTE', 'store'),
        (3, false, 90, 'lilio', 'EAU', 'store'),
        (4, false, 95,'naloufio', 'EAU', 'store'),
        (5, false, 70,'paudo', 'FEU', 'store'),
        (6, false, 50,'machoc', 'PLANTE', 'store'),
        (7, true ,10, 'carlo', 'FEU', 'store'),
        (8, false, 50,'ounissio', 'EAU', 'store'),
        (9, false, 110,'chenno', 'PLANTE', 'store'),
        (10, false, 110,'olivio', 'PLANTE', 'store'),
        (11, false, 50,'chiriaco', 'EAU', 'store'),
        (12, false, 110,'clavelo', 'FEU', 'store');

REPLACE INTO `Dresseur_Pokemon` (`Dresseur_idDresseur`, `pokedex_idPokemon`) VALUES ('1', '1');
REPLACE INTO `BankAccount` (`balance`, `dresseur_idDresseur`, `idBankAccount`) VALUES
                                                                                  (200, 2, 11122334),
                                                                                  (300, 1, 12345678);