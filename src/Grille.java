
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
				if (this.cases[ligne_a_verifier][colonne_a_verifier].getEtat() != ' ' || i > bateau.getLongueur()) {
					verifier = false;
					// Si on a parcouru toute les cases sans soucis on peut ajouterle bateau
					if (i > bateau.getLongueur())
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
				}
			}
		}
		
		// Ajout du bateau dans le tableau si possible
		if (ajout && position < 5)
			this.bateaux[position] = bateau;
			
		return ajout;
	}

}
