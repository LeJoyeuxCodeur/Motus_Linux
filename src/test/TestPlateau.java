/**
* Bryan Vergauwen
* 07 Jan. 2014
* 
* Gestion des tests sur le plateau
*/
package test;
import static org.junit.Assert.*;
import org.junit.Test;
import classes.Fenetre;
import classes.Mot;
import classes.Plateau;
public class TestPlateau {
	/**
	 * Teste de la méthode testFr(...)
	 */
	@Test
	public void testTestFr(){
		Plateau p=new Plateau();
		Mot m=new Mot();
		
		assertTrue(p.testFr(m.getDico(), m.getAlphabet(), "platane", "platane"));
		assertTrue(p.testFr(m.getDico(), m.getAlphabet(), "abricot", "abricot"));
		assertFalse(p.testFr(m.getDico(), m.getAlphabet(), "platane", "abricot"));
		assertFalse(p.testFr(m.getDico(), m.getAlphabet(), "toto", "toto"));
		assertFalse(p.testFr(m.getDico(), m.getAlphabet(), "abcdefg", "abcdefg"));
		assertFalse(p.testFr(m.getDico(), m.getAlphabet(), "", ""));
	}
	/**
	 * Test de la méthode toString(...)
	 */
	@Test
	public void testToString(){
		Plateau p=new Plateau();
		Mot m=new Mot();
		Fenetre f=new Fenetre();

		m.setMot("etudier");
		f.setJtf("entente");
		p.update(f, m);
		assertEquals(p.toString(m.getMot()),
				 " e  . (t)(e) . (t)(e)" +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n" +
				 "--------------------------------------");
		f.setJtf("tutu");
		p.update(f, m);
		assertEquals(p.toString(m.getMot()),
				 " e  . (t)(e) . (t)(e)" +
				 "\n .  .  .  .  .  .  . " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n" +
				 "--------------------------------------");
		f.setJtf("etudier");
		p.update(f, m);
		assertEquals(p.toString(m.getMot()),
				 " e  . (t)(e) . (t)(e)" +
				 "\n .  .  .  .  .  .  . " +
				 "\n e  t  u  d  i  e  r " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n                     " +
				 "\n" +
				 "--------------------------------------");
	}
}