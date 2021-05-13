package fr.insa.viannay.projet.s2;


import fr.insa.viannay.projet.s2.tuto.Segment;
import fr.insa.viannay.projet.s2.tuto.Point;
import fr.insa.viannay.projet.s2.tuto.Point;
import fr.insa.viannay.projet.s2.tuto.Segment;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class TriangleTerrain2 {
    private int idTriangle;
    private Point sommet1;
    private Point sommet2;
    private Point sommet3;
    private Segment segment1;
    private Segment segment2;
    private Segment segment3;

    //Constructeur avec points
    public TriangleTerrain2(int idTriangle, Point sommet1, Point sommet2, Point sommet3) {
        this.idTriangle = idTriangle;
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.sommet3 = sommet3;
        this.segment1 = new Segment(sommet1, sommet2);
        this.segment2 = new Segment(sommet2, sommet3);
        this.segment3 = new Segment(sommet3, sommet1);
    }
   
     
     
    //getter et setter
    public int getIdTriangle() {
        return idTriangle;
    }

    public void setIdTriangle(int idTriangle) {
        this.idTriangle = idTriangle;
    }

    public Point getSommet1() {
        return sommet1;
    }

    public void setSommet1(Point sommet1) {
        this.sommet1 = sommet1;
    }

    public Point getSommet2() {
        return sommet2;
    }

    public void setSommet2(Point sommet2) {
        this.sommet2 = sommet2;
    }

    public Point getSommet3() {
        return sommet3;
    }

    public void setSommet3(Point sommet3) {
        this.sommet3 = sommet3;
    }

    public Point getSommet(int indice){
        Point sommet=null;
        switch(indice){
            case 1 : sommet = this.sommet1;
            break;
            case 2 : sommet = this.sommet2;
            break;
            case 3 : 
            case 0 :
                sommet = this.sommet3;
            break;
        }
        return sommet;
    }
    
    
    
    //Affichage

    @Override
    public String toString() {
        return "TriangleTerrain ;" + idTriangle + " ; " + sommet1 + " ; " + sommet2 + " ; " + sommet3 ;
    }
    
    //Essai d'un main
     public static void main(String[]args){
        Point p1=new Point(0,6);
        Point p2=new Point(0,2);
        Point p3=new Point(1,3);
        Point p4=new Point(2,8);
        
        TriangleTerrain2 exemple = new TriangleTerrain2(1,p1,p2,p3);
        System.out.println(exemple.toString());
        exemple.setSommet1(p4);
        System.out.println(exemple.toString());
    }
  
     
}
