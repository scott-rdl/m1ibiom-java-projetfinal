package projetfinal;

/*
 * 
 *  Class Symptome
 *  
 *  Définit le nom est la sévérité d'un symptome
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class Symptome {
  
  private String symptom;
  private int severity;
  
  
  // --- CONSTRUCTEUR ---
  
  public Symptome(String _symptome, int _severity) {
    this.symptom = _symptome;
    this.severity = _severity;
  }
  
  
  // --- METHODE ---
  
  public String toString() {
    /* Retourne une String d'affichage du symptome */
    return this.symptom + " (" + this.severity + "/5)";
  }

  public int getSever() {
    /* Retourne la sévérité du symptome */
    return severity;
  }
}