import java.util.Scanner;

/**
 * La classe repr�sentant les participants du jeu (personnes physiques)
 * Un joueur conna�t sa grille de jeu et son �tat (attaque ou d�fense)
 * Un joueur a une phase de placement des bateaux et une phase de jeu
 * Un joueur peut consulter sa grille pour savoir s'il lui reste des bateaux
 * Dans le cas contraire, il sait qu'il a perdu
 * @author Marie Darrigol
 *
 */
public class Joueur {

	private int num;				// num�ro du joueur
	private Grille grille;			// repr�sente la grille de jeu du joueur
	private boolean phaseAttaque;	// indique si le joueur est en phase d'attaque ou non (changera l'affichage de sa grille)
	
	/**
	 * Le constructeur de base du joueur, o� on lui fournit une grille de jeu
	 * @param num le num�ro qui sera associ� au joueur tout au long de la partie
	 * @param grille une grille vide
	 */
	public Joueur(int num, Grille grille) {
		super();
		this.num = num;
		this.grille = grille;
		this.phaseAttaque = false;
	}
	
	/**
	 * M�thode permettant au joueur de choisir une case et une orientation pour un bateau quelconque
	 * V�rifie si les informations donn�es sont correctes avant de les renvoyer
	 * @return infosCase les coordonn�es (ligne, colonne) ainsi que l'orientation du bateau
	 */
	public String[] placerBateau(){
		char touchePressee;
		String infosCase[] = new String[3];
		Scanner scan = new Scanner(System.in);
		
		System.out.println("S�lection de la case d'origine");
		
		infosCase[0] = selectionnerLigne(scan)+"";
		infosCase[1] = selectionnerColonne(scan)+"";
		
		// Choix de l'orientation du bateau
		while(infosCase[2] == null){
			System.out.println("S�lectionnez l'orientation du bateau");
			System.out.println("(n pour Nord, e pour Est, o pour Ouest et s pour Sud)");
			touchePressee = scan.next().charAt(0);
			if("nseo".indexOf(touchePressee) >= 0 || "NSEO".indexOf(touchePressee) >= 0){
				int i;
				if("nseo".indexOf(touchePressee) >= 0)
					i = "nseo".indexOf(touchePressee);
				else
					i = "NSEO".indexOf(touchePressee); 
				switch(i){
				case 0 : infosCase[2] = "nord"; break;
				case 1 : infosCase[2] = "sud"; break;
				case 2 : infosCase[2] = "est"; break;
				case 3 : infosCase[2] = "ouest";
				}
			}
			else
				System.out.println("Mauvaise touche, vous avez tap� "+touchePressee);
		}
		
		return infosCase;
	}
	
	/**
	 * M�thode permettant au joueur de s�lectionner une ligne
	 * V�rifie si la case s�lectionn�e par le joueur est correcte
	 * Redemande au joueur de faire une saisie tant que celle-ci n'est pas correcte
	 * @param scan le scanner utilis� par le joueur
	 * @return le caract�re repr�sentant la ligne s�lectionn�e
	 */
	private char selectionnerLigne(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("S�lectionnez une ligne (de A � J)");
			touchePressee = scan.next().charAt(0);
			if("abcdefghij".indexOf(touchePressee) >= 0 || "ABCDEFGHIJ".indexOf(touchePressee) >= 0)
				if("ABCDEFGHIJ".indexOf(touchePressee) >= 0)
					res = (touchePressee+"").toLowerCase().charAt(0);
				else
					res = touchePressee;
			else
				System.out.println("Mauvaise touche, vous avez tap� "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * M�thode permettant au joueur de s�lectionner une colonne
	 * V�rifie si la case s�lectionn�e par le joueur est correcte
	 * Redemande au joueur de faire une saisie tant que celle-ci n'est pas correcte
	 * @param scan le scanner utilis� par le joueur
	 * @return le caract�re repr�sentant la ligne s�lectionn�e
	 */
	private char selectionnerColonne(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("S�lectionnez une colonne (de 0 � 9)");
			touchePressee = scan.next().charAt(0);
			if("0123456789".indexOf(touchePressee) >= 0)
				res = touchePressee;
			else
				System.out.println("Mauvaise touche, vous avez tap� "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * Le joueur entre dans sa phase d'attaque
	 * D'abord, il s�lectionne quel bateau il souhaite utiliser
	 * Puis, il indique quelle case il souhaite bombarder avec ce bateau
	 * Cette phase est r�p�t�e tant que le joueur n'entre pas une bonne valeur
	 * @return caseAttaquee la case que l'on a bombard�
	 */
	// A revoir plus tard
	/*
	public Case bombarderCase(){
		Bateau flotte[] = grille.getBateaux();
		Case caseAttaquee;
		char bateauChoisi;
		Scanner scan = new Scanner(System.in);
		
		// On s�lectionne d'abord un bateau parmi les navires restants
		System.out.println("Veuillez s�lectionner un bateau :");
		for(int i = 0; i < flotte.length(); i++){
			System.out.println(flotte[i].toString());
		}
		bateauChoisi = scan.next().charAt(0);
		
		// Le syst�me affiche la liste des coups possibles (pour faciliter la lecture du joueur)
		System.out.println("...");
		// Puis le joueur choisit la case qu'il souhaite bombarder
		// ...
		
		return caseAttaquee;
	}
	*/
	
	/**
	 * Le joueur interroge sa grille pour savoir s'il reste au moins un bateau
	 * Si ce n'est pas le cas, le joueur a perdu
	 * @return true si la grille du joueur est vide
	 */
	public boolean aPerdu(){
		// return !grille.resteBateaux();
		return false;
	}
	
	// ---------- Getters et Setters ----------
	public int getNum(){
		return this.num;
	}
	
	public Grille getGrille(){
		return this.grille;
	}
}