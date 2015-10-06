/**
 * Classe qui gére l'affichage
 * @author Quentin Audinot & Marie Darrigol
 *
 */
public class Vue {
	
	public void affichageTitre(){
		System.out.println("----------------------------------------");
		System.out.println("------------ Battleship 2.0 ------------");
		System.out.println("- par Quentin Audinot & Marie Darrigol -");
		System.out.println("-------------- 2015, UQAC --------------");
		System.out.println("----------------------------------------");
	}
	
	/**
	 * Affichage indiquant à quel joueur appartient le tour
	 * @param n numéro du joueur
	 */
	public void affichageTourJoueurCourant(int n){
		System.out.println("\n----- TOUR DU JOUEUR " + n + " -----");
	}
	
	public void affichageGrilleAttaque(Grille g){
		System.out.println("\n" + g.affichageAttaque());
	}
	
	public void affichageGrilleDefense(Grille g){
		System.out.println("\n" + g.affichageDefense());
	}
	
	public void affichageFlotteIA(Grille g){
		System.out.println("\n----- Placement de la flotte terminé -----");
	}
	
	public void affichageAttaque(int n, Grille g1, Grille g2, boolean estIA){
		affichageTourJoueurCourant(n);
		System.out.println("\n----- Phase d'attaque -----");
		if(!estIA){
			affichageGrilleAttaque(g1);
			affichageGrilleDefense(g2);
		}
	}
	
	public void affichageDeplacement(int n, Grille g, boolean estIA){
		affichageTourJoueurCourant(n);
		System.out.println("\n----- Phase de déplacement -----");
		if(!estIA)
			affichageGrilleAttaque(g);
	}
	
	public void affichageVictoire(int n){
		System.out.println("\n----- Victoire du joueur "+ n +" ! Félicitations ! -----");
		System.out.println("-------------- Merci d'avoir joué ! --------------");
	}

}
