
/**
 * Classe principale qui va gérer toute la partie
 * C'est elle qui va créer les joueurs, les grilles et qui va faire appel à la Vue pour les affichages
 * C'est dans cette classe que se trouve la boucle de jeu
 * @author Marie Darrigol & Quentin Audinot
 *
 */

public class Battleship {
	
	private static Joueur joueur1;
	private static Joueur joueur2;
	private static Joueur joueurCourant;
	private static Joueur joueurInactif;
	private static final String[] FLOTTE = {"Porte-avion", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
	
	public static void main(String[] args) {
		
		//Initialisation des paramètres
		joueur1 = new Joueur(1, new Grille());
		joueur2 = new JoueurIA(2, new Grille());
		Vue partie = new Vue();
		
		// On fixe le joueur 1 comme étant le premier joueur
		joueur1.toggleEnJeu();
		joueurCourant = joueur1;
		joueurInactif = joueur2;
		
		partie.affichageTitre();
		
		// Boucle de placement
		for(int i = 0; i < 2; i++){
			partie.affichageTourJoueurCourant(joueurCourant.getNum());
			
			System.out.println("\n----- Phase de placement -----");
			for(int j = 0; j < FLOTTE.length; j++){
				placerBateau(FLOTTE[j], joueurCourant, j);
				if(!(joueurCourant instanceof JoueurIA)){
					partie.affichageGrilleAttaque(joueurCourant.getGrille());
				}
			}
			if(joueurCourant instanceof JoueurIA){
				partie.affichageFlotteIA();
			}
			
			toggleJoueurCourant();
		}
		
		// Deuxième phase : boucle de jeu et attaque
		boolean resultatTir = false;
		while(!joueurCourant.aPerdu() && !joueurInactif.aPerdu()){
			
			// Attaque
			partie.affichageAttaque(joueurCourant.getNum(), joueurCourant.getGrille(),
					joueurInactif.getGrille(), (joueurCourant instanceof JoueurIA));
			
			// On met de côté le réultat du tir
			resultatTir = joueurInactif.getGrille().gererTir(joueurCourant.bombarderCase());
			
			// On regarde si le joueur inactif a perdu tous ses bateaux
			if(!joueurInactif.aPerdu()){
				// Sinon, on change de joueur actif
				toggleJoueurCourant();
				// Si le tir précédent n'a pas abouti, le nouveau joueur déplace un bateau
				if(!resultatTir){
					partie.affichageDeplacement(joueurCourant.getNum(), joueurCourant.getGrille(), (joueurCourant instanceof JoueurIA));
					joueurCourant.deplacerBateau();
					if(!(joueurCourant instanceof JoueurIA))
						partie.affichageGrilleAttaque(joueurCourant.getGrille());
				}
			}
		}
		
		partie.affichageVictoire(joueurCourant.getNum());
	}
	
	/**
	 * Permet de changer le statut des joueurs
	 * Si le joueur 1 était actif jusqu'à présent, on met le joueur 2 en actif et le 1 en inactif et vice versa
	 */
	public static void toggleJoueurCourant(){
		if(joueur1.estEnJeu()){
			joueurCourant = joueur2;
			joueurInactif = joueur1;
		}else{
			joueurCourant = joueur1;
			joueurInactif = joueur2;
		}
		joueur1.toggleEnJeu();
		joueur2.toggleEnJeu();
	}
	
	/**
	 * Fonction permettant de créer et placer un bateau en fonction des informations données par le joueur
	 * La saisie est redemandée tant qu'une information est erronée ou que le bateau ne peut pas être placé
	 * @param nom nom du bateau à placer
	 * @param j joueur qui va placer le bateau
	 * @param num numéro du bateau à placer pour le joueur
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
		
		if(!(j instanceof JoueurIA))
			System.out.println(nom + " placé en "+infosBateau.charAt(0) + infosBateau.charAt(1) + " " + orientation);
	}
}
