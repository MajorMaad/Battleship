/**
 * Classe représentant un contre-torpilleur, sous-classe de Bateau
 * @author Quentin Audinot
 *
 */
public class ContreTorpilleur extends Bateau {
	
	/**
	 * Constructeur de ContreTorpilleur, on appel juste le constructeur de Bateau en lui passant une longueur de 3, une portée de 2 et un symbole r
	 * @param case_origine
	 * @param orientation
	 */
	public ContreTorpilleur(Case case_origine, String orientation) {
		super(3, 2, case_origine, orientation, 'r');

	}

}
