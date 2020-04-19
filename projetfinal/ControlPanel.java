package projetfinal;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/*
 * 
 *  Class ControlPanel
 *  
 *  Permet la construction du menu supérieur.
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class ControlPanel extends JPanel implements ActionListener{

  protected MainPanel mp;
  protected JComboBox<String> menuComboBox, selecComboBox;
  public JButton addX;
  public static final String[] menuItems = {"PATIENT", "PERSONNEL", "TOUS"};
  protected static ArrayList<Object> tmpList = new ArrayList<Object>();

  
  // --- CONSTRUCTEUR ---
  
  public ControlPanel(MainPanel _mp) {
    
    this.mp = _mp;
    setLayout(new GridLayout(1, 3));

    // Menu déroulant - type d’Occupants à afficher (PATIENT, PERSONNEL, TOUS)
    menuComboBox = new JComboBox<String>();
    menuComboBox.setModel(new DefaultComboBoxModel<String>(menuItems));
    menuComboBox.addActionListener(this);
    menuComboBox.setPreferredSize(new Dimension(200,50));
    menuComboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
    add(menuComboBox);

    // Menu déroulant - choisir un occupant à éditer
    selecComboBox = new JComboBox<String>();
    //selecComboBox.setModel(new DefaultComboBoxModel(menuItems));
    selecComboBox.addActionListener(this);
    selecComboBox.setPreferredSize(new Dimension(200,50));
    selecComboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
    add(selecComboBox);

    // Bouton Ajouter pour afficher la card d'ajout de patient dans PatientPanel
    addX = new JButton("Nouveau");
    addX.addActionListener(this);
    addX.setAlignmentY(Component.CENTER_ALIGNMENT);
    add(addX);
  }

  
  // --- METHODES ---
  
  public void updatePanel() {
    switch ((String) menuComboBox.getSelectedItem()) {
      case "PATIENT":
        selecComboBox.setModel(new DefaultComboBoxModel(mp.rp.patients.toArray()));
        if (mp.rp.patients.size() > 0) selecComboBox.setSelectedItem(mp.rp.patients.get(0));
        break;
        
      case "PERSONNEL":
        selecComboBox.setModel(new DefaultComboBoxModel(mp.rp.personnels.toArray()));
        if (mp.rp.personnels.size() > 0) selecComboBox.setSelectedItem(mp.rp.personnels.get(0));
        break;
        
      case "TOUS":
        tmpList.clear();
        tmpList.addAll(mp.rp.occupants);
        tmpList.addAll(mp.rp.patients);
        tmpList.addAll(mp.rp.personnels);
        selecComboBox.setModel(new DefaultComboBoxModel(tmpList.toArray()));
        if (tmpList.size() > 0) selecComboBox.setSelectedItem(tmpList.get(0));
        break;
        
      default:
        System.out.println(">> updatePanel selecComboBox error value");
    }
    System.out.println("Panel update : " + this.getClass().getSimpleName());
  }

  
  // --- OVERRIDES ---
  
  @Override
  public void actionPerformed(ActionEvent e) {
    
    Object source =  e.getSource();

    if (source == menuComboBox) {
      switch ((String) menuComboBox.getSelectedItem()) {
        case "PATIENT": 
          mp.rp.cards.show(mp.rp, "cardPat");
          mp.update();
          break;
          
        case "PERSONNEL": 
          mp.rp.cards.show(mp.rp, "cardPer");
          mp.update();
          break;
          
        case "TOUS": 
          mp.rp.cards.show(mp.rp, "cardOcc");
          mp.update();
          break;
        default: System.out.println("menuComboBox error value");
      }
    }
    
    
    if (source == selecComboBox) {

      switch ((String) menuComboBox.getSelectedItem()) {
      case "PATIENT":
        Patient pat = (Patient)selecComboBox.getSelectedItem();
        mp.rp.setCurrentPat(pat);
        mp.rp.cardPat.updatePanel();
        mp.rp.cards.show(mp.rp, "cardPat");
        break;

      case "PERSONNEL":
        Personnel per = (Personnel)selecComboBox.getSelectedItem();
        mp.rp.setCurrentPer(per);
        mp.rp.cardPer.updatePanel();
        mp.rp.cards.show(mp.rp, "cardPer");
        break;

      case "TOUS":
        Occupant occ = (Occupant)selecComboBox.getSelectedItem();
        mp.rp.setCurrentOcc(occ);
        mp.rp.cardOcc.updatePanel();
        mp.rp.cards.show(mp.rp, "cardOcc");
        break;
        
      default: System.out.println("selecComboBox error value");
      }
    }
    
    
    if (source == addX) {
      mp.ip.sendConsole(">> Chargement d'une fiche vierge.\n");
      mp.rp.updatePanel();
    }
  }
}