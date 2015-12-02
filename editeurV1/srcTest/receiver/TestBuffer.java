package receiver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *Classe de test du buffer
 *@author Sanaa Mairouch / Frédéric Rochard
 *@version V1 - 02/12/2015
 */
public class TestBuffer {

	//Instanciation de l'objet à tester
	Buffer testBuffer = new Buffer(new StringBuffer(""));

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetContenu() {
		String str="test";
		StringBuffer strbuf = new StringBuffer(str);

		testBuffer.setContenu(strbuf);
		assertEquals(str,testBuffer.getContenu().toString());
	}

	@Test
	public void testSetContenu() {
		String str="test";
		StringBuffer strbuf = new StringBuffer(str);

		testBuffer.setContenu(strbuf);
		assertEquals(str,testBuffer.getContenu().toString());
	}
}
