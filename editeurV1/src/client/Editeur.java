/***********************************************************************
 * Module:  Editeur.java
 * Author:  21000155
 * Purpose: Defines the Class Editeur
 ***********************************************************************/
package client;

import command.Coller;
import command.Copier;
import command.Couper;
import command.Saisir;
import command.Selectionner;
import command.SuppressionArriere;
import command.SuppressionAvant;
import invoker.Ihm;
import receiver.Buffer;
import receiver.MoteurEditionImpl;
import receiver.PressePapier;
import receiver.Selection;


/**
 *Client dans le Design Pattern Commande
 *L’éditeur configure l’invoker (Ihm) en créant et configurant les commandes concrètes. 
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */
public class Editeur{
	
	private Ihm ihm;
	private MoteurEditionImpl moteurEdition;
	//Déclaration des commandes
	private Coller cmdColler;
	private Copier cmdCopier;
	private Couper cmdCouper;
	private Saisir cmdSaisir;
	private Selectionner cmdSelectionner;
	private SuppressionArriere cmdSuppressionArriere;
	private SuppressionAvant cmdSuppressionAvant;

	/**
	 * retourne l'IHM de l'éditeur
	 * @return ihm ihm de l'éditeur 
	 */
	public Ihm getIhm() {
		return ihm;
	}

	/**
	 * retourne le moteur d'édition de l'éditeur
	 * @return moteurEdition moteur d'édition de l'éditeur
	 */
	public MoteurEditionImpl getMoteurEdition() {
		return moteurEdition;
	}

	/**
	 * Constructeur de la classe Editeur
	 */
	public Editeur() {
		//Création de l'IHM
		this.ihm = new Ihm();
		//Création du moteur d'édition
		this.moteurEdition=new MoteurEditionImpl(new Buffer(new StringBuffer("")),new PressePapier(""),new Selection(0,0));
		//Ajout des commandes
		cmdColler=new Coller(moteurEdition);
		cmdCopier=new Copier(moteurEdition);
		cmdCouper=new Couper(moteurEdition);
		cmdSaisir=new Saisir(moteurEdition,ihm);
		cmdSelectionner=new Selectionner(moteurEdition,ihm);
		cmdSuppressionArriere=new SuppressionArriere(moteurEdition);
		cmdSuppressionAvant=new SuppressionAvant(moteurEdition);
		//Ajout des commandes à l'ihm
		this.ihm.setCmdColler(cmdColler);
		this.ihm.setCmdCopier(cmdCopier);
		this.ihm.setCmdCouper(cmdCouper);
		this.ihm.setCmdSaisir(cmdSaisir);
		this.ihm.setCmdSelectionner(cmdSelectionner);
		this.ihm.setCmdSuppressionArriere(cmdSuppressionArriere);
		this.ihm.setCmdSuppressionAvant(cmdSuppressionAvant);
		//Ajout d'un observer
		this.moteurEdition.addObserver(this.ihm);
	}
	
	public static void main(String [] arg){
		new Editeur();
	}
}