## Objectifs du système à modéliser

On propose de modéliser un système de gestion (master) du pokecity pouvant supporter plusieurs pokemons (pokemon) et dresseurs (dresseur). Le système master gère le pokestore, la pokebank, le pokeschool, la mairie, l'infirmerie, les différents dresseurs se combattant entre eux, l'inscription des pokemons dans le pokeschool, acheter / revendre / échanger un pokemon dans le pokestore, la gestion des pokescores dans la pokebank, l'enregistrement des inscriptions au pokeschool se fait à la mairie avec le nombre de pokémons pour chaque dresseur.

Chaque dresseur possède un pokémon avec 20 pokescores au départ, les pokemons et les pokescores peuvent évoluer en gagnant les combats.
Le pokescore diminue avec les achats et perte d'un combat. Le nombre de pokemons peut augmenter avec l'achat d'autres pokemons dans le pokestore.
La mairie recense les dresseurs ainsi que leurs pokemons respectifs, ainsi que le score associé et leur inscription ou non au pokeschool.

### Communications entre systèmes

La mairie reçoit une notification lorsque :
 - un dresseur inscrit son pokemon dans le pokeschool (message du pokeschool)
 - un dresseur achète / revent / échange un pokemon (toutes les actions liées au pokestore : message du pokestore)
 - un dresseur n'a plus assez de pokescore pour acquérir un pokemon et que son/ses pokemons ont 0 score (éliminer ce dresseur du jeu : message du pokestore)

L'infirmerie reçoit une notification à chaque fin de combat : 
- le pokemon qui a perdu (message du système de combat)

Le pokestore reçoit une notification lorsque :
- le dresseur du pokemon perdant n'a pas assez de pokescore pour le soigner mais assez de pokescore pour acheter un pokemon (message de l'infirmerie)

La pokebank reçoit une notification lorsque :
- le dresseur soigne son pokemon (message de l'infirmerie) : diminution du pokescore
- le dresseur effectue une opération au pokestore (achat : diminution, revente : augmentation) (message du pokestore)
- le dresseur inscrit son pokemon au pokeschool (message du pokeschool : diminution du pokescore)
- à chaque fin de combat lorsque le gagnant gagne un certain nombre de pokescore (message du système de combat)

### Descriptions des systèmes

Détail : Système de combat : 
Acteurs : Deux dresseurs avec un pokemon chacun
Fonctionnalités : Attaques
Chaque combat est associé à une somme à gagner (pokescore fixé)

![Fight (1)](https://github.com/meryamgh/POKECITY/assets/113671198/210c447a-5aa3-408c-b2cf-c502614a3ef4)

![Buy a Pokemon](https://github.com/meryamgh/POKECITY/assets/113671198/17519768-0bc6-4ff1-b55a-587de70d18bb)
![send_Pokemon_School](https://github.com/meryamgh/POKECITY/assets/113671198/48e78faa-5a70-4ce0-af42-d5135ec59ba7)


### Exigences

Un Pokemon ne doit pas pouvoir effectuer des actions simultanément (combat, soin et école).

Le dresseur doit pouvoir acheter des Pokemon, les soigner, combattres et aller a l'école.

Il y a 3 catégories de Pokemon (plante, feu et eau).

Un dresseur doit avoir un pokémon ayant un pokescore = 50 au début du jeu.

Les combats doivent se faire entre un pokemon du dresseur contre un pokemon prélever du store (PNJ).

Pokeschool : 3 sessions : si pokémon entre 50 et 70 : +10, pokescore de mon pokémon + 5 si pokémon entre 70 et 100 : +15, pokescore de mon pokémon + 10 si pokémon +100 : +30, pokescore de mon pokémon + 25

La fin d’un combat : si le dresseur gagne il remporte une somme de pokescore qui dans son compte en banque qui est égale au pokescore de son adversaire.

Le pokémon perdant et appartenant au dresseur passe obligatoirement par l’infirmerie.

Si le dresseur a un pokescore suffisant pour soigner son pokémon, le pokémon est soigné et revient à son score de départ.

Si le dresseur ne peut pas soigner son pokémon, son pokémon est déclaré KO à la mairie et la mairie le remet au pokeStore.

Si le dresseur ne peut pas soigner son pokemon et que c’est son dernier pokémon est éliminé du jeu.

La marie se charge de toutes les opérations lié à la banque.

La mairie doit éliminer le joueur si il ne lui reste plus aucun Pokemon.

La mairie doit envoyer un message à tout les systèmes que le dresseur X à était éliminer.

La mairie peut rajouter des pokémons au pokestore.

Lorsqu'un Pokemon du pokestore est envoyer au combat il doit être supprimer de la base de donnée le temps du combat.

Le pokestore doit pouvoir fabriqué des pokemon toutes les 30secondes.

Lorsqu'un pokemon est crée par le store la mairie doit etre avertit et doit également le stocker dans sa base de donnée.


![](seqDiagram.png)

![](send_Pokemon_School)

