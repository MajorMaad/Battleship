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

	private int num;					// num�ro du joueur
	private Grille grille;				// repr�sente la grille de jeu du joueur
	private boolean enJeu;				// indique si le joueur est en train de joueur ou non
	
	/**
	 * Le constructeur de base du joueur, o� on lui fournit une grille de jeu
	 * Par d�faut, on indique que le joueur est inactif
	 * @param num le num�ro qui sera associ� au joueur tout au long de la partie
	 * @param grille une grille vide
	 */
	public Joueur(int num, Grille grille) {
		super();
		this.num = num;
		this.grille = grille;
		this.enJeu = false;
	}
	
	/**
	 * M�thode permettant au joueur de choisir une case et une orientation pour un bateau quelconque
	 * V�rifie si les informations donn�es sont correctes avant de les renvoyer
	 * @return infosCase une cha�ne de caract�re repr�sentant les coordonn�es (ligne, colonne) ainsi que l'orientation du bateau
	 */
	public String placerBateau(){
		String infosCase = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println("S�lection de la case d'origine");
		
		infosCase = infosCase + selectionnerLigne(scan);
		infosCase = infosCase + selectionnerColonne(scan);
		infosCase = infosCase + selectionnerOrientation(scan);
		
		return infosCase;
	}
	
	/**
	 * M�thode permettant au joueur de choisir une case � bombarder
	 * D'abord, le joueur choisit un bateau parmis ceux qui lui restent
	 * Ensuite, les coups jouables sont affich�s
	 * Enfin, il entre les coordonn�es d'une case
	 * La saisie sera recommenc�e tant que le joueur n'aura pas choisi une case correcte
	 * @return caseAttaquee une cha�ne de caract�res contenant les coordonn�es (ligne, colonne) de la case frapp�e
	 */
	public String bombarderCase(){
		String caseAttaquee = "";
		Scanner scan = new Scanner(System.in);
		
		// affichage des bateaux et choix
		Bateau bateauChoisi = grille.getBateau(choisirBateau(scan));
		
		// on r�cup�re toutes les informations utiles sur le bateau choisi
		int vision = bateauChoisi.getChampTir();
		char ligne = bateauChoisi.getCaseOrigine().getLigne();
		int colonne = bateauChoisi.getCaseOrigine().getColonneInt();
		
		// affichage des coups possibles
		String tirsPossibles = "";
		System.out.println("Tirs possibles : ");
		
		// On commence par les cases au-dessus du bateau
		int j = ligne - vision+1;
		int k = colonne;
		if(j < 'a' - 0)
			j = 'a' - 0;
		while(j < ligne){
			tirsPossibles += " " + intToChar(j) + "" + k;
			j++;
		}
		tirsPossibles += "\n";
		
		// On s'occupe ensuite des cases situ�es sur la m�me ligne que le bateau
		k = colonne - vision+1;
		if(k < 0)
			k = 0;
		while(k < colonne + vision && k <= 9){
			tirsPossibles += " " + intToChar(j) + "" + k;
			k++;
		}
		tirsPossibles += "\n";
		
		// Enfin, on r�cup�re les cases situ�es au-dessous du bateau
		k = colonne;
		j++;
		while(j < ligne + vision && j <= 'j'){
			tirsPossibles += " " + intToChar(j) + "" + k;
			j++;
		}
		
		// On affiche ensuite la liste des tirs pour l'utilisateur
		System.out.println(tirsPossibles);
		
		// choix d'une case
		System.out.println("S�lectionner une case � attaquer");
		boolean tirValide = false;
		do{
			caseAttaquee = caseAttaquee + selectionnerLigne(scan);
			caseAttaquee = caseAttaquee + selectionnerColonne(scan);
			
			if(caseAttaquee.charAt(0)  > ligne - vision && caseAttaquee.charAt(0) < ligne + vision
					&& caseAttaquee.charAt(1) - '0' > colonne - vision && caseAttaquee.charAt(1) - '0' < colonne + vision){
				System.out.println("Case attaqu�e : " + caseAttaquee);
				tirValide = true;
			}else{
				System.out.println("Case impossible � atteindre, veuillez recommencer");
				caseAttaquee = "";
			}
		}while(!tirValide);
		
		return caseAttaquee;
	}
	
	/**
	 * M�thode permettant de transcrire un code entier en le caract�re correspondant
	 * @param i le code � transcrire
	 * @return le caract�re correspondant
	 */
	private char intToChar(int i){
		char res = '.';
		if(i >= 97){
			switch(i){
				case 97 : res = 'a'; break;
				case 98 : res = 'b'; break;
				case 99 : res = 'c'; break;
				case 100 : res = 'd'; break;
				case 101 : res = 'e'; break;
				case 102 : res = 'f'; break;
				case 103 : res = 'g'; break;
				case 104 : res = 'h'; break;
				case 105 : res = 'i'; break;
				case 106 : res = 'j';
			}
		}else if(i>=65){
			switch(i){
				case 65 : res = 'A'; break;
				case 66 : res = 'B'; break;
				case 67 : res = 'C'; break;
				case 68 : res = 'D'; break;
				case 69 : res = 'E'; break;
				case 70 : res = 'F'; break;
				case 71 : res = 'G'; break;
				case 72 : res = 'H'; break;
				case 73 : res = 'I'; break;
				case 74 : res = 'J';
			}
		}
		return res;
	}
	
	public boolean demandeDeplacement(){
		Scanner scan = new Scanner(System.in);
		char reponse;
		boolean res = false;
		
		do{
			System.out.println("Voulez-vous d�placer un bateau ?");
			System.out.println("(Tapez o pour Oui, n pour Non)");
			reponse = scan.next().charAt(0);
			if(reponse == 'o' || reponse == 'O'){
				res = true;
			}else if(reponse == 'n' || reponse == 'N'){
				res = false;
			}else{
				System.out.println("Mauvaise touche, vous avez tap� " + reponse);
			}
		}while(reponse != 'o' && reponse != 'n' && reponse != 'O' && reponse != 'N');
		
		return res;
		
	}
	
	/**
	 * M�thode permettant au joueur de d�placer le bateau de son choix
	 * D'abord, il va choisir un bateau parmis ceux restant dans sa flotte
	 * Puis, il va entrer une orientation et une valeur de d�placement
	 * Enfin, le programme va tester si son d�placement est valide
	 * La saisie sera recommenc�e tant que le d�placement n'est pas valide
	 */
	public void deplacerBateau(){
		Scanner scan = new Scanner(System.in);
		String orientation;
		char touchePressee;
		int deplacement = 0;
		boolean deplacementOk;
		
		do{
			int indiceBateau = choisirBateau(scan);
			
			System.out.println("Choix de la direction du d�placement");
			orientation = selectionnerOrientation(scan)+"";
			switch(orientation){
			case "n" : orientation = "nord"; break;
			case "s" : orientation = "sud"; break;
			case "e" : orientation = "est"; break;
			case "o" : orientation = "ouest";
			}
			
			while(deplacement == 0){
				System.out.println("Choisissez le nombre de cases pour le d�placement (1 ou 2)");
				touchePressee = scan.next().charAt(0);
				if("12".indexOf(touchePressee) >= 0)
					deplacement = touchePressee - '0';
				else{
					System.out.println("Mauvaise touche, vous avez tap� "+touchePressee);
					deplacement = 0;
				}
			}
			
			deplacementOk = grille.deplacer_bateau(orientation, deplacement, indiceBateau);
			if(!deplacementOk){
				System.out.println("Erreur de d�placement, veuillez recommencer");
				deplacement = 0;
			}
			else
				System.out.println("Bateau d�plac�");
		}while(!deplacementOk);
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
	 * @return le caract�re repr�sentant la colonne s�lectionn�e
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
	 * M�thode permettant au joueur de s�lectionner une orientation
	 * V�rifie si la saisie est correcte
	 * Demande de refaire la saisie tant que ce n'est pas le cas
	 * @param scan le scanner utilis� pour lire l'entr�e du joueur
	 * @return le caract�re (n, s, e ou o) repr�sentant l'orientation choisie
	 */
	private char selectionnerOrientation(Scanner scan){
		char res = '.';
		char touchePressee;
		
		while(res == '.'){
			System.out.println("S�lectionnez une orientation");
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
				System.out.println("Mauvaise touche, vous avez tap� "+touchePressee);
		}
		
		return res;
	}
	
	/**
	 * M�thode permettant au joueur de s�lectionner un bateau parmis ceux qui lui restent
	 * @param scan l'objet Scanner utilis� pour lire la saisie au clavier du joueur
	 * @return l'indice du bateau s�lectionn� dans le tableau bateaux de la grille
	 */
	private int choisirBateau(Scanner scan){
		char choixBateau;
		int i;
		
		System.out.println("Choisissez un navire (taper le chiffre associ�):");
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
			if(choixBateau - '0' >= 0 && choixBateau - '0' < i && grille.getBateau(choixBateau - '0') != null)
				choixFait = true;
			else
				System.out.println("Mauvaise touche, vous avez tap� "+choixBateau);
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
	
	/**
	 * Permet de changer l'�tat du joueur (s'il �tait actif, il devient inactif et vice versa)
	 */
	public void toggleEnJeu(){
		this.enJeu = !enJeu;
	}
	
	public boolean estEnJeu(){
		return this.enJeu;
	}
}