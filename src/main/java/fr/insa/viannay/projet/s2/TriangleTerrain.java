package fr.insa.viannay.projet.s2;


import fr.insa.viannay.projet.s2.Segment;
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
public class TriangleTerrain {
    private int idTriangle;
    private Point[] sommetsTriangle;
    private Segment[] segmentsTriangle;

    //Constructeur avec les points
    public TriangleTerrain(int idTriangle, Point PT1, Point PT2, Point PT3) {
        this.idTriangle = idTriangle;
        this.sommetsTriangle = new Point[3];
        this.sommetsTriangle[0]= PT1;
        this.sommetsTriangle[1]= PT2;
        this.sommetsTriangle[2]= PT3;
        this.segmentsTriangle = new Segment[3];
        this.segmentsTriangle[0] = new Segment(PT1,PT2);
        this.segmentsTriangle[1] = new Segment(PT2,PT3);
        this.segmentsTriangle[2] = new Segment(PT3,PT1);
    }
    
    /*Constructeur avec les segments
     public TriangleTerrain(Segment segment1, Segment segment2, Segment segment3) {
        this.segmentsTriangle = new Segment[3];
        this.segmentsTriangle[0] = segment1;
        this.segmentsTriangle[1] = segment2;
        this.segmentsTriangle[2] = segment3;
     }
    */
     
    //getter et setter
    public int getIdTriangle() {
        return idTriangle;
    }

    public void setIdTriangle(int idTriangle) {
        this.idTriangle = idTriangle;
    }

    public Point[] getSommetsTriangle() {
        return sommetsTriangle;
    }

    public void setSommetsTriangle(Point[] pointsTriangle) {
        this.sommetsTriangle = pointsTriangle;
    }

    //Affichage
    @Override
    public String toString() {
        return "Triangle ; " + idTriangle + ";" +this.sommetsTriangle[0] + ";"+this.sommetsTriangle[1]+";"+this.sommetsTriangle[2];
    }

    //Essai d'un main 
    /*public static void main(String[]args){
        Point p1=new Point(0,6);
        Point p2=new Point(0,2);
        Point p3=new Point(1,3);
        Point p4=new Point(2,8);
        Point p5=new Point(3,2);
        Point p6=new Point(7,4);
        
        TriangleTerrain exemple = new TriangleTerrain(1,p1,p2,p3);
        System.out.println(exemple.toString());
    }*/
}
