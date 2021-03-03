/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism;

//import  java.io.PrintStream;
//import  java.util.Formatter;
import  java.awt.Point;
import  java.awt.Color;
import  javavirtualworld.theworld.TheWorld;
import java.io.Serializable;
/**
 *
 * @author AChojaczyk
 */
public abstract class Organism implements Comparable<Organism>,Serializable {
    
            protected     String  name;
            protected     Point   location;
            protected     Point   origin;
            protected     short   strength;
            protected   byte    activity;
            protected     int     dateOfBirth;
            protected     byte    defaultActions;
            protected     byte    actionsLeft;
            protected     byte    chanceToAct;
            protected     Color   color;
            protected     TheWorld thisWorld;
            protected     boolean isAnimal;
            protected     boolean bonusMove;
            protected     boolean hasMoved;
    
    
    
    
    public Organism(){
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
            
    public Organism(String name, Point location,TheWorld thisWorld){
        this.name = name;
        this.location = location;
        this.origin = this.location;
        this.activity = 0;                  //inicjatywa
        
        this.defaultActions = 1;
        this.actionsLeft = 0;
        this.chanceToAct = 100;
        this.color = new Color(204,204,204);
        this.thisWorld = thisWorld;
        this.dateOfBirth = this.thisWorld.getTimeStamp();
        this.isAnimal = false;
        this.bonusMove = false;
        this.hasMoved = false;
    }
    
    @Override
    public int compareTo(Organism org) {
    
        if (this.activity < org.getActivity()) return -1;
        else if (this.activity > org.getActivity()) return 1;
        else return 0;
    }   
    
    
    @Override
    public int hashCode(){
        final int prime = 37;
        int result = 1;
        result = prime* result+((name==null)? 0:name.hashCode());
        long temp;
        temp = Double.doubleToLongBits((double)thisWorld.hashCode());
        result = prime*result+(int)(temp^(temp>>>32));
        
        return result;
    }
    
    public void action()
    {
        
    }
    
    public void reAction(Organism offender){}
    
    public void eatIt(Organism eaten){}
    
    //public void checkColision(){}
    
    public void moveOnField(Point targetField){}
    
    public void print(){
        
        System.out.format("Name: %s, location: [%d,%d], Activity: %d, DateOfBirth :%d, hashCode : %d\n",name,(int)location.getX(),(int)location.getY(), (int)getActivity(), (int)getDateOfBirth(), (int)hashCode());
    }
    
    public boolean reproduce(){
        byte field = 1;
                
        while(this.getWorld().lookForSpace(this.getLocation())==false)
        {            
            if(field>=9)
                return false;
            field++;
        }
        Point targetField = this.getWorld().targetField(this.getLocation());
        
        while((this.getWorld().isFieldFree(targetField)==false)||(this.getWorld().isFieldOnBoard(targetField)==false))
        {
            targetField = this.getWorld().targetField(this.getLocation());
        }
        //this.getWorld().breedOrganism(this,targetField);
        //System.out.println(this.getName()+" will breed on field ["+targetField.getX()+","+targetField.getY()+"]");
        this.thisWorld.addOrganism(this.name, targetField);
        return true;
    }
    
    //==========================================================================
    //================== GETTERS ===============================================
    //==========================================================================
    
    public short getStrength(){
        
        return this.strength;
    }
    
    public short getActivity(){
        
        return this.activity;
    }    
    
    public String getName(){
        
        return this.name;
    }
    
    public Point getLocation(){
        
        return this.location;
    }
    
    
    public Point getOrigin(){
        
        return this.origin;
    }
    
    public Color getColor() {
    
        return this.color;
    }
    
    public byte getChanceToAct(){
    
        return this.chanceToAct;    
    }
    
    public byte getActionsLeft(){
    
        return this.actionsLeft;
    }
    
    public byte getDefaultActions(){
        
        return this.defaultActions;
    }
    
    public TheWorld getWorld(){
    
        return thisWorld;
    }
    
    public int getDateOfBirth(){
    
        return this.dateOfBirth;
    }
    
    public boolean getIsAnimal(){
        
        return this.isAnimal;
    }
    
    public boolean getBonusMove(){
        
        return this.bonusMove;
    }
    
    public boolean getHasMoved(){
        
        return this.hasMoved;
    }
    
    //==========================================================================
    //================== SETTERS ===============================================
    //==========================================================================
    
    public void setWorld(TheWorld thisWorld){
        this.thisWorld = thisWorld;
    }
    
    
    public void setActionsLeft(byte actionsLeft){
        this.actionsLeft = actionsLeft;
    }
    
    protected void setDefaultActions(byte defaultActions){
    
        this.defaultActions = defaultActions;
    }
    
    public void incActionsLeftBy(byte addActions){
        this.actionsLeft+=addActions;
    }
    
    public void setActivity(byte activity){
        this.activity = activity;
    }
    
    public void setStrength(byte strength){
        this.strength = strength;
    }
    
    public void decActionsLeft(){
        this.actionsLeft--;
    }
    
    public void setColor(Color color){
    
        this.color = color;
    }
    
    public boolean setLocation(Organism moved, Point targetField){
        
        moved.location = targetField;
        
        return true;
    }
    
    public void setOrigin(Organism moved, Point sourceField){
        
        moved.origin = sourceField;
    }
    
    public void setChanceToAct(byte chanceToAct){
        this.chanceToAct = chanceToAct;
    }
    
    public void setIsAnimal(boolean toBeAnimalOrNotToBe){
        
        this.isAnimal = toBeAnimalOrNotToBe;
    }
    
    public void setBonusMove(boolean setBonusMove){
        
        this.bonusMove = setBonusMove;
    }
    
    public void setHasMoved(boolean setHasMoved){
        
        this.hasMoved = setHasMoved;
    }
}
