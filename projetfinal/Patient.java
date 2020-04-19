package projetfinal;
import java.util.ArrayList;

/*
 * 
 *  Class Patient
 *  
 *  Hérite de la classe Occupant
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class Patient extends Occupant {

  ArrayList<Symptome> symptoms = new ArrayList<Symptome> ();


  // --- CONSTRUCTEUR ---
  
  public Patient(String _nom, String _prenom, int _age, ArrayList<Symptome> symptsLst) {
    super(_nom, _prenom, _age);
    this.symptoms.addAll(symptsLst);
  }


  // --- METHODES --
  
  public void addSymptom(Symptome _syptome) {this.symptoms.add(_syptome);}

  public boolean isSick() {
    /* retoure true si le patient à une sévérité moyenne des symptomes >= 3, sinon false */
    if (getAverageGravity() >= 3) return true; 
    else return false;
  }
  
  public int getAverageGravity() {
    /* retourne la moyenne  */
    int sum = 0;
    for (Symptome s : symptoms) sum += s.getSever();
    return sum / symptoms.size();
  }
  
  public String symptsLst() {
    /* Retourne la liste des symptomes */
    String str = "";
    for (Symptome s : symptoms)  str += s.toString() + "\n";
    return str;
  }

  public String toString() {
    /* Retourne une String avec les détails du patient */
    String etat;
    etat = (isSick() ? "malade" : "sain");
    return nom.toUpperCase()+" "+this.capitalize(prenom)+", "+this.age+" ans ("+etat+").";
  }
}