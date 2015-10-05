/**
 * Classe représentant un bateau sur la grille
 * Un bateau est caractérisé par sa longueur, sa portée (champ_tir), une case d'origine, sa direction et un symbole
 * @author Quentin Audinot
 *
 */

public class Bateau {
	
	private int longueur;
	private int champ_tir;
	private Case case_origine;
	private String orientation;
	private char symbole;
	
	/**
	 * Constructeur du bateau
	 * @param longueur => Longueur du bateau, ici entre 2 et 5
	 * @param champ_tir => Portée du bateau
	 * @param case_origine => La case correspondant à l'arriere du bateau
	 * @param orientation => nord sud est ouest
	 * @param symbole => lettre que l'on va afficher sur la grille pour différencier les différents bateaux
	 */
	public Bateau(int longueur, int champ_tir, Case case_origine, String orientation, char symbole) {
		
		if (longueur > 1 && longueur <= 5)
			this.longueur = longueur;
		
		if (champ_tir > 1 && champ_tir <= 5)
			this.champ_tir = champ_tir;
		
		if (orientation == "nord" || orientation == "sud" || orientation == "est" || orientation == "ouest")
			this.orientation = orientation;
		
		if (symbole == 'p' || symbole == 'c' || symbole == 'r' || symbole == 's' || symbole == 't')
			this.symbole = symbole;

		this.case_origine = case_origine;
		
	}
	
	public int getLongueur() {
		return this.longueur;
	}
	
	public int getChampTir() {
		return this.champ_tir;
	}
	
	public Case getCaseOrigine() {
		return this.case_origine;
	}
	
	public String getOrientation() {
		return this.orientation;
	}
	
	public char getSymbole() {
		return this.symbole;
	}
	
	/**
	 * Méthode de déplacement du bateau => On lui attribue une nouvelle case d'origine
	 * @param direction => la direction du déplacement
	 * @param nb => Nombre de case de déplacement (1 ou 2)
	 */
	public void deplacer(String direction, int nb) {
		Case nouvelle_case;
		char etat = this.case_origine.getEtat();
		int nouvelle_ligne = this.case_origine.getLigneInt();
		int nouvelle_colonne = this.case_origine.getColonneInt();
		char nouvelle_ligne_char, nouvelle_colonne_char;
		
		switch (direction) {
			case "nord":
				nouvelle_ligne -= nb;
				break;
			case "sud":
				nouvelle_ligne += nb;
				break;
			case "est":
				nouvelle_colonne += nb;
				break;
			case "ouest":
				nouvelle_colonne -= nb;
				break;
			default:
				break;
		}
		
		switch (nouvelle_ligne) {
			case 0:
				nouvelle_ligne_char = 'a';
				break;
			case 1:
				nouvelle_ligne_char = 'b';
				break;
			case 2:
				nouvelle_ligne_char = 'c';
				break;
			case 3:
				nouvelle_ligne_char = 'd';
				break;
			case 4:
				nouvelle_ligne_char = 'e';
				break;
			case 5:
				nouvelle_ligne_char = 'f';
				break;
			case 6:
				nouvelle_ligne_char = 'g';
				break;
			case 7:
				nouvelle_ligne_char = 'h';
				break;
			case 8:
				nouvelle_ligne_char = 'i';
				break;
			case 9:
				nouvelle_ligne_char = 'j';
				break;
			default: 
				nouvelle_ligne_char = ' ';
				break;
				
		}
		
		nouvelle_colonne_char = Integer.toString(nouvelle_colonne).charAt(0);
		
		nouvelle_case = new Case(nouvelle_ligne_char, nouvelle_colonne_char, etat);
		this.case_origine = nouvelle_case;
	}

}
