/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.viannay.projet.s2;

import java.util.HashMap;
import java.util.Map;
import recup.Lire;

/**
 *
 * @author pop
 */
public class Treillis {
   private ZoneConstructible zone;
   private Map<Integer, TriangleTerrain> triangleterrain;
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

    public Map<Integer, TriangleTerrain> getTriangleterrain() {
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
   
    
    
    
    //METHODES POUR AJOUTER OU SUPPRIMER DES ELEMENTS DU TREILLIS
    
    //Ajouter ou retirer un triangle terrain
    public void addTriangle(TriangleTerrain nouveauT){
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
                                throw new Error("On ne peut pas supprimer ce type du catalogue car il y a une barre de ce type dans le treillis");
                            }
                    }
                }
        this.catalogue.remove(IdARetirer);
        }
    }
    
           
    
    //Ajouter ou retirer un noeud  
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
            TriangleTerrain triangleappui = this.triangleterrain.get(nouveauA.getIdTriangle());
            Point origine = triangleappui.getSommet(nouveauA.getSommetDebut());
            Point extremite = triangleappui.getSommet((nouveauA.getSommetDebut()+1)%3);
          
            // Pour un appui simple on calcule l'angle de la normale au terrain avec l'horizontale
            if (nouveauA.isAppuiSimple()==true) {
                double hypothenuse = Math.sqrt(Math.pow(origine.getPx()-extremite.getPx(),2)+Math.pow(origine.getPy()-extremite.getPy(),2));
                nouveauA.setCosBeta((origine.getPx()-extremite.getPx())/hypothenuse);
                nouveauA.setSinBeta((extremite.getPy()-origine.getPy())/hypothenuse);
            }
        }

        // On verifie que le point est bien dans la zone constructible
        Point positionN = nouveauN.getPositionNoeud();
        if ((positionN.getPx() < this.zone.getXmin())||(positionN.getPx()>this.zone.getXmax())||(positionN.getPy() < this.zone.getYmin())||(positionN.getPy() > this.zone.getYmax())) {
            throw new Error("Le noeud n'est pas dans la zone constructible");
        } 
        //On verifie qu'il n'y ait pas deja un noeud a cette endroit
        for (Noeud noeudtreillis : this.noeuds.values()) {
            if (nouveauN.equals(noeudtreillis)) {
                throw new Error("Il y a deja un noeud a cet endroit");
            }
        }

        this.noeuds.put(nouveauN.getIdNoeud(), nouveauN);
    }
    
    
    public void removeNoeud(int IdARetirer) {
        if (this.noeuds != null){
              // On verifie qu'il n'y ait pas de barre attachee a ce noeud
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

       
    //ajouter ou supprimer une barre
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
            throw new Error("Un des noeuds de la barre n'existe pas dans le treillis");
        }
       
        for (Barre barretreillis : this.barres.values()) {
            if (nouvelleB.equals(barretreillis)) {
                throw new  Error("Il y a deja une barre qui relie les deux memes noeuds");
            }
        }
        
        this.barres.put(nouvelleB.getIdBarre(), nouvelleB);
    }
    
   
    public void removeBarre(int IdARetirer){
        if (this.barres != null) {
            this.barres.remove(IdARetirer);
        }
    }
    
    
    
    //METHODES POUR CHOISIR LES CARACTERISTIQUES DES ELEMENTS QU'ON VEUT AJOUTER AU TREILLIS
    
    //Choisir le triangle qu'on veut ajouter
    public TriangleTerrain chooseTriangle(){
        int idTriangle;
        double x1,y1,x2,y2,x3,y3;
       
        System.out.println("Donner l'identificateur du triangle");
        idTriangle = Lire.i();
        
        System.out.println("Donner l'abscisse puis l'ordonnee du sommet 1 du triangle");
        x1=Lire.d();
        y1=Lire.d();
        Point sommet1 = new Point(x1,y1);
        
        System.out.println("Donner l'abscisse puis l'ordonnee du sommet 2 du triangle");
        x2=Lire.d();
        y2=Lire.d();
        Point sommet2 = new Point(x2,y2);
        
        System.out.println("Donner l'abscisse puis l'ordonnee du sommet 3 du triangle");
        x3=Lire.d();
        y3=Lire.d();
        Point sommet3 = new Point(x3,y3);
       
        TriangleTerrain nouveauT = new TriangleTerrain (idTriangle,sommet1,sommet2,sommet3);
        return nouveauT;
    }
    
    
    //Choisir le noeud qu'on veut ajouter
    public Noeud chooseNoeudSimple() {
        int idNoeud;
        double x,y;
        System.out.println("Donner l'identificateur du noeud");
        idNoeud = Lire.i();
        System.out.println("Donner l'abscisse puis l'ordonnee du noeud");
        x=Lire.d();
        y=Lire.d();
        Point positionNoeud=new Point(x,y);
        Noeud nouveauN=new Noeud(idNoeud,positionNoeud);
        return nouveauN;
    }
    
    
    //Choisir l'appui simple qu'on veut ajouter
    public Appui chooseAppuiSimple() {
        int idNoeud,idTriangle;
        int n=0;
        double positionSegment=-1;
        Appui nouveauA;
        
        System.out.println("Donner l'identificateur de l'appui");
        idNoeud = Lire.i();
        
        System.out.println("Choisir le triangle sur lequel sera placé l'appui");
        idTriangle=Lire.i();
        TriangleTerrain triangleChoisi= this.triangleterrain.get(idTriangle);
        
        System.out.println("Choisir le segment sur lequel l'appui sera placé (1, 2 ou 3))");
        while((n!=1)||(n!=2)||(n!=3)){
            n=Lire.i();
        }
        
        System.out.println("Chosir la position de l'appui sur le segment(en %)");
        while((positionSegment<0)||(positionSegment>100)){
            positionSegment=Lire.d();
        }
        positionSegment=positionSegment/100;
        
        Point origine = triangleChoisi.getSommet(n);
        Point extremite = triangleChoisi.getSommet((n+1)%3);
        Point positionNoeud = new Point(origine.getPx() + (extremite.getPx()-origine.getPx()) * positionSegment, origine.getPy() + (extremite.getPy() - origine.getPy()) * positionSegment);
        
        nouveauA= new Appui(idNoeud,positionNoeud,idTriangle,n,positionSegment,true);
        return nouveauA;
        //ajoutter le calcul de cos et sin beta ici???
    }
    
    
    //Choisir l'appui double qu'on veut ajouter
    public Appui chooseAppuiDouble() {
        int idNoeud,idTriangle;
        int n=0;
        double positionSegment=-1;
        Appui nouveauA;
        
        System.out.println("Donner l'identificateur de l'appui");
        idNoeud = Lire.i();
        
        System.out.println("Choisir le triangle sur lequel sera placé l'appui");
        idTriangle=Lire.i();
        TriangleTerrain triangleChoisi= this.triangleterrain.get(idTriangle);
        
        System.out.println("Choisir le segment sur lequel l'appui sera placé (1, 2 ou 3))");
        while((n!=1)||(n!=2)||(n!=3)){
            n=Lire.i();
        }
        
        System.out.println("Chosir la position de l'appui sur le segment(en %)");
        while((positionSegment<0)||(positionSegment>100)){
            positionSegment=Lire.d();
        }
        positionSegment=positionSegment/100;
        
        Point origine = triangleChoisi.getSommet(n);
        Point extremite = triangleChoisi.getSommet((n+1)%3);
        Point positionNoeud = new Point(origine.getPx() + (extremite.getPx()-origine.getPx()) * positionSegment, origine.getPy() + (extremite.getPy() - origine.getPy()) * positionSegment);
        
        nouveauA= new Appui(idNoeud,positionNoeud,idTriangle,n,positionSegment,false);
        return nouveauA;
    }
 
    
    //Choisir un type de barre
    public TypeBarre chooseType(){
        int idType;
        double cout;
        double lmin;
        double lmax;
        double maxTension;
        double maxCompression;
        System.out.println("Donner l'identificateur du type");
        idType=Lire.i();
        System.out.println("Donner le cout au metre");
        cout=Lire.d();
        System.out.println("Donner la longueur minimale");
        lmin=Lire.d();
        System.out.println("Donner la longueur maximale");
        lmax=Lire.d();
        System.out.println("Donner la resistance max a la tension");
        maxTension=Lire.d();
        System.out.println("Donner la resistance max a la compression");
        maxCompression=Lire.d();
        TypeBarre nouveautype = new TypeBarre(idType,cout,lmin,lmax,maxTension,maxCompression);
        return nouveautype;
    }
    
    
    //Choisir la barre qu'on veut ajouter
    public Barre chooseBarre(){
        int idBarre;
        int debut;
        int fin;
        int idType;
        System.out.println("Donner l'identificateur de la barre");
        idBarre=Lire.i();
        System.out.println("Donner l'identificateur du noeud de depart de la barre");
        debut=Lire.i();
        System.out.println("Donner l'identificateur du noeud d'arrivee de la barre");
        fin=Lire.i();
        System.out.println("Donner l'identificateur du type de la barre");
        idType=Lire.i();
        Barre nouvelleB = new Barre(idBarre,debut,fin,idType);
        return nouvelleB;
    }
    
    
  
    
    
    //METHODES POUR CALCULER LES FORCES 
    
    //Ajouter une force de extérieure à un noeud
    public void addForce(Noeud noeud){//ou identifiant du noeud
        double forceX, forceY;
        System.out.println("donner la composante de la force sur x");
        forceX=Lire.d();
        noeud.setForceX(forceX);
        System.out.println("donner la composante de la force sur y");
        forceY=Lire.d();
        noeud.setForceY(forceY);
    }
    
    
    
    
    public Matrice MatriceSysteme() {
        int nN = this.noeuds.size(); //nombre de noeuds
        int nB = this.barres.size(); //nombre de barres
        int nAS = 0; //nombre d'appuis simples
        int nAD = 0; //nombre d'appuis doubles
        
        for (Noeud noeudtreillis : noeuds.values()) {
            if (noeudtreillis instanceof Appui) {
                Appui appuitreillis = (Appui) noeudtreillis;
                if (appuitreillis.isAppuiSimple()==true) {
                    nAS++;
                } else {
                    nAD++;
                }
            }
        }
        
        //On vérifie d'abord que le treillis est isostatique : 2*nN = nB + nAS + 2*nAD
        //Sinon on ne pourra pas faire de calcul de forces
        //Si c'est bon on créer la matrice du systeme d'equations
        
        if (2*nN != nB+nAS+2*nAD){
            throw new Error("On ne peut pas faire de calcul de force car le treillis n'est pas isostatique.");
        } else {
        
            Matrice systemeEquations = new Matrice(2*nN, 2*nN);

            // On rempli la matrice en parcourant tous les noeuds du treillis
            // On met d'abord les tensions aux barres, puis les reactions des appuis doubles et simples
    
            int i = 0; //ligne de la matrice qu'on est en train de remplir
            int jAD = 0; //indice de l'appui double qu'on analyse
            int jAS = 0; //indice de l'appui simple qu'on analyse
        
            for (Noeud noeudtreillis : noeuds.values()) {
                // On remplit les lignes i et i+1
                
                // D'abord avec les forces liees aux barres 
            
                int jB = 0; //indice de la barre qu'on analyse
                for (Barre barretreillis : barres.values()) {
                    Noeud autreNoeud = null;
                    if (barretreillis.getDebut() == noeudtreillis.getIdNoeud()) {
                        autreNoeud = this.noeuds.get(barretreillis.getFin());
                    } else if (barretreillis.getFin() == noeudtreillis.getIdNoeud()) {
                        autreNoeud = this.noeuds.get(barretreillis.getDebut());
                    } else {
                        jB++;
                    } 
                    // On remplit la matrice aux lignes i et i+1 et a la colonne correspondant a l'indice de la barre
                    systemeEquations.setCoeff(i,jB,noeudtreillis.cosAlpha(autreNoeud));
                    systemeEquations.setCoeff(i+1,jB,noeudtreillis.sinAlpha(autreNoeud));
                    jB++;
                }

                //  Ensuite avec les forces liees aux appuis doubles ou simples
                if (noeudtreillis instanceof Appui) {
                    Appui appui = (Appui) noeudtreillis;
                    if (appui.isAppuiSimple()==true) {
                        systemeEquations.setCoeff(i,jB+2*jAD+jAS,appui.getCosBeta());
                        systemeEquations.setCoeff(i+1,jB+2*jAD+jAS,appui.getSinBeta());
                        jAS++;
                    } else {
                        systemeEquations.setCoeff(i,jB+2*jAD,1);
                        systemeEquations.setCoeff(i+1,jB+2*jAD+1,1);
                        jAD++;
                    }
                }
                // Noeud suivant donc i+2 car il y a 2 lignes par noeud
                i = i+2;
            }
            return systemeEquations;
        }
   
    }
    
    public Matrice MatriceForceExt(){
        int nN = this.noeuds.size(); //nombre de noeuds
        int i = 0;
        Matrice matriceDeDroite = new Matrice(2*nN,1);
        for (Noeud noeudtreillis : noeuds.values()) {
            matriceDeDroite.setCoeff(i,0, -(noeudtreillis.getForceX()));
            matriceDeDroite.setCoeff(i+1,0, -(noeudtreillis.getForceY()));
            i = i+2;
        }
        return matriceDeDroite;
    }
    //Multiplier 2 matrices
    public Matrice multiplication(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrLig()) {
            throw new Error("Tailles incompatibles pour multiplier les 2 matrices");
        }
        Matrice res = new Matrice(this.getNbrLig(), m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                double produit = 0;
                for (int k = 0; k < this.getNbrCol(); k++) {
                    produit = produit + this.getCoeff(i, k) * m2.getCoeff(k, j);
                }
                res.setCoeff(i, j, produit);
            }
        }
        return res;
    }
    
    //Pour resoudre le systeme
    public Matrice Resolution(){
        Matrice resultat;
        resultat=this.MatriceSysteme().inverse().multiplication(this.MatriceForceExt());
        return resultat;
    }
    
    
    //Essai d'un main
     public static void main(String[]args){
        ZoneConstructible zone = new ZoneConstructible(0,10,0,10);
        Treillis exemple = new Treillis(zone);
        TriangleTerrain triangle1=exemple.chooseTriangle();
        exemple.addTriangle(triangle1);
        TriangleTerrain triangle2=exemple.chooseTriangle();
        exemple.addTriangle(triangle2);
        for (TriangleTerrain triangletreillis : exemple.triangleterrain.values()){
                if (triangletreillis != null) {
                    System.out.println(triangletreillis.toString()+"\n");
                }
        }
        
    }
     
}
