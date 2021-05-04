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
    private Noeud debut;
    private Noeud fin;
    private TypeBarre type;
    private Color couleur;
    
    //Constructeur

    public Barre(int idBarre, Noeud debut, Noeud fin, TypeBarre type) {
        this.idBarre = idBarre;
        this.debut = debut;
        this.fin = fin;
        this.type = type;
        this.couleur=Color.blue;
    }
  
    //getter et setter

    public int getIdBarre() {
        return idBarre;
    }

    public void setIdBarre(int idBarre) {
        this.idBarre = idBarre;
    }

    public Noeud getDebut() {
        return debut;
    }

    public void setDebut(Noeud debut) {
        this.debut = debut;
    }

    public Noeud getFin() {
        return fin;
    }

    public void setFin(Noeud fin) {
        this.fin = fin;
    }

    public TypeBarre getType() {
        return type;
    }

    public void setType(TypeBarre type) {
        this.type = type;
    }
    
    
    //Affichage

    @Override
    public String toString() {
        return "Barre ; " + idBarre + " ; " + this.debut.getIdNoeud() + " ; " + this.fin.getIdNoeud() + " ; " + this.type.getIdType();
    }
}
