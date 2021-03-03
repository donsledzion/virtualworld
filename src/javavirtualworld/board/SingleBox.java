/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.board;



import java.awt.*;
import java.awt.geom.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author AChojaczyk
 */
public class SingleBox extends JPanel {
    
    private Point location;
    private short boxSize;
    private Color color;
    
    public SingleBox(Point location, Color color, short boxSize){
       
        this.location   = location;
        this.boxSize    = boxSize;                
        this.color      = color;
    }
    
    public short getBoxSize(){
    
        return this.boxSize;
    }
    
    public void draw(Graphics g){
    
        Graphics2D g2d = (Graphics2D)g;
        Rectangle2D.Double box = new Rectangle2D.Double(boxSize*(int)location.getX(),boxSize*(int)location.getY(),boxSize,boxSize);
        
        g2d.setColor(this.color);
        g2d.fill(box);        
    }
    
}
