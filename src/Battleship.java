
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
		
		System.out.println("\n----- Phase de placement -----");
		System.out.println("\nTOUR DU JOUEUR " + j1.getNum());
		
		placerBateau("Porte-avion", j1, 0);
		placerBateau("Croiseur", j1, 1);
		placerBateau("Contre-torpilleur", j1, 2);
		placerBateau("Sous-marin", j1, 3);
		placerBateau("Torpilleur", j1, 4);
		
		System.out.println("\n");
		System.out.println(grille.affichageAttaque());
	}
	
	/**
	 * Fonction permettant de créer et placer un bateau en fonction des informations données par le joueur
	 * La saisie est redemandée tant qu'une information est erronée ou que le bateau ne peut pas être placé
	 * @param nom nom du bateau à placer
	 * @param j joueur qui va placer le bateau
	 * @param num numéro du bateau à placer pour le joueur
	 */
	public static void placerBateau(String nom, Joueur j, int num){
		String infosBateau[];
		Bateau b;
		boolean bateauPlace = false;
		
		System.out.println(nom + " en cours de placement");
		do{
			infosBateau = j.placerBateau();
			switch(nom){
			case "Porte-avion" : b = new PorteAvion(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]); break;
			case "Croiseur" : b = new Croiseur(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]); break;
			case "Contre-torpilleur" : b = new ContreTorpilleur(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]); break;
			case "Sous-marin" : b = new SousMarin(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]); break;
			case "Torpilleur" : b = new Torpilleur(new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2]); break;
			default : b = new Bateau(1, 1, new Case(infosBateau[0].charAt(0), infosBateau[1].charAt(0)), infosBateau[2], 'b');
			}
			bateauPlace = j.getGrille().ajouterBateau(b, num);
			if(!bateauPlace)
				System.out.println("Erreur de placement, veuillez recommencer");
		}while(!bateauPlace);
		System.out.println(nom + " placé en "+infosBateau[0]+infosBateau[1]+" "+infosBateau[2]);
	}

}
