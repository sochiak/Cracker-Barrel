package TicTacToe;

/**
 * File Name: TicTacToeWindow.java
 * Project Name: CrackerBarrel.java
 * Author: Charlie Shahan
 * Date: Created: 3/7/2022
 * Date: modified 4/12/2022
 * Purpose: Gui display for TicTacToe Game
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeWindow extends JFrame
{
    //Gui Attributes
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 300;

    private JPanel gamePanel;
    private JButton buttonTL,
                    buttonTM,
                    buttonTR,
                    buttonML,
                    buttonMM,
                    buttonMR,
                    buttonBL,
                    buttonBM,
                    buttonBR;

    public TicTacToeWindow()
    {
        super("TicTacToe"); //Set Title

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //Set Size

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Set Close Operation

        gamePanel = new JPanel(); //Create new game panel
        gamePanel.setLayout(new GridLayout(3,3)); //Set gridlayout 

        //Create Buttons
        buttonTL = new JButton("-");
        buttonTM = new JButton("-");
        buttonTR = new JButton("-");
        buttonML = new JButton("-");
        buttonMM = new JButton("-");
        buttonMR = new JButton("-");
        buttonBL = new JButton("-");
        buttonBM = new JButton("-");
        buttonBR = new JButton("-");

        //Add buttons to panel
        gamePanel.add(buttonTL);
        gamePanel.add(buttonTM);
        gamePanel.add(buttonTR);
        gamePanel.add(buttonML);
        gamePanel.add(buttonMM);
        gamePanel.add(buttonMR);
        gamePanel.add(buttonBL);
        gamePanel.add(buttonBM);
        gamePanel.add(buttonBR);

        //Add action Listener to button
        buttonTL.addActionListener(new Playerclicked());
        buttonTM.addActionListener(new Playerclicked());
        buttonTR.addActionListener(new Playerclicked());
        buttonML.addActionListener(new Playerclicked());
        buttonMM.addActionListener(new Playerclicked());
        buttonMR.addActionListener(new Playerclicked());
        buttonBL.addActionListener(new Playerclicked());
        buttonBM.addActionListener(new Playerclicked());
        buttonBR.addActionListener(new Playerclicked());

        add(gamePanel); //add gamePanel to Gui interface

        setLocationRelativeTo(null); //Center on screen

        setVisible(true); //show window

    }


    /**
     * Class handles button clicked event for all buttons
     */
    private class Playerclicked implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {   
                //check what button got pressed
                if(e.getSource() == buttonTL)
                {
                    TicTacToe.playerMove(buttonTL); //fill spot on window with x 
                    TicTacToe.fill(0); //mark a spot got filled
                }
                else if(e.getSource() == buttonTM)
                {
                    TicTacToe.playerMove(buttonTM);
                    TicTacToe.fill(1);
                }
                else if(e.getSource() == buttonTR)
                {
                    TicTacToe.playerMove(buttonTR);
                    TicTacToe.fill(2);
                }
                else if(e.getSource() == buttonML)
                {
                    TicTacToe.playerMove(buttonML);
                    TicTacToe.fill(3);
                }
                else if(e.getSource() == buttonMM)
                {
                    TicTacToe.playerMove(buttonMM);
                    TicTacToe.fill(4);
                }
                else if(e.getSource() == buttonMR)
                {
                    TicTacToe.playerMove(buttonMR);
                    TicTacToe.fill(5);
                }
                else if(e.getSource() == buttonBL)
                {
                    TicTacToe.playerMove(buttonBL);
                    TicTacToe.fill(6);
                }
                else if(e.getSource() == buttonBM)
                {
                    TicTacToe.playerMove(buttonBM);
                    TicTacToe.fill(7);
                }
                else if(e.getSource() == buttonBR)
                {
                    TicTacToe.playerMove(buttonBR);
                    TicTacToe.fill(8);
                }

                //Check if the player won
                TicTacToe.checkWin(buttonTL, buttonTM, buttonTR, buttonML, buttonMM, buttonMR, buttonBL, buttonBM, buttonBR); 

                //Make the opponent move            
                int opponentMove = TicTacToe.opponentMove();

                //The opponent moves
                switch(opponentMove)
                {
                    case 0:
                        TicTacToe.opponentMove(buttonTL); //Fill spot with a 0
                        TicTacToe.fill(0); //mark spot as filled 
                        break;
                    case 1: 
                        TicTacToe.opponentMove(buttonTM);      
                        TicTacToe.fill(1);           
                        break;
                    case 2:
                        TicTacToe.opponentMove(buttonTR);     
                        TicTacToe.fill(2);              
                        break;
                    case 3:
                        TicTacToe.opponentMove(buttonML);  
                        TicTacToe.fill(3);
                        break;                 
                    case 4:
                        TicTacToe.opponentMove(buttonMM);   
                        TicTacToe.fill(4);                
                        break;
                    case 5: 
                        TicTacToe.opponentMove(buttonMR);   
                        TicTacToe.fill(5);                
                        break;
                    case 6:
                        TicTacToe.opponentMove(buttonBL); 
                        TicTacToe.fill(6);                  
                        break;
                    case 7:
                        TicTacToe.opponentMove(buttonBM);   
                        TicTacToe.fill(7);               
                        break;
                    case 8:
                        TicTacToe.opponentMove(buttonBR);
                        TicTacToe.fill(8);
                        break;      
                }

                //Check if the player loses
                if(TicTacToe.getWin() != true)
                    TicTacToe.checkLose(buttonTL, buttonTM, buttonTR, buttonML, buttonMM, buttonMR, buttonBL, buttonBM, buttonBR); 
                
                    
                
                if(TicTacToe.getWin() || TicTacToe.getLose() == true)
                {
                    TicTacToe.resetGame(buttonTL, buttonTM, buttonTR, buttonML, buttonMM, buttonMR, buttonBL, buttonBM, buttonBR);
                }
        }
    }

}
