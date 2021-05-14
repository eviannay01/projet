/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.viannay.projet.s2;

/**
 *
 * @author viann
 */
public class Matrice {
    private int nbrLig;
    private int nbrCol;
    private double[][] coeffs;

    //Constructeur
    public Matrice(int nl, int nc) {
        this.nbrLig = nl;
        this.nbrCol = nc;
        this.coeffs = new double[nl][nc];
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                this.coeffs[i][j] = 0;
            }
        }
    }
    
    
    //getter et setter
     public int getNbrLig() {
        return this.nbrLig;
    }
      public int getNbrCol() {
        return this.nbrCol;
    }

    public double getCoeff(int lig, int col) {
        return this.coeffs[lig][col];
    }
    
    public void setCoeff(int lig,int col,double nouvelVal) {
        this.coeffs[lig][col] = nouvelVal;
    }
    
    
    
    //Affichage
    @Override
     public String toString() {
        String res = "";
        for (int i = 0; i < this.nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < this.getNbrCol(); j++) {
                res = res + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.getNbrCol() - 1) {
                    res = res + " ";
                }

            }
            res = res + "]\n";

        }
        return res;
    }
     
     
     //Matrice identité
      public static Matrice identite(int taille) {
        Matrice res = new Matrice(taille, taille);
        for (int i = 0; i < res.nbrLig; i++) {
            res.coeffs[i][i] = 1;
        }
        return res;
    }

      
      //Concaténations
       public Matrice concatCol(Matrice m) {
        if (this.getNbrLig() != m.getNbrLig()) {
            throw new Error("Nombre de lignes incompatibles");
        }
        Matrice res = new Matrice(this.getNbrLig(),this.getNbrCol()+m.getNbrCol());
        for(int i = 0 ; i < res.getNbrLig() ; i ++) {
            for (int j = 0 ; j < res.getNbrCol() ; j ++) {
                if(j< this.getNbrCol()){
                    res.setCoeff(i, j, this.getCoeff(i, j));
                }else{
                    res.setCoeff(i, j, m.getCoeff(i, j-this.getNbrCol()));
                }
            }
        }
        return res;
    }
    
       
       //Copie
       public Matrice copie() {
           Matrice res= new Matrice (this.getNbrLig(),this.getNbrCol());
           for (int i=0; i<this.getNbrLig(); i++){
               for (int j=0; j<this.getNbrCol(); j++) {
                   res.setCoeff(i, j, this.getCoeff(i, j));
               }
           }
           return res;
       }
       
       
       
       //SousMatrice
       public Matrice subCol(int cmin, int cmax){
           if(cmin<0 || cmax<cmin || cmax>this.getNbrCol()){
               throw new Error("Erreur d'indice de colonne");
           }
           Matrice res= new Matrice(this.getNbrLig(),cmax-cmin+1);
           for (int i=0; i<res.getNbrLig(); i++){
               for(int j=0; j<res.getNbrCol(); j++){
                   res.setCoeff(i, j, this.getCoeff(i, cmin+j));
               }
           }
           return res;
       }
       
       
       
       //Pivot de Gauss
       public Matrice pivotGauss(){
           Matrice res = this.copie();
           //ligne du pivot
           int p = -1;
           //Pour chaque colonne:
           for (int j=0; j<res.getNbrLig(); j++){
               //max est le plus grand coeff de la ligne en valeur absolue
               double max=0;
               //imax est la ligne de max
               int imax=0;
               for (int i=(p+1); i<res.getNbrLig(); i++){
                   if (Math.abs(res.getCoeff(i, j))> max){
                       max=Math.abs(res.getCoeff(i, j));
                       imax= i;
                   }
               }
               //le pivot
               double pivot=res.getCoeff(imax, j);
               if(pivot!=0){
                   p=p+1;
                   //On modifie la ligne du pivot tel que pivot=1
                   for(int col=0; col<res.getNbrCol(); col++){
                       res.setCoeff(imax, col, res.getCoeff(imax, col)/pivot);
                   }
                   //On place la ligne du pivot a la ligne p
                   if(imax!=p){
                       for (int col=0; col<res.getNbrCol(); col++){
                           Double echange = res.getCoeff(imax, col);
                           res.setCoeff(imax, col, res.getCoeff(p, col));
                           res.setCoeff(p, col, echange);
                       }
                   }
                   
                   //On simplifie les autres lignes
                   for(int lig=0; lig<res.getNbrLig(); lig++){
                       if(lig!=p){
                           Double annule = res.getCoeff(lig, j);
                           for (int col=0; col<res.getNbrCol(); col++){
                               res.setCoeff(lig, col, res.getCoeff(lig, col)-(res.getCoeff(p, col)*annule));
                           }
                       }
                   }
               }
           }
           return res;
       }
    
       
       
       //Inversion de matrice
       public Matrice inverse(){
           if (this.getNbrLig()!= this.getNbrCol()){
               throw new Error("La Matrice n'est pas carrée");
           }
           //On colle la matrice identité à la dernière colonne de la matrice
           Matrice mDebut = this.concatCol(Matrice.identite(this.getNbrLig()));
           //On applique le pivot de Gauss
           Matrice mFin = mDebut.pivotGauss();
           //On récupère uniquement la matrice inverse de la matrice d'origine (= la SousMatrice de droite)
           Matrice res = mFin.subCol(mFin.getNbrCol()/2, (mFin.getNbrCol()-1));
           return res;
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
       
       public static void testInverse(){
        Matrice test = new Matrice(3,3);
        test.setCoeff(0,0, 2);
        test.setCoeff(0,1, -1);
        test.setCoeff(0,2, 0);
        test.setCoeff(1,0, -1);
        test.setCoeff(1,1, 2);
        test.setCoeff(1,2, -1);
        test.setCoeff(2,0, 0);
        test.setCoeff(2,1, -1);
        test.setCoeff(2,2, 2);
        System.out.println("Matrice a inverser : " +test.toString());
        Matrice lInverse =  test.inverse();
        System.out.println("Matrice inversee : " +lInverse.toString());
    }
       
       public static void main(String[]args){
           Matrice m1 = new Matrice(2,2);
           m1.setCoeff(0,0,2);
           m1.setCoeff(0,1,3);
           m1.setCoeff(1,0,1);
           m1.setCoeff(1,1,-1);
           Matrice m2 = m1.inverse();
           System.out.println(m1.toString());
           System.out.println(m2.toString());
           testInverse();
           
           
       }
       
       
}
