import java.util.Random;


/**
 * La classe représentant le joueur contrôlé par l'ordinateur
 * Il dispose du même comportement qu'un joueur physique, ormis le fait qu'il ne nécessite aucune saisie de l'utilisateur
 * @author Marie Darrigol
 *
 */
public class JoueurIA extends Joueur {
	
	public JoueurIA (int num, Grille g){
		super(num, g);
	}
	
	/**
	 * Méthode équivalente à celle du joueur physique, mais elle choisit une case automatiquement et aléatoirement
	 * @return une string représentant les coordonnées (ligne, colonne) et l'orientation du bateau
	 */
	public String placerBateau(){
		String infosCase = "";
		
		// Choix de la ligne
		infosCase = infosCase + selectionAleatoire("abcdefghij");
		// Choix de la colonne
		infosCase = infosCase + selectionAleatoire("0123456789");
		// Choix de l'orientation
		infosCase = infosCase + selectionAleatoire("nseo");
		
		return infosCase;
	}
	
	/**
	 * Méthode permettant de bombarder une case de façon automatique
	 * D'abord, l'IA choisit un bateau
	 * Puis, elle détermine quelle case elle va attaquer
	 * @return une chaîne de caractère représentant les coordonnées (ligne, colonne) de la case attaquée
	 */
	public String bombarderCase(){
		String caseAttaquee = "";
		
		Bateau bateauChoisi = choisirBateau();
		caseAttaquee = caseAttaquee + selectionAleatoire("abcdefghij");
		caseAttaquee = caseAttaquee + selectionAleatoire("0123456789");
		
		return caseAttaquee;
	}
	
	/**
	 * Choix aléatoire du bateau parmis la flotte de l'IA
	 * @return le bateau sélectionné
	 */
	public Bateau choisirBateau(){
		String listeBateaux = "";
		Bateau choix;
		
		for(int i = 0; this.getGrille().getBateau(i) != null; i++){
			listeBateaux = listeBateaux + this.getGrille().getBateau(i).getSymbole(); 
		}
		choix = this.getGrille().getBateau(selectionAleatoire(listeBateaux) - '0');
		
		return choix;
	}
	
	/**
	 * Sélectionne aléatoirement un caractère parmis ceux passés en paramètre
	 * Utilisée pour choisir une ligne, une colonne ou une orientation aléatoirement
	 * @param s une chaîne de caractère représentant tous les éléments pouvant être tirés
	 * @return un de ces éléments
	 */
	private char selectionAleatoire(String s){
		int i = s.length();
		Random r = new Random();
		return s.charAt(r.nextInt(i));
	}
}
