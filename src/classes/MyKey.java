/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Gestion de l'évènement "Appuyer sur entrée" pour vailder un mot
*/
package classes;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener{
	/**
	 * go - =true si l'utilisateur appuie sur le clavier, =false sinon
	 */
	static boolean go=false;

	/**
	 * Détecte une touche du clavier pressée par l'utilisateur
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) // Si l'utilisateur appuye sur entree
			go=true;
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
}