/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package fr.insa.viannay.projet.s2;

import java.util.HashMap;
import java.util.Map;
*/
/**
 *
 * @author viann
 */
/*public class Treillis {
   private ZoneConstructible zone;
   private Map<Integer, TriangleTerrain2> triangleterrain;
   private Map<Integer, Barre> barres;
   private Map<Integer, Noeud> noeuds;
   private Map<Integer,TypeBarre> catalogue;
   
   
   //Constructeur
    public Treillis(ZoneConstructible zone) {  
        this.zone = zone;
        this.triangleterrain = new HashMap<>();
        this.barres = new HashMap<>();
        this.noeuds = new HashMap<>();
        this.catalogue = new HashMap<>() ;
    }

    //getter et setter
    public ZoneConstructible getZone() {
        return zone;
    }

    public void setZone(ZoneConstructible zone) {
        this.zone = zone;
    }

    public Map<Integer, TriangleTerrain2> getTriangleterrain() {
        return triangleterrain;
    }

    public Map<Integer, Barre> getBarres() {
        return barres;
    }

    public Map<Integer, Noeud> getNoeuds() {
        return noeuds;
    }

    public Map<Integer, TypeBarre> getCatalogue() {
        return catalogue;
    }
   
    
    
    
    
    //Ajouter ou retirer un triangle terrain
    public void addTriangle(TriangleTerrain2 nouveauT){
        if(this.triangleterrain.containsKey(nouveauT.getIdTriangle())){
            throw new Error("Un autre triangle a le meme identificateur");
        }
        //On verifie que le triangle est dans la zone constructible
        for(int i=1; i<4; i++){
            if ((nouveauT.getSommet(i).getPx() < this.zone.getXmin())||(nouveauT.getSommet(i).getPx()>this.zone.getXmax())||(nouveauT.getSommet(i).getPy() < this.zone.getYmin())||(nouveauT.getSommet(i).getPy() > this.zone.getYmax())) {
                throw new Error("Un des sommets du triangle terrain n'est pas dans la zone constructible");
            }
        } 
        this.triangleterrain.put(nouveauT.getIdTriangle(), nouveauT);
    }
   
    
    public void removeTriangle(int IdARetirer){
        if(this.triangleterrain!=null){
            //On verifie qu'il n'y ait pas d'appui sur ce triangle
            if(this.noeuds!=null){
                for(Noeud noeudtreillis: this.noeuds.values()) {
                    if (noeudtreillis instanceof Appui) {
                        Appui appuitreillis = (Appui) noeudtreillis;
                        if (appuitreillis.getIdTriangle() == IdARetirer) {
                            throw new Error("On ne peut pas supprimer ce triangle car il y a un appui dessus");
                        }
                    }
                }
            }
        this.triangleterrain.remove(IdARetirer);    
        }
    }
    
    
    
    //Ajouter ou retirer des types de Barre dans le catalogue
    public void addCatalogue(TypeBarre nouveautype){
        if(this.catalogue.containsKey(nouveautype.getIdType())){
            throw new Error("Un autre type a le meme identificateur");
        }
        this.catalogue.put(nouveautype.getIdType(), nouveautype);
    }
    
    public void removeCatalogue(int IdARetirer){
        if (this.catalogue != null) {
            // On verifie qu'il n'y ait pas de barre de ce type dans le treillis
                if (this.barres !=null) {
                    for (Barre barretreillis : this.barres.values()) {
                            if (barretreillis.getIdType() == IdARetirer) {
                                throw new Error("Il y a au moins une barre du type vous essayez de supprimer");
                            }
                    }
                }
        this.catalogue.remove(IdARetirer);
        }
    }
    
           
    
    //Ajoutter ou retirer un noeud  
    public void addNoeud(Noeud nouveauN){
  
        if (this.noeuds.containsKey(nouveauN.getIdNoeud())) {
            throw new Error("Un autre noeud a le meme identificateur");
        }       
        
        if (nouveauN instanceof Appui) {
            if (this.triangleterrain == null) {
                throw new Error("Il faut ajouter un triangle terrain avant d'ajouter un appui");
            }
           
            Appui nouveauA = (Appui) nouveauN;
            // On calcule la position de l'appui dans le plan => utile???
            TriangleTerrain2 triangleappui = this.triangleterrain.get(nouveauA.getIdTriangle());
            Point origine = triangleappui.getSommet(nouveauA.getSommetDebut());
            Point extremite = triangleappui.getSommet((nouveauA.getSommetDebut()+1)%3);
            Point positionA = new Point(origine.getX() + (extremite.getX()-origine.getX()) * nouveauA.getPositionSegment(), origine.getY() + (extremite.getY() - origine.getY()) * nouveauA.getPositionSegment());
            
            nouveauA.setPositionNoeud(positionA);
            
            // Pour un appui simple on calcule l'angle de la normale au terrain avec l'horizontale
            if (nouveauA.isAppuiSimple()==true) {
                double hypothenuse = Math.sqrt(Math.pow(origine.getX()-extremite.getX(),2)+Math.pow(origine.getY()-extremite.getY(),2));
                nouveauA.setCosBeta((origine.getX()-extremite.getX())/hypothenuse);
                nouveauA.setSinBeta((extremite.getY()-origine.getY())/hypothenuse);
            }
        }

        // On verifie que le point est bien dans la zone constructible
        Point positionN = nouveauN.getPositionNoeud();
        if ((positionN.getX() < this.zone.getXmin())||(positionN.getX()>this.zone.getXmax())||(positionN.getY() < this.zone.getYmin())||(positionN.getY() > this.zone.getYmax())) {
            throw new Error("Le noeud n'est pas dans la zone constructible");
        } 

        this.noeuds.put(nouveauN.getIdNoeud(), nouveauN);
    }
    
    
    public void removeNoeud(int IdARetirer) {
        if (this.noeuds != null){
            Noeud noeudARetirer = this.noeuds.get(IdARetirer);
                // On verifie qu'il n'y a pas de barre avec ce noeud
                if (this.barres !=null) {
                    for (Barre barretreillis : this.barres.values()) {
                        if ((barretreillis.getDebut() == IdARetirer) || (barretreillis.getFin() == IdARetirer)) {
                            throw new Error("On ne peut pas supprimer ce noeud car une barre y est attachée");
                        }
                    }
                }
            this.noeuds.remove(IdARetirer);
        }

    }

    
    
    //ajoutter ou supprimer une barre
    public void addBarre(Barre nouvelleB){
        
        if (this.barres.containsKey(nouvelleB.getIdBarre())) {
            throw new Error("Une autre barre a le même identificateur");
        }
        
        if ((this.catalogue == null)||(!this.catalogue.containsKey(nouvelleB.getIdType()))) {
            throw new Error("Le type de cette barre n'est pas dans le catalogue du treillis");
        }
        
        if (this.noeuds == null) {
            throw new Error("Il faut ajouter des noeuds avant d'ajouter une barre");
        }
        if (!this.noeuds.containsKey(nouvelleB.getDebut())||(!this.noeuds.containsKey(nouvelleB.getFin()))) {
                throw new Error("Un des noeuds de la barre n'existe pas");
        }
       
        for (Barre barretreillis : this.barres.values()) {
            if (nouvelleB.equals(barretreillis)) {
                throw new  Error("Il y a deja une barre qui relie les deux memes noeuds");
            }
        }
        
        this.barres.put(nouvelleB.getIdBarre(), nouvelleB);
    }
}*/
