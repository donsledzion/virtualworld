/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package javavirtualworld.theworld;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;

import javavirtualworld.board.Board;
import javavirtualworld.organism.Organism;
import javavirtualworld.organism.animals.Wolf;
import javavirtualworld.organism.animals.Sheep;
import javavirtualworld.organism.animals.Viper;
import javavirtualworld.organism.animals.Bear;
import javavirtualworld.organism.animals.Sloth;
import javavirtualworld.organism.plants.grass.Grass;
import javavirtualworld.organism.plants.Coca;
import javavirtualworld.organism.plants.Dandelion;
import javax.swing.JOptionPane;
import java.io.Serializable;

/**
 *
 * @author AChojaczyk
 */
public class TheWorld implements Serializable {
    
    final private byte size;
    final private byte howManySpecies;
    private short population;
    private int timeStamp;
    private List<Organism> inhabitants;
    private Board gameBoard;
    private int roundCounter;
    
    
    
    public TheWorld(/*byte size, short population*/){
    
        this.size = setWorldSize();
        this.population = setWorldPopulation();
        this.inhabitants = new LinkedList<>();
        this.howManySpecies = 8;
        this.timeStamp = 0;
        
        short proportion = (short)(this.population/this.howManySpecies);
        
        organismGenerator((short)(population/howManySpecies),"Wolf");
        organismGenerator((short)(population/howManySpecies),"Sheep");
        organismGenerator((short)(population/howManySpecies),"Viper");
        organismGenerator((short)(population/howManySpecies),"Bear");
        organismGenerator((short)(population/howManySpecies),"Sloth");
        organismGenerator((short)(population/howManySpecies),"Grass");
        organismGenerator((short)(population/howManySpecies),"Coca");
        organismGenerator((short)(population-(howManySpecies-1)*proportion),"Dandelion");
        
        gameBoard = new Board(this);
        roundCounter = 0;
    }
    
    public void takeTurn()
    {
        this.incRoundCounter();
        for(Organism _organism : this.inhabitants)
        {
            _organism.incActionsLeftBy(_organism.getDefaultActions());
            if(_organism.getBonusMove()==true){
            
                _organism.incActionsLeftBy((byte)1);
                _organism.setBonusMove(false);
            }
        }
        
        for(int i = 0 ; i < this.inhabitants.size(); i++)
        {
            this.inhabitants.get(i).action();
        }
        
        this.getBoard().setCredits(getRoundCounter(), getPopulation());
    }
    
    public Organism addOrganism(String orgName, Point destination){
        
        Organism tmp = organismFactory(orgName,destination);
        tmp.moveOnField(destination);
        this.getInhabitants().add(tmp);
        this.incPopulation();
        return tmp;
    }
    
    public void setGameBoard(TheWorld theWorld){
        this.gameBoard = new Board(theWorld);
    }
    
    public void setInhabitants(List<Organism> inhabitants){
        this.inhabitants = inhabitants;
    }
    
    public void setWorld(TheWorld loadedWorld, TheWorld overriddenWorld){
        overriddenWorld = loadedWorld;
    }
    
    public void eatOrganism(Organism eaten){
        
        this.decPopulation();
        this.getInhabitants().remove(eaten);
    }
    
    private Organism organismFactory(String orgName, Point destination){
        this.incTimeStamp();
        if(orgName=="Grass")
            return new Grass(orgName,destination, this);
        else if(orgName=="Coca")
            return new Coca(orgName,destination, this);
        else if(orgName=="Dandelion")
            return new Dandelion(orgName,destination, this);
        else if(orgName=="Wolf")
            return new Wolf(orgName,destination, this);
        else if(orgName=="Sheep")
            return new Sheep(orgName,destination, this);
        else if(orgName=="Viper")
            return new Viper(orgName,destination, this);
        else if(orgName=="Bear")
            return new Bear(orgName,destination, this);
        else if(orgName=="Sloth")
            return new Sloth(orgName,destination, this);
        else
            return new Grass("Upsss! I'm kind'a badgrass!",destination, this);
        
    }
    
    public void lookForColisions(){
    
        for(int i = 0 ; i < this.getInhabitants().size(); i++)
        {
            for(int j = 0 ; j < this.getInhabitants().size(); j++)
            {
                if(i!=j)
                {
                    if((this.getInhabitants().get(i).getLocation().x==this.getInhabitants().get(j).getLocation().x)&&
                            (this.getInhabitants().get(i).getLocation().y==this.getInhabitants().get(j).getLocation().y))
                    {
                        System.out.println("ERROR! "+this.getInhabitants().get(i).getName()+" and " + this.getInhabitants().get(i).getName());
                        System.out.println(" are on the same field ["+this.getInhabitants().get(i).getLocation()+"]");
                    }
                }
            }
        }
    }
    
    private void organismGenerator(short amount, String type){
        Random generator = new Random();
        
        for(int i=0;i<amount;i++)
        {
            int x = generator.nextInt(this.size);
            int y = generator.nextInt(this.size);
            Point location = new Point(x,y);
            
            boolean isOccupied = true;
            while(isOccupied==true)
            {
                isOccupied = false;
                for(Organism _organism : this.inhabitants)
                {
                    if(_organism.getLocation().equals(location))
                    {
                        isOccupied = true;
                        x = generator.nextInt(this.size);
                        y = generator.nextInt(this.size);
                        //System.out.println("Ooops! Someone lives here! Let's find another place to live...");
                        location.setLocation(x,y);
                        break;
                    }
                }
            }
            if(type=="Wolf")
                this.getInhabitants().add(new Wolf("Wolf",location,this));
            else if(type=="Sheep")
                this.getInhabitants().add(new Sheep("Sheep", location, this ));
            else if(type=="Viper")
                this.getInhabitants().add(new Viper("Viper", location, this ));
            else if(type=="Bear")
                this.getInhabitants().add(new Bear("Bear", location, this ));
            else if(type=="Sloth")
                this.getInhabitants().add(new Sloth("Sloth", location, this ));
            else if(type=="Grass")
                this.getInhabitants().add(new Grass("Grass", location, this ));
            else if(type=="Coca")
                this.getInhabitants().add(new Coca("Coca", location,this));
            else if(type=="Dandelion")
                this.getInhabitants().add(new Dandelion("Dandelion", location,this));
            incTimeStamp();
        }        
    }    
    
    private byte setWorldSize(){
         String worldSizePicker = JOptionPane.showInputDialog(null,
            "Select world size", "World size...", JOptionPane.PLAIN_MESSAGE);
    
        int worldSize = Integer.parseInt(worldSizePicker);
    
        return (byte)worldSize;
    }
    
    private short setWorldPopulation(){
        String worldPopulationPicker = JOptionPane.showInputDialog(null,
            "Select world start population", "World population...", JOptionPane.PLAIN_MESSAGE);
    
        int worldPopulation = Integer.parseInt(worldPopulationPicker);
    
        return (short)worldPopulation;
    }
    
    public List<Organism> getInhabitants(){
    
        return this.inhabitants;
    }
    
    
    public void sortOrganisms(){
    
        Comparator<Organism> comparator = new Comparator<Organism>(){
        
            @Override
            public int compare(final Organism thisOrg, final Organism otherOrg){
                if(thisOrg.getActivity()<otherOrg.getActivity()) return 1;
                else if(thisOrg.getActivity()>otherOrg.getActivity()) return -1;
            else {
                if(thisOrg.getDateOfBirth()<otherOrg.getDateOfBirth()) return -1;
                else if(thisOrg.getDateOfBirth()>otherOrg.getDateOfBirth()) return 1;
                else return 0;
            }
        }
    };
        Collections.sort(inhabitants,comparator);
    }
    
    public void incTimeStamp(){
        this.timeStamp++;
    }
    
    public int getRoundCounter(){
        return this.roundCounter;
    }
    
    public void setRoundCounter(int numberOfRounds){
        this.roundCounter = numberOfRounds;
    }
    
    public void incRoundCounter(){
        this.roundCounter++;
    }
    
    public int getTimeStamp(){
    
        return this.timeStamp;
    }
    
    
    
    public void listWorld() {
    
       
    }
    
    public Board getBoard(){
        
        return this.gameBoard;
    }
    
    public byte getSize() {
        
        return this.size;
    }
    
    public int getPopulation(){
    
        return this.population;
    }
    
    public void setPopulation(short population){
        
        this.population = population;
    }
    
    public void incPopulation(){
        
        this.population++;
    }
    
    public void decPopulation(){
        
        this.population--;
    }
    
    public void listOrganisms(){
        
        int i = 0 ;
        for(Organism organism : inhabitants)
        {
            System.out.println(i);
            organism.print();
            i++;
            //targetField(organism.getLocation()).getLocation();
        }
        
    }    
    
    public int getWorldCapacity(){
    
        return this.getSize()*this.getSize();
    }
    
    public boolean isFieldOnBoard(Point field) // metchod checks if the field lays on the board
    {
        if((field.getX()<0)||(field.getX()>=this.getSize())||(field.getY()<0)||(field.getY()>=this.getSize()))
            return false;
        else 
            return true;
    }
    
    public boolean isFieldFree(Point field)
    {
        for(Organism organism: this.getInhabitants()){
        
            if(organism.getLocation().equals(field))
                return false;
        }        
    return true;
    }
    
    public boolean isFieldFreeOfAnimals(Point field){
    
        for(Organism organism: this.getInhabitants()){
        
            if((organism.getLocation().equals(field))&&(organism.getIsAnimal()==true))
                return false;
        }        
        return true;
    }
    
    
    public boolean lookForSpace(Point field){ // method checks if there is any free space around given "field"
                                              // field must be unoccupied and on the board
        for(byte i = 1 ; i <=8 ; i++ )
        {
            Point shiftTo = new Point(shiftTo(i));        
            Point target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
            if((isFieldFree(target))&&(isFieldOnBoard(target)))
                return true;
        }
    return false;
    }
    
    public boolean lookForSpaceForAnimal(Point field){ // method checks if there is any free space around given "field"
                                              // field must be unoccupied by any animal and on the board
        for(byte i = 1 ; i <=8 ; i++ )
        {
            Point shiftTo = new Point(shiftTo(i));        
            Point target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
            if((isFieldFreeOfAnimals(target))&&(isFieldOnBoard(target)))
                return true;
        }
    return false;
    }
    
    public Point findSpace(Point field){ //method returns some field around the one field given by argument that lays on the board;
        Random generator = new Random();
        int draw = generator.nextInt(8)+1;
        
        Point target = shiftTo((byte)draw);
        target = new Point(target.x+field.x,target.y+field.y);
        while(isFieldOnBoard(target)==false)
        {
            draw = generator.nextInt(8)+1;
            target = shiftTo((byte)draw);
            //target.x = target.x+field.x;
            //target.y = target.y+field.y;
            
            target = new Point(target.x+field.x,target.y+field.y);
        }               
        return target;
    }
    
    public Point shiftTo(byte field){
    
        switch(field){
        
            case 1:
                return new Point(-1,-1);
            case 2:
                return new Point(0,-1);
            case 3:
                return new Point(1,-1);    
            case 4:
                return new Point(-1,0);
            case 5:
                return new Point(1,0);
            case 6:
                return new Point(1,1);        
            case 7:
                return new Point(0,1);        
            case 8:
                return new Point(1,1);        
            default:
                return new Point (0,0);
        }
    }
    
    public Point targetField(Point field){ // method returns random unocuppied field around field given by argument
        
        Random generator = new Random();

        int shift = generator.nextInt(8)+1;        
        Point shiftTo = new Point(shiftTo((byte)shift));        
        Point target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
        while(isFieldFree(target)==false)
        {
            //System.out.println("Ooooops! Field ["+target.getX()+","+target.getY()+"] is occupied!");
            shift = generator.nextInt(8)+1;        
            shiftTo = new Point(shiftTo((byte)shift));        
            target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
        }
        
        //System.out.println("Drawn field: "+target.getLocation());
        return target;    
    }
    
    public Point targetFieldForAnimal(Point field){ // method returns random unocuppied field around field given by argument
        
        Random generator = new Random();

        int shift = generator.nextInt(8)+1;        
        Point shiftTo = new Point(shiftTo((byte)shift));        
        Point target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
        while(isFieldFreeOfAnimals(target)==false)
        {
            //System.out.println("Ooooops! Field ["+target.getX()+","+target.getY()+"] is occupied!");
            shift = generator.nextInt(8)+1;        
            shiftTo = new Point(shiftTo((byte)shift));        
            target = new Point((int)field.getX()+(int)shiftTo.getX(),(int)field.getY()+(int)shiftTo.getY());
        }
        //System.out.println("Drawn field: "+target.getLocation());
        return target;    
    }
    
}
