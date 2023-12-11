package com.example.projetfacgestionproduit;
//model
//nous avons généré notre classe qui représente notre model
public class Produit {
    int id;
    String nom;



    public Produit(int id, String nom) {
        this.id = id;
        this.nom = nom;

    }

    public Produit(String nom) {
        this.nom = nom;

    }

    public Produit() {
    }
    //cela m'aider au niveau du code de mes activités
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}


