/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.board;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import javavirtualworld.theworld.TheWorld;
import javavirtualworld.organism.Organism;
import javavirtualworld.board.OrganismInfoBox;
/**
 *
 * @author AChojaczyk
 */
public class DrawBoxes extends JPanel implements MouseListener, MouseMotionListener  {
    
    private List<Object> boxes = new ArrayList<>();
    private List<Organism> inhabitants = new ArrayList<>();
    private short boxSize;
    private Board board;
    private TheWorld thisWorld;
    
    protected Organism inspected;
    
    protected DrawBoxes(TheWorld thisWorld, short boxSize){
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        this.boxSize = boxSize;
        inhabitants = thisWorld.getInhabitants();
        board = thisWorld.getBoard();
        this.thisWorld = thisWorld;
        inspected = null;
        
        for(Organism organism: thisWorld.getInhabitants()){
                        
            addBox(organism.getLocation(), organism.getColor(),boxSize);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
    
        super.paintComponent(g);
        for(Object box : boxes){
        
            ((SingleBox) box).draw(g);
        }      
    }
    
    public void addBox(Point location, Color color, short size) {
    
        boxes.add(new SingleBox(location,color,size));
        repaint();
    }
    
    
        
    
    @Override
    public void mouseDragged(MouseEvent arg0){
        System.out.println("mouseDragged");
    }
    
    
    @Override
    public void mouseMoved(MouseEvent arg0){
        //System.out.println("mouseMoved");
    }
    
     @Override
    public void mouseEntered(MouseEvent e){
        System.out.println("mouseEntered");
    }
    
    
    @Override
    public void mouseExited(MouseEvent e){
        System.out.println("mouseExited");
    }  
    
    @Override
    public void mouseClicked(MouseEvent e){
        System.out.println("mousePressed");        
    }
    
    
    @Override
    public void mousePressed(MouseEvent e){
        boolean isThereAnybodyInThere = false;
        System.out.println("mousePressed: ["+e.getX()/boxSize+","+e.getY()/boxSize+"]");
        
        for(Organism organism: inhabitants){
            if(((organism.getLocation().getX()-e.getX()/boxSize)==0)&&((organism.getLocation().getY()-e.getY()/boxSize)==0))
            {
                //board.redrawBoxes();
                this.inspected = organism;
                
                System.out.println(organism.getName()+ " lives here!");
                
                isThereAnybodyInThere = true;      
            }        
        }
        if(isThereAnybodyInThere == false){
                inspected = null;
                System.out.println("Nobody lives here! (yet)");
                
                board.getLeftPanel().add(board.addOrganismPanel(new Point(e.getX()/boxSize,e.getY()/boxSize)));
                board.redrawBoxes();
        }
    }
    
        
    @Override
    public void mouseReleased(MouseEvent e){
        System.out.println("mouseRelesed");
    }    
    
    
}
