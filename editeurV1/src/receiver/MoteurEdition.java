package receiver;

/**
 *Receiver dans le Design Pattern Command
 *Cette interface réalise l’action sur demande de la commande concrète avec une opération associée. 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public interface MoteurEdition {

	/**
	 * Opération correspondant à la commande concrète couper
	 */
	void couper();
	
	/**
	 * Opération correspondant à la commande concrète copier
	 */
	void copier();
	
	/**
	 * Opération correspondant à la commande concrète coller
	 */
	void coller();

	/**
	 * Opération correspondant à la commande concrète sélectionner
	*  @param debut début de la sélection
    * @param longueur longueur de la sélection
    */
   void selectionner(int debut, int longueur);

   /** 
	 * Opération correspondant à la commande concrète Saisir
	 *     * @param texte texte à saisir dans le buffer
    */
   void saisir(String texte);
   
   /** 
	 * Opération correspondant à la commande concrète suppressionAvant
    */
   void suppressionAvant();
   
   /** 
	 * Opération correspondant à la commande concrète suppressionArriere
    */
   void suppressionArriere();
   
}