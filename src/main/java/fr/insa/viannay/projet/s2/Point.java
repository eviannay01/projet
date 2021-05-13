package fr.insa.viannay.projet.s2;

import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viann
 */
public class Point {
private double x;
private double y;

   //Constructeur
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //getter et setter
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    //Affichage
    @Override
    public String toString() {
        return "("  + x + "; " + y + ")";
    }
        
        
        }
