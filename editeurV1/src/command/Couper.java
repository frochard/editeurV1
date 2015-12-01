/***********************************************************************
 * Module:  Couper.java
 * Author:  21000155
 * Purpose: Defines the Class Couper
 ***********************************************************************/
package command;

import receiver.*;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération Couper par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Couper implements Command {
	
	private MoteurEditionImpl receiver ;

	/**
	 * retourne le moteur d'édition de la commande Couper
	 * @return receiver moteur d'édition de la commande Couper
	 */
	public MoteurEditionImpl getReceiver() {
		return receiver;
	}

	/**
	 * Constructeur de la classe Couper
	 * @param receiver moteur d'édition de la commande Couper
	 */
	public Couper(MoteurEditionImpl receiver) {
		this.receiver = receiver;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		receiver.couper();
   }
	
}