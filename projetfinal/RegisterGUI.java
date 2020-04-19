package projetfinal;
import java.awt.EventQueue;
import javax.swing.UIManager;

/*
 * 
 *  Class RegisterGUI
 *  
 *  Classe principale du programme
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class RegisterGUI {

  public static void main(String[] args) {
    
    EventQueue.invokeLater(() -> {
      // Tentative d'applique du thème Nimbus.
      try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
              UIManager.setLookAndFeel(info.getClassName());
              break;
          }
        }
      } catch (Exception e) {}
            
      // instanciation du Frame principal
      new MainFrame().setVisible(true);
    });
  }
}