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
public class Appui extends Noeud {
    private int idTriangle;
    private int sommetDebut;
    private double positionSegment;
    private boolean appuiSimple;
    private double cosBeta;
    private double sinBeta;
    
    //Constructeur

    public Appui(int idNoeud, Point positionNoeud, int idTriangle, int sommetDebut, double positionSegment,boolean appuiSimple) {
        super(idNoeud,positionNoeud);
        this.idTriangle = idTriangle;
        this.sommetDebut = sommetDebut;
        this.positionSegment = positionSegment;
        this.appuiSimple=appuiSimple;
        this.cosBeta = 2;
        this.sinBeta = 2;
        
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

    public double getPositionSegment() {
        return positionSegment;
    }

    public void setPositionSegment(double positionSegment) {
        this.positionSegment = positionSegment;
    }

    
    //Pour savoir si c'est un appui simple(true) ou double(false)
    
    public boolean isAppuiSimple() {
        return appuiSimple;
    }

    public void setAppuiSimple(boolean appuiSimple) {
        this.appuiSimple = appuiSimple;
    }
    
    
    //Pour les appuis simples, cos et sin Beta
    
    public double getCosBeta() {
        return cosBeta;
    }

    public void setCosBeta(double cosBeta) {
        this.cosBeta = cosBeta;
    }

    public double getSinBeta() {
        return sinBeta;
    }

    public void setSinBeta(double sinBeta) {
        this.sinBeta = sinBeta;
    }
    
    
    
    
    //Affichage

    @Override
    public String toString() {
        if(this.isAppuiSimple()==true){
            return "Appui Simple ; " + super.getIdNoeud() +" ; "+  this.idTriangle +" ; "+ this.sommetDebut +" ; "+ this.positionSegment;
        }
        else{
            return "Appui Double ; " + super.getIdNoeud() +" ; "+  this.idTriangle +" ; "+ this.sommetDebut +" ; "+ this.positionSegment;
        }
    }
    
    
}


