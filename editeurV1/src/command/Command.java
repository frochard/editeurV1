package command;


/**
 *Interface Commande dans le Design Pattern Command. Cette interface est commune aux commandes concrètes. 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public interface Command {
   
	/**
	 * Exécution de la commande
	 */
	void execute();
}