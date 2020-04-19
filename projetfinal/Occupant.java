package projetfinal;

/*
 * 
 *  Class Occupant
 *  
 *  Class mère de Patient et Personnel
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class Occupant {
  
  protected String nom, prenom;
  protected int age;
  
  
  // --- CONSTRUCTEURS ---
  
  public Occupant(String _nom, String _prenom, int _age) {
    this.nom = _nom.toLowerCase();
    this.prenom = _prenom.toLowerCase();
    this.age = _age;
  }
  
  
  // --- METHODE ---
  
  public String toString() {return this.nom.toUpperCase() + " " + this.capitalize(prenom) + ", " + this.age + " ans";}
  
  public String capitalize(String _str) {
    /* Met la première lettre du prénom en majuscule et le reste en minuscule */
    return _str.substring(0,1).toUpperCase() + _str.substring(1).toLowerCase();
  }
  
  /* Méthodes equals : test l'egalité avec un Occupant, Patient, Personnel*/
  public boolean equals(Occupant occ) {
    return (this.nom.equalsIgnoreCase(occ.nom) && this.prenom.equalsIgnoreCase(occ.prenom) && this.age == occ.age);
  }
  
  public boolean equals(Patient pat) {
    return (this.nom.equalsIgnoreCase(pat.nom) && this.prenom.equalsIgnoreCase(pat.prenom) && this.age == pat.age);
  }
  
  public boolean equals(Personnel per) {
    return (this.nom.equalsIgnoreCase(per.nom) && this.prenom.equalsIgnoreCase(per.prenom) && this.age == per.age);
  }
  
  public void copy(Occupant nouveau) {
    /* Copie la classe occupant en argumant. */
    this.nom = nouveau.getNom();
    this.prenom = nouveau.getPrenom();
    this.age = nouveau.getAge();
  }
  
  
  // --- GETTERS AND SETTERS ---
  
  public String getNom() {return nom;}
  public void setNom(String nom) {this.nom = nom;}
  
  public String getPrenom() {return prenom;}
  public void setPrenom(String prenom) {this.prenom = prenom;}

  public int getAge() {return age;}
  public void setAge(int age) {this.age = age;}
}