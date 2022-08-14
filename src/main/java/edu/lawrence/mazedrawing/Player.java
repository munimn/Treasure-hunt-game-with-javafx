/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.mazedrawing;

import java.io.PrintWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player {

    private Circle rect;
    private Color color;
    
    private double snap(double x) {
        double n = Math.floor(x / 40);
        return n * 40.0;
    }
    
    public Player(double x, double y,Color c) {
        this.color = c;
        rect = new Circle();
        rect.setCenterX(x);
        rect.setCenterY(y);
        rect.setRadius(15.0);
        rect.setFill(color);
    }
    
    public Shape getShape() { return rect; }
    
    public void setLocation(double x, double y) {
        rect.setCenterX(x);
        rect.setCenterY(y);
    }
    public double getX(){
        double x = Math.round(rect.getCenterX());
        return x;   
    }
    public double getY(){
        double y = Math.round(rect.getCenterY());
        return y;   
    }
    public double getRadius(){
        double y = Math.round(rect.getRadius());
        return y;   
    }
    
    public void snapToGrid(double x, double y) {
        rect.setCenterX(snap(x)+ 5.0);
        rect.setCenterY(snap(y)+ 5.0);
    }
}
