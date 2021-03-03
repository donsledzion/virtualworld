/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.animals;

import  javavirtualworld.theworld.TheWorld;
import java.awt.Color;
import java.awt.Point;
import javavirtualworld.organism.Organism;
import java.io.Serializable;
/**
 *
 * @author AChojaczyk
 */
public class Viper extends Animal implements Serializable{
        
    public Viper(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(198,47,14));
    this.setStrength((byte)2);
    this.setActivity((byte)3);
    }
    
    public void reAction(Organism offender){
        System.out.println(offender.getName()+" defeted Viper but got poisoned and dies in pain!");
        this.getWorld().eatOrganism(offender);
    }
    
}
