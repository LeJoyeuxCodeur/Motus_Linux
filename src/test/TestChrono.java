/**
* Bryan Vergauwen
* 07 Jan. 2014
* 
* Gestion des tests sur le chronomètre
*/
package test;
import static org.junit.Assert.*;
import org.junit.Test;
import classes.Chrono;
public class TestChrono {
	/**
	 * Tests sur les méthodes updateMin() & updateSec()
	 */
	@Test
	public void testUpdateTime() {
		Chrono c=new Chrono();
		if(Chrono.secondes==0){
			assertEquals(c.updateMin(), Chrono.minutes-1);
			assertEquals(c.updateSec(), 59);
		}
		else{
			assertEquals(c.updateMin(), Chrono.minutes);
			assertEquals(c.updateSec(), Chrono.secondes-1);
		}
	}
}