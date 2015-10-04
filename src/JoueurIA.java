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
	 * M�thode �quivalente � celle du joueur physique, mais elle choisit une case automatiquement et al�atoirement
	 * @return une string repr�sentant les coordonn�es (ligne, colonne) et l'orientation du bateau
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
	 * S�lectionne al�atoirement un caract�re parmis ceux pass�s en param�tre
	 * Utilis�e pour choisir une ligne, une colonne ou une orientation al�atoirement
	 * @param s une cha�ne de caract�re repr�sentant tous les �l�ments pouvant �tre tir�s
	 * @return un de ces �l�ments
	 */
	private char selectionAleatoire(String s){
		int i = s.length();
		Random r = new Random();
		return s.charAt(r.nextInt(i));
	}
}
