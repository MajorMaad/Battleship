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
	 * M�thode permettant de bombarder une case de fa�on automatique
	 * D'abord, l'IA choisit un bateau
	 * Puis, elle d�termine quelle case elle va attaquer
	 * @return une cha�ne de caract�re repr�sentant les coordonn�es (ligne, colonne) de la case attaqu�e
	 */
	public String bombarderCase(){
		String caseAttaquee = "";
		
		Bateau bateauChoisi = choisirBateau();
		caseAttaquee = caseAttaquee + selectionAleatoire("abcdefghij");
		caseAttaquee = caseAttaquee + selectionAleatoire("0123456789");
		
		return caseAttaquee;
	}
	
	/**
	 * Choix al�atoire du bateau parmis la flotte de l'IA
	 * @return le bateau s�lectionn�
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
