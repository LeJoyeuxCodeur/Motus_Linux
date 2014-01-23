/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Gestion de la musique du jeu 
*/
package classes;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	/**
	 * f -Stocke le fichier wave nécéssaire
	 */
	private File f;
	/**
	 * clip - clip Lit le fichier wave
	 */
	private Clip clip;

	/**
	 * Utilise les méthodes nécéssaires pour jouer un son
	 */
	public void sound(){
		try {
	    	f=new File("ressources/motus.wav");
	    	clip = AudioSystem.getClip();
	    	clip.open(AudioSystem.getAudioInputStream(f));          
	    	clip.start();          
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
