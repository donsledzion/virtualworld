/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.animals;
import java.awt.Color;
import  javavirtualworld.theworld.TheWorld;
import  javavirtualworld.organism.Organism;
import  javavirtualworld.board.Board;
import  java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author AChojaczyk
 */
public abstract class Animal extends Organism implements Serializable{
        
    public Animal(String name, Point location, TheWorld thisWorld){
    super(name,location,thisWorld);
    this.setIsAnimal(true);
    }
    
 
 public Animal(){
        this.name= "Nemo";
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
    
        //System.out.println(this.getName()+" from ["+this.getLocation()+"] is performing some action...");
        while(this.getActionsLeft()>0)
        {
            this.move();
            this.decActionsLeft();
            
            if(this.getActionsLeft()<0)
                this.setActionsLeft((byte)0);
        }
    }    


protected void move(){
    
    Point targetField = this.getWorld().findSpace(this.getLocation());
    /*
    while(this.getWorld().isFieldOnBoard(targetField)==false)
    {
        targetField = this.getWorld().findSpace(this.getLocation());
    }
    */
    System.out.println(this.getName()+" from "+ this.getOrigin() + "moves on " + targetField.getLocation());
    moveOnField(targetField);
}

public void moveOnField(Point targetField){
    this.setOrigin(this, this.getLocation());
    this.setLocation(this, targetField);
    checkColision();
}

private void checkColision(){
    for(int i = 0 ; i < this.getWorld().getInhabitants().size() ; i++)
    {   
        Organism o = this.getWorld().getInhabitants().get(i);
        
        if((this.getLocation().getX()==o.getLocation().getX())&&(this.getLocation().getY()==o.getLocation().getY()))
        {   
            if(this.getDateOfBirth()!=o.getDateOfBirth())
                this.collide(o);
        }
    }    
}

public boolean reproduce(){
        if(this.getWorld().lookForSpaceForAnimal(this.getLocation())==false)
            return false;
    
        Point targetField = this.getWorld().targetFieldForAnimal(this.getLocation());
        
        while((this.getWorld().isFieldFreeOfAnimals(targetField)==false)||(this.getWorld().isFieldOnBoard(targetField)==false))
        {
            targetField = this.getWorld().targetFieldForAnimal(this.getLocation());
        }
        //this.getWorld().breedOrganism(this,targetField);
        //System.out.println(this.getName()+" will breed on field ["+targetField.getX()+","+targetField.getY()+"]");
        this.getWorld().addOrganism(this.getName(), targetField);
        return true;
    }

private void collide(Organism toColide){
    if(this.getClass()==toColide.getClass())
    {
        
        this.moveOnField(this.getOrigin());
        toColide.decActionsLeft();
        if(this.reproduce()==false){            
            toColide.reproduce();
        }
    }
    else if(this.getStrength()>=toColide.getStrength())
    {
        this.eatIt(toColide);
    }
    else if(this.getStrength()<toColide.getStrength())
    {
        toColide.eatIt(this);
    }
    else
    {
        System.out.println("STH GONE WRONG....");
    }
}

public void eatIt(Organism eaten){
    
    String comment = this.getName()+ " from ["+ (short)this.getOrigin().getX()+","+(short)this.getOrigin().getY()+"] eats "
            + eaten.getName() + " from [" + (short)eaten.getLocation().getX()+","+(short)eaten.getLocation().getX()+"]";
    if(eaten.getIsAnimal()==true)
        this.getWorld().getBoard().addComment((byte)1, (byte)1, comment);
    
    eaten.reAction(this);
    this.getWorld().eatOrganism(eaten);
}

    
}
