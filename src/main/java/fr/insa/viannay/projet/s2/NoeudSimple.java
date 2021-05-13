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
    
    private Color couleur;
    
    
    //Constructeur
    public NoeudSimple(int idNoeud, Point positionNoeud){
        super(idNoeud,positionNoeud);
        this.couleur=Color.pink;
    }
    
    //Affichage

    @Override
    public String toString() {
        return "NoeudSimple ; " + super.getIdNoeud() + " ; " + super.getPositionNoeud().toString() ;
    }
    
    //Exemple de main
    public static void main(String[]args){
        Point p1= new Point (2,4.5);
        Noeud n1= new Noeud(78,p1);
        NoeudSimple exemple = new NoeudSimple(n1.getIdNoeud(),n1.getPositionNoeud());
        System.out.println(exemple.toString());
    }
    
    
}
