/**
 * Classe qui gére l'affichage
 * @author Quentin Audinot & Marie Darrigol
 *
 */
public class Vue {
	
	/**
	 * Permet d'afficher l'entête du projet 
	 */
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
	
	/**
	 * Permet d'afficher la grille donnée en mode attaque
	 * @param g la grille à afficher
	 */
	public void affichageGrilleAttaque(Grille g){
		System.out.println("\n" + g.affichageAttaque());
	}
	
	/**
	 * Permet d'afficher la grille donnée en mode défense
	 * @param g la grille à afficher
	 */
	public void affichageGrilleDefense(Grille g){
		System.out.println("\n" + g.affichageDefense());
	}
	
	/**
	 * Affiche un message pour le joueur humain lorsque l'IA a terminé de placer ses bateaux
	 */
	public void affichageFlotteIA(){
		System.out.println("\n----- Placement de la flotte terminé -----");
	}
	
	/**
	 * Permet d'afficher le mode attaque
	 * @param n le numéro de joueur attaquant
	 * @param g1 la grille de l'attaquant
	 * @param g2 la grille de l'attaqué
	 * @param estIA indique si le joueur attaquant est une IA : si c'est le cas, les grilles ne seront pas affichées
	 */
	public void affichageAttaque(int n, Grille g1, Grille g2, boolean estIA){
		affichageTourJoueurCourant(n);
		System.out.println("\n----- Phase d'attaque -----");
		if(!estIA){
			affichageGrilleAttaque(g1);
			affichageGrilleDefense(g2);
		}
	}
	
	/**
	 * Permet d'afficher le mode déplacement
	 * @param n le numéro du joueur qui doit déplacer un bateau
	 * @param g la grille du joueur
	 * @param estIA indique si le joueur en question est une IA : si c'est le cas, la grille n'est pas affichée
	 */
	public void affichageDeplacement(int n, Grille g, boolean estIA){
		affichageTourJoueurCourant(n);
		System.out.println("\n----- Phase de déplacement -----");
		if(!estIA)
			affichageGrilleAttaque(g);
	}
	
	/**
	 * Message de victoire affiché en fin de partie
	 * @param n le numéro du joueur victorieux
	 */
	public void affichageVictoire(int n){
		System.out.println("\n----- Victoire du joueur "+ n +" ! Félicitations ! -----");
		System.out.println("-------------- Merci d'avoir joué ! --------------");
	}

}
