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

Options : ambulances (durant le combat pour augmenter le score de celui qui l'appelle), gestion d'un pokecimetière, lorsque un pokemon meurt et que dresseur en possède d'autre mais n'a pas assez d'argent pour soigner son pokemon qui va mourrir alors est obliger de passer par le pokestore pour en vendre un autre et récupérer de l'argent).

### Descriptions des systèmes

Détail : Système de combat : 
Acteurs : Deux dresseurs avec un pokemon chacun
Fonctionnalités : Attaques
Chaque combat est associé à une somme à gagner (pokescore fixé)

## Interfaces

```
artist->master: POST venue
vendor->master: GET Gigs
master->vendor: Collection<Gigs>

Customer->vendor: cli:gig selection

vendor->master: jms:booking
alt booking successfull
    master->vendor: transitional tickets
    vendor->Customer: ticket purshase ok
    Customer->vendor: cli:customer informations
    
    vendor->master: jms:ticketing
    master->vendor: tickets

else booking unsuccessfull
    master->vendor: no quota for gigs
end

opt venue cancellation
    artist->master: DELETE venue
    master->vendor: jms:topic:cancellation
    vendor->Customer: smtp:cancellation email
end
```
![](seqDiagram.png)

## Schéma relationnel

![](EER.png)

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
