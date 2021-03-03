/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.organism.plants;

import  javavirtualworld.theworld.TheWorld;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author AChojaczyk
 */
public class Dandelion extends Plant implements Serializable{
    
    public Dandelion(String name, Point location, TheWorld thisWorld){
    super(name, location, thisWorld);
    this.setColor(new Color(255,255,0));
    this.setDefaultActions((byte)3);
    this.setChanceToAct((byte)15);
    }
}
