/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.plants.grass;
import  javavirtualworld.theworld.TheWorld;
import  javavirtualworld.organism.plants.Plant;
import  java.awt.Point;
import  java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author AChojaczyks
 */
public class Grass extends Plant implements Serializable{
    
    public Grass(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(0,204,0));
    this.setChanceToAct((byte)25);
    }
    
}
