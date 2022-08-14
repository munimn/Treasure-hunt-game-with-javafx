package edu.lawrence.mazedrawing;

import java.io.PrintWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Treasure {

    private Rectangle rect;
    private Color color;
    
    private double snap(double x) {
        double n = Math.floor(x / 40);
        return n * 40.0;
    }
    
    public Treasure(double x, double y,Color c) {
        this.color = c;
        rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(30.0);
        rect.setHeight(30.0);
        rect.setFill(color);
    }
    
    public Shape getShape() { return rect; }
    
    public void setLocation(double x, double y) {
        rect.setX(x);
        rect.setY(y);
    }
    public Color getColor(){
        Color c = (Color) rect.getFill();
        return c;
    }
    
    public void snapToGrid(double x, double y) {
        rect.setX(snap(x)+ 5.0);
        rect.setY(snap(y)+ 5.0);
    }
    
    
}

