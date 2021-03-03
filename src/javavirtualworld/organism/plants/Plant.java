/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.plants;
import java.awt.Color;
import  javavirtualworld.theworld.TheWorld;
import  javavirtualworld.organism.Organism;
import  java.awt.Point;
import  java.util.Random;
import java.io.Serializable;
/**
 *
 * @author AChojaczyk
 */
public abstract class Plant extends Organism implements Serializable{
        
    public Plant(String name, Point location, TheWorld thisWorld){
    super(name,location,thisWorld);
    setChanceToAct((byte)25);
    }
  
    
 public Plant(){
        this.name = "Nemo";
        this.location = new Point(-1,-1);
        this.origin = new Point (-1, -1);
        this.activity = -1;
        
        this.defaultActions = 0;
        this.actionsLeft = 0;
        this.chanceToAct = 0;
        this.color = new Color(0,0,0);
        this.thisWorld = null;
        this.dateOfBirth = 2020;
        this.isAnimal = false;
        this.bonusMove = false;
        this.hasMoved = true;
            }
    
public void action(){
        while((this.getActionsLeft()>0)&&(this.getWorld().getPopulation()<this.getWorld().getWorldCapacity()))
        {
            if(spreadAttempt()==true)
            {
                //System.out.println(this.getName()+" has drawn a chance to reproduce!");
                reproduce();            
            }
            if(getActionsLeft()>0)
            {
                decActionsLeft();
                if(this.getActionsLeft()<0)
                    this.setActionsLeft((byte)0);
            }
        }
    }
    
    public boolean spreadAttempt(){
    
        Random generator = new Random();
        int roulette = generator.nextInt(100)+1;
        
        if(roulette <= this.getChanceToAct())
            return true;
        else return false;
    
    }
}
