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
public class Noeud {
    
    
    private int idNoeud;
    private Point positionNoeud;
    private double forceX;
    private double forceY;

    //Constructeur

    public Noeud(int idNoeud, Point positionNoeud) {
        this.idNoeud = idNoeud;
        this.positionNoeud = positionNoeud;
        this.forceX=0;
        this.forceY=0;
    }
  
    
    //Getter et setter
    
    public int getIdNoeud() {
        return idNoeud;
    }

    public void setIdNoeud(int idNoeud) {
        this.idNoeud = idNoeud;
    }

    public Point getPositionNoeud() {
        return positionNoeud;
    }

    public void setPositionNoeud(Point positionNoeud) {
        this.positionNoeud = positionNoeud;
    }

    public double getForceX() {
        return forceX;
    }

    public void setForceX(double forceX) {
        this.forceX = forceX;
    }

    public double getForceY() {
        return forceY;
    }

    public void setForceY(double forceY) {
        this.forceY = forceY;
    }
    
    
    //Calcul du cos de l'angle entre l'horizontale et une barre partant du noeud
    //On sait que cos = adjacent/hypothenuse
    //Avec adjacent = X(autre point)-X(ce point)
    //Et hypothenuse => pythagore
    
     public double cosAlpha(Noeud noeud1) {
        double hypothenuse;
        double calpha;
        hypothenuse = Math.sqrt(Math.pow(noeud1.getPositionNoeud().getPx()-this.getPositionNoeud().getPx(),2)+Math.pow(noeud1.getPositionNoeud().getPy()-this.getPositionNoeud().getPy(),2));
        calpha = (noeud1.getPositionNoeud().getPx()-this.getPositionNoeud().getPx())/hypothenuse;
        return calpha;
    }

    //Calcul du sin de l'angle entre l'horizontale et une barre partant du noeud
    //On sait que sin = oppose/hypothenuse
    //Avec oppose = Y(autre point)-Y(ce point)
    //Et hypothenuse => pythagore
    
     public double sinAlpha(Noeud noeud1) {
        double hypothenuse;
        double salpha;
        hypothenuse = Math.sqrt(Math.pow(noeud1.getPositionNoeud().getPx()-this.getPositionNoeud().getPx(),2)+Math.pow(noeud1.getPositionNoeud().getPy()-this.getPositionNoeud().getPy(),2));
        salpha = (noeud1.getPositionNoeud().getPy()-this.getPositionNoeud().getPy())/hypothenuse;
        return salpha;
    }
}
    

