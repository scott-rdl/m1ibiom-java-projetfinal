package projetfinal;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/*
 * 
 *  Class MainPanel
 *  
 *  Défini l'agencement des panel selon lee BorderLayout
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class MainPanel extends JPanel {
  
  protected ControlPanel cp;
  protected RegistrePanel rp;
  protected InfoPanel ip;
  
  
  // --- CONSTRUCTEUR ---
  
  public MainPanel() {
    
    setLayout(new BorderLayout(20,20));
    
    // Ajout du Panel de contrôle
    cp = new ControlPanel(this);
    add(cp, BorderLayout.NORTH);
    
    // Ajout du Panel de gestion : Occupant Patient Personnel
    rp = new RegistrePanel(this);
    add(rp, BorderLayout.CENTER);
    
    // Ajout du Panel d'information
    ip = new InfoPanel(this);
    add(ip, BorderLayout.SOUTH);
    ip.sendConsole("###### Bienvenue dans l'interface de gestion du registre hospitalier ######\n\n");
  }
  
  
  // --- METHODE ---
  
  public void update() {
    /* Mise à jours de ControlPanel et RegistrePanel*/
    System.out.println(">> Update : " + this.getClass().getSimpleName());
    cp.updatePanel();
    rp.updatePanel();
  }
}