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

    //Constructeur

    public Noeud(int idNoeud, Point positionNoeud) {
        this.idNoeud = idNoeud;
        this.positionNoeud = positionNoeud;
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

    
    
    
    //Calcul du cos de l'angle entre l'horizontale et une barre partant du noeud
    //On sait que cos = adjacent/hypothenuse
    //Et adjacent = X(autre point)-X(ce point)
    //Et hypothenuse => pythagore
    
     public double cosAlpha(Noeud noeud1) {
        double hypothenuse;
        double calpha;
        hypothenuse = Math.sqrt(Math.pow(noeud1.getPositionNoeud().getX()-this.getPositionNoeud().getX(),2)+Math.pow(noeud1.getPositionNoeud().getY()-this.getPositionNoeud().getY(),2));
        calpha = (noeud1.getPositionNoeud().getX()-this.getPositionNoeud().getX())/hypothenuse;
        return calpha;
    }

    //Calcul du sin de l'angle entre l'horizontale et une barre partant du noeud
    //On sait que sin = oppose/hypothenuse
    //Et oppose = Y(autre point)-Y(ce point)
    //Et hypothenuse => pythagore
    
     public double sinAlpha(Noeud noeud1) {
        double hypothenuse;
        double salpha;
        hypothenuse = Math.sqrt(Math.pow(noeud1.getPositionNoeud().getX()-this.getPositionNoeud().getX(),2)+Math.pow(noeud1.getPositionNoeud().getY()-this.getPositionNoeud().getY(),2));
        salpha = (noeud1.getPositionNoeud().getY()-this.getPositionNoeud().getY())/hypothenuse;
        return salpha;
    }

     
    
    
}

