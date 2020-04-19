package projetfinal;
import java.util.ArrayList;

/*
 * 
 *  Class Personnel
 *  
 *  HÃ©rite de la classe Occupant
 *  Auteur  : Scott RIDEL
 *  Version : 18/04/2020
 * 
 */

public class Personnel extends Occupant{
  
  ArrayList <String> roles = new ArrayList<String> ();

  
  // --- CONSTRUCTEUR ---
  
  public Personnel(String _nom, String _prenom, int _age, ArrayList<String> _roles) {
    super(_nom, _prenom, _age);
    this.roles.addAll(_roles);
  }

  
  // --- METHODES --
  
  public void addRole(String _role) {this.roles.add(_role);}
  
  public String rolesLst() {
    /* Retourne la liste des roles */
    String str = "";
    for (String r : roles)  str += r+"\n";
    return str;
  }
  
  public String toString() {
    /* Retourne une String avec les détails du personnel*/
    String str = "";
    for (String r : roles) str += r+" ";
      return nom.toUpperCase()+" "+this.capitalize(prenom)+", "+this.age+" ans ("+str+").";
    }
}