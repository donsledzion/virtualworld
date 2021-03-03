/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import javavirtualworld.organism.Organism;

/**
 *
 * @author AChojaczyk
 */
public class OrganismInfoBox extends JPanel {
    
    
    private short boxWidth;
    private short boxHeight;
    private Color bgColor;
    private Organism organism;
    private int boxSize;
    private int x;
    private int y;

/**
 *
 * @author AChojaczyk
 */
    
    public OrganismInfoBox(){
         
        this.organism = null;
        
        this.boxSize = 0;                
        this.bgColor = new Color(1,1,1);
        this.boxHeight =0;
        this.boxWidth = 0;
    }
    
    public OrganismInfoBox(Organism inspected){
         
        this.organism = inspected;        
        this.boxSize = inspected.getWorld().getBoard().getBoxSize();                
        this.bgColor = inspected.getColor();
        this.boxHeight =(short)(boxSize*2);
        this.boxWidth = (short)(boxSize*3);
        this.x = (int)organism.getLocation().getX();
        this.y = (int)organism.getLocation().getY();
    }
    
    /*
    public short getBoxSize(){
    
        return this.boxSize;
    }
    */
    
    public void draw(Graphics g){
    
        
        Graphics2D g2d = (Graphics2D)g;
        Rectangle2D.Double box = new Rectangle2D.Double(x*boxSize,y*boxSize,this.boxWidth,this.boxHeight);
        
        g2d.setColor(this.bgColor);
        g2d.fill(box);                
    }
    
}
