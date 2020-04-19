package projetfinal;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * 
 *  Class InfoPanel
 *  
 *  Affiche des messages à destination de l’usager, dans une JTextArea scrollable.
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class InfoPanel extends JPanel {
  
  protected JTextArea display;
  protected String info = "";
  
  
  // --- CONSTRUTEUR ---
  
  public InfoPanel(MainPanel mp) {
    setLayout(new BorderLayout());
    display = new JTextArea(5, 100);
    display.setEditable(false);
    display.setLineWrap(true);
    display.setWrapStyleWord(true);
    display.setFont(new Font("Courier", Font.BOLD, 14));
    JScrollPane scrollDisplay = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    add(scrollDisplay, BorderLayout.CENTER);
  }
  
  public void sendConsole(String _info) {
    this.info += _info;
    display.setText(info);
  }
}