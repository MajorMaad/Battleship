/**
 * Classe représentant un porte-avion, sous-classe de Bateau
 * @author Quentin Audinot
 *
 */
public class PorteAvion extends Bateau {
	
	/**
	 * Constructeur de PorteAvion, on appel juste le constructeur de Bateau en lui passant une longueur de 5, une portée de 2 et un symbole p
	 * @param case_origine
	 * @param orientation
	 */
	public PorteAvion(Case case_origine, String orientation) {
		super(5, 2, case_origine, orientation, 'p');

	}

}
