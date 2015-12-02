/***********************************************************************
 * Module:  Selectionner.java
 * Author:  21000155
 * Purpose: Defines the Class Selectionner
 ***********************************************************************/
package command;

import invoker.*;
import receiver.*;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération Selectionner par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Selectionner implements Command {
	
	private MoteurEditionImpl receiver ;
	private Ihm	invocator;
	
	/**
	 * retourne le moteur d'édition de la commande Selectionner
	 * @return receiver moteur d'édition de la commande Selectionner
	 */
	public MoteurEditionImpl getReceiver() {
		return receiver;
	}

	/**
	 * Constructeur de la classe Selectionner
	 * @param receiver moteur d'édition de la commande Selectionner
	 * @param invocator IHM de la commande Selectionner
	 */
	public Selectionner(MoteurEditionImpl receiver, Ihm	invocator) {
		this.receiver = receiver;
		this.invocator = invocator;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		//Appel de la commande sélectionner
		int debut=invocator.getSelectionDebut();
		int longueur=invocator.getSelectionLongueur();
		receiver.selectionner(debut, longueur);
   }

}