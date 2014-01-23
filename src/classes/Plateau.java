/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Gestion du plateau de jeu
*/
package classes;
import java.util.LinkedList;
import java.util.ListIterator;

public class Plateau {
	/**
	 * tab - grille de jeu
	 */
	private char[][] tab;
	/**
	 * boucleJeu - =true si le jeu est fini, =false sinon
	 * boucleMots - =true si un mot est trouvé, =false sinon
	 */
	static boolean boucleJeu=false, boucleMots=false;
	
	/**
	 * Construit un plateau vide
	 */
	public Plateau(){
		tab=new char[7][7];
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++)
				tab[i][j]=' ';
		}
	}
	/**
	 * Met à jour la zone de texte et utilise la fonction updateAll(...)
	 * @param f Fenetre lancée
	 * @param m Mot en cours
	 */
	public void update(Fenetre f, Mot m){
		f.setDisplay(true);
		if(f.getTest().length()==0)
			f.setTest("x");
		else
			f.setTest(f.getJtf().getText().toLowerCase());
		updateAll(m.getDico(), m.getAlphabet(), f.getTest(), m.getMot());
		f.repaint();
		// ------ Mode Texte ---------------------
		// System.out.println(toString(m.getMot()));
		MyKey.go=false;
	}
	/**
	 * Vérifie si le mot rentré par l'utilisateur est dans le dictionnaire français des mots de 7 lettres
	 * @param dico Dictionnaire
	 * @param alphabet Indice des premières occurences de chaque lettre
	 * @param test Mot rentré par l'utilisateur
	 * @param mot Mot choisi aléatoirement
	 * @return true si le mot est français, false sinon
	 */
	public boolean testFr(LinkedList<String> dico, int[] alphabet, String test, String mot){
		int bInf, bSup;
		ListIterator<String> i;

		if(!(test.length()!=7 || test.charAt(0)!=mot.charAt(0))){
			bInf=alphabet[test.charAt(0)-'a'];
			bSup=dico.size();
			if(test.charAt(0)!='z')
				bSup=alphabet[test.charAt(0)-('a'-1)];
			i=dico.listIterator(bInf);
			while(bInf<bSup){
				if(test.equals(i.next()))
					return true;
				bInf++;
			}
		}
		return false;
	}
	/**
	 * Retourne la grille sous forme d'un tableau de caractères
	 */
	public char[][] getTab(){
		return tab;
	}
	/**
	 * Met à jour le plateau avec le mot rentré par l'utilisateur
	 * @param dico Dictionnaire
	 * @param alphabet Indice des premières occurences de chaque lettre
	 * @param test Mot rentré par l'utilisateur
	 * @param mot Mot choisi aléatoirement
	 */
	public void updateAll(LinkedList<String> dico, int[] alphabet, String test, String mot){
		int cpt=0;
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				if(tab[i][j]==' ' && cpt<7){
					if(!testFr(dico, alphabet, test, mot))
						tab[i][j]='0';
					else{
						if(test.charAt(j)==mot.charAt(0) || test.charAt(j)==mot.charAt(1) || test.charAt(j)==mot.charAt(2) || test.charAt(j)==mot.charAt(3) || test.charAt(j)==mot.charAt(4) || test.charAt(j)==mot.charAt(5) || test.charAt(j)==mot.charAt(6))
							tab[i][j]=test.charAt(j);
						else
							tab[i][j]='2';
					}
					cpt++;
				}
			}
		}
	}
	/**
	 * Représente la grille de jeu par une chaine de caractère
	 * @param mot Mot choisi aléatoirement
	 * @return la grille de jeu dans un String
	 */
	public String toString(String mot){ // Pour la correction du mode texte...
		String tmp="";
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				if(tab[i][j]==mot.charAt(j) || tab[i][j]==' ')
					tmp+=" " + tab[i][j] + " ";
				else if(tab[i][j]==mot.charAt(0) || tab[i][j]==mot.charAt(1) || tab[i][j]==mot.charAt(2) || tab[i][j]==mot.charAt(3) || tab[i][j]==mot.charAt(4) || tab[i][j]==mot.charAt(5) || tab[i][j]==mot.charAt(6))
					tmp+="(" + tab[i][j] + ")";
				else
					tmp+=" . ";
			}
			tmp+="\n";
		}
		tmp+="--------------------------------------";
		return tmp;
	}
}