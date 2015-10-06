# Battleship

## Jeu de bataille navale

*Battleship est un jeu de bataille navale dont les règles diffèrent un peu de la bataille navale classique. Il se joue à un seul joueur contre un adversaire virtuel (IA).*

## Règles du jeu

1. Deux joueurs
2. Chaque joueur a une flotte composée de 5 bateaux (cases, champ de tir)
    * Porte-Avion (5, 2)
    * Croiseur (4, 2)
    * Contre Torpilleur (3, 4)
    * Sous-Marin (3, 4)
    * Torpilleur (2, 5)
3. Un tir provient d'un bateau et dans son champs de tir, vertical ou horizontal
4. Après un tir raté, l'adversaire a le droit de déplacer un bateau jusqu'à deux positions

## Déroulement

Les joueurs placent d'abord leurs 5 bateaux à tour de rôle sur leur grille. La partie peut alors commencer. 

A chaque tour, le joueur doit choisir avec quel bateau tirer, il a alors une liste cases possibles pour tirer. Si il touche un bateau adverse, celui-ci est détruit, sinon l'adversaire peut déplacer un de ses bateaux de 1 ou 2 cases.

Le premier joueur qui détruit tout les bateaux adverses remporte la partie.

## Interface

Le jeu se déroule entièrement dans la console.

Il y a une grille par joueur, et chacune de ces grilles peuvent s'afficher de 2 manières :

* Avec les bateaux pour l'attaquant (le joueur voit ses propres bateaux)
* Sans les bateaux pour voir uniquement les coups joués sur la grille adverse

La taille de la grille est de 10x10. Les lignes sont représentées par les lettres de A à J, et les colonnes par les chiffres de 0 à 9.

Chaque case de la grille est représentée par un symbole sur la grille :

* Porte-Avion = P
* Croiseur = C
* Contre Torpilleur = R
* Sous-Marin = S
* Torpilleur = T
* Touché = O
* Raté = X

Les bateaux sont représentés notamment à l'aide d'une case d'origine, une longueur et une direction. La case d'origine est "l'arrière du bateau". Son orientation (nord, sud, est, ouest) est l'endroit vers lequel seront placées le reste de ses cases. Exemple : Bateau de longueur 3 orienté vers l'est => OXX (O la case d'origine et X le reste du bateau)

Lorsqu'un bateau est détruit, toute ses cases deviennent symboliséses par des O (et non pas seulement la case touchée).

## Classes 
* Grille
* Case (partie d'une grille)
* Joueur
* JoueurIA qui est une sous-classe de Joueur
* Bateau
* PorteAvion, Croiseur, ContreTorpilleur, SousMarin et Torpilleur qui sont des sous-classes de Bateau
* Vue
* Battleship (qui contiendra notre Main)