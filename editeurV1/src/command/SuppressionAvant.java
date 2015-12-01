package command;

import receiver.MoteurEdition;
import receiver.MoteurEditionImpl;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération SuppressionAvant par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class SuppressionAvant implements Command{

	private MoteurEdition receiver ;
	
	/**
	 * Constructeur de la classe SuppressionAvant
	 * @param receiver moteur d'édition de la commande SuppressionAvant
	 */
	public SuppressionAvant(MoteurEditionImpl receiver) {
		this.receiver = receiver;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		receiver.suppressionAvant();
   }
}
