REPLACE INTO `Dresseur` (`bannedStatus`, `idDresseur`, `name`) VALUES
                                                                  (b'0', 1, 'Sacha'),
                                                                  (b'0', 2, 'Jonny');


REPLACE INTO `Pokemon` (`idPokemon`, `isAdopted`, `pokeScore`, `name`, `type`)
VALUES
    (1, false ,100, 'ghulamo', 'feu'),
    (2, false, 80 ,'nabilo', 'plante'),
    (3, false, 90, 'lilio', 'eau'),
    (4, false, 95,'naloufio', 'eau'),
    (5, false, 70,'paudo', 'feu');


REPLACE INTO `BankAccount` (`balance`, `dresseur_idDresseur`, `idBankAccount`) VALUES
                                                                                  (200, 2, 11122334),
                                                                                  (100, 1, 12345678);