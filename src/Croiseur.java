/**
 * Classe représentant un croiseur, sous-classe de Bateau
 * @author Quentin Audinot
 *
 */
public class Croiseur extends Bateau {
	
	/**
	 * Constructeur de Croiseur, on appel juste le constructeur de Bateau en lui passant une longueur de 4, une portée de 2 et un symbole c
	 * @param case_origine
	 * @param orientation
	 */
	public Croiseur(Case case_origine, String orientation) {
		super(4, 2, case_origine, orientation, 'c');

	}

}
