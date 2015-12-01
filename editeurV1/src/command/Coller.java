/***********************************************************************
 * Module:  Coller.java
 * Author:  21000155
 * Purpose: Defines the Class Coller
 ***********************************************************************/
package command;

import receiver.*;


/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération Coller par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Coller implements Command {

	private MoteurEditionImpl receiver ;

	/**
	 * retourne le moteur d'édition de la commande Coller
	 * @return receiver moteur d'édition de la commande Coller
	 */
	public MoteurEditionImpl getReceiver() {
		return receiver;
	}

	/**
	 * Constructeur de la classe Coller
	 * @param receiver moteur d'édition de la commande Coller
	 */
	public Coller(MoteurEditionImpl receiver) {
		this.receiver = receiver;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		receiver.coller();
	}
}