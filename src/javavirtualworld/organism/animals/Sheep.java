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
public class Sheep extends Animal implements Serializable {
        
    public Sheep(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(234,30,229));
    this.setStrength((byte)4);
    this.setActivity((byte)4);
    }
    
}
