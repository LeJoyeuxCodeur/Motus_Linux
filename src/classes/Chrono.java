/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Gestion du Chronomètre situé en haut à droite de la fenêtre
*/
package classes;
public class Chrono {
	/**
	 * minutes - Nombres de minutes pour finir le jeu
	 * secondes - Nombres de secondes dans chaque minute
	 */
	public static int minutes=30, secondes; // Laisse une marge pour les enfants...
	/**
	 * chrono - Représentation en String du chronomètre 
	 */
	private String chrono="init ...";

	/**
	 * Pause de 5 secondes avant de passer au mot suivant
	 */
	public void pause(){ // 5 secondes de chargement
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Pause de 8 ms
	 */
	public void wait8ms(){
		try {
			Thread.sleep(8);
		} catch (InterruptedException e) {}
	}
	/**
	 * Mise à jour du chrono sous forme de chaine de caractères
	 */
	public void setChrono(){
		if(minutes<10)
			chrono="0" + minutes + ":";
		else
			chrono=minutes + ":";
		if(secondes<10)
			chrono+="0" + secondes;
		else
			chrono+=secondes;
	}
	/**
	 * Mise à jour des minutes
	 * @return L'entier qui représente les minutes
	 */
	public int updateMin(){
		if(secondes==0)
			return minutes-1;
		return minutes;
	}
	/**
	 * Mise à jour des secondes
	 * @return L'entier qui représente les secondes
	 */
	public int updateSec(){
		if(secondes==0)
			return secondes=59;
		return secondes-1;
	}
	/** retourne Le chronomètre sous forme de chaine
	 * @return Le chronomètre sous forme de chaine
	 */
	public String getChrono() {
		return chrono;
	}
	/**
	 * Mise à jour du chronomètre, utilisant les deux fonctions updateMin() & updateSec()
	 */
	public void updateChrono() {
		setChrono();
		minutes=updateMin();
		secondes=updateSec();
		if(minutes==-1){
			Plateau.boucleMots=true;
			Plateau.boucleJeu=true;
			Fenetre.finJeu=true;
		}
	}
}