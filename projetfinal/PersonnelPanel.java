package projetfinal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * 
 *  Class PersonnelPanel
 *  
 *  Définit le formulaire Personnel
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class PersonnelPanel  extends JPanel implements ActionListener, FocusListener {

  protected JLabel index;
  protected JTextArea roles;
  protected JTextField nom, prenom, age, role;
  protected JButton addPer, editPer, suppPer, plusRole;
  protected RegistrePanel rp;
  protected static final String NOM = "(Nom)";
  protected static final String PRENOM = "(Prenom)";
  protected static final String AGE = "(Age)";
  protected static final String ROLE = "(Role)";


  // --- CONSTRUCTEUR ---

  public PersonnelPanel(RegistrePanel _rp) {

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

    role = new JTextField(ROLE);
    role.setPreferredSize(new Dimension(150, 50));
    role.addFocusListener(this);

    plusRole = new JButton("+");
    plusRole.setPreferredSize(new Dimension(50, 50));
    plusRole.addActionListener(this);

    roles = new JTextArea(3, 30);
    roles.setText("Aucun symptome");
    roles.setEditable(false);
    roles.setLineWrap(true);
    roles.setWrapStyleWord(true);
    roles.setFont(new Font("Courier", Font.BOLD, 11));
    JScrollPane rolesScroll = new JScrollPane(roles, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    addPer = new JButton("Ajouter");
    addPer.addActionListener(this);

    editPer = new JButton("Modifier");
    editPer.addActionListener(this);

    suppPer = new JButton("Supprimer");
    suppPer.addActionListener(this);

    // Construction de Box (pour chaque ligne)
    Box hBox1 = Box.createHorizontalBox();
    Box hBox2 = Box.createHorizontalBox();
    Box hBox3 = Box.createHorizontalBox();

    hBox1.add(nom);
    hBox1.add(prenom);
    hBox1.add(age);

    hBox2.add(role);
    hBox2.add(plusRole);
    hBox2.add(rolesScroll);

    hBox3.add(addPer);
    hBox3.add(Box.createRigidArea(new Dimension(30, 0)));
    hBox3.add(editPer);
    hBox3.add(Box.createRigidArea(new Dimension(30, 0)));
    hBox3.add(suppPer);

    // On ajoutes toutes les hBox dans vBox 
    Box vBox = Box.createVerticalBox();
    vBox.add(Box.createRigidArea(new Dimension(0, 20)));
    vBox.add(hBox1);
    vBox.add(Box.createRigidArea(new Dimension(0, 20)));
    vBox.add(hBox2);
    vBox.add(Box.createRigidArea(new Dimension(0, 20)));
    vBox.add(hBox3);

    add(vBox, BorderLayout.CENTER);
  }

  
  // --- METHODES ---
  
  public void setFieldValues() {
    /* Completions des champs */
    nom.setText(rp.currentPer != null ? rp.currentPer.getNom() : NOM);
    prenom.setText(rp.currentPer != null ? rp.currentPer.getPrenom() : PRENOM);
    age.setText(rp.currentPer != null ? String.valueOf(rp.currentPer.getAge()) : AGE);
    roles.setText(rp.currentPer != null ? rp.listeRolesPer() : "Aucun symptome");
    role.setText(ROLE);
  }

  public void updatePanel() {
    /* Mise à jour du Panel */
    System.out.println("Panel update : " + this.getClass().getSimpleName());
    setFieldValues();
  }
  
  public boolean inputOk() {
    /* Vérification des champs */
    if (!nom.getText().equalsIgnoreCase(NOM) && !prenom.getText().equalsIgnoreCase(PRENOM) && !age.getText().equalsIgnoreCase(AGE)) {
      if (rp.isInt(age.getText())) return true;
      else rp.mp.ip.sendConsole(">> [!] L'age doit être une valeure numérique !\n");
    } else rp.mp.ip.sendConsole(">> [!] Merci de compléter tous les champs !\n");
    return false;
  }
  
  
  // --- OVERRIDES ---
  
  @Override
  public void actionPerformed(ActionEvent e) {
    /* Action pour chaque bouton : Supprimer, Modifier, Ajouter. */
    Object source = e.getSource();

    if (source == suppPer) rp.deletePer(rp.currentPer);

    else if (source == plusRole) {
      if (!role.getText().equalsIgnoreCase(ROLE)) {
        roles.setText(rp.addRolePer(role.getText()));
        role.setText(ROLE);
      } else rp.mp.ip.sendConsole(">> [!] Veuillez renseigner le champ (Role) !\n");
    }

    else if (inputOk()) {
      
      if (source == addPer) {
        if (rp.tmpRoles.size() > 0) {
          Personnel Per = new Personnel(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()), rp.tmpRoles);
          rp.setCurrentPer(null);
          rp.addPer(Per);
        } else rp.mp.ip.sendConsole(">> [!] Veuillez ajouter au moins un rôle !\n");
      } 

      if (source == editPer) {
        Occupant occ = new Occupant(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()));
        if (rp.currentPer != null) rp.updateOcc(rp.currentPer, occ);
        else rp.mp.ip.sendConsole(">> [!] Le praticien n'existe pas, cliquez sur Ajouter !\n");
      }
    }
  }
  
  
  @Override
  public void focusGained(FocusEvent e) {
    /* retrait du "placeholder" lors du clic sur un TextField */
    JComponent source = (JComponent) e.getSource();
    
    if (rp.currentPer == null) {
      if (source == nom) nom.setText("");
      if (source == prenom) prenom.setText("");
      if (source == age) age.setText("");
    }
    
    if (source == role) role.setText("");
  }


  @Override
  public void focusLost(FocusEvent e) {
    /* Action sur les TextField lors de la perte de focus */
    JComponent source = (JComponent) e.getSource();
    
    if (source == nom && nom.getText().isEmpty()) nom.setText(rp.currentPer != null ? rp.currentPer.getNom() : NOM);
    if (source == prenom && prenom.getText().isEmpty()) {prenom.setText(rp.currentPer != null ? rp.currentPer.getPrenom() : PRENOM);}
    if (source == age && age.getText().isEmpty()) age.setText(rp.currentPer != null ? String.valueOf(rp.currentPer.getAge()) : AGE);
    if (source == role && role.getText().isEmpty()) role.setText(ROLE);
  }
}
