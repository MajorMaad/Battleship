import java.util.Random;


/**
 * La classe repr�sentant le joueur contr�l� par l'ordinateur
 * Il dispose du m�me comportement qu'un joueur physique, ormis le fait qu'il ne n�cessite aucune saisie de l'utilisateur
 * @author Marie Darrigol
 *
 */
public class JoueurIA extends Joueur {
	
	public JoueurIA (int num, Grille g){
		super(num, g);
	}
	
	/**
	 * M�thode �quivalente � celle du joueur physique, mais elle choisit une case automatiquement
	 * Une ligne et une colonne sont choisies al�atoirement
	 * L'orientation d�pend dans quelle partie de la carte la case se trouve :
	 * - quart haut-gauche : orientation Sud ou Est
	 * - quart haut-droit : orientation Sud ou Ouest
	 * - quart bas-gauche : orientation Nord ou Est
	 * - quart bas-droit : orientation Nord ou Ouest
	 * @return une string repr�sentant les coordonn�es (ligne, colonne) et l'orientation du bateau
	 */
	public String placerBateau(){
		String infosCase = "";
		
		infosCase = infosCase + selectionAleatoire("abcdefghij");
		infosCase = infosCase + selectionAleatoire("0123456789");
		
		// Choix de l'orientation du bateau
		if("abcde".indexOf(infosCase.charAt(0)) >= 0){	// Moiti� haute
			if("01234".indexOf(infosCase.charAt(1)) >= 0){	// Moiti� gauche
				infosCase = infosCase + selectionAleatoire("se");
			}else{	// Moiti� droite
				infosCase = infosCase + selectionAleatoire("so");
			}
		}else{	// Moiti� basse
			if("01234".indexOf(infosCase.charAt(1)) >= 0){	// Moiti� gauche
				infosCase = infosCase + selectionAleatoire("ne");
			}else{	// Moiti� droite
				infosCase = infosCase + selectionAleatoire("no");
			}
		}
		
		return infosCase;
	}
	
	/**
	 * S�lectionne al�atoirement un caract�re parmis ceux pass�s en param�tre
	 * Utilis�e pour choisir une ligne ou une colonne al�atoirement
	 * @param s une cha�ne de caract�re repr�sentant tous les �l�ments pouvant �tre tir�s
	 * @return un de ces �l�ments
	 */
	private char selectionAleatoire(String s){
		int i = s.length();
		Random r = new Random();
		return s.charAt(r.nextInt(i));
	}
}
