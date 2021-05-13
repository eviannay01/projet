/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.viannay.projet.s2.tuto.gui;


import fr.insa.viannay.projet.s2.tuto.Groupe;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author francois
 */
public class MainPane extends BorderPane {

    /**
     * @return the rbTriangle
     */
    public RadioButton getRbTriangle() {
        return rbTriangleTerrain;
    }

    /**
     * @return the rbAppui
     */
    public RadioButton getRbAppui() {
        return rbAppui;
    }

    /**
     * @return the rbToutEffacer
     */
    public ToggleButton getRbToutEffacer() {
        return rbToutEffacer;
    }

    /**
     * @param rbToutEffacer the rbToutEffacer to set
     */
    public void setRbToutEffacer(ToggleButton rbToutEffacer) {
        this.rbToutEffacer = rbToutEffacer;
    }

    /**
     * @return the rbSupprimer
     */
    public ToggleButton getRbSupprimer() {
        return rbSupprimer;
    }

    /**
     * @return the rbGrouper
     */
    public ToggleButton getRbGrouper() {
        return rbGrouper;
    }

    /**
     * multiplicateur pour l'espace de départ : pour ne pas que les bords de la
     * figure soit confondus avec les bords de la fenêtre, on considère que l'on
     * veut que la fenêtre puisse contenir une figure MULT_POUR_FIT_ALL fois
     * plus grande que la figure réelle. Par exemple, si MULT_POUR_FIT_ALL = 2,
     * la figure complète n'occupera en fait qu'environ la moitié de la fenetre
     * graphique.
     */
    private static double MULT_POUR_FIT_ALL = 1.1;

    private Groupe model;
    private Controleur controleur;

    private Stage inStage;
    private File curFile;

    
    private RadioButton rbPoints;
    private RadioButton rbNoeud;
    private RadioButton rbSegments;
    private RadioButton rbBarre;
    private RadioButton rbTriangleTerrain;
    private RadioButton rbAppui;
    
    private ToggleButton rbToutEffacer;
    private ToggleButton rbSupprimer;
    private ToggleButton rbSelect;
    private ToggleButton rbGrouper;
    
    private ColorPicker cpCouleur;

    private Button bZoomDouble;
    private Button bZoomDemi;
    private Button bZoomFitAll;
 
    private DessinCanvas cDessin;
    private RectangleHV zoneModelVue;

    private MainMenu menu;

    public MainPane(Stage inStage) {
        this(inStage, new Groupe());
    }

    public MainPane(Stage inStage, Groupe model) {
        this(inStage, null, model);
    }

    public MainPane(Stage inStage, File fromFile, Groupe model) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.model = model;
        this.fitAll();
        this.controleur = new Controleur(this);

        this.rbSelect = new ToggleButton("Select");
        this.rbSelect.setOnAction((t) -> {
            this.controleur.boutonSelect(t);
        });
        this.rbPoints = new RadioButton("Points");
        this.rbPoints.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
        this.rbSegments = new RadioButton("Segments");
        this.rbSegments.setOnAction((t) -> {
            this.controleur.boutonSegments(t);
        });
        this.rbTriangleTerrain = new RadioButton("TriangleTerrain");
        this.rbTriangleTerrain.setOnAction((t) -> {
            this.controleur.boutonSegments(t);
        });
        this.rbNoeud = new RadioButton("Noeud");
        this.rbNoeud.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
         this.rbBarre = new RadioButton("Barre");
        this.rbBarre.setOnAction((t) -> {
            this.controleur.boutonSegments(t);
        });
         this.rbAppui = new RadioButton("Appui");
        this.rbAppui.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
        this.rbToutEffacer = new ToggleButton("Tout Effacer");
        this.rbToutEffacer.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
        this.rbSupprimer = new ToggleButton("Supprimer");
        this.rbSupprimer.setOnAction((t) -> {
            this.controleur.boutonPoints(t);
        });
        this.rbGrouper = new ToggleButton("Grouper");
        this.rbGrouper.setOnAction((t) -> {
            this.controleur.boutonGrouper(t);
        });

        ToggleGroup bgEtat = new ToggleGroup();
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbPoints.setToggleGroup(bgEtat);
        this.rbSegments.setToggleGroup(bgEtat);
        this.rbTriangleTerrain.setToggleGroup(bgEtat);
        this.rbAppui.setToggleGroup(bgEtat);
        this.rbToutEffacer.setToggleGroup(bgEtat);
        this.rbSupprimer.setToggleGroup(bgEtat);
        this.rbGrouper.setToggleGroup(bgEtat);
        
        this.rbPoints.setSelected(true);

        VBox vbGauche = new VBox(this.rbPoints,this.rbNoeud, this.rbSegments,this.rbBarre, this.rbTriangleTerrain, this.rbAppui);
        vbGauche.setSpacing(15);
        vbGauche.setPadding(new Insets(20,20,15,15));
        this.setLeft(vbGauche);

        this.menu = new MainMenu(this);
        
        HBox hbTop = new HBox (this.rbSelect, this.rbGrouper, this.rbSupprimer, this.rbToutEffacer);
        hbTop.setSpacing(15);
        hbTop.setPadding(new Insets(20,20,15,15));
        this.setTop (hbTop);
        
        VBox vbTop = new VBox (this.menu, hbTop);
        this.setTop(vbTop);
        
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.controleur.changeColor(this.cpCouleur.getValue());
        });

        this.bZoomDouble = new Button("Zoom x2");
        this.bZoomDouble.setOnAction((t) -> {
            this.controleur.zoomDouble();
        });
        this.bZoomDemi = new Button("Zoom /2");
        this.bZoomDemi.setOnAction((t) -> {
            this.controleur.zoomDemi();
        });
        this.bZoomFitAll = new Button("Zoom Fit All");
        this.bZoomFitAll.setOnAction((t) -> {
            this.controleur.zoomFitAll();
        });
        VBox vbZoom = new VBox(this.bZoomDouble, this.bZoomDemi, this.bZoomFitAll);
        vbZoom.setSpacing(10);
        vbZoom.setPadding(new Insets(10,10,10,10));

        VBox vbDroit = new VBox(this.cpCouleur, vbZoom);
        this.setRight(vbDroit);

        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);
        
        this.rbGrouper.setOnMouseEntered(e-> 
        { rbGrouper.setStyle("-fx-background-color: lightblue");
        });
        this.rbGrouper.setOnMouseExited(e-> 
        { rbGrouper.setStyle("-fx-background-color:");
        });
        
         this.rbSelect.setOnMouseEntered(e-> 
        { rbSelect.setStyle("-fx-background-color: lightblue");
        });
        this.rbSelect.setOnMouseExited(e-> 
        { rbSelect.setStyle("-fx-background-color:");
        });
        
         this.rbSupprimer.setOnMouseEntered(e-> 
        { rbSupprimer.setStyle("-fx-background-color: lightblue");
        });
        this.rbSupprimer.setOnMouseExited(e-> 
        { rbSupprimer.setStyle("-fx-background-color:");
        });

        this.rbToutEffacer.setOnMouseEntered(e-> 
        { rbToutEffacer.setStyle("-fx-background-color: lightblue");
        });
        this.rbToutEffacer.setOnMouseExited(e-> 
        { rbToutEffacer.setStyle("-fx-background-color:");
        });
      
        this.bZoomDouble.setOnMouseEntered(e-> 
        { bZoomDouble.setStyle("-fx-background-color: lightyellow");
        });
        this.bZoomDouble.setOnMouseExited(e-> 
        { bZoomDouble.setStyle("-fx-background-color:");
        });
        
        this.bZoomDemi.setOnMouseEntered(e-> 
        { bZoomDemi.setStyle("-fx-background-color: lightyellow");
        });
        this.bZoomDemi.setOnMouseExited(e-> 
        { bZoomDemi.setStyle("-fx-background-color:");
        });
        
        this.bZoomFitAll.setOnMouseEntered(e-> 
        { bZoomFitAll.setStyle("-fx-background-color: lightyellow");
        });
        this.bZoomFitAll.setOnMouseExited(e-> 
        { bZoomFitAll.setStyle("-fx-background-color:");
        });
        

        this.controleur.changeEtat(20);

    }

    public void fitAll() {
        this.zoneModelVue = new RectangleHV(this.model.minX(),
                this.model.maxX(), this.model.minY(), this.model.maxY());
        this.zoneModelVue = this.zoneModelVue.scale(MULT_POUR_FIT_ALL);
    }

    public void redrawAll() {
        this.cDessin.redrawAll();
    }

    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }
    
    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @return the rbSelect
     */
    public ToggleButton getRbSelect() {
        return rbSelect;
    }

    /**
     * @return the rbPoints
     */
    public RadioButton getRbPoints() {
        return rbPoints;
    }

    /**
     * @return the rbSegments
     */
    public RadioButton getRbSegments() {
        return rbSegments;
    }

    /**
     * @return the bGrouper
     */
    public ToggleButton getbGrouper() {
        return rbGrouper;
    }

    /**
     * @return the cpCouleur
     */
    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }

    /**
     * @return the cDessin
     */
    public DessinCanvas getcDessin() {
        return cDessin;
    }

    /**
     * @return the inStage
     */
    public Stage getInStage() {
        return inStage;
    }

    /**
     * @return the curFile
     */
    public File getCurFile() {
        return curFile;
    }

    /**
     * @param curFile the curFile to set
     */
    public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    /**
     * @return the MULT_POUR_FIT_ALL
     */
    public static double getMULT_POUR_FIT_ALL() {
        return MULT_POUR_FIT_ALL;
    }

    /**
     * @return the bZoomDouble
     */
    public Button getbZoomDouble() {
        return bZoomDouble;
    }

    /**
     * @return the bZoomDemi
     */
    public Button getbZoomDemi() {
        return bZoomDemi;
    }

    /**
     * @return the bZoomFitAll
     */
    public Button getbZoomFitAll() {
        return bZoomFitAll;
    }

    /**
     * @return the zoneModelVue
     */
    public RectangleHV getZoneModelVue() {
        return zoneModelVue;
    }

    /**
     * @return the menu
     */
    public MainMenu getMenu() {
        return menu;
    }

    /**
     * @param zoneModelVue the zoneModelVue to set
     */
    public void setZoneModelVue(RectangleHV zoneModelVue) {
        this.zoneModelVue = zoneModelVue;
    }

}