
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
	
	public String toString() {
		String str = String.valueOf(this.etat);
		
		return str;
	}

}
	