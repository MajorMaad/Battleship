import java.util.Scanner;

/**
 * La classe représentant les participants du jeu (personnes physiques)
 * Un joueur connaît sa grille de jeu et son état (attaque ou défense)
 * Un joueur a une phase de placement des bateaux et une phase de jeu
 * Un joueur peut consulter sa grille pour savoir s'il lui reste des bateaux
 * Dans le cas contraire, il sait qu'il a perdu
 * @author Marie Darrigol
 *
 */
public class Joueur {

	private int num;				// numéro du joueur
	private Grille grille;			// représente la grille de jeu du joueur
	private boolean phaseAttaque;	// indique si le joueur est en phase d'attaque ou non (changera l'affichage de sa grille)
	
	/**
	 * Le constructeur de base du joueur, où on lui fournit une grille de jeu
	 * @param num le numéro qui sera associé au joueur tout au long de la partie
	 * @param grille une grille vide
	 */
	public Joueur(int num, Grille grille) {
		super();
		this.num = num;
		this.grille = grille;
		this.phaseAttaque = false;
	}
	
	/**
	 * Méthode permettant au joueur de choisir une case et une orientation pour un bateau quelconque
	 * Vérifie si les informations données sont correctes avant de les renvoyer
	 * @return infosCase les coordonnées (ligne, colonne) ainsi que l'orientation du bateau
	 */
	public String[] placerBateau(){
		char touchePressee;
		String infosCase[] = new String[3];
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Sélection de la case d'origine");
		
		infosCase[0] = selectionnerLigne(scan)+"";
		infosCase[1] = selectionnerColonne(scan)+"";
		
		// Choix de l'orientation du bateau
		while(infosCase[2] == null){
			System.out.println("Sélectionnez l'orientation du bateau");
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
				System.out.println("Mauvaise touche, vous avez tapé "+touchePressee);
		}
		
		return infosCase;
	}
	
	/**
	 * Méthode permettant au joueur de sélectionner une ligne
	 * Vérifie si la case sélectionnée par le joueur est correcte
	 * Redemande au joueur de faire une saisie tant que celle-ci n'est pas correcte
	 * @param scan le scanner utilisé par le joueur
	 * @return le caractère représentant la ligne sélectionnée
	 */
	private char selectionnerLigne(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("Sélectionnez une ligne (de A à J)");
			touchePressee = scan.next().charAt(0);
			if("abcdefghij".indexOf(touchePressee) >= 0 || "ABCDEFGHIJ".indexOf(touchePressee) >= 0)
				if("ABCDEFGHIJ".indexOf(touchePressee) >= 0)
					res = (touchePressee+"").toLowerCase().charAt(0);
				else
					res = touchePressee;
			else
				System.out.println("Mauvaise touche, vous avez tapé "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * Méthode permettant au joueur de sélectionner une colonne
	 * Vérifie si la case sélectionnée par le joueur est correcte
	 * Redemande au joueur de faire une saisie tant que celle-ci n'est pas correcte
	 * @param scan le scanner utilisé par le joueur
	 * @return le caractère représentant la ligne sélectionnée
	 */
	private char selectionnerColonne(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("Sélectionnez une colonne (de 0 à 9)");
			touchePressee = scan.next().charAt(0);
			if("0123456789".indexOf(touchePressee) >= 0)
				res = touchePressee;
			else
				System.out.println("Mauvaise touche, vous avez tapé "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * Le joueur entre dans sa phase d'attaque
	 * D'abord, il sélectionne quel bateau il souhaite utiliser
	 * Puis, il indique quelle case il souhaite bombarder avec ce bateau
	 * Cette phase est répétée tant que le joueur n'entre pas une bonne valeur
	 * @return caseAttaquee la case que l'on a bombardé
	 */
	// A revoir plus tard
	/*
	public Case bombarderCase(){
		Bateau flotte[] = grille.getBateaux();
		Case caseAttaquee;
		char bateauChoisi;
		Scanner scan = new Scanner(System.in);
		
		// On sélectionne d'abord un bateau parmi les navires restants
		System.out.println("Veuillez sélectionner un bateau :");
		for(int i = 0; i < flotte.length(); i++){
			System.out.println(flotte[i].toString());
		}
		bateauChoisi = scan.next().charAt(0);
		
		// Le système affiche la liste des coups possibles (pour faciliter la lecture du joueur)
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