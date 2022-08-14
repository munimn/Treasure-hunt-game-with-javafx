package edu.lawrence.mazedrawing;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class MazePane extends Pane {

    private ArrayList<Wall> walls;
    private ArrayList<Treasure> treasure;
    private Wall dragging;
    private Treasure placing;
    private Color color;
    private boolean drawingWalls;
    private Player player;
    private int initialscore;
    private Label score;
    private Bounds bounds;
    
    
    

    public MazePane(Label l) {
        this.score = l;
        walls = new ArrayList<Wall>();
        treasure = new ArrayList<Treasure>();
        File theFile = new File("maze.txt");
    if(theFile.exists()) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(theFile));
        } catch(Exception ex) {
            System.out.println("Exception when trying to open file:"+ex.getMessage());
        }
        while(input.hasNext()){
        int numberofWalls = input.nextInt();
        for(int n=0;n<numberofWalls;n++){
                double x= input.nextDouble();
                double y=input.nextDouble();
                Wall wall = new Wall(x,y);
                double endx= input.nextDouble();
                double endy= input.nextDouble();
                wall.setEnd(endx,endy);
                this.getChildren().add(wall.getShape());
                walls.add(wall);    
            }
        int numberofTreasure = input.nextInt();
        for(int j=0;j<numberofTreasure;j++){     
                double x= input.nextDouble();
                double y=input.nextDouble();
                String colorstr= input.next();
                color=Color.valueOf(colorstr);
                Treasure t= new Treasure(x,y,color);
                this.getChildren().add(t.getShape());
                treasure.add(t);    
            }
        }
        }
        drawingWalls = true;
        color = Color.YELLOW;
       
        player = new Player(20,20,color);
        this.getChildren().add(player.getShape());  
        this.setOnKeyPressed(e -> keyPress(e));
    }

    

    public void keyPress(KeyEvent e) {
        switch (e.getCode()) {
            case LEFT:{
                if(hittingborderleft(player))
                {
                    break;  }
                else if(intersectwallleft(player)){
                    break;
                }
                else {
                    player.setLocation(player.getX()-40,player.getY());}
                    
                Circle l = (Circle) player.getShape();
                intersectrect(l);
                break;}
            
            case UP:{
                if(hittingbordertop(player)){
                break;}
                else if(intersectwalltop(player)){
                    break;
                }
                else{
                    player.setLocation(player.getX(),player.getY()-40);}
                Circle l = (Circle) player.getShape();
                intersectrect(l);
                break;}
            case DOWN:{
                if(hittingborderdown(player)){
                break;}
                else if(intersectwalldown(player)){
                    break;
                }
                else{
                    player.setLocation(player.getX(),player.getY()+40);}
                Circle l = (Circle) player.getShape();
                intersectrect(l);
                break;}
            case RIGHT:{
                if(hittingborderright(player)){
                break;}
                else if(intersectwallright(player)){
                    break;
                }
                else{
                    player.setLocation(player.getX()+40,player.getY());}
                Circle l = (Circle) player.getShape();
                intersectrect(l);
                break;    }
        }
    }
    public void intersectrect(Circle c){
        for(Treasure t : treasure) {
                    Shape intersection = Shape.intersect(t.getShape(), c);
                    if(intersection.getBoundsInLocal().getWidth() > 0){
                       treasure.remove(t); 
                       this.getChildren().remove(t.getShape());
                       int add = checkcolor(t);
                       initialscore = initialscore+add;
                       score.setText("Score: "+ initialscore);
                       break;
                    }  
    }
    }
    public boolean hittingbordertop(Player p){
        Bounds bounds = this.getBoundsInLocal();
        boolean top = p.getY()<= (bounds.getMinY()+(2*p.getRadius()));
        return top;}
    public boolean hittingborderdown(Player p){
        Bounds bounds = this.getBoundsInLocal();
        boolean down = p.getY() >= (bounds.getMaxY()-(2*p.getRadius()));
        return down;}
    public boolean hittingborderleft(Player p){
        Bounds bounds = this.getBoundsInLocal();
        boolean left = p.getX() <= (bounds.getMinX()+(2*p.getRadius()));
        return left;}
    public boolean hittingborderright(Player p){
        Bounds bounds = this.getBoundsInLocal();
        boolean right = p.getX() >= (bounds.getMaxX()-(2*p.getRadius()));
        return right;}
    
        
        
        
    public int checkcolor(Treasure t){
        if (t.getColor()==Color.RED){
            return 100;
        }
        else if(t.getColor()==Color.BLUE)
            return 10;
        else 
            return 40;
    }
    public boolean intersectwallleft(Player p){
        boolean n = false;
        Line l = new Line();
        l.setStartX(p.getX());
        l.setStartY(p.getY());
        l.setEndX(p.getX()-40);
        l.setEndY(p.getY());
        this.getChildren().add(l);
        for(Wall w : walls) {
                    Shape intersection = Shape.intersect(w.getShape(), l);
                    if(intersection.getBoundsInLocal().getWidth() > 0)
                         n=true;
            } 
        this.getChildren().remove(l);
        return n;         
}
    public boolean intersectwallright(Player p){
        boolean n = false;
        Line l = new Line();
        l.setStartX(p.getX());
        l.setStartY(p.getY());
        l.setEndX(p.getX()+40);
        l.setEndY(p.getY());
        this.getChildren().add(l);
        for(Wall w : walls) {
                    Shape intersection = Shape.intersect(w.getShape(), l);
                    if(intersection.getBoundsInLocal().getWidth() > 0)
                         n=true;
            } 
        this.getChildren().remove(l);
        return n;         
}
    public boolean intersectwalltop(Player p){
        boolean n = false;
        Line l = new Line();
        l.setStartX(p.getX());
        l.setStartY(p.getY());
        l.setEndX(p.getX());
        l.setEndY(p.getY()-40);
        this.getChildren().add(l);
        for(Wall w : walls) {
                    Shape intersection = Shape.intersect(w.getShape(), l);
                    if(intersection.getBoundsInLocal().getWidth() > 0)
                         n=true;
            } 
        this.getChildren().remove(l);
        return n;         
}
    public boolean intersectwalldown(Player p){
        boolean n = false;
        Line l = new Line();
        l.setStartX(p.getX());
        l.setStartY(p.getY());
        l.setEndX(p.getX());
        l.setEndY(p.getY()+40);
        this.getChildren().add(l);
        for(Wall w : walls) {
                    Shape intersection = Shape.intersect(w.getShape(), l);
                    if(intersection.getBoundsInLocal().getWidth() > 0)
                         n=true;
            } 
        this.getChildren().remove(l);
        return n;         
}

    
}
