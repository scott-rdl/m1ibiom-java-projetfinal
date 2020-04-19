package projetfinal;
import java.util.ArrayList;

/*
 * 
 *  Class Registre
 *  
 *  classe permettant de gérer le registre des patients
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class Registre {

  protected ArrayList<Patient> patients = new ArrayList<Patient>();
  protected ArrayList<Personnel> personnels = new ArrayList<Personnel>();
  protected ArrayList<Occupant> occupants = new ArrayList<Occupant>();
  protected MainPanel mp;


  // --- CONSTRUCTEUR ---
  
  public Registre(MainPanel _mp, Patient _patients, Personnel _personnels, Occupant _occupants) {
    this.mp = _mp;
    this.patients.add(_patients);
    this.personnels.add(_personnels);
    this.occupants.add(_occupants);
  }


  // --- METHDES ---

  public void addOccupant(Occupant occ){
    /* ajoute un occupant à la liste des occupants. */
    occupants.add(occ);
  }

  public void getOccupant() {
    /* retourne la liste de tous les Occupants. */
    mp.ip.sendConsole(">> Liste des occupants :\n");
    for (Occupant occ : occupants) {
      mp.ip.sendConsole(">> " + occ.toString() + "\n");
    }
  }

  public void getPatient() {
    /* retourne la liste des Patients */
    mp.ip.sendConsole(">> Liste des patients :\n");
    for (Patient pat : patients) {
      mp.ip.sendConsole(">> " + pat.toString() + "\n");
    }
  }

  public void getPersonnel() {
    /* retourne la liste du Personnel */
    mp.ip.sendConsole(">> Liste du personnel :\n");
    for (Personnel per : personnels) {
      mp.ip.sendConsole(">> " + per.toString() + "\n");
    }
  }
  
  public void getSickPatients() {
    /* retourne la liste des patients qui sont malades */
    mp.ip.sendConsole(">> Liste des patients malades:\n");
    for (Patient pat : patients) {
      if (pat.isSick()) mp.ip.sendConsole(">> " + pat.toString() + "\n");
    }
  }
}