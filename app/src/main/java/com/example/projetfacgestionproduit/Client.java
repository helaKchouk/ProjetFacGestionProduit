package com.example.projetfacgestionproduit;

public class Client {

    String nom,prenom;
    int id;

    public Client(int id,String nom, String prenom) {
         this.id=id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Client()
    {}

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId()
    {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(int id) {
        this.id = id;
    }
}
