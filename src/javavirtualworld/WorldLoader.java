/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javavirtualworld.theworld.TheWorld;
import javavirtualworld.organism.Organism;

/**
 *
 * @author Lenovo
 */
public class WorldLoader {
 
    
    public WorldLoader(){
        
    }
    
    
    public TheWorld loadGame(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("myWorld.bin"))){
            TheWorld tmpWorld = (TheWorld)inputStream.readObject();
            for(Organism o : tmpWorld.getInhabitants())
            {
                o.setWorld(tmpWorld);
                System.out.println("Loaded organism:  "+o.getName()+" of type "+o.getClass());
            }
            System.out.println("Loaded world size =  "+tmpWorld.getSize());
            //outputStream.writeObject(this.thisWorld);
            return tmpWorld;
        }
        catch(Exception e){
            System.out.println("Faild to write stream "+e);
        }
        return new TheWorld();
    }
}
