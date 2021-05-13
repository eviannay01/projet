package fr.insa.viannay.projet.s2;


import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class Barre {
    private int idBarre;
    private int debut;
    private int fin;
    private int idType;
    private Color couleur;
    
    //Constructeur

    public Barre(int idBarre, int debut, int fin, int idType) {
        this.idBarre = idBarre;
        this.debut = debut;
        this.fin = fin;
        this.idType = idType;
        this.couleur=Color.blue;
    }
  
    //getter et setter

    public int getIdBarre() {
        return idBarre;
    }

    public void setIdBarre(int idBarre) {
        this.idBarre = idBarre;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    
    
    //Affichage

    @Override
    public String toString() {
        return "Barre ; " + this.getIdBarre() + " ; " + this.getDebut() + " ; " + this.getFin() + " ; " + this.getIdType();
    }
    
    
    
}
