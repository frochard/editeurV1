package receiver;

/**
 *Sélection du moteur d'édition dans le Design Pattern Commande
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Selection {

	// Déclaration des attributs
	private int debutSelection;
	private int longueurSelection;

	/**
	 * Constructeur
	 * @param debutSelection position du début de la sélection
	 * @param longueurSelection longueur de la sélection
	 */
	public Selection(int debutSelection, int longueurSelection) {
		super();
		this.debutSelection = debutSelection;
		this.longueurSelection = longueurSelection;
	}
	
	/**
	 * retourne la position de début de la sélection
	 * @return debutSelection position de début de la sélection
	 */
	public int getDebutSelection() {
		return debutSelection;
	}

	/**
	 * affecte une valeur au début de la sélection
	 * @param debut valeur affectée au début de la sélection
	 */
	public void setDebutSelection(int debut) {
		this.debutSelection = debut;
	}
	
	/**
	 * retourne la longueur de la sélection
	 * @return debutSelection longueur de la sélection
	 */
	public int getLongueurSelection() {
		return longueurSelection;
	}

	/**
	 * affecte une valeur à la longueur de la sélection
	 * @param debut valeur affectée à la longueur de la sélection
	 */
	public void setLongueurSelection(int longueur) {
		this.longueurSelection = longueur;
	}
}
