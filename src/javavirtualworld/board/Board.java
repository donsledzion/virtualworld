/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavirtualworld.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javavirtualworld.theworld.TheWorld;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.*;
    /**
     *
     * @author AChojaczyk
     */
public class Board extends JPanel implements ActionListener,Serializable{
    
    private TheWorld thisWorld;
    private JButton newRoundButton;
    private JButton saveGameButton;
    private JButton loadGameButton;
    protected JFrame frame;
    private short boxSize;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    
    private JPanel addOrganismPanel;
    
    private short  boardFieldSize;
    private int  windowWidth;
    private int  windowHeight;
    private JTextArea liveComments;
    private JTextArea credits;
    private short textLines;
    
    private JButton BearButton;
    private JButton SheepButton;
    private JButton WolfButton;
    private JButton SlothButton;
    private JButton ViperButton;
    
    private JButton DandelionButton;
    private JButton CocaButton;
    private JButton GrassButton;
    
    
    private DrawBoxes arena;
    
    private int newcomerX;
    private int newcomerY;
    
    
    
   
    
    public Board(TheWorld thisWorld){
    
        this.thisWorld = thisWorld;
        byte worldSize = thisWorld.getSize();        
        this.frame = new JFrame();
        this.frame.setLayout(new BorderLayout());
        
        this.topPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
                
        this.topPanel.setLayout(new FlowLayout());
        
        this.frame.add(topPanel,BorderLayout.NORTH);
        this.frame.add(bottomPanel,BorderLayout.SOUTH);
        this.frame.add(leftPanel,BorderLayout.WEST);
        this.frame.add(rightPanel,BorderLayout.EAST);
        
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setExtendedState(this.frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
        this.boxSize = setBoxSize();
        this.boardFieldSize = (short)(boxSize*this.thisWorld.getSize());
        
        this.leftPanel.setLayout(new GridLayout());
        
        this.leftPanel.setPreferredSize(new Dimension((int)((windowWidth-boardFieldSize)/2.2),200));
        this.rightPanel.setPreferredSize(new Dimension((int)((windowWidth-boardFieldSize)/2.2),200));
        
        this.liveComments = new JTextArea("World's actions:\n");
        this.credits = new JTextArea("Virtual World by Adam Chojaczyk - 183269");
        
        this.textLines = 1;
        
        this.arena = new DrawBoxes(this.thisWorld,this.boxSize);
        
        this.BearButton = new JButton("Bear");        
        this.SheepButton = new JButton("Sheep");
        this.WolfButton = new JButton("Wolf");        
        this.SlothButton = new JButton("Sloth");
        this.ViperButton = new JButton("Viper");
    
        this.DandelionButton = new JButton("Dandelion");
        this.CocaButton = new JButton("Coca");
        this.GrassButton = new JButton ("Grass");
        
        this.newcomerX = -1;
        this.newcomerY = -1;
        this.addOrganismPanel = addOrganismPanel(new Point(newcomerX,newcomerY));
        
    }
    
    
    public void saveGame(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("myWorld.bin"))){
            outputStream.writeObject(this.thisWorld);
        }
        catch(Exception e){
            System.out.println("Faild to write stream");}
        }
    
    public TheWorld loadGame(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("myWorld.bin"))){
            TheWorld tmpWorld = (TheWorld)inputStream.readObject();
            System.out.println("Loaded world size =  "+tmpWorld.getSize());
            //outputStream.writeObject(this.thisWorld);
            return tmpWorld;
        }
        catch(Exception e){
            System.out.println("Faild to write stream "+e);
        }
        return new TheWorld();
    }
    
    
    protected void removeOrganismPanel(){
        this.leftPanel.remove(addOrganismPanel);
        this.leftPanel.revalidate();
        this.leftPanel.repaint();
        
    }
    
    private void incTextLines(){
        this.textLines++;
    }
    
    private short getTextLines(){
        return this.textLines;
    }
    
    private void setTextLines(short numberOfLines){
        this. textLines = numberOfLines;
    }
    
    public void showBoard() {
        System.out.println("Drawing board!");
        this.newRoundButton = new JButton("New round!");
        this.saveGameButton = new JButton("Save game!");
        this.loadGameButton = new JButton("Load game!");

        this.newRoundButton.addActionListener(this);
        this.saveGameButton.addActionListener(this);
        this.loadGameButton.addActionListener(this);
        
        this.BearButton.addActionListener(this);
        this.WolfButton.addActionListener(this);
        this.SheepButton.addActionListener(this);
        this.SlothButton.addActionListener(this);
        this.ViperButton.addActionListener(this);
        this.GrassButton.addActionListener(this);
        this.CocaButton.addActionListener(this);
        this.DandelionButton.addActionListener(this);

        this.topPanel.add(newRoundButton);
        this.topPanel.add(saveGameButton);
        this.topPanel.add(loadGameButton);
        
        this.centerPanel = new DrawBoxes(this.thisWorld,this.boxSize);
        this.frame.add(centerPanel,BorderLayout.CENTER);
        
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
                
        liveComments.setBounds(this.thisWorld.getSize(), this.thisWorld.getSize(), this.thisWorld.getSize()/2, this.thisWorld.getSize()/2);
        liveComments.setBackground(Color.ORANGE);
        Font commentsFont = new Font("Times New Roman", Font.BOLD,10);
        liveComments.setFont(commentsFont);
        this.rightPanel.add(liveComments);
        
        
        credits.setBounds(this.thisWorld.getSize(), this.thisWorld.getSize(), this.thisWorld.getSize()/2, this.thisWorld.getSize()/2);
        credits.setBackground(Color.ORANGE);
        this.leftPanel.add(credits);
                
        this.frame.revalidate();
        this.frame.repaint();        
    }    
    
    public void addComment(byte type, byte rank, String message){
        this.incTextLines();
        this.liveComments.append("==============================\n");
        this.incTextLines();
        this.liveComments.append(message+"\n");
        if(this.getTextLines()>this.windowHeight/(2*10))
        {
            this.liveComments.setText("World's actions:\n");
            this.setTextLines((short)1);
        }
    }
    
    public void setCredits(int round,int population){
        this.credits.setText("Virtual World by Adam Chojaczyk - 183269\n");
        this.credits.append("\n=============================");
        this.credits.append("\nROUND : "+ round);
        this.credits.append("\nPOPULATION : "+ population);
        this.credits.append("\n=============================\n");
        this.leftPanel.add(credits);        
    }
    
    private short setBoxSize(){
        Rectangle r = this.frame.getBounds();
        int h = r.height;
        int w = r.width;
        this.windowHeight = h;
        this.windowWidth = w;
        System.out.println("Windows dimensions:\nheight: "+h+"\nwidth: "+w);
        return (short)(h/(this.thisWorld.getSize()*1.1));
    }
    
    protected int getBoxSize(){
        return this.boxSize;
    }
    
    protected JPanel getCenterPanel(){
        return this.centerPanel;
    }
    
    protected JPanel getLeftPanel(){
        return this.leftPanel;
    }
    
    protected JPanel getAddOrganismPanel(){
        return this.addOrganismPanel;
    }
    
    protected void redrawBoxes(){
            this.centerPanel = new DrawBoxes(this.thisWorld,this.boxSize);
            this.frame.add(centerPanel,BorderLayout.CENTER);
            this.centerPanel.revalidate();
            this.centerPanel.repaint();
    }
    
    protected void resetLeftPanel(){
        this.addOrganismPanel=addOrganismPanel(new Point(newcomerX,newcomerY));
        this.leftPanel = new JPanel();
        this.frame.add(leftPanel,BorderLayout.WEST);
        this.leftPanel.setLayout(new GridLayout());
        this.leftPanel.setPreferredSize(new Dimension((int)((windowWidth-boardFieldSize)/2.2),200));
        this.leftPanel.add(credits);
        this.leftPanel.revalidate();
        this.leftPanel.repaint();        
    }
    
    protected JPanel addOrganismPanel(Point destination){
        JPanel panel = new JPanel();
        
        this.newcomerX = (int)destination.getX();
        this.newcomerY = (int)destination.getY();
        
        panel.add(this.BearButton);
        panel.add(this.WolfButton);
        panel.add(this.SheepButton);
        panel.add(this.SlothButton);
        panel.add(this.ViperButton);
        
        panel.add(this.DandelionButton);
        panel.add(this.CocaButton);
        panel.add(this.GrassButton);
        
        return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        Object source = e.getSource();
        
        if(source == newRoundButton)
        {
            
            this.thisWorld.sortOrganisms();
            this.thisWorld.takeTurn();
            
            this.redrawBoxes();
            
            this.rightPanel.revalidate();
            this.rightPanel.repaint();
            
            this.leftPanel.revalidate();
            this.leftPanel.repaint();                        
        }
        else if(source == saveGameButton)
        {
            this.thisWorld.sortOrganisms();
            this.thisWorld.listOrganisms();
            this.saveGame();
        }
        else if(source == loadGameButton)
        {
            System.out.println("Button clicked!");
            
            
            
            //this.thisWorld.sortOrganisms();
            //this.thisWorld.lookForColisions();
          
        }
        else if(source == BearButton)
        {
            System.out.print("New Bear!");
            this.thisWorld.addOrganism("Bear", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == WolfButton)
        {
            System.out.print("New Wolf!");
            this.thisWorld.addOrganism("Wolf", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == SheepButton)
        {
            System.out.print("New Sheep!");
            this.thisWorld.addOrganism("Sheep", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == SlothButton)
        {
            System.out.print("New Sloth!");
            this.thisWorld.addOrganism("Sloth", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == ViperButton)
        {
            System.out.print("New Viper!");
            this.thisWorld.addOrganism("Viper", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == GrassButton)
        {
            System.out.print("New Grass!");
            this.thisWorld.addOrganism("Grass", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == CocaButton)
        {
            System.out.print("New Coca!");
            this.thisWorld.addOrganism("Coca", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        else if(source == DandelionButton)
        {
            System.out.print("New Dandelion!");
            this.thisWorld.addOrganism("Dandelion", new Point(this.newcomerX,this.newcomerY));
            this.thisWorld.sortOrganisms();            
            this.resetLeftPanel();
            this.redrawBoxes();
        }
        
        
        else
        {
            this.revalidate();
            this.repaint();
        }       
    }
}
    

    
    
