package projetfinal;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * 
 *  Class RegistrePanel
 *  
 *  classe permettant de gérer le registre des patients
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class RegistrePanel extends JPanel {

  protected MainPanel mp;
  protected CardLayout cards;
  protected PatientPanel cardPat;
  protected PersonnelPanel cardPer;
  protected OccupantPanel cardOcc;

  protected Patient currentPat = null;
  protected Personnel currentPer = null;
  protected Occupant currentOcc = null;

  protected static ArrayList<Patient> patients = new ArrayList<Patient>();
  protected static ArrayList<Personnel> personnels = new ArrayList<Personnel>();
  protected static ArrayList<Occupant> occupants = new ArrayList<Occupant>();
  protected static ArrayList<Symptome> tmpSympts = new ArrayList<Symptome>();
  protected static ArrayList<String> tmpRoles = new ArrayList<String>();


  // --- CONSTRUCTEUR ---
  public RegistrePanel(MainPanel _mp) {

    this.mp = _mp;

    // Setting cardLayout
    cards = new CardLayout();
    setLayout(cards);

    //  - Card : PATIENT -
    cardPat = new PatientPanel(this);
    add(cardPat, "cardPat");

    //  - Card : PERSONNEL -
    cardPer = new PersonnelPanel(this);
    add(cardPer, "cardPer");

    //  - Card : TOUS -
    cardOcc = new OccupantPanel(this);
    add(cardOcc, "cardOcc");  
  }


  // --- METHODES ---
  
  public boolean isInt(String chaine) {
    /* Retourne true si la chaine est convertible en int siono false */
    try {Integer.parseInt(chaine);
    } catch (NumberFormatException e){return false;}
    return true;
  }
  
  public void updatePanel() {
    /* Reset des cards */
    setCurrentPat(null);
    setCurrentPer(null);
    setCurrentOcc(null);
    cardPat.updatePanel();
    cardPer.updatePanel();
    cardOcc.updatePanel();
  }
  

  // --- METHODES : PATIENT ---
  
  protected String addSymptPat(Symptome symptPat) {
    /* Ajoute un symptome à la liste tmpSympts ou au currentPat si existant. */
    String var = "";
    if (currentPat == null) {
      tmpSympts.add(symptPat);
      for (Symptome s : tmpSympts) var += s.toString()+"\n";
    } else {
      currentPat.addSymptom(symptPat);
      var = currentPat.symptsLst();
    }
    return var;
  }
  
  protected String listeSymptPat() {
    /* Retourne la liste des symptomes avec retour chariot */
    String var = "";
    if (currentPat == null) for (Symptome s : tmpSympts) var += s.toString()+"\n";
    else var = currentPat.symptsLst();
    return var;
  }
  
  protected void addPat(Patient _pat) {
    /* Ajoute un patient à la liste */
    boolean flag = false;
    for (Patient p : patients) {
      if (_pat.equals(p)) {
        flag = true;
        mp.ip.sendConsole(">> [!] Le patient existe déjà !\n");
      }
    }
    if (!flag) {
      patients.add(_pat);
      setCurrentPat(_pat);
      tmpSympts.clear();
      mp.update();
      updatePanel();
      mp.ip.sendConsole(">> Patient créé : " + _pat.toString() + "\n");
    }
  } 

  protected void deletePat(Patient _pat) {
    /* Supprime un patient (si existant) */
    for (Patient _p : patients) {
      if (_pat == null) {
        mp.ip.sendConsole(">> [!] Impossible de supprimer un patient inexistant !\n");
        break;
      }
      if (_pat.equals(_p)) {
        patients.remove(_pat);
        setCurrentPat(null);
        mp.ip.sendConsole(">> Patient supprimé : " +  _pat.toString()+ ".\n");
        mp.update();
        break;
      }
    }
  }


  // --- METHODES : PERSONNEL ---
  
  protected String addRolePer(String rolePer) {
    /* Ajoute un rôle à tmpRoles ou au currentPer (si existant) et retourne la liste */
    String var = "";
    if (currentPer == null) {
      tmpRoles.add(rolePer);
      for (String r : tmpRoles) var += r+"\n";
    } else {
      currentPer.addRole(rolePer);
      var = currentPer.rolesLst();
    }
    return var;
  }
  
  protected String listeRolesPer() {
    /* Retourne la liste des roles */
    String var = "";
    if (currentPer == null) for (String r : tmpRoles) var += r+"\n";
    else var = currentPer.rolesLst();
    return var;
  }
  
  protected void addPer(Personnel _per) {
    /* Ajoute un Personnel à la liste */
    boolean flag = false;
    for (Personnel p : personnels) {
      if (_per.equals(p)) {
        flag = true;
        mp.ip.sendConsole(">> [!] Le praticien existe déjà !\n");
      }
    }
    if (!flag) {
      personnels.add(_per);
      setCurrentPer(_per);
      tmpRoles.clear();
      mp.update();
      updatePanel();
      mp.ip.sendConsole(">> Personnel créé : " + _per.toString() + "\n");
    }
  } 

  protected void deletePer(Personnel _per) {
    /* Supprime un Personnel de la liste*/
    for (Personnel _p : personnels) {
      if (_per == null) {
        mp.ip.sendConsole(">> [!] Impossible de supprimer un patient inexistant !\n");
        break;
      }
      if (_per.equals(_p)) {
        personnels.remove(_per);
        setCurrentPer(null);
        mp.ip.sendConsole(">> Personnel supprimé : " +  _per.toString()+ ".\n");
        mp.update();
        break;
      }
    }
  }

  
  // --- METHODES : OCCUPANT ---
  
  protected void addOcc(Occupant _occ) {
    /* Ajoute un occupant */
    boolean flag = false;
    for (Occupant occ : occupants) {
      if (_occ.equals(occ)) {
        flag = true;
        mp.ip.sendConsole(">> [!] L'occupant existe déjà !\n");
      }
    }
    if (!flag) {
      occupants.add(_occ);
      setCurrentOcc(_occ);
      mp.update();
      mp.ip.sendConsole(">> Occupant créé : " + _occ.toString() + "\n");
    }
  }

  
  protected void deleteOcc(Occupant _occ) {
    
    boolean flag = false;
    
    for (Occupant occ : occupants) {
      if (_occ == null) {
        mp.ip.sendConsole(">> [!] Impossible de supprimer un occupant inexistant !\n");
        break;
      }
      if (_occ.equals(occ)) {
        occupants.remove(_occ);
        setCurrentOcc(null);
        mp.ip.sendConsole(">> Occupant supprimé : " + _occ.toString() + ".\n");
        mp.update();
        flag = true;
        break;
      }
    }
    
    if (!flag && patients.size() > 0) {
      for (Patient pat : patients) {
        if (pat.equals(_occ)) {
          patients.remove(pat);
          setCurrentPat(null);
          mp.ip.sendConsole(">> Patient supprimé : " + pat.toString() + ".\n");
          mp.update();
          flag = true;
          break;
        }
      }
    }
    
    if (!flag && personnels.size() > 0) {
      for (Personnel per : personnels) {
        if (per.equals(_occ)) {
          personnels.remove(per);
          setCurrentPer(null);
          mp.ip.sendConsole(">> Personnel supprimé : " + per.toString() + ".\n");
          mp.update();
          flag = true;
          break;
        }
      }
    }
    
    if (!flag) mp.ip.sendConsole(">> Occupant introuvable\n");
  }

  protected void updateOcc(Occupant old, Occupant updated) {
    if (isInListOcc(old) || isInListPat(old) || isInListPer(old)) mp.ip.sendConsole(">> Mise à jour : " +  old.toString() + " --> " +  updated.toString() + ".\n");
    if (isInListOcc(old)) occupants.get(occupants.indexOf(old)).copy(updated);
    else if (isInListPat(old)) patients.get(patients.indexOf(old)).copy(updated);
    else if (isInListPer(old)) personnels.get(personnels.indexOf(old)).copy(updated);
    mp.update();
  }

  public boolean isInListOcc(Occupant _occ) {
    /* Vérification de la présence d'un Occupant dans la liste */
    for (Occupant occ : occupants) if (_occ.equals(occ)) return true;
    return false;
  }
  
  public boolean isInListPat(Occupant _occ) {
    for (Patient pat : patients) if (pat.equals(_occ)) return true;
    return false;
  }
  
  public boolean isInListPer(Occupant _occ) {
    for (Personnel per : personnels) if (per.equals(_occ)) return true;
    return false;
  }

  
  // --- SETTERS ---

  public void setCurrentPat(Patient pat) {this.currentPat = pat;}
  public void setCurrentPer(Personnel per) {this.currentPer = per;}
  public void setCurrentOcc(Occupant occ) {this.currentOcc = occ;}
}