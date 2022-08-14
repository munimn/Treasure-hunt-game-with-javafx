package edu.lawrence.mazedrawing;

import java.io.PrintWriter;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Wall {

    private Line line;
    
    private double snap(double x) {
        long n = Math.round(x / 40);
        return n * 40.0;
    }
    
    public Wall(double x, double y) {
        line = new Line();
        line.setStartX(snap(x));
        line.setStartY(snap(y));
        line.setEndX(x);
        line.setEndY(y);
    }
    
    public Shape getShape() { return line; }
    
    public void setEnd(double x, double y) {
        line.setEndX(x);
        line.setEndY(y);
    }
    
    public void snapToGrid(double x, double y) {
        double deltaX = Math.abs(x - line.getStartX());
        double deltaY = Math.abs(y - line.getStartY());
        if (deltaX > deltaY) {
            line.setEndX(snap(x));
            line.setEndY(line.getStartY());
        } else {
            line.setEndX(line.getStartX());
            line.setEndY(snap(y));
        }
    }
    
    
}
