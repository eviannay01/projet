package fr.insa.viannay.projet.s2;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class CatalogueBarre {
   private List<TypeBarre> catalogue;

    //Constructeur
    public CatalogueBarre() {
        this.catalogue = new ArrayList() ;
        
    }
    
    //Ajoutter un type dans le catalogue
    public void add(TypeBarre nouveautype){
        this.catalogue.add(nouveautype);
    }
    
    //getter et setter

    public List<TypeBarre> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<TypeBarre> catalogue) {
        this.catalogue = catalogue;
    }
    
    
    //Affichage

    @Override
    public String toString() {
        String res = "Catalogue {\n";
        for (int i = 0; i < this.catalogue.size(); i++) {
            res = res + this.catalogue.get(i).toString() + "\n";
        }
        return res + " }";
    }
    
      //Essai d'un main
     /*public static void main(String[]args){
        TypeBarre tb1 = new TypeBarre(1,25,0.5,5,180,99);
        TypeBarre tb2 = new TypeBarre(2,25,1,20,180,54);
        TypeBarre tb3 = new TypeBarre(3,75,1,30,200,54);
        TypeBarre tb4 = new TypeBarre(4,100,0.5,5,250,120);
        
        CatalogueBarre exemple = new CatalogueBarre();
        exemple.add(tb1);
        exemple.add(tb2);
        exemple.add(tb3);
        
        System.out.println(exemple.toString());
        exemple.add(tb4);
        System.out.println(exemple.toString());
    } */
}
