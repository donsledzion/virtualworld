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
public class Wolf extends Animal implements Serializable{
        
    public Wolf(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(96,96,96));
    this.setStrength((byte)9);
    this.setActivity((byte)5);
    }
    
}
