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
public class NoeudSimple extends Noeud {
    
    protected Point pointNoeud;
    private Color couleur;
    
    
    //Constructeur
    public NoeudSimple(int idNoeud, Point pointNoeud){
        super(idNoeud);
        this.pointNoeud = pointNoeud;
        this.couleur=Color.pink;
    }
    
    //getter et setter

    public Point getPointNoeud() {
        return pointNoeud;
    }

    public void setPointNoeud(Point pointNoeud) {
        this.pointNoeud = pointNoeud;
    }
    
    //Affichage

    @Override
    public String toString() {
        return "NoeudSimple ; " + super.getIdNoeud() + " ; " + pointNoeud ;
    }
    
    //Exemple de main
    /*public static void main(String[]args){
        Noeud n1= new Noeud(78);
        Point p1= new Point (2,4.5);
        NoeudSimple exemple = new NoeudSimple(n1.getIdNoeud(),p1);
        System.out.println(exemple.toString());
    }*/
    
}
