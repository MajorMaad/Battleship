/**
 * Classe représentant un sous-marin, sous-classe de Bateau
 * @author Quentin Audinot
 *
 */
public class SousMarin extends Bateau {
	
	/**
	 * Constructeur de SousMarin, on appel juste le constructeur de Bateau en lui passant une longueur de 3, une portée de 4 et un symbole s
	 * @param case_origine
	 * @param orientation
	 */
	public SousMarin(Case case_origine, String orientation) {
		super(3, 4, case_origine, orientation, 's');

	}

}
