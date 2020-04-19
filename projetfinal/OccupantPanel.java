package projetfinal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * 
 *  Class OccupantPanel
 *  
 *  Définit le formulaire Occupant
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class OccupantPanel  extends JPanel implements ActionListener, FocusListener {
  
  public JLabel index;
  public JTextField nom, prenom, age;
  public JButton addOcc, editOcc, suppOcc;
  protected RegistrePanel rp;
  protected static final String NOM = "(Nom)";
  protected static final String PRENOM = "(Prenom)";
  protected static final String AGE = "(Age)";
  
  
  // --- CONSTRUCTEUR ---
  
  public OccupantPanel(RegistrePanel _rp) {
    
    this.rp = _rp;
  
    // Création des composants
    nom = new JTextField(NOM);
    nom.setPreferredSize(new Dimension(150, 50));
    nom.addFocusListener(this);
    
    prenom = new JTextField(PRENOM);
    prenom.setPreferredSize(new Dimension(150, 50));
    prenom.addFocusListener(this);
    
    age = new JTextField(AGE);
    age.setPreferredSize(new Dimension(100, 50));
    age.addFocusListener(this);
    
    addOcc = new JButton("Ajouter");
    addOcc.addActionListener(this);
    
    editOcc = new JButton("Modifier");
    editOcc.addActionListener(this);
    
    suppOcc = new JButton("Supprimer");
    suppOcc.addActionListener(this);
      
    // Construction de Box (pour chaque ligne)
    Box hBox1 = Box.createHorizontalBox();
    Box hBox2 = Box.createHorizontalBox();
    
    hBox1.add(nom);
    hBox1.add(prenom);
    hBox1.add(age);
    
    hBox2.add(addOcc);
    hBox2.add(Box.createRigidArea(new Dimension(50, 0)));
    hBox2.add(editOcc);
    hBox2.add(Box.createRigidArea(new Dimension(50, 0)));
    hBox2.add(suppOcc);
    
    // On ajoutes toutes les hBox dans vBox 
    Box vBox = Box.createVerticalBox();
    vBox.add(Box.createRigidArea(new Dimension(0, 80)));
    vBox.add(hBox1);
    vBox.add(Box.createRigidArea(new Dimension(0, 20)));
    vBox.add(hBox2);

    add(vBox, BorderLayout.CENTER); 
  }
    
    
  // --- METHODES ---
  public void setFieldValues() {
    nom.setText(rp.currentOcc != null ? rp.currentOcc.getNom() : NOM);
    prenom.setText(rp.currentOcc != null ? rp.currentOcc.getPrenom() : PRENOM);
    age.setText(rp.currentOcc != null ? String.valueOf(rp.currentOcc.getAge()) : AGE);
  }
    
  public void updatePanel() {
    System.out.println("Panel update : " + this.getClass().getSimpleName());
    setFieldValues();
  }
    
  public boolean inputOk() {
    if (!nom.getText().equalsIgnoreCase(NOM) && !prenom.getText().equalsIgnoreCase(PRENOM) && !age.getText().equalsIgnoreCase(AGE)) {
      if (rp.isInt(age.getText())) return true;
      else rp.mp.ip.sendConsole(">> [!] L'age doit être une valeure numérique !\n");
    } else rp.mp.ip.sendConsole(">> [!] Merci de compléter tous les champs !\n");
    return false;
  }
    
    
  // --- OVERRIDES ---
    
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == suppOcc) rp.deleteOcc(rp.currentOcc);
    
    else if (inputOk()) {
      
      if (source == addOcc) {
        Occupant occ = new Occupant(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()));
        rp.setCurrentOcc(null);
        rp.addOcc(occ);
      } 

      if (source == editOcc) {
        Occupant occ = new Occupant(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()));
        if (rp.currentOcc != null) rp.updateOcc(rp.currentOcc, occ); 
        else rp.mp.ip.sendConsole(">> [!] L'occupant n'existe pas, cliquez sur Ajouter !\n");
      }
    }
  }

  @Override
  public void focusGained(FocusEvent e) {
    JComponent source = (JComponent) e.getSource();
    if (rp.currentOcc == null) {
      if (source == nom) nom.setText("");
      if (source == prenom) prenom.setText("");
      if (source == age) age.setText("");
    }
  }

  @Override
  public void focusLost(FocusEvent e) {
    JComponent source = (JComponent) e.getSource();
    if (source == nom && nom.getText().isEmpty()) nom.setText(rp.currentOcc != null ? rp.currentOcc.getNom() : NOM);
    if (source == prenom && prenom.getText().isEmpty()) prenom.setText(rp.currentOcc != null ? rp.currentOcc.getPrenom() : PRENOM);
    if (source == age && age.getText().isEmpty()) age.setText(rp.currentOcc != null ? String.valueOf(rp.currentOcc.getAge()) : AGE);
  }
}
