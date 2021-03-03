/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.plants;

import java.awt.Color;
import java.awt.Point;
import javavirtualworld.organism.Organism;
import javavirtualworld.organism.plants.Plant;
import javavirtualworld.theworld.TheWorld;
import java.io.Serializable;
/**
 *
* @author AChojaczyk
 */
public class Coca extends Plant implements Serializable{
    
    public Coca(String name, Point location, TheWorld thisWorld){
        super(name, location,thisWorld);;
        this.setColor(new Color(0,102,0));
        this.setChanceToAct((byte)25);
    }
    
    public void reAction(Organism offender){
        System.out.println(offender.getName()+" eats Coca and get's an extra move!");
        offender.setBonusMove(true);
    }
    
}