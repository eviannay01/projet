package fr.insa.viannay.projet.s2;


import fr.insa.viannay.projet.s2.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class Segment {
    private Point point1;
    private Point point2;
    private double longueur; //longueur à rajoutter?
    
    //Constructeur
    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.longueur=Math.sqrt(Math.pow((point2.getX()-point1.getX()),2)+Math.pow((point2.getY()-point1.getY()),2));
    }

    
    // Getter et setter
    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    //Affichage
    @Override
    public String toString() {
        return "Segment["  + point1 + "," + point2 + "]"+"longueur = "+ longueur;
    }
  
    //Exemple de main
    /*public static void main (String[]args){
        Point p1=new Point(1,2);
        Point p2=new Point(4,10);
        Segment exemple= new Segment (p1,p2);
        System.out.println(exemple.toString());
               
    }*/    
}
