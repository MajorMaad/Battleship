
public class Battleship {

	public static void main(String[] args) {
		Grille grille = new Grille();
		
		// Porte Avion
		Case origine = new Case('d', '2');
		Bateau pa = new PorteAvion(origine, "sud");
		boolean ok = grille.ajouterBateau(pa, 0);
		
		// Croiseur
		Case origine2 = new Case('i', '0');
		Bateau c = new Croiseur(origine2, "est");
		boolean ok2 = grille.ajouterBateau(c, 1);
		
		System.out.println(grille.affichageAttaque());
	}

}
