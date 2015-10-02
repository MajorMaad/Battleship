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
	 * Méthode équivalente à celle du joueur physique, mais elle choisit une case automatiquement
	 * Une ligne et une colonne sont choisies aléatoirement
	 * L'orientation dépend dans quelle partie de la carte la case se trouve :
	 * - quart haut-gauche : orientation Sud ou Est
	 * - quart haut-droit : orientation Sud ou Ouest
	 * - quart bas-gauche : orientation Nord ou Est
	 * - quart bas-droit : orientation Nord ou Ouest
	 * @return une string représentant les coordonnées (ligne, colonne) et l'orientation du bateau
	 */
	public String placerBateau(){
		String infosCase = "";
		
		infosCase = infosCase + selectionAleatoire("abcdefghij");
		infosCase = infosCase + selectionAleatoire("0123456789");
		
		// Choix de l'orientation du bateau
		if("abcde".indexOf(infosCase.charAt(0)) >= 0){	// Moitié haute
			if("01234".indexOf(infosCase.charAt(1)) >= 0){	// Moitié gauche
				infosCase = infosCase + selectionAleatoire("se");
			}else{	// Moitié droite
				infosCase = infosCase + selectionAleatoire("so");
			}
		}else{	// Moitié basse
			if("01234".indexOf(infosCase.charAt(1)) >= 0){	// Moitié gauche
				infosCase = infosCase + selectionAleatoire("ne");
			}else{	// Moitié droite
				infosCase = infosCase + selectionAleatoire("no");
			}
		}
		
		return infosCase;
	}
	
	/**
	 * Sélectionne aléatoirement un caractère parmis ceux passés en paramètre
	 * Utilisée pour choisir une ligne ou une colonne aléatoirement
	 * @param s une chaîne de caractère représentant tous les éléments pouvant être tirés
	 * @return un de ces éléments
	 */
	private char selectionAleatoire(String s){
		int i = s.length();
		Random r = new Random();
		return s.charAt(r.nextInt(i));
	}
}
