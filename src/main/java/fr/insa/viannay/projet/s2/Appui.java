/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.viannay.projet.s2;


/**
 *
 * @author viann
 */
public class Appui  extends Noeud {
    protected int idTriangle;
    protected int sommetDebut;
    protected double position;
    
    //Constructeur

    public Appui(int idNoeud, int idTriangle, int sommetDebut, double position) {
        super(idNoeud);
        this.idTriangle = idTriangle;
        this.sommetDebut = sommetDebut;
        this.position = position;
    }
    
    
    //getter et setter

    public int getIdTriangle() {
        return idTriangle;
    }

    public void setIdTriangle(int idTriangle) {
        this.idTriangle = idTriangle;
    }

    public int getSommetDebut() {
        return sommetDebut;
    }

    public void setSommetDebut(int sommetDebut) {
        this.sommetDebut = sommetDebut;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
    
}

