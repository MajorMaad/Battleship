
public class Battleship {
	
	public static void main(String[] args) {
		
		System.out.println("----------------------------------------");
		System.out.println("------------ Battleship 2.0 ------------");
		System.out.println("- par Quentin Audinot & Marie Darrigol -");
		System.out.println("-------------- 2015, UQAC --------------");
		System.out.println("----------------------------------------");
		
		Grille grille = new Grille();
		
		/* Test pour le placement des bateaux
		// Porte Avion
		Case origine = new Case('d', '2');
		Bateau pa = new PorteAvion(origine, "sud");
		boolean ok = grille.ajouterBateau(pa, 0);
		
		// Croiseur
		Case origine2 = new Case('i', '0');
		Bateau c = new Croiseur(origine2, "est");
		boolean ok2 = grille.ajouterBateau(c, 1);
		*/
		
		// Test pour le placement avec un joueur humain
		Joueur j1 = new Joueur(1, grille);
		String infosBateau[];
		boolean bateauJ1Place = false;
		
		System.out.println("\n----- Phase de placement -----");
		System.out.println("\nTOUR DU JOUEUR " + j1.getNum());
		
		System.out.println("\nPorte-avion en cours de placement");
		do{
			infosBateau = j1.placerBateau();
			Bateau pa1 = new PorteAvion(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]);
			bateauJ1Place = grille.ajouterBateau(pa1, 0);
			if(!bateauJ1Place)
				System.out.println("Erreur de placement, veuillez recommencer");
		}while(!bateauJ1Place);
		System.out.println("Porte-avion placé en "+infosBateau[0]+infosBateau[1]+" "+infosBateau[2]);
		/*
		placerBateau("Croiseur", grille, j1);
		placerBateau("Contre-torpilleur", grille, j1);
		placerBateau("Sous-marin", grille, j1);
		placerBateau("Torpilleur", grille, j1);
		*/
		
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
	}

}
