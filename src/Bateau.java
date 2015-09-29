
public class Bateau {
	
	private int longueur;
	private int champ_tir;
	private Case case_origine;
	private String orientation;
	private char symbole;

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
	
	public void deplacer(String direction, int nb) {
		
		if (direction == "nord" || direction == "sud" || direction == "est" || direction == "ouest") {
			if (nb > 0 && nb <= 2) {
				//this.case_origine.setEtat(' ');
			}
		}
	}

}
