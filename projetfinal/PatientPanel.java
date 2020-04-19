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
 *  Class PatientPanel
 *  
 *  Définit le formulaire Patient
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class PatientPanel  extends JPanel implements ActionListener, FocusListener{

  protected JLabel index;
  protected JTextArea sympts;
  protected JTextField nom, prenom, age, sympt, sever;
  protected JButton addPat, editPat, suppPat, plusSympt;
  protected RegistrePanel rp;
  protected static final String NOM = "(Nom)";
  protected static final String PRENOM = "(Prenom)";
  protected static final String AGE = "(Age)";
  protected static final String SYMPT = "(Symptome)";
  protected static final String SEVER = "(Sévérité)";


  // --- CONSTRUCTEUR ---
  
  public PatientPanel(RegistrePanel _rp) {

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

    sympt = new JTextField(SYMPT);
    sympt.setPreferredSize(new Dimension(100, 50));
    sympt.addFocusListener(this);

    sever = new JTextField(SEVER);
    sever.setPreferredSize(new Dimension(100, 50));
    sever.addFocusListener(this);

    plusSympt = new JButton("+");
    plusSympt.setPreferredSize(new Dimension(50, 50));
    plusSympt.addActionListener(this);

    sympts = new JTextArea(3, 30);
    sympts.setText("Aucun symptome");
    sympts.setEditable(false);
    sympts.setLineWrap(true);
    sympts.setWrapStyleWord(true);
    sympts.setFont(new Font("Courier", Font.BOLD, 11));
    JScrollPane symptsScroll = new JScrollPane(sympts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    addPat = new JButton("Ajouter");
    addPat.addActionListener(this);

    editPat = new JButton("Modifier");
    editPat.addActionListener(this);

    suppPat = new JButton("Supprimer");
    suppPat.addActionListener(this);

    // Construction de Box (pour chaque ligne)
    Box hBox1 = Box.createHorizontalBox();
    Box hBox2 = Box.createHorizontalBox();
    Box hBox3 = Box.createHorizontalBox();

    hBox1.add(nom);
    hBox1.add(prenom);
    hBox1.add(age);

    hBox2.add(sympt);
    hBox2.add(sever);
    hBox2.add(plusSympt);
    hBox2.add(symptsScroll);

    hBox3.add(addPat);
    hBox3.add(Box.createRigidArea(new Dimension(30, 0)));
    hBox3.add(editPat);
    hBox3.add(Box.createRigidArea(new Dimension(30, 0)));
    hBox3.add(suppPat);

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
    nom.setText(rp.currentPat != null ? rp.currentPat.getNom() : NOM);
    prenom.setText(rp.currentPat != null ? rp.currentPat.getPrenom() : PRENOM);
    age.setText(rp.currentPat != null ? String.valueOf(rp.currentPat.getAge()) : AGE);
    sympts.setText(rp.currentPat != null ? rp.listeSymptPat() : "Aucun symptome");
    sympt.setText(SYMPT);
    sever.setText(SEVER);
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

    if (source == suppPat) rp.deletePat(rp.currentPat);

    else if (source == plusSympt) {
      if (!sympt.getText().equalsIgnoreCase(SYMPT) && !sever.getText().equalsIgnoreCase(SEVER)) {
        if (rp.isInt(sever.getText())) {
          if (Integer.valueOf(sever.getText()) <= 5 && Integer.valueOf(sever.getText()) >= 1) {
            Symptome symptPat = new Symptome(sympt.getText(), Integer.valueOf(sever.getText()));
            sympts.setText(rp.addSymptPat(symptPat));
            sympt.setText(SYMPT);
            sever.setText(SEVER);
          } else rp.mp.ip.sendConsole(">> [!] La sévérité doit être une valeur entre 1 et 5 !\n");
        } else rp.mp.ip.sendConsole(">> [!] La sévérité doit être un chifre !\n");
      } else rp.mp.ip.sendConsole(">> [!] Veuillez renseigner le syptome est la sévérité !\n");
    }

    else if (inputOk()) {
      
      if (source == addPat) {
        if (rp.tmpSympts.size() > 0) {
          Patient Pat = new Patient(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()), rp.tmpSympts);
          rp.setCurrentPat(null);
          rp.addPat(Pat);
        } else rp.mp.ip.sendConsole(">> [!] Veuillez ajouter au moins un symptome !\n");
      } 

      if (source == editPat) {
        Occupant occ = new Occupant(nom.getText(), prenom.getText(), Integer.valueOf(age.getText()));
        if (rp.currentPat != null) rp.updateOcc(rp.currentPat, occ);
        else rp.mp.ip.sendConsole(">> [!] Le patient n'existe pas, cliquez sur Ajouter !\n");
      }
    }
  }

  
  @Override
  public void focusGained(FocusEvent e) {
    /* retrait du "placeholder" lors du clic sur un TextField */
    JComponent source = (JComponent) e.getSource();
    
    if (rp.currentPat == null) {
      if (source == nom) nom.setText("");
      if (source == prenom) prenom.setText("");
      if (source == age) age.setText("");
    }
    
    if (source == sympt) sympt.setText("");
    if (source == sever) sever.setText("");
  }

  
  @Override
  public void focusLost(FocusEvent e) {
    /* Action sur les TextField lors de la perte de focus */
    JComponent source = (JComponent) e.getSource();
    
    if (source == nom && nom.getText().isEmpty()) nom.setText(rp.currentPat != null ? rp.currentPat.getNom() : NOM);
    if (source == prenom && prenom.getText().isEmpty()) {prenom.setText(rp.currentPat != null ? rp.currentPat.getPrenom() : PRENOM);}
    if (source == age && age.getText().isEmpty()) age.setText(rp.currentPat != null ? String.valueOf(rp.currentPat.getAge()) : AGE);
    if (source == sympt && sympt.getText().isEmpty()) sympt.setText(SYMPT);
    if (source == sever && sever.getText().isEmpty()) sever.setText(SEVER);
  }
}