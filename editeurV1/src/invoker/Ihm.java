/***********************************************************************
 * Module:  Ihm.java
 * Author:  21000155
 * Purpose: Defines the Class Ihm
 ***********************************************************************/
package invoker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import command.*;
import receiver.*;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *Invoker dans le Design Pattern Commande. o	On appelle les commandes concr�tes depuis l'IHM suivant l'action ex�cut�e par l'utilisateur. Notre Ihm d�tecte les �v�nements avec diff�rents listener  et ex�cute la commande concr�te associ�e � l��v�nement. 
 *@author Sanaa Mairouch / Fr�d�ric Rochard
 *@version V1 - 30/11/2015
 */
public class Ihm extends JFrame implements Observer, ActionListener, KeyListener, CaretListener {

	//Attribut de la barre de menu
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenuItem quitter;
	private JMenu menuEdition;
	private JMenuItem mCopier;
	private JMenuItem mCouper;
	private JMenuItem mColler;
	//Attribut de notre barre d'outils
	private JToolBar toolBar = new JToolBar();
	//Attribut contenant les boutons de la barre d'outils
	private JButton btnCopier = new JButton(new ImageIcon("images/copier.jpg"));
	private JButton btnCouper = new JButton(new ImageIcon("images/couper.jpg"));
	private JButton btnColler = new JButton(new ImageIcon("images/coller.jpg"));
	private JTextArea  zoneTxt = new JTextArea(30,50);
	//renvoie dernier caract�re saisie
	private String txt;
	//Commandes � ex�cuter
	private Coller cmdColler;
	private Copier cmdCopier;
	private Couper cmdCouper;
	private Saisir cmdSaisir;
	private Selectionner cmdSelectionner;
	private SuppressionArriere cmdSuppressionArriere;
	private SuppressionAvant cmdSuppressionAvant;

	/**
	 * retourne la zone de texte de l'IHM
	 * @return zoneTxt zone de texte de l'IHM
	 */
	public JTextArea getZoneTxt() {
		return zoneTxt;
	}

	/**
	 * Affecte la comande Coller � l'IHM (Invoker)
	 * @param cmdColler Commande coller de l'IHM
	 */
	public void setCmdColler(Coller cmdColler) {
		this.cmdColler = cmdColler;
	}

	/**
	 * Affecte la comande Copier � l'IHM (Invoker)
	 * @param cmdCopier Commande copier de l'IHM
	 */
	public void setCmdCopier(Copier cmdCopier) {
		this.cmdCopier = cmdCopier;
	}

	/**
	 * Affecte la comande Couper � l'IHM (Invoker)
	 * @param cmdCouper Commande couper de l'IHM
	 */
	public void setCmdCouper(Couper cmdCouper) {
		this.cmdCouper = cmdCouper;
	}

	/**
	 * Affecte la comande saisir � l'IHM (Invoker)
	 * @param cmdSaisir Commande saisir de l'IHM
	 */
	public void setCmdSaisir(Saisir cmdSaisir) {
		this.cmdSaisir = cmdSaisir;
	}

	/**
	 * Affecte la comande Selectionner � l'IHM (Invoker)
	 * @param cmdSelectionner Commande Selectionner de l'IHM
	 */
	public void setCmdSelectionner(Selectionner cmdSelectionner) {
		this.cmdSelectionner = cmdSelectionner;
	}

	/**
	 * Affecte la comande SuppressionArriere � l'IHM (Invoker)
	 * @param cmdSuppressionArriere Commande SuppressionArriere de l'IHM
	 */
	public void setCmdSuppressionArriere(SuppressionArriere cmdSuppressionArriere) {
		this.cmdSuppressionArriere = cmdSuppressionArriere;
	}

	/**
	 * Affecte la comande SuppressionAvant � l'IHM (Invoker)
	 * @param cmdSuppressionAvant Commande SuppressionAvant de l'IHM
	 */
	public void setCmdSuppressionAvant(SuppressionAvant cmdSuppressionAvant) {
		this.cmdSuppressionAvant = cmdSuppressionAvant;
	}

	/**
	 * Constructeur de la classe IHM
	 */
	public Ihm(){
		this.setTitle("Moteur d'�dition V1");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		Font police = new Font("Arial", Font.BOLD, 14);
		zoneTxt.setFont(police);
		zoneTxt.setForeground(Color.BLUE);
		this.initMenu();
		this.initToolBar();
		this.creerTextArea();
		this.setVisible(true); 
	}
	
	/**
	 * Cette m�thode initialise le menu de l'IHM.
	 */
	public void initMenu() {
		this.menuBar = new JMenuBar();
		// Menu Fichier
		this.menuFichier = new JMenu("Fichier");
		this.quitter = new JMenuItem("Quitter");
		this.quitter.addActionListener(this);
		this.menuFichier.add(this.quitter);
		this.menuBar.add(this.menuFichier);
		// Menu Edition
		this.menuEdition = new JMenu("Edition");
		this.menuEdition.addSeparator();
		//Menu copier
		this.mCopier = new JMenuItem("Copier");
		this.mCopier.addActionListener(this);
		this.menuEdition.add(this.mCopier);
		//Menu couper
		this.mCouper = new JMenuItem("Couper");
		this.mCouper.addActionListener(this);
		this.menuEdition.add(this.mCouper);
		//Menu coller
		this.mColler = new JMenuItem("Coller");
		this.mColler.addActionListener(this);
		this.menuEdition.add(this.mColler);
		//Ajout des menus
		this.menuBar.add(this.menuEdition);
		this.setJMenuBar(this.menuBar);
	}

	/**
	 * Cette m�thode initialise la toolbar de l'IHM. Elle ajoute les boutons : 
	 * - couper
	 * - copier
	 * - coller
	 */
	private void initToolBar(){
		//Ajout des boutons � la barre d'outils
		this.toolBar.add(btnCouper);
		this.toolBar.add(btnCopier);
		this.toolBar.add(btnColler);
		//Affichage du texte au survol des boutons
		btnCouper.setToolTipText("Couper");
		btnCopier.setToolTipText("Copier");
		btnColler.setToolTipText("Coller");
		//On ajoute le listener sur les boutons
		btnCouper.addActionListener(this);
		btnCopier.addActionListener(this);
		btnColler.addActionListener(this);
		this.getContentPane().add(toolBar,BorderLayout.NORTH);    
	}

	/**
	 * Methode qui cree une zone de texte editable dans l'IHM.
	 */
	public void creerTextArea() {
		this.getContentPane().add(zoneTxt,BorderLayout.WEST);
		//On ajoute le listener du clavier pour la zone de texte
		zoneTxt.addKeyListener(this);
		//On ajoute le listener de la s�lection pour la zone de texte
		zoneTxt.addCaretListener(this);
	}

	/**
	 * retourne le d�but de la s�lection de la zone de texte
	 * @return this.zoneTxt.getSelectionStart() position du d�but de la s�lection de la zone texte
	 */
	public int getSelectionDebut(){
		return this.zoneTxt.getSelectionStart();
	}

	/**
	 * retourne la longueur de la s�lection de la zone de texte
	 * @return longueurSelection longueur de la s�lection de la zone texte
	 */
	public int getSelectionLongueur(){
		int longueurSelection=this.zoneTxt.getSelectionEnd()-this.zoneTxt.getSelectionStart();
		return longueurSelection;
	}

	/**
	 * retourne la fin de la s�lection de la zone de texte
	 * @return this.zoneTxt.getSelectionEnd() position de la fin de la s�lection de la zone texte
	 */
	public int getSelectionFin(){
		return this.zoneTxt.getSelectionEnd();
	}
	
	/**
	 * renvoie dernier caract�re saisie
	 * @return this.txt dernier caract�re saisie
	 */
	public String getText(){
		return this.txt;
	}

	/**
	 * permet de stocker le dernier caract�re saisi
	 * @param txt dernier caract�re saisi
	 */
	public void setText(String txt){
		this.txt=txt;
	}

	/**
	 * Listener du clavier
	 * M�thode appel�e lorsqu'une touche du clavier est press�e
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		//Test du type de touche press�
		if(event.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
			// Caractere special (SHIFT, CTRL, ALT) => On ne l'�crit pas dans le buffer
		} else if(event.getKeyCode() == KeyEvent.VK_DELETE) {
			//Appel de la commande SuppressionAvant
			cmdSuppressionAvant.execute();
		} else if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			//Appel de la commande SuppressionArriere
			cmdSuppressionArriere.execute();
		} else {
			//Appel de saisie
			txt=Character.toString(event.getKeyChar());
			//Appel de la commande Saisir
			cmdSaisir.execute();
		}
	}	

	/**
	 * Listener du clavier
	 * M�thode appel�e lorsqu'une touche du clavier est relach�e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Listener du clavier
	 * M�thode appel�e lorsqu'une touche du clavier est enfonc�e puis relev�e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Listener du curseur de la zone texte
	 * M�thode appel�e lorsque le curseur de s�lection est modifi�
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		cmdSelectionner.execute();
	}

	/**
	 * Listener des menus et des boutons
	 * M�thode appel�e lorsqu'on clique sur un bouton ou un �l�ment du menu
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//fonction copier
		if((event.getSource() == this.btnCopier) | (event.getSource() == this.mCopier)) {
			cmdCopier.execute();
		//fonction couper
		} else if((event.getSource() == this.btnCouper) | (event.getSource() == this.mCouper)) {
			cmdCouper.execute();
		//fonction coller
		} else if((event.getSource() == this.btnColler) | (event.getSource() == this.mColler)) {
			cmdColler.execute();
		//fonction quitter
		}else if(event.getSource() == this.quitter) {
			System.exit(0);
		}else {
			System.out.println("Action listener ne prend pas en compte cette action: \n"+event.getSource());
		}
	}

	/**
	 * M�thode qui met � jour l'Observer (l'IHM) lorsque l'observable (le moteur d'�dition) est modifi�
	 * @param o the observable object.
	 * @param arg an argument passed to the notifyObservers method.
	 */	
	@Override
	public void update(Observable o, Object arg) {
		// Test si l'observable est un moteur d'�dition
		if(o instanceof MoteurEditionImpl){
//			this.moteurEdition=(MoteurEditionImpl) o;
			// On r�cup�re le texte du buffer pour mettre � jour la zone de texte
			String txtBuffer=((MoteurEditionImpl) o).getBuffer().getContenu().toString();
			this.zoneTxt.setText(txtBuffer);
			// Mise � jour la position du curseur
			this.zoneTxt.setCaretPosition(((MoteurEditionImpl) o).getSelection().getDebutSelection());
			this.zoneTxt.moveCaretPosition(((MoteurEditionImpl) o).getSelection().getLongueurSelection());
		}
	}
}