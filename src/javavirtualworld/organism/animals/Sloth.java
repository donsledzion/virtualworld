/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.animals;

import  javavirtualworld.theworld.TheWorld;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
/**
 *
 * @author AChojaczyk
 */
public class Sloth extends Animal implements Serializable{
        
    public Sloth(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(57,234,231));
    this.setStrength((byte)2);
    this.setActivity((byte)1);
    this.setHasMoved(false);
    }
    
    public void action(){
    
        //System.out.println(this.getName()+" from ["+this.getLocation()+"] is performing some action...");
        while(this.getActionsLeft()>0)
        {
            if(this.getHasMoved()==false)
            {
                this.move();
                this.setHasMoved(true);
            }
            else {
                System.out.println(this.getName()+" from ["+this.getLocation()+"] has moved last turn. Now it's time to rest...");
                this.setHasMoved(false);
            }
            this.decActionsLeft();
            
            if(this.getActionsLeft()<0)
                this.setActionsLeft((byte)0);
        }
    }    
    
}
