import java.util.List;

public class Grille {
	
	private Case[][] cases;
	private Bateau[] bateaux;

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
	
	public void ajouterBateau(Bateau bateau, int position) {
		this.bateaux[position] = bateau;
	}

}
