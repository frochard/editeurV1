package command;

import receiver.MoteurEdition;
import receiver.MoteurEditionImpl;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération SuppressionArriere par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class SuppressionArriere implements Command{

	private MoteurEdition receiver ;
	
	/**
	 * Constructeur de la classe SuppressionArriere
	 * @param receiver moteur d'édition de la commande SuppressionArriere
	 */
	public SuppressionArriere(MoteurEditionImpl receiver) {
		this.receiver = receiver;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		receiver.suppressionArriere();
   }
}
