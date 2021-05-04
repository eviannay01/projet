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
public class Terrain {
    private ZoneConstructible zone;
    private List<TriangleTerrain> ensembleTriangle;
    
    //Constructeur

    public Terrain(ZoneConstructible zone) {
        this.zone = zone;
        this.ensembleTriangle = new ArrayList();
        
    }
    
     //Ajoutter un triangle terrain dans au terrain
    public void add(TriangleTerrain nouveautt){
        this.ensembleTriangle.add(nouveautt);
    }

   /* OU  public Terrain(ZoneConstructible zone, List<TriangleTerrain> ensembleTriangle) {
             this.zone = zone;
             this.ensembleTriangle = ensembleTriangle;
          }
   */
    
    //getter et setter

    public ZoneConstructible getZone() {
        return zone;
    }

    public void setZone(ZoneConstructible zone) {
        this.zone = zone;
    }

    public List<TriangleTerrain> getEnsembleTriangle() {
        return ensembleTriangle;
    }

   /* Si 2eme version du constructeur
    public void setEnsembleTriangle(List<TriangleTerrain> ensembleTriangle) {
        this.ensembleTriangle = ensembleTriangle;
    }
   */    
}
