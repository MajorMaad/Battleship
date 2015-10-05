
public class Battleship {
	
	public static void main(String[] args) {
		
		System.out.println("----------------------------------------");
		System.out.println("------------ Battleship 2.0 ------------");
		System.out.println("- par Quentin Audinot & Marie Darrigol -");
		System.out.println("-------------- 2015, UQAC --------------");
		System.out.println("----------------------------------------");
		
		// D�claration des �l�ments du jeu
		Grille grille = new Grille();
		Grille grille2 = new Grille();
		Joueur j1 = new Joueur(1, grille);
		JoueurIA j2 = new JoueurIA(2, grille2);
		
		// Premi�re phase : les placements
		System.out.println("\n----- Phase de placement -----");
		System.out.println("\nTOUR DU JOUEUR " + j1.getNum());
		
		placerBateau("Porte-avion", j1, 0);
		System.out.println("\n");
		//boolean ok = grille.deplacer_bateau("sud", 1, 0);
		System.out.println(grille.affichageAttaque());
		
		placerBateau("Croiseur", j1, 1);
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
		
		placerBateau("Contre-torpilleur", j1, 2);
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
		
		placerBateau("Sous-marin", j1, 3);
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
		
		placerBateau("Torpilleur", j1, 4);
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
		
		System.out.println("\n");
		System.out.println(grille.affichageDefense());
		
		System.out.println("\nTOUR DU JOUEUR " + j2.getNum());
		System.out.println("Placement de la flotte...");
		placerBateau("Porte-avion", j2, 0);
		placerBateau("Croiseur", j2, 1);
		placerBateau("Contre-torpilleur", j2, 2);
		placerBateau("Sous-marin", j2, 3);
		placerBateau("Torpilleur", j2, 4);
		System.out.println("Placement termin�");
		// juste pour le debug, devra �tre enlev� pour l'IA dans la version finale
		System.out.println(j2.getGrille().affichageAttaque());
		
		
		// Deuxi�me phase : boucle de jeu et attaque
		/*
		System.out.println("\n----- Phase d'attaque -----");
		System.out.println("\nTOUR DU JOUEUR " + j1.getNum());
		System.out.println(j1.getGrille().affichageAttaque());
		System.out.println(j2.getGrille().affichageDefense());
		System.out.println("Case attaqu�e : " + j1.bombarderCase());
		
		System.out.println("\nTOUR DU JOUEUR " + j2.getNum());
		System.out.println(j2.getGrille().affichageAttaque());	// A enlever pour la version finale
		System.out.println(j1.getGrille().affichageDefense());	// idem
		System.out.println("Case attaqu�e : " + j2.bombarderCase());
		*/
		
		System.out.println("\n----- Phase de d�placement -----");
		/*
		System.out.println("\nTOUR DU JOUEUR " + j1.getNum());
		System.out.println(j1.getGrille().affichageAttaque());
		j1.deplacerBateau();
		System.out.println(j1.getGrille().affichageAttaque());
		*/
		
		System.out.println("\nTOUR DU JOUEUR " + j2.getNum());
		System.out.println(j2.getGrille().affichageAttaque());
		j2.deplacerBateau();
		System.out.println(j2.getGrille().affichageAttaque());
	}
	
	/**
	 * Fonction permettant de cr�er et placer un bateau en fonction des informations donn�es par le joueur
	 * La saisie est redemand�e tant qu'une information est erron�e ou que le bateau ne peut pas �tre plac�
	 * @param nom nom du bateau � placer
	 * @param j joueur qui va placer le bateau
	 * @param num num�ro du bateau � placer pour le joueur
	 */
	public static void placerBateau(String nom, Joueur j, int num){
		String infosBateau;
		String orientation = "";
		Bateau b;
		boolean bateauPlace = false;
		
		// Si le joueur en question est une IA, on n'affiche pas les messages
		if(!(j instanceof JoueurIA))
			System.out.println(nom + " en cours de placement");
		do{
			infosBateau = j.placerBateau();
			switch(infosBateau.charAt(2)){
			case 'n' : orientation = "nord"; break;
			case 's' : orientation = "sud"; break;
			case 'e' : orientation = "est"; break;
			case 'o' : orientation = "ouest";
			}
			switch(nom){
			case "Porte-avion" : b = new PorteAvion(new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation); break;
			case "Croiseur" : b = new Croiseur(new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation); break;
			case "Contre-torpilleur" : b = new ContreTorpilleur(new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation); break;
			case "Sous-marin" : b = new SousMarin(new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation); break;
			case "Torpilleur" : b = new Torpilleur(new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation); break;
			default : b = new Bateau(1, 1, new Case(infosBateau.charAt(0), infosBateau.charAt(1)), orientation, 'b');
			}
			bateauPlace = j.getGrille().ajouterBateau(b, num);
			if(!bateauPlace && !(j instanceof JoueurIA))
				System.out.println("Erreur de placement, veuillez recommencer");
		}while(!bateauPlace);
		// Devra �tre enlev� pour l'IA dans la version finale, on le garde juste pour le debug
		System.out.println(nom + " plac� en "+infosBateau.charAt(0) + infosBateau.charAt(1) + " " + orientation);
	}

}
