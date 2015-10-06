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

	private int num;					// numéro du joueur
	private Grille grille;				// représente la grille de jeu du joueur
	private boolean enJeu;				// indique si le joueur est en train de joueur ou non
	
	/**
	 * Le constructeur de base du joueur, où on lui fournit une grille de jeu
	 * Par défaut, on indique que le joueur est inactif
	 * @param num le numéro qui sera associé au joueur tout au long de la partie
	 * @param grille une grille vide
	 */
	public Joueur(int num, Grille grille) {
		super();
		this.num = num;
		this.grille = grille;
		this.enJeu = false;
	}
	
	/**
	 * Méthode permettant au joueur de choisir une case et une orientation pour un bateau quelconque
	 * Vérifie si les informations données sont correctes avant de les renvoyer
	 * @return infosCase une chaîne de caractère représentant les coordonnées (ligne, colonne) ainsi que l'orientation du bateau
	 */
	public String placerBateau(){
		String infosCase = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Sélection de la case d'origine");
		
		infosCase = infosCase + selectionnerLigne(scan);
		infosCase = infosCase + selectionnerColonne(scan);
		infosCase = infosCase + selectionnerOrientation(scan);
		
		return infosCase;
	}
	
	/**
	 * Méthode permettant au joueur de choisir une case à bombarder
	 * D'abord, le joueur choisit un bateau parmis ceux qui lui restent
	 * Ensuite, les coups jouables sont affichés
	 * Enfin, il entre les coordonnées d'une case
	 * La saisie sera recommencée tant que le joueur n'aura pas choisi une case correcte
	 * @return caseAttaquee une chaîne de caractères contenant les coordonnées (ligne, colonne) de la case frappée
	 */
	public String bombarderCase(){
		String caseAttaquee = "";
		Scanner scan = new Scanner(System.in);
		
		// choix d'une case
		System.out.println("Sélectionner une case à attaquer");
		
		caseAttaquee = caseAttaquee + selectionnerLigne(scan);
		caseAttaquee = caseAttaquee + selectionnerColonne(scan);
		
		System.out.println("Case attaquée : " + caseAttaquee);
		
		return caseAttaquee;
	}
	
	/**
	 * Méthode permettant au joueur de déplacer le bateau de son choix
	 * D'abord, il va choisir un bateau parmis ceux restant dans sa flotte
	 * Puis, il va entrer une orientation et une valeur de déplacement
	 * Enfin, le programme va tester si son déplacement est valide
	 * La saisie sera recommencée tant que le déplacement n'est pas valide
	 */
	public void deplacerBateau(){
		Scanner scan = new Scanner(System.in);
		String orientation;
		char touchePressee;
		int deplacement = 0;
		boolean deplacementOk;
		
		do{
			int indiceBateau = choisirBateau(scan);
			
			System.out.println("Choix de la direction du déplacement");
			orientation = selectionnerOrientation(scan)+"";
			switch(orientation){
			case "n" : orientation = "nord"; break;
			case "s" : orientation = "sud"; break;
			case "e" : orientation = "est"; break;
			case "o" : orientation = "ouest";
			}
			
			while(deplacement == 0){
				System.out.println("Choisissez le nombre de cases pour le déplacement (1 ou 2)");
				touchePressee = scan.next().charAt(0);
				if("12".indexOf(touchePressee) >= 0)
					deplacement = touchePressee - '0';
				else{
					System.out.println("Mauvaise touche, vous avez tapé "+touchePressee);
					deplacement = 0;
				}
			}
			
			deplacementOk = grille.deplacer_bateau(orientation, deplacement, indiceBateau);
			if(!deplacementOk)
				System.out.println("Erreur de déplacement, veuillez recommencer");
			else
				System.out.println("Bateau déplacé");
		}while(!deplacementOk);
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
	 * @return le caractère représentant la colonne sélectionnée
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
	 * Méthode permettant au joueur de sélectionner une orientation
	 * Vérifie si la saisie est correcte
	 * Demande de refaire la saisie tant que ce n'est pas le cas
	 * @param scan le scanner utilisé pour lire l'entrée du joueur
	 * @return le caractère (n, s, e ou o) représentant l'orientation choisie
	 */
	private char selectionnerOrientation(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("Sélectionnez une orientation");
			System.out.println("(n pour Nord, e pour Est, o pour Ouest et s pour Sud)");
			touchePressee = scan.next().charAt(0);
			if("nseo".indexOf(touchePressee) >= 0 || "NSEO".indexOf(touchePressee) >= 0){
				int i;
				if("nseo".indexOf(touchePressee) >= 0)
					i = "nseo".indexOf(touchePressee);
				else
					i = "NSEO".indexOf(touchePressee); 
				switch(i){
				case 0 : res = 'n'; break;
				case 1 : res = 's'; break;
				case 2 : res = 'e'; break;
				case 3 : res = 'o';
				}
			}
			else
				System.out.println("Mauvaise touche, vous avez tapé "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * Méthode permettant au joueur de sélectionner un bateau parmis ceux qui lui restent
	 * @param scan l'objet Scanner utilisé pour lire la saisie au clavier du joueur
	 * @return l'indice du bateau sélectionné dans le tableau bateaux de la grille
	 */
	private int choisirBateau(Scanner scan){
		char choixBateau;
		int i;
		
		System.out.println("Choisissez un navire (taper le chiffre associé):");
		for(i = 0; i < grille.getNbBateaux(); i++){
			if(grille.getBateau(i) != null){
				switch(grille.getBateau(i).getSymbole()){
				case 'p' : System.out.println(i+". Porte-avion (p)"); break;
				case 'c' : System.out.println(i+". Croiseur (c)"); break;
				case 'r' : System.out.println(i+". Contre-torpilleur (r)"); break;
				case 's' : System.out.println(i+". Sous-marin (s)"); break;
				case 't' : System.out.println(i+". Torpilleur (t)"); break;
				default : System.out.println(i+". Autre bateau");
				}
			}
		}
		// choix
		boolean choixFait = false;
		do{
			choixBateau = scan.next().charAt(0);
			if(choixBateau - '0' >= 0 && choixBateau - '0' < i)
				choixFait = true;
			else
				System.out.println("Mauvaise touche, vous avez tapé "+choixBateau);
		}while(!choixFait);
		
		return choixBateau - '0';
	}
	
	/**
	 * Le joueur interroge sa grille pour savoir s'il reste au moins un bateau
	 * Si ce n'est pas le cas, le joueur a perdu
	 * @return true si la grille du joueur est vide
	 */
	public boolean aPerdu(){
		boolean res = true;
		for(int i = 0; i < grille.getNbBateaux(); i++){
			if(grille.getBateau(i) != null){
				res = false;
			}
		}
		return res;
	}
	
	// ---------- Getters et Setters ----------
	public int getNum(){
		return this.num;
	}
	
	public Grille getGrille(){
		return this.grille;
	}
	
	public void toggleEnJeu(){
		this.enJeu = !enJeu;
	}
	
	public boolean estEnJeu(){
		return this.enJeu;
	}
}