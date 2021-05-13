package fr.insa.viannay.projet.s2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class TypeBarre {
    private int idType;
    private double cout;
    private double lmin;
    private double lmax;
    private double maxTension;
    private double maxCompression;
    
    
    //Constructeur

    public TypeBarre(int idType, double cout, double lmin, double lmax, double maxTension, double maxCompression) {
        this.idType = idType;
        this.cout = cout;
        this.lmin = lmin;
        this.lmax = lmax;
        this.maxTension = maxTension;
        this.maxCompression = maxCompression;
    }
    
    //getter et setter

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idBarre) {
        this.idType = idBarre;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public double getLmin() {
        return lmin;
    }

    public void setLmin(double lmin) {
        this.lmin = lmin;
    }

    public double getLmax() {
        return lmax;
    }

    public void setLmax(double lmax) {
        this.lmax = lmax;
    }

    public double getMaxTension() {
        return maxTension;
    }

    public void setMaxTension(double maxTension) {
        this.maxTension = maxTension;
    }

    public double getMaxCompression() {
        return maxCompression;
    }

    public void setMaxCompression(double maxCompression) {
        this.maxCompression = maxCompression;
    }
    
    
    
    //Affichage

    @Override
    public String toString() {
        return "TypeBarre ; " + idType + " ; " + cout + " ; " + lmin + " ; " + lmax + " ; " + maxTension + " ; " + maxCompression;
    }
    
            
}
