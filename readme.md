## Objectifs du système à modéliser

On propose de modéliser un système de gestion (master) du pokecity pouvant supporter plusieurs pokemons (pokemon) et dresseurs (dresseur). Le système master gère le pokestore, la pokebank, le pokeschool, la mairie, l'infirmerie (avec plusieurs ambulances), les différents dresseurs se combattant entre eux, l'inscription des pokemons dans le pokeschool, acheter / revendre / échanger un pokemon dans le pokestore, la gestion des pokescores dans la pokebank, l'enregistrement des inscriptions au pokeschool se fait à la mairie avec le nombre de pokémons pour chaque dresseur.

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

![POKECITY](https://github.com/meryamgh/POKECITY/assets/113670988/fe44d815-a9b2-4b57-b880-b765f2aff621)

[![Fight](https://github.com/meryamgh/POKECITY/assets/113671198/63db64ff-b06b-4de8-b59a-f83a40803d55)](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRmlnaHQKYWN0b3IgRHJlc3NldXIKCgACCC0-TWFpcmllOiBmaWdodCB3aXRoIHBva2Vtb24geAoAFwYgLT4gU3RvcmU6IEZpbmQgYSBQTkogdG8ALgYKABYFABsJIDogZGVsZXRlIFBOSgAUCC0-IABEBzoAEgUATgpDb21iYXQgAHgIOiB4IHZzADYFABMHABMRaW5nCmFsdCB4IChkAIFJB3MncwCBLAlpcyB3aW5uZXIpADkJAHIMABYGAGMFKyBhbW91bnQgd2luAIFfC0JhbmsgOiBjcmVkaXQgYW1vABsHABMFAIE9DQplbHMAgWIFAGsKAIErCwBhEgCBbA5JbmZpcm1lAIIPBkdldFRyZWF0bWVudCBwcmljZQoAFQsAgjMNABkFIHQAJggAgScTaGVjayBlbm91Z2ggbW9uZXkAgisFAAQNAIFHBgCBZAlkZWJpdCBiYW5rAIFTFACBHRcAfgUgdGhpAIJzCgCBKA0AGhUAhFUHAIFFGQCCTgZubwCBIQ8AhH8KAIQ_CQCEXwcAhSUIZnJvbSAAhAkIAIQJB2RlAIUyESA6IGFkZACGAQkAhC4LdG8gc3RvcmUAhSAUAIRsBU5vIG1vcgBoCmluAFITAIVeCUJBTiBEUkVTU0VVUgplbmQKZW5kAAMFAIB_ElBOSiByZXR1cm4AhjoSYWRkAIZmCACBDhAAh0YIIDoKCg&s=default)

![Buy a Pokemon](https://github.com/meryamgh/POKECITY/assets/113671198/17519768-0bc6-4ff1-b55a-587de70d18bb)
![send_Pokemon_School](https://github.com/meryamgh/POKECITY/assets/113671198/48e78faa-5a70-4ce0-af42-d5135ec59ba7)


### Exigences

- Un dresseur doit avoir un pokémon (un parmi les nuls = 50 pokescores) et un pokescore = 50 au début du jeu.
- Les combats doivent se faire seulement à un contre un (dresseur et pokémon).
- Un pokémon ne peut pas participer à un combat s’il est au pokeschool (participe à une session)
- Une session = 2 combats du dresseur
- Pokestore : 3 catégories : pokémon 50, 70 et 100.
- Pokeschool : 3 sessions : 
  si pokémon entre 50 et 70 : +10, pokescore de mon pokémon + 5
  si pokémon entre 70 et 100 : +15, pokescore de mon pokémon + 10
  si pokémon +100 : +30, pokescore de mon pokémon + 25
- La fin d’un combat : perdant = 0 pokescore et gagnant : pokéscore de départ + prime (va au dresseur)
- Prime des combats : Moyenne entre pokescore des 2 pokémons
- Si le dresseur a un seul pokémon il ne peut pas l’inscrire à la pokeschool
- Si le pokémon est à la pokeschool et que son dresseur est éliminé du jeu, le pokémon est ajouté au pokestore.
- La seule manière de gagner du pokescore pour un pokémon est d’aller à la pokeschool.
- Le pokémon perdant passe obligatoirement par l’infirmerie 
- L’infirmerie consulte le pokescore du dresseur à partir de la pokeBank 
- Si le dresseur a un pokescore suffisant pour soigner son pokémon, le pokémon est soigné et revient à son score de départ 
- Si le dresseur ne peut pas soigner son pokémon, son pokémon  est déclaré KO à la mairie et la mairie le remet au pokeStore. 
- Si le dresseur ne peut pas soigner son pokemon  et que c’est son dernier pokémon  mais qu’il a un pokescore suffisant pour acheter un autre pokemon, il doit en acheter un et n’est pas éliminé du jeu 
- Si le dresseur ne peut pas soigner son pokemon  et que c’est son dernier pokémon  et  qu’il n’a pas  un pokescore suffisant pour acheter un autre pokemon, la mairie reçoit une notification et elimine le dresseur du jeu 
- Au pokestore il est possible d’acheter des pokémons. 
- La mairie peut rajouter des pokémons au pokestore
- La pokeBank a le pokescore du dresseur qui joue 
- Lorsque que la mairie élimine le joueur, elle l’efface de sa liste et le notifie 


![](seqDiagram.png)

![](send_Pokemon_School)

## Exigences fonctionnelles

* le vendor NE DOIT proposer que les concerts pour lesquels il a un quota disponible, transmis par le master.
* le vendor DOIT pouvoir effectuer les opérations de booking et ticketing
* le master DOIT permettre à l'artiste d'annuler son concert.
* le master DOIT informer le vendor en cas d'annulation de concert
* le vendor DOIT informer les clients de l'annulation du concert par mail
* le master DOIT proposer un service de validation de la clé du ticket, pour les contrôles aux entées.

## Exigences non fonctionnelles

* le booking et le ticketing, bien qu'étant des opérations synchrones, DOIVENT être fiables et donc utiliser le messaging
* Lors de l'annulation de tickets, le master DOIT informer tous les vendors de l'annulation, de façon fiable.
