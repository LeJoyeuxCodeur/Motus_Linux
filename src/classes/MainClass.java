/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Classe principale, gestion des tours de jeu
*/
package classes;
public class MainClass{
	public static void main(String[] args){
		while(!Plateau.boucleJeu){ // Tant que le jeu n'est pas fini
			Mot m=new Mot();
			Fenetre f=new Fenetre();
			Chrono c=f.getChrono();
			Plateau p=f.getPlateau();
			int essais=0;
			
			m.init(f);
			while(!Plateau.boucleMots){ // Tant que le mot n'est pas trouve
				int iChrono=0;
				while(iChrono<125 && essais!=7){ // On fait...
					c.wait8ms(); // ... 8*125ms = 1 sec
					if(MyKey.go){ // Si l'utilisateur appuie sur entree
						essais++;
						p.update(f, m);
						f.testFin(essais);
					}
					iChrono++;
				}
				f.setDisplay(false);
				if(essais!=7){
					c.updateChrono(); // Mise a jour du chrono
					f.repaint();
				}
			}
		}
	}
}
