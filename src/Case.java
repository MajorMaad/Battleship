/**
 * Classe représentant une case
 * Une grille (voir Grille.java) est composée de 10x10 cases
 * Un bateau possèdera également une case d'origine
 * Une case est caractérisée par une ligne, une colonne et un état (vide, avec un bateau dessus, touché, ...)
 * @author Quentin Audinot
 *
 */
public class Case {
	
	private char ligne;
	private char colonne;
	private char etat;
	
	public Case(char ligne, char colonne) {
		if ("abcdefghij".indexOf(ligne) >= 0)
			this.ligne = ligne;
		
		if ("0123456789".indexOf(colonne) >= 0)
			this.colonne = colonne;
		
		this.etat = ' ';
	}
	
	public Case(char ligne, char colonne, char etat) {
		if ("abcdefghij".indexOf(ligne) >= 0)
			this.ligne = ligne;
		
		if ("0123456789".indexOf(colonne) >= 0)
			this.colonne = colonne;
		
		if (" pcrstox".indexOf(etat) >= 0)
			this.etat = etat;
	}
	
	public char getLigne() {
		return this.ligne;
	}
	
	// ---------- Getters et Setters ----------
	public int getLigneInt() {
		int ligne = 0;
		switch(this.ligne) {
			case 'a':
				ligne = 0; 
				break;
			case 'b':
				ligne = 1; 
				break;
			case 'c':
				ligne = 2; 
				break;
			case 'd':
				ligne = 3; 
				break;
			case 'e':
				ligne = 4; 
				break;
			case 'f':
				ligne = 5; 
				break;
			case 'g':
				ligne = 6; 
				break;
			case 'h':
				ligne = 7; 
				break;
			case 'i':
				ligne = 8; 
				break;
			case 'j':
				ligne = 9; 
				break;
		}
		return ligne;
	}
	
	public char getColonne() {
		return this.colonne;
	}
	
	public int getColonneInt() {
		return Character.getNumericValue(this.colonne);
	}
	
	public char getEtat() {
		return this.etat;
	}
	
	public void setEtat(char etat) {
		this.etat = etat;
	}
	
	public String toString() {
		String str = String.valueOf(this.etat);
		
		return str;
	}

}
	