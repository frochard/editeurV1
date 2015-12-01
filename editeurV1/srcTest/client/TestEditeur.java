package client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import command.*;

public class TestEditeur {

	private Editeur testEditeur;
	
	@Before
	public void setUp() throws Exception {
		//Instanciation de l'objet utilisé pour les tests
		testEditeur = new Editeur();
	}

	/**
	 * Test de l'enregistreur pour la commande saisir
	 */
	@Test
	public void testScenario1() {
		//Initialisation du buffer
		String str = "Le M1MIAGE est super.";
		StringBuffer strbuf = new StringBuffer(str);
		testEditeur.getMoteurEdition().getBuffer().setContenu(strbuf);
		testEditeur.getIhm().getZoneTxt().setText(str);
		Command cmdToExecute;
		//Positionnement du curseur dans la zone de texte
		testEditeur.getIhm().getZoneTxt().setSelectionStart(15);
		testEditeur.getIhm().getZoneTxt().setSelectionEnd(20);
		cmdToExecute= new Selectionner(testEditeur.getMoteurEdition(),testEditeur.getIhm());
		cmdToExecute.execute();
		//Appel de la commande couper enregistrable
		cmdToExecute = new Couper(testEditeur.getMoteurEdition());
		cmdToExecute.execute();
		//Positionnement du curseur dans la zone de texte
		testEditeur.getIhm().getZoneTxt().setSelectionStart(0);
		testEditeur.getIhm().getZoneTxt().setSelectionEnd(0);
		cmdToExecute = new Selectionner(testEditeur.getMoteurEdition(),testEditeur.getIhm());
		cmdToExecute.execute();
		cmdToExecute = new Coller(testEditeur.getMoteurEdition());
		cmdToExecute.execute();
		//méthodes de test
		assertEquals("superLe M1MIAGE est .", testEditeur.getMoteurEdition().getBuffer().getContenu().toString());
	}

	/**
	 * Test de l'enregistreur pour saisir
	 */
	@Test
	public void testEnregistrement2() {
		//Initialisation du buffer
		String str = "";
		StringBuffer strbuf = new StringBuffer(str);
		testEditeur.getMoteurEdition().getBuffer().setContenu(strbuf);
		Command cmdToExecute;
		//Positionnement du curseur dans la zone de texte
		testEditeur.getIhm().getZoneTxt().setSelectionStart(0);
		testEditeur.getIhm().getZoneTxt().setSelectionEnd(0);
		cmdToExecute= new Selectionner(testEditeur.getMoteurEdition(),testEditeur.getIhm());
		cmdToExecute.execute();
		//Positionnement du curseur dans la zone de texte
		testEditeur.getIhm().getZoneTxt().setText("Ceci est un test.");
		testEditeur.getIhm().setText("Ceci est un test.");
		cmdToExecute = new Saisir(testEditeur.getMoteurEdition(),testEditeur.getIhm());
		cmdToExecute.execute();
		cmdToExecute = new Selectionner(testEditeur.getMoteurEdition(),testEditeur.getIhm());
		cmdToExecute.execute();
		//méthodes de test
		assertEquals(testEditeur.getMoteurEdition().getBuffer().getContenu().toString(),"Ceci est un test.");
	}
	
}