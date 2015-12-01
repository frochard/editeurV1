package receiver;

import java.util.Observable;
import java.util.Observer;

import invoker.Ihm;

/**
 *Cette classe implémente l'interface MoteurEdition du Design Pattern Command
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 30/11/2015
 */

public class MoteurEditionImpl extends Observable implements MoteurEdition {

	private Buffer buffer;
	private PressePapier pressePapier;
	private Selection selection;
	private boolean etat;/*attribut pour le DP Observer*/

	/**
	 * retourne le buffer du moteur d'édition
	 * @return buffer buffer du moteur d'édition
	 */
	public Buffer getBuffer() {
		return buffer;
	}

	/**
	 * retourne le presse papier du moteur d'édition
	 * @return pressePapier presse papier du moteur d'édition
	 */
	public PressePapier getPressePapier() {
		return pressePapier;
	}

	/**
	 * retourne la sélection du moteur d'édition
	 * @return selection relourne la sélection du moteur d'édition
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 * affecte un buffer au moteur d'édition
	 * @param buffer buffer à affecter au moteur d'édition
	 */
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * affecte un presse Papier au moteur d'édition
	 * @param pressePapier presse Papier à affecter au moteur d'édition
	 */
	public void setPressePapier(PressePapier pressePapier) {
		this.pressePapier = pressePapier;
	}

	/**
	 * affecte une sélection au moteur d'édition
	 * @param selection sélection à affecter au moteur d'édition
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	/**
	 * Constructeur du moteur d'édition
	 * @param buffer buffer du moteur d'édition
	 * @param pressePapier presse papier du moteur d'édition
	 * @param selection sélection du moteur d'édition
	 */
	public MoteurEditionImpl(Buffer buffer, PressePapier pressePapier, Selection selection) {
		this.buffer = buffer;
		this.pressePapier = pressePapier;
		this.selection = selection;
	}

	/**
	 * Méthode couper : 
	 * - Supprime sélection du buffer
	 * - Ajout de la sélection dans le presse papier
	 */
	@Override
	public void couper() throws UnsupportedOperationException{
		int debutSelection= this.selection.getDebutSelection();
		int longueurSelection = this.selection.getLongueurSelection();
		//Recuperation de la chaîne dans le buffer
		String chaineArecuperer=this.buffer.getContenu().substring(debutSelection, debutSelection+longueurSelection);
		//Affectation de la chaine dans le presse papier
		this.pressePapier.setContenu(chaineArecuperer);
		// Suppression dans le buffer de la chaine coupée
		this.buffer.getContenu().delete(debutSelection,debutSelection+longueurSelection);
		//Modification de la sélection
		this.selection.setLongueurSelection(0);
		//Envoi d'une notification à l'IHM
		majObserver();
	}

	/**
	 * Méthode copier : 
	 * - Ajout de la sélection dans le presse papier
	 */
	@Override
	public void copier() throws UnsupportedOperationException{
		int debutSelection= this.selection.getDebutSelection();
		int longueurSelection = this.selection.getLongueurSelection();
		//Test s'il y a une chaine de sélectionner
		if (longueurSelection>0){
			//Recuperation de la chaîne dans le buffer
			String chaineArecuperer=this.buffer.getContenu().substring(debutSelection, debutSelection+longueurSelection);
			//Affectation de la chaine dans le presse papier
			this.pressePapier.setContenu(chaineArecuperer);
		}
	}

	/**
	 * Méthode coller : 
	 * le presse papier est inséré dans le buffer à partie du début de la sélection
	 */
	@Override
	public void coller() throws UnsupportedOperationException{
		int debutSelection= this.selection.getDebutSelection();
		//Recuperation de la chaîne dans le presse papier
		String txtAInserer=this.pressePapier.getContenu();
		//Insertion de la chaîne dans le buffer
		this.buffer.getContenu().insert(debutSelection,txtAInserer);
		//Modification de la sélection
		this.selection.setDebutSelection(debutSelection+txtAInserer.length());
		this.selection.setLongueurSelection(0);
		//Envoi d'une notification à l'IHM
		majObserver();
	}

	/** 
	 * Méthode sélectionner : modifie le début et la longueur de la sélection
	 * @param debut debut de la sélection
	 * @param longueur longueur de la sélection
	 */
	@Override
	public void selectionner(int debut, int longueur) throws UnsupportedOperationException{
		//Preconditions
		if (debut<0){
			debut=0;
			//throw new IllegalArgumentException("Le debut de la selection doit etre superieur a 0. ");
		}
		if (longueur<0){
			longueur=0;
			//throw new IllegalArgumentException("La longueur de la selection doit etre superieur a 0. ");
		}
		if (debut>this.buffer.getContenu().length()){
			debut=this.buffer.getContenu().length();
			//throw new IllegalArgumentException("Le debut de la selection doit etre a l'interieur d buffer. ");
		}
		if (debut+longueur>this.buffer.getContenu().length()){
			longueur=0;
			//throw new IllegalArgumentException("La fin de la selection doit etre à l'intérieur du buffer. ");
		}
		// Mise à jour des attributs
		this.selection.setDebutSelection(debut);
		this.selection.setLongueurSelection(longueur);
	}

	/** 
	 * Méthode saisir : insère à partir du début de la sélection le texte passé en paramètre
	 * @param texte texte à insérer dans le buffer
	 */
	@Override
	public void saisir(String texte) throws UnsupportedOperationException{
		int debutSelection= this.selection.getDebutSelection();
		// Insertion du texte saisie dans le buffer
		this.buffer.getContenu().insert(debutSelection, texte);
		//Modification de la sélection
		this.selection.setDebutSelection(debutSelection+texte.length());
		this.selection.setLongueurSelection(0);
	}
	
	/** 
	* Méthode suppressionAvant : Supprime le caractère suivant le début de la sélection si la longueur de sélection est nulle
	* Supprime la sélection si plusieurs caractères sont sélectionnés
	**/
	@Override
	public void suppressionAvant() throws UnsupportedOperationException{
		// récuperation du début de la sélection
		int debutSelection = this.selection.getDebutSelection();
		// récuperation de la longueur de la sélection
		int longueurSelection = this.selection.getLongueurSelection();
		// récuperation de la fin de la sélection
		int finSelection = debutSelection+this.selection.getLongueurSelection();
		// récuperation du longueur du buffer
		int longueurBuffer = this.buffer.getContenu().length();
		//Si longueur de la selection = 0
		if(longueurSelection==0){
			// si la sélection n'est pas a la fin du buffer 
			if(debutSelection<longueurBuffer){
				//suppression du caractère après la sélection
				this.buffer.getContenu().delete(debutSelection, finSelection+1);
				//Initialisation de la longueur de sélection à 0
				this.selection.setLongueurSelection(0);
			}
		}else{
			// si la sélection n'est pas a la fin du buffer 
			if(debutSelection<longueurBuffer){
				//suppression du caractère après la sélection
				this.buffer.getContenu().delete(debutSelection, finSelection);
				//Initialisation de la longueur de sélection à 0
				this.selection.setLongueurSelection(0);
			}
		}
	}
	
	/***
	* Méthode suppressionArriere : Supprime le caractère précédent le début de la sélection si la longueur de sélection est nulle
	* Supprime la sélection si plusieurs caractères sont sélectionnés
	 */
	@Override
	public void suppressionArriere() throws UnsupportedOperationException{
		// récuperation du début de la sélection
		int debutSelection = this.selection.getDebutSelection();
		// récuperation de la fin de la sélection
		int finSelection = debutSelection+this.selection.getLongueurSelection();
		// récuperation de la longueur de la sélection
		int longueurSelection = this.selection.getLongueurSelection();
		//Test si la longueur de sélection est > à 0
		if (longueurSelection>0){
			//suppression des caractères apres la sélection
			this.buffer.getContenu().delete(debutSelection, finSelection);
			//Initialisation de la longueur de sélection à 0
			this.selection.setLongueurSelection(0);
		}else{
			// si la sélection n'est pas a la fin du buffer 
			if(debutSelection>0){
				//suppression du caractere apres la sélection
				this.buffer.getContenu().deleteCharAt(debutSelection-1);
				//Modification de la sélection
				this.selection.setDebutSelection(this.selection.getDebutSelection()-1);
			}
		}
	}
	
	/**
	 * envoie une notification à l'observer
	 * @param etat etat de l'observable
	 */
	 public void setEtat(boolean etat){
		this.etat=etat;		
		setChanged();
		notifyObservers(this.etat);		
	 }
	 
	 /**
	  * Ajoute un observer
	  * @param ihm observer à ajouter
	  */
	 public void addObserver(Ihm ihm) {
		this.addObserver((Observer) ihm);
	}
	 
	 /**
	  * Envoi d'une notification à l'IHM (observer)
	  */
	 public void majObserver(){
		this.setChanged();
		this.notifyObservers(this);
	 }
}
