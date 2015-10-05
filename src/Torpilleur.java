/**
 * Classe représentant un torpilleur, sous-classe de Bateau
 * @author Quentin Audinot
 *
 */
public class Torpilleur extends Bateau {
	
	/**
	 * Constructeur de Torpilleur, on appel juste le constructeur de Bateau en lui passant une longueur de 2, une portée de 5 et un symbole t
	 * @param case_origine
	 * @param orientation
	 */
	public Torpilleur(Case case_origine, String orientation) {
		super(2, 5, case_origine, orientation, 't');

	}

}
