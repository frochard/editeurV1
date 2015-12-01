package receiver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPressePapier {

	//Instanciation de l'objet à tester
	PressePapier testPressePapier = new PressePapier("");
		
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetContenu() {
		String str = "test";
		
		testPressePapier.setContenu(str);
		assertEquals(str,testPressePapier.getContenu());
	}

	@Test
	public void testSetContenu() {
		String str = "test";

		testPressePapier.setContenu(str);
		assertEquals(str,testPressePapier.getContenu());
	}

}
