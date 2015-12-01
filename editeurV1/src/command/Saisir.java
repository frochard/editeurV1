/***********************************************************************
 * Module:  Saisir.java
 * Author:  21000155
 * Purpose: Defines the Class Saisir
 ***********************************************************************/
package command;

import invoker.*;
import receiver.*;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l’exécution de l’opération Saisir par le moteur d’édition (receiver). 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Saisir implements Command {
	
	private MoteurEditionImpl receiver ;
	private Ihm invocator;
	
	/**
	 * retourne le moteur d'édition de la commande Saisir
	 * @return receiver moteur d'édition de la commande Saisir
	 */
	public MoteurEditionImpl getReceiver() {
		return receiver;
	}

	/**
	 * Constructeur de la classe Saisir
	 * @param receiver moteur d'édition de la commande Saisir
	 */
	public Saisir(MoteurEditionImpl receiver, Ihm invocator) {
		this.receiver = receiver;
		this.invocator=invocator;
	}

	/**
	 * Exécution de la commande : Appel de l'opération correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		String txt=invocator.getText();
		receiver.saisir(txt);
   }
}