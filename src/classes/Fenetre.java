/**
* Bryan Vergauwen
* 20 oct. 2013
* 
* Création d'une fenetre et gestion de toute la partie graphique
*/
package classes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class Fenetre extends JFrame{
	/**
	 * p - Crée un plateau
	 */
	private Plateau p = new Plateau();
	/**
	 * c - Crée un chronomètre
	 */
	private Chrono c=new Chrono();
	/**
	 * mot - Crée la chaine nécéssaire à stocker le mot tiré aléatoirement
	 * test - Crée la chaine nécéssaire à stocker le mot rentré par l'utilisateur
	 */
	private String mot=".......", test;
	/**
	 * jtf - Crée le champ de texte dans lequel l'utilisateur va rentrer son mot
	 */
	private JTextField jtf = new JTextField();
	/**
	 * jp - Conteneur principal
	 */
	private JPanel jp=new JPanel();
	/**
	 * jb - Crée le bouton "Quitter"
	 * jb2 - Crée le bouton "Rejouer"
	 */
	private JButton jb=new JButton("Quitter"), jb2=new JButton("Rejouer");
	/**
	 * i - Création de l'objet Image pour importer l'image de fond de la fenetre
	 */
	private Image i=getToolkit().getImage("ressources/wallpaper.jpg");
	/**
	 * a - Crée l'objet Audio nécéssaire à la gestion du son
	 */
	private Audio a=new Audio();
	/**
	 * display - variable qui gère l'affichage, pour éviter les clignotements dans la fenetre
	 */
	private boolean display=true;
	/**
	 * mots - Variable qui stocke le nombre de mots réussis par l'utilisateur
	 */
	static int mots=1;
	/**
	 * finMot - = true si le mot est trouvé, = false sinon
	 * finJeu - = true si les 10 mots sont trouvés (jeu fini), = false sinon
	 */
	static boolean finMot=false, finJeu=false;

	/**
	 * Création de la fenetre
	 */
	public Fenetre() { // Construction de la fenetre
		configFrame();
		addButtons();
		creeJTF();
	}
	/**
	 * Création et positionnement de la zone de texte
	 */
	public void creeJTF(){
		jp.setLayout(null);
		jtf.setFont(new Font("arial", Font.BOLD, 14));
        jtf.setSize(150, 30);
        jtf.setLocation(547, 204);
        jtf.addKeyListener(new MyKey());
        jp.add(jtf);
        add(jp);
	}
	/**
	 * Configuration de la fenetre
	 */
	public void configFrame(){
		display=true;
		setTitle("Motus -- Level: CM1,CM2 -- Linux Version -- By Bryan Vergauwen");
		setSize(950,670);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/** 
	 * Ajoute et configure les boutons
	 */
	public void addButtons(){
		jb.setLayout(null);
		jb2.setLayout(null);
		jb.setFont(new Font("arial", Font.BOLD, 20));
		jb2.setFont(new Font("arial", Font.BOLD, 20));
        jb.setSize(150, 60);
        jb2.setSize(150, 60);
		jb.setLocation(760, 520);
		jb2.setLocation(590, 520);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec(new String[] {"/bin/bash", "-c", "./again"});
				} catch (Exception e) {}
				System.exit(0);
			}
		});
		jp.add(jb);
		jp.add(jb2);
		add(jp);
	}
	/**
	 * Retourne le mot entré par l'utilisateur sous forme de chaine, converti en minuscule, au cas où il rentre des lettres majuscules
	 * @return le mot entré par l'utilisateur en minuscule
	 */
	public String getTest(){
		return jtf.getText().toLowerCase();
	}
	/**
	 * Retourne l'objet jtf
	 * @return la zone de texte
	 */
	public JTextField getJtf(){
		return jtf;
	}
	/**
	 * @param jtf Place le String passé en paramètre dans la zone de texte 
	 */
	public void setJtf(String jtf){
		this.jtf.setText(jtf);
	}
	/**
	 * @param value met la variable display à la même valeur que value
	 */
	public void setDisplay(boolean value){
		display=value;
	}
	/**
	 * @param mot Modifie la variable de classe mot
	 */
	public void setMot(String mot){
		this.mot=mot;
	}
	/**
	 * @param test Modifie la variable de classe test
	 */
	public void setTest(String test){
		this.test=test;
	}
	/**
	 * Retourne l'objet Chrono
	 * @return le chronomètre
	 */
	public Chrono getChrono(){
		return c;
	}
	/**
	 * Retourne l'objet Plateau
	 * @return le plateau
	 */
	public Plateau getPlateau(){
		return p;
	}
	/**
	 * Modifie le plateau
	 * @param g Objet Graphics
	 */
	public void modif(Graphics g){ // Ecriture sur le plateau
		g.setFont(new Font("serif", Font.PLAIN, 50));
		g.setColor(new Color(255,255,255));
		for(int i=0; i<p.getTab().length; i++){
			for(int j=0; j<p.getTab().length; j++){
				if(p.getTab()[i][j]==mot.charAt(j)){
					g.setColor(new Color(204,0,0));
					g.fillRect(60+65*j, 150+65*i, 65, 65);
					g.setColor(new Color(255,255,255));
					g.drawString(Character.toUpperCase(p.getTab()[i][j])+"", 75+65*j, 198+65*i);
				}
				else if(p.getTab()[i][j]=='0')
					g.drawString(" .", 75+65*j, 198+65*i);
				else if(p.getTab()[i][j]=='2'){
					p.getTab()[i][j]=test.charAt(j);
					g.drawString(Character.toUpperCase(p.getTab()[i][j])+"", 75+65*j, 198+65*i);
				}
				else{
					g.setColor(new Color(255,204,0));
					if(p.getTab()[i][j]!=' ' && (p.getTab()[i][j]==mot.charAt(0) || p.getTab()[i][j]==mot.charAt(1) || p.getTab()[i][j]==mot.charAt(2) || p.getTab()[i][j]==mot.charAt(3) || p.getTab()[i][j]==mot.charAt(4) || p.getTab()[i][j]==mot.charAt(5) || p.getTab()[i][j]==mot.charAt(6)))
						g.fillOval(60+65*j, 150+65*i, 63, 63);
					g.setColor(new Color(255,255,255));
					g.drawString(Character.toUpperCase(p.getTab()[i][j]) + "", 75+65*j, 198+65*i);
				}
			}
		}
	}
	/**
	 * Gère l'affichage du fond d'écran, des jtf/jb, du chronomètre, des informations et du plateau
	 * @param g Graphics g
	 */
	public void display(Graphics g){
		// Affichage wallpaper
		if(display)
			g.drawImage(i, 0, 0, this);
		// Actualisation affichage Jtf/Jb
		jtf.repaint();
		jb.repaint();
		jb2.repaint();
	    // Affichage Chrono
		g.setFont(new Font("serif", Font.PLAIN, 41));
		g.setColor(new Color(255,255,240));
		g.fillRect(789, 60, 120, 60);
		g.setColor(new Color(0,0,0));
		g.drawRect(788, 60, 120, 60);
		g.drawString(c.getChrono(), 790, 108);
		// Affichage infos
		g.setFont(new Font("sansserif", Font.PLAIN, 30));
		g.drawString("Jeu du Motus!", 60, 80);
		g.drawString("Vous jouez avec la lettre " + Character.toUpperCase(mot.charAt(0)), 60, 115);
		g.drawString("Entrez votre mot ici: ", 547, 190);
		g.setFont(new Font("sansserif", Font.BOLD, 30));
		g.setColor(new Color(255,0,0));
		g.drawString("Mot " + mots + " sur 10", 550, 100);
		// Affichage Plateau
		if(display){
			for(int i=0; i<7; i++){
				for(int j=0; j<7; j++){
					g.setColor(new Color(51,102,255));
					g.fillRect(60+50*i, 150+50*j, 157, 157);
				}
			}
			for(int i=0; i<7; i++){
				for(int j=0; j<7; j++){
					g.setColor(new Color(255,255,255));
					g.drawRect(60+65*i, 150+65*j, 65, 65);
				}
			}
		}
	}
	/**
	 * Teste si une fin (du mot ou du jeu) est présente, et si oui affiche les informations correspondantes
	 * @param g Graphics g
	 */
	public void testFinMotOuJeu(Graphics g){ // Test x mot sur 10
		g.setFont(new Font("sansserif", Font.PLAIN, 30));
		g.setColor(new Color(0,0,0));
		if(finJeu){
			g.setFont(new Font("sansserif", Font.ITALIC, 30));
			if(!mot.equals(test)){
		    	g.drawString("Temps écoulé ... ", 550, 300);
		    	g.drawString("Dommage!", 550, 340);
		    }
		    else{
		    	g.drawString("Félicitations, vous avez", 550, 300);
		    	g.drawString("fini le jeu!", 550, 340);
		    }
			g.drawString("A bientot :)", 550, 500);
		}
		else if(finMot){
			if(mot.equals(test)){
				g.drawString("Bravo, vous avez trouvé", 550, 300);
				g.drawString("le mot mystere :)",550, 340);
			}
			else{
				g.drawString("Dommage. Le mot etait ", 550, 300);
				g.drawString("\"" + mot.toUpperCase() + "\"", 550, 340);
			}
			finMot=false;
			g.setFont(new Font("arial", Font.ITALIC, 30));
			g.drawString("Chargement en cours...", 550, 470);
			if(!Fenetre.finJeu)
				c.pause();
		}
	}
	/**
	 * Teste si le jeu est terminé, pour effectuer les actions d'affchage correspondantes
	 * @param cptEssais entier qui contient le nombre de mots rentré par l'utilisateur en un tour
	 */
	public void testFin(int cptEssais) {
		if(mot.equals(getTest()) || cptEssais==7){
			if(mot.equals(getTest()))
				a.sound();
			if(mots==10 && mot.equals(getTest())){ // Jeu fini et gagné
				Plateau.boucleMots=true;
				Plateau.boucleJeu=true;
				Fenetre.finJeu=true;
				repaint();
			}
			else{
				Plateau.boucleMots=true;
				Fenetre.finMot=true;
				if(mot.equals(getTest()))
					mots++;
				repaint();
				dispose();		
			}
		}
		jtf.setText(""); // Remet a jour le JTextField
	}
	/**
	 * Met à jour l'affichage de toute la fenêtre
	 */
	public void paint(Graphics g){
		display(g);
		modif(g);
		testFinMotOuJeu(g);
	}
}
