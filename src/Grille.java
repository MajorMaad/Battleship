
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
	 * M�thode d'ajout d'un bateau
	 * @param bateau : le bateau
	 * @param position : la position dans le tableau bateaux de la grille
	 * @return false si le bateau ne peut �tre plac� l�, true sinon (et il est plac�)
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
					// Suivant l'orientation du bateau on va incr�menter la ligne ou la colonne a verifier
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
			
			//Modification de l'etat des cases en cons�quence
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
	 * Methode de d�placement d'un bateau
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
				
				if (ligne < 0 || ligne > 9 || colonne < 0 || colonne > 9)
					return false;
				else {
					int tampon_ligne = ligne;
					int tampon_colonne = colonne;
					
					for (int i = 0; i < longueur; i++) {
						if (this.cases[ligne][colonne].getEtat() == 'p' || this.cases[ligne][colonne].getEtat() == 'c' || this.cases[ligne][colonne].getEtat() == 'r' || this.cases[ligne][colonne].getEtat() == 's' || this.cases[ligne][colonne].getEtat() == 't')
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
					for (int i = 0; i < longueur; i++) {
						this.cases[ancienne_ligne][ancienne_colonne].setEtat(' ');
						this.cases[ligne][colonne].setEtat(this.bateaux[position].getSymbole());
						
						switch (this.bateaux[position].getOrientation()) {
							case "nord":
								ligne -= 1;
								ancienne_ligne -= 1;
								break;
							case "sud":
								ligne += 1;
								ancienne_ligne += 1;
								break;
							case "est":
								colonne += 1;
								ancienne_colonne += 1;
								break;
							case "ouest":
								colonne -= 1;
								ancienne_colonne -= 1;
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
	 * Permet de r�cup�rer le bateau � l'indice i du tableau s'il existe
	 * @param i indice du bateau
	 * @return le bateau stock� � l'indice i
	 */
	public Bateau getBateau(int i){
		Bateau res = null;
		if(i < this.bateaux.length)
			res = this.bateaux[i];
		return res;
	}

}
