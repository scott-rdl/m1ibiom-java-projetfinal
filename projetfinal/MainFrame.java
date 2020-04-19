package projetfinal;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * 
 *  Class MainFrame
 *  
 *  Défini les caractéristiques de la fenêtre principale.
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class MainFrame extends JFrame {
  
  public MainPanel mp;
  
  
  // --- CONSTRUCTEUR ---
  
  public MainFrame() {
    
    // Frame properties
    setSize(800,500);
    setMinimumSize(new Dimension(800, 500));
    setTitle("Gestion du Registre Médical");
    setResizable(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Adding icon
    try {this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    } catch (Exception e) {e.printStackTrace();}
    
    // Adding MainPanel
    mp = new MainPanel();
    setContentPane(mp);
  }
}
