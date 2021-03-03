/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld;

import java.awt.EventQueue;
import javavirtualworld.WelcomeDialog;
import javavirtualworld.board.Board;
import javavirtualworld.theworld.TheWorld;
import javax.swing.JOptionPane;


/**
 *
 * @author AChojaczyk
 */
public class JavaVirtualWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
                    public void run(){
                        
                        
               String[] options = {"NEW GAME", "LOAD GAME"};
        //Integer[] options = {1, 3, 5, 7, 9, 11};
        //Double[] options = {3.141, 1.618};
        //Character[] options = {'a', 'b', 'c', 'd'};
        int x = JOptionPane.showOptionDialog(null, "Select starting option",
                "Welcome to the Unreal World",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                System.out.println(x);
                if(x==0)
                {
                    TheWorld newWorld = new TheWorld();
                    newWorld.getBoard().showBoard();
                }
                else
                {     
                    WorldLoader load = new WorldLoader();
                    TheWorld newWorld = load.loadGame();
                    //newWorld.setInhabitants(newWorld.getInhabitants());
                    newWorld.setGameBoard(newWorld);
                    
                    newWorld.getBoard().showBoard();
                }
                    
                
            }
    });
                
    }
    
}
