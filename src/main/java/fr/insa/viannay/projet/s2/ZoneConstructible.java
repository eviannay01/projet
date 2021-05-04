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
public class ZoneConstructible {
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    
    
    
    //Constructeur
    
    public ZoneConstructible(double xmin, double xmax, double ymin, double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }
    
    
    
    //getter et setter
    
    public double getXmin() {
        return xmin;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }
    
    //Affichage

    @Override
    public String toString() {
        return "ZoneConstructible ; " + xmin + " ; " + xmax + " ; " + ymin + " ; " + ymax;
    }
    
    
   //Exemple de main
   /*public static void main(String[]args){
        double p1=1;
        double p2=4;
        double p3=2;
        double p4=6;
       ZoneConstructible exemple = new ZoneConstructible(p1,p2,p3,p4);
        System.out.println(exemple.toString());
    }*/
}
