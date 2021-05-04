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
public class AppuiSimple extends Appui{
    
    private Color couleur;
    
    //Constructeur
    public AppuiSimple(int idNoeud, int idTriangle, int sommetDebut, double position) {
        super(idNoeud, idTriangle, sommetDebut, position);
        this.couleur=Color.green;
    }
    
    //Affichage

    @Override
    public String toString() {
        return "AppuiSimple ; " + super.idNoeud +" ; "+ super.idTriangle +" ; "+ super.sommetDebut +" ; "+ super.position;
    }
    
    //Essai d'un main
    /*public static void main(String[]args){
        Appui appui1= new Appui(8,2,1,4.3);
        AppuiSimple exemple = new AppuiSimple(appui1.getIdNoeud(),appui1.getIdTriangle(), appui1.getSommetDebut(),appui1.getPosition());
        System.out.println(exemple.toString());
    }
    */
    
}
