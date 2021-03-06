/***********************************************************************
 * Module:  Saisir.java
 * Author:  21000155
 * Purpose: Defines the Class Saisir
 ***********************************************************************/
package command;

import invoker.*;
import receiver.*;

/**
 *Commande concrete dans le Design Pattern Commande. Elle Provoque l�ex�cution de l�op�ration Saisir par le moteur d��dition (receiver). 
 *@author Sanaa Mairouch / Fr�d�ric Rochard
 *@version V1 - 30/11/2015
 */
public class Saisir implements Command {
	
	private MoteurEditionImpl receiver ;
	private Ihm invocator;
	
	/**
	 * retourne le moteur d'�dition de la commande Saisir
	 * @return receiver moteur d'�dition de la commande Saisir
	 */
	public MoteurEditionImpl getReceiver() {
		return receiver;
	}

	/**
	 * Constructeur de la classe Saisir
	 * @param receiver moteur d'�dition de la commande Saisir
	 * @param invocator IHM de la commande Saisir
	 */
	public Saisir(MoteurEditionImpl receiver, Ihm invocator) {
		this.receiver = receiver;
		this.invocator=invocator;
	}

	/**
	 * Ex�cution de la commande : Appel de l'op�ration correspondante dans le receiver.
	 */
	@Override
	public void execute() {
		String txt=invocator.getText();
		receiver.saisir(txt);
   }
}