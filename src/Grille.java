/**
 * Classe représentant une grille de jeu pour un joueur
 * L'affichage de cette grille peut se faire de 2 façons :
 * Avec ou sans l'affichage des bateaux (de cette maniere, un joueur verra sa grille avec les 
 * bateaux et la grille de l'adversaire uniquement avec les coups joués)
 * La grille est de taille 10x10
 * Les lignes sont représentées par les lettres de A à J
 * Les colonnes par les chiffres de 0 à 9
 * @author Quentin Audinot
 *
 */

public class Grille {

	private Case[][] cases;
	private Bateau[] bateaux;

	/**
	 * Constructeur de grille vide
	 */
	public Grille() {
		this.cases = new Case[10][10];
		
		String lignes = "abcdefghij";
		String colonnes = "0123456789";
		
		for (int i=0; i < 10; i++) {
			for (int j=0; j < 10; j++) {
				Case nouvelle_case = new Case(lignes.charAt(i), colonnes.charAt(j));
				this.cases[i][j] = nouvelle_case;
			}
		}
		
		this.bateaux = new Bateau[5];
	}
	
	/**
	 * Méthode d'ajout d'un bateau
	 * @param bateau : le bateau
	 * @param position : la position dans le tableau bateaux de la grille
	 * @return false si le bateau ne peut être placé là, true sinon (et il est placé)
	 */
	public boolean ajouterBateau(Bateau bateau, int position) {
		int ligne_a_verifier = -1;
		int colonne_a_verifier = -1;
		boolean verifier = true;
		boolean ajout = false;
		
		// On cherche la case de la grille correspondant a la case du bateau que l'on veut placer
		for (int i=0; i < 10; i++) {
			for (int j=0; j < 10; j++) {
				if (this.cases[i][j].getLigne() == bateau.getCaseOrigine().getLigne() && this.cases[i][j].getColonne() == bateau.getCaseOrigine().getColonne()) {
					ligne_a_verifier = i;
					colonne_a_verifier = j;
				}
			}
		}
		
		// Si on ne l'a pas trouver on renvoit une erreur
		if (ligne_a_verifier < 0 || colonne_a_verifier < 0 || ligne_a_verifier > 9 || colonne_a_verifier > 9)
			ajout = false;
		// Sinon, on va checker une par une les cases du bateau afin de voir s'il n'y a pas deja un autre bateau en place
		else {
			int i = 1;
			while(verifier) {
				// Si il y un autre bateau sur cette case ou si on a verifier toute la longueur du bateau
				if (this.cases[ligne_a_verifier][colonne_a_verifier].getEtat() != ' ' || i >= bateau.getLongueur()) {
					verifier = false;
					// Si on a parcouru toute les cases sans soucis on peut ajouterle bateau
					if (this.cases[ligne_a_verifier][colonne_a_verifier].getEtat() == ' ' && i == bateau.getLongueur())
						ajout = true;
				}
				else {					
					// Suivant l'orientation du bateau on va incrémenter la ligne ou la colonne a verifier
					switch(bateau.getOrientation()) {
						case "nord":
							ligne_a_verifier -= 1;
							break;
						case "sud":
							ligne_a_verifier += 1;
							break;
						case "est":
							colonne_a_verifier += 1;
							break;
						case "ouest":
							colonne_a_verifier -= 1;
							break;
						default:
							ajout = false;
							break;
					}
					if (ligne_a_verifier < 0  || ligne_a_verifier > 9 || colonne_a_verifier < 0 || colonne_a_verifier > 9) {
						ajout = false;
						break;
					}
				}
				i++;
			}
		}
		
		// Ajout du bateau dans le tableau si possible et modification des cases
		if (ajout && position < 5) {
			this.bateaux[position] = bateau;
			
			//Modification de l'etat des cases en conséquence
			boolean modification_fini = false;
			int i = 1;
			
			// numeric value de a => 10, de b =>11 et ainsi de suite, on enleve donc 10
			int ligne_a_modifier = Character.getNumericValue(bateau.getCaseOrigine().getLigne()) - 10;
			int colonne_a_modifier = Character.getNumericValue(bateau.getCaseOrigine().getColonne());
			
			while(!modification_fini) {
				
				this.cases[ligne_a_modifier][colonne_a_modifier].setEtat(bateau.getSymbole());
				
				switch(bateau.getOrientation()) {
					case "nord":
						ligne_a_modifier -= 1;
						break;
					case "sud":
						ligne_a_modifier += 1;
						break;
					case "est":
						colonne_a_modifier += 1;
						break;
					case "ouest":
						colonne_a_modifier -= 1;
						break;
					default:
						break;
				}
				
				i++;
				if (i > bateau.getLongueur())
					modification_fini = true;
			}
		}
			
		return ajout;
	}
	
	/**
	 * Retourne la grille, bateaux compris
	 * @return String de la grille
	 */
	public String affichageAttaque() {
		String ligne ="";
		String str = "  0 1 2 3 4 5 6 7 8 9\n";
		
		for (int i=0; i < 10; i++) {
			
			switch (i) {
				case 0:
					ligne = "A";
					break;
				case 1:
					ligne = "B";
					break;
				case 2:
					ligne = "C";
					break;
				case 3:
					ligne = "D";
					break;
				case 4:
					ligne = "E";
					break;
				case 5:
					ligne = "F";
					break;
				case 6:
					ligne = "G";
					break;
				case 7:
					ligne = "H";
					break;
				case 8:
					ligne = "I";
					break;
				case 9:
					ligne = "J";
					break;
				default:
					ligne = "U";
					break;
			}
			
			str += ligne + " ";
			
			for (int j=0; j < 10; j++) {
				str += this.cases[i][j].toString() + " ";
			}
			
			str += "\n";
		}
		
		return str;
	}
	
	/**
	 * Retourne la grille sans les bateaux
	 * @return String de la grille
	 */
	public String affichageDefense() {
		String ligne ="";
		String str = "  0 1 2 3 4 5 6 7 8 9\n";
		
		for (int i=0; i < 10; i++) {
			
			switch (i) {
				case 0:
					ligne = "A";
					break;
				case 1:
					ligne = "B";
					break;
				case 2:
					ligne = "C";
					break;
				case 3:
					ligne = "D";
					break;
				case 4:
					ligne = "E";
					break;
				case 5:
					ligne = "F";
					break;
				case 6:
					ligne = "G";
					break;
				case 7:
					ligne = "H";
					break;
				case 8:
					ligne = "I";
					break;
				case 9:
					ligne = "J";
					break;
				default:
					ligne = "U";
					break;
			}
			
			str += ligne + " ";
			
			for (int j=0; j < 10; j++) {
				if (this.cases[i][j].getEtat() != 'p' && this.cases[i][j].getEtat() != 'c' && this.cases[i][j].getEtat() != 'r' && this.cases[i][j].getEtat() != 's' && this.cases[i][j].getEtat() != 't')
					str += this.cases[i][j].toString() + " ";
				else {
					str += "  "; // 2 espaces
				}
			}
			
			str += "\n";
		}
		
		return str;
	}
	/**
	 * Methode de déplacement d'un bateau
	 * @param direction : nord sud est ouest
	 * @param nb : nb de case de deplacement (1 ou 2)
	 * @param position : position du bateau dans le tableau (this.bateaux)
	 * @return false si impossible a deplacer
	 */
	public boolean deplacer_bateau(String direction, int nb, int position) {
		if (direction == "nord" || direction == "sud" || direction == "est" || direction == "ouest") {
			if (nb > 0 && nb <= 2 && position < 5) {
				int longueur = this.bateaux[position].getLongueur();
				int ancienne_ligne = this.bateaux[position].getCaseOrigine().getLigneInt();
				int ancienne_colonne = this.bateaux[position].getCaseOrigine().getColonneInt();
				int ligne = this.bateaux[position].getCaseOrigine().getLigneInt();
				int colonne = this.bateaux[position].getCaseOrigine().getColonneInt();
				String direction_bateau = this.bateaux[position].getOrientation();
				
				// Nouvelle valeure de la ligne ou colonne de la case d'origine
				switch (direction) {
					case "nord":
						ligne -= nb;
						break;
					case "sud":
						ligne += nb;
						break;
					case "est":
						colonne += nb;
						break;
					case "ouest":
						colonne -= nb;
						break;
					default: 
						break;
				}
				
				// On vérifie que le bateau de va pas dépasser
				switch (direction_bateau) {
					case "nord":
						if ((ligne - longueur) < 0)
							return false;
						break;
					case "sud":
						if ((ligne + longueur) > 9)
							return false;
						break;
					case "est":
						if ((colonne + longueur) > 9)
							return false;
						break;
					case "ouest":
						if ((colonne - longueur) < 0)
							return false;
						break;
					default:
						break;
				}	
				
				// Si la nouvelle case d'origine du bateau n'est plus dans la grille => deplacement impossible
				if (ligne < 0 || ligne > 9 || colonne < 0 || colonne > 9)
					return false;
				else {
					int tampon_ligne = ligne;
					int tampon_colonne = colonne;
					
					// On va vérifier pour chaque case qu'on empiete pas sur un autre bateau
					for (int i = 0; i < longueur; i++) {
						// Si on empiete sur un autre bateau => deplacement impossible
						if (this.cases[ligne][colonne].getEtat() != this.bateaux[position].getSymbole() && this.cases[ligne][colonne].getEtat() != ' ' && this.cases[ligne][colonne].getEtat() != 'x' && this.cases[ligne][colonne].getEtat() != 'o')
							return false;
						else {
							switch (this.bateaux[position].getOrientation()) {
								case "nord":
									ligne -= 1;
									break;
								case "sud":
									ligne += 1;
									break;
								case "est":
									colonne += 1;
									break;
								case "ouest":
									colonne -= 1;
									break;
								default:
									break;
							}
						}
					}
					
					ligne = tampon_ligne;
					colonne = tampon_colonne;
					// Effacement de l'ancienne position du bateau
					for (int i = 0; i < longueur; i++) {
						this.cases[ancienne_ligne][ancienne_colonne].setEtat(' ');
						
						switch (this.bateaux[position].getOrientation()) {
							case "nord":
								ancienne_ligne -= 1;
								break;
							case "sud":
								ancienne_ligne += 1;
								break;
							case "est":
								ancienne_colonne += 1;
								break;
							case "ouest":
								ancienne_colonne -= 1;
								break;
							default:
								break;
						}
					}
					// Mise a jour de la position du bateau
					for (int i = 0; i < longueur; i++) {
						this.cases[ligne][colonne].setEtat(this.bateaux[position].getSymbole());
						
						switch (this.bateaux[position].getOrientation()) {
							case "nord":
								ligne -= 1;
								break;
							case "sud":
								ligne += 1;
								break;
							case "est":
								colonne += 1;
								break;
							case "ouest":
								colonne -= 1;
								break;
							default:
								break;
						}
					}
					this.bateaux[position].deplacer(direction, nb);
					return true;
				}
				
			} else return false;
		} else return false;
	}
	
	/**
	 * Méthode qui va changer l'état de la case attaquée (ou plusieurs case si c'est
	 * un bateau qui a été touché)
	 * @param stringCaseAttaquee => la case attaquée sous forme de string (exemple : "b8")
	 * @return true si un bateau a été détruit
	 * false si un bateau n'a pas pu être détruit ou si on a manqué
	 */
	public boolean gererTir(String stringCaseAttaquee) {
		boolean retour = false;
		Case caseAttaquee = this.getCase(stringCaseAttaquee.charAt(0), stringCaseAttaquee.charAt(1));
		int ligne = caseAttaquee.getLigneInt();
		int colonne = caseAttaquee.getColonneInt();
		
		switch (caseAttaquee.getEtat()) {
			case 'p':
				retour = this.detruireBateau(0);
				break;
			case 'c':
				retour = this.detruireBateau(1);
				break;
			case 'r':
				retour = this.detruireBateau(2);
				break;
			case 's':
				retour = this.detruireBateau(3);
				break;
			case 't':
				retour = this.detruireBateau(4);
				break;
			default:
				retour = false;
				this.cases[ligne][colonne].setEtat('x');
				break;
		}
		
		return retour;
	}
	
	/**
	 * Méthode pour détruire un bateau de la grille
	 * @param position => Position du bateau dans le tableau de la grille
	 * @return true si le bateau a été détruit
	 */
	public boolean detruireBateau(int position) {
		if (position >= 0 && position < 5) {
			int ligne = this.bateaux[position].getCaseOrigine().getLigneInt();
			int colonne = this.bateaux[position].getCaseOrigine().getColonneInt();
			int longueur = this.bateaux[position].getLongueur();
			String orientation = this.bateaux[position].getOrientation();
			
			// On remplace toute les casesdu bateau par des 'o' (pour touché)
			for (int i = 0; i < longueur; i++) {
				this.cases[ligne][colonne].setEtat('o');
				
				switch (orientation) {
					case "nord":
						ligne -= 1;
						break;
					case "sud":
						ligne += 1;
						break;
					case "est":
						colonne += 1;
						break;
					case "ouest":
						colonne -= 1;
						break;
					default:
						break;
				}
			}
			
			// On enlève ensuite le bateau du tableau
			this.bateaux[position] = null;
			return true;
		} else return false;
	}
	
	/**
	 * Fonction pour recuperer une case du tableau de cases de la grille
	 * @param ligne
	 * @param colonne
	 * @return La case correspondant a la ligne et colonne
	 */
	public Case getCase(char ligne, char colonne) {
		Case caseRetour = null;
		
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				if (this.cases[i][j].getLigne() == ligne && this.cases[i][j].getColonne() == colonne)
					caseRetour = this.cases[i][j];
			}
		}
		
		return caseRetour;
	}
	
	/**
	 * Permet de récupérer le bateau à l'indice i du tableau s'il existe
	 * @param i indice du bateau
	 * @return le bateau stocké à l'indice i
	 */
	public Bateau getBateau(int i){
		Bateau res = null;
		if(i < this.bateaux.length)
			res = this.bateaux[i];
		return res;
	}
	
	/**
	 * Permet de connaître le nombre de bateaux initial
	 * @return le nombre de bateaux (longueur du tableau bateaux)
	 */
	public int getNbBateaux(){
		return bateaux.length;
	}

}
