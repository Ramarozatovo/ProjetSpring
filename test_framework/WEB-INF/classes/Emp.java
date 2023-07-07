package emp;


import ETU1850.framework.AnnotedClass;

@AnnotedClass(methodName = "emp-class")
public class Emp {
    int numero;
    String nom;
    String prenom;
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}