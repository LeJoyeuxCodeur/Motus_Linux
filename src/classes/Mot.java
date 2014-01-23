/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Gestion des mots et dictionnaires
*/
package classes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
public class Mot{
	/**
	 * b - Lit les fichiers texte
	 */
	private BufferedReader b;
	/**
	 * r - Instancie l'objet Random nécéssaire pour l'aléatoire
	 */
	private Random r;
	/**
	 * dico - Stocke le dictionnaire des mots de 7 lettres
	 * listeMotsEnfants - Stocke les mots générés aléatoirement pour les enfants
	 */
	private LinkedList<String> dico, listeMotsEnfants;
	/**
	 * alphabet - stocke les indices du premier mot de chaque lettre pour faciliter la recherche
	 */
	private int[] alphabet;
	/**
	 * ascii - stocke les lettres minuscules nécéssaires à remplir le tableau alphabet
	 */
	private char ascii='a';
	/**
	 * tmp - stocke la valeur de chaque mot avant d'être rentré dans la liste chainée
	 * mot - stocke le mot généré aléatoirement
	 */
	private String tmp, mot;
	/**
	 * indice - Commpteur nécéssaire au remplissage de l'alphabet
	 */
	private int indice=0;
	
	/**
	 * Construit un mot
	 */
	public Mot(){
		alphabet=new int[26];
		r=new Random();
		dico=new LinkedList<String>();
		listeMotsEnfants=new LinkedList<String>();
		
		// construction de la liste "dico"
		try {
			b=new BufferedReader(new FileReader("ressources/dico.txt"));
		} catch (FileNotFoundException e) {}
		try {
			tmp=b.readLine();
		} catch (IOException e) {}
		while(tmp!=null){
			dico.add(tmp);
			setAlphabet();
			try {
				tmp=b.readLine();
			} catch (IOException e) {}
		}
		// Au cas ou l'utilisateur modifie le dictionnaire avec un ordre non alphabetique
		Collections.sort(dico); 
		// construction de la liste "mots enfants"
		try {
			b=new BufferedReader(new FileReader("ressources/motsEnfants.txt"));
		} catch (FileNotFoundException e) {}
		try {
			tmp=b.readLine();
		} catch (IOException e) {}
		while(tmp!=null){
			listeMotsEnfants.add(tmp);
			try {
				tmp=b.readLine();
			} catch (IOException e) {}
		}
		// Au cas ou l'utilisateur modifie la liste des mots enfants avec un ordre non alphabetique
		Collections.sort(listeMotsEnfants);
		// Attribution d'un mot aleatoire
		mot=listeMotsEnfants.get(r.nextInt(listeMotsEnfants.size()));
	}
	/**
	 * @return le mot sous forme de chaine de caractères
	 */
	public String getMot() {
		return mot;
	}
	/**
	 * @return le dictionnaire contenant tous les mots
	 */
	public LinkedList<String> getDico(){
		return dico;
	}
	/**
	 * Met à jour l'alphabet en fonction du dictionnaire
	 */
	public void setAlphabet(){
		if(tmp.charAt(0)==ascii){
			alphabet[ascii-'a']=indice;
			ascii++;
		}
		indice++;
	}
	/**
	 * @return l'alphabet sous forme d'un tableau d'entier contenant les indices de chaque lettre (A:1, B:262, C:655, ...)
	 */
	public int[] getAlphabet(){
		return alphabet;
	}
	/**
	 * Stocke le mot aléatoire
	 * @param f Fenetre f
	 */
	public void init(Fenetre f){
		Plateau.boucleMots=false;
		f.setMot(mot);
	}
	/**
	 * Stocke le mot en paramètre dans le variable de classe mot
	 * @param mot Mot qui stocke le mot passé en paramètre
	 */
	public void setMot(String mot){
		this.mot=mot;
	}
}
