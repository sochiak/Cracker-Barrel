package TicTacToe;

/**
 * File name: TicTacToe.java
 * Project name: Five Nights at Cracker Barrel
 * Author: Charlie Shahan
 * Date: Created: 3/1/22
 * Date: Modified: 4/12/22
 * Purpose: Logic for TicTacToe game
 * 
 */

import javax.swing.*;

import Main.PlayerCharacter;
import Main.UI;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Random;

public class TicTacToe
{
    private static boolean [] spot = new boolean[9]; //spots on the board
    private static int turnCount = 0; //counter for turns
    private static boolean run = true; //true if the game is running
    private static boolean win = false; //true if the game is in a won state
    private static boolean lose = false; //true is the game is in a loss state
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance(); ///Used for UI display 
 
    /**
     * Return if the game is running
     * Date created: 4/12/22
     */

    public static boolean getRun()
    {
        return run;
    }

    /**
     * Return true if the user wins
     * Date created 4/12/22
     */
    public static boolean getWin()
    {
        return win;
    }

    /**
     * Return true if the user loses
     * Date created 4/12/22
     */
    public static boolean getLose()
    {
        return lose;
    }


    /**
     * Oppent finds a spot on the board to place a O
     * Date created: 3/1/22
     * Date modified: 3/7/22
     */
    public static int opponentMove()
    {
        Random rand = new Random(); //New random object
        int randomInt = rand.nextInt(9); //Get random int 0-8
        while (spot[randomInt] == true && turnCount < 4) //If spot is already filled and if less than 4 turns
        {
            randomInt = rand.nextInt(9); //move to a different spot
        }
        turnCount++; //increment turn counter
        return randomInt; 
    }

    /**
     * Oppent places a O
     * Date created: 3/1/22
     * Date modified: 3/7/22
     */
    public static void opponentMove(JButton button)
    {
        if(button.getText().equals("-")) //If blank
        {
            button.setText("O"); //Place 0
        }
    }

    /**
     * Grid spot gets filled with an x or o 
     * Date created 4/9/2022
     */
    public static void fill(int i)
    {
        spot[i] = true; //Spot gets filled
    }


    /**
     * Gui player changes a button to an x 
     * Date created: 3/1/22
     * Date modified: 3/7/22
     */
    public static void playerMove(JButton button)
    {
        if(button.getText().equals("-")) //If blank
        {
            button.setText("X"); //place X
        }
    }

    /**
     * Reset the game
     * Date created: 4/12/22
     * Date modified: 4/12/22
     */
    public static void resetGame(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JButton b7, JButton b8, JButton b9)
    {
            resetButtons(b1, b2, b3, b4, b5, b6, b7, b8, b9);
            for(int i = 0; i < spot.length; i++) //Empty the spots on the board
            {
                spot[i] = false;
            }
            turnCount = 0; //Set number of turns back to 0
            run = true; //default
            win = false; //default
            lose = false; //default
    }

    /**
     * Reset the game buttons
     * Date created: 4/19/22
     * Date modified: 4/19/22
     */
    private static void resetButtons(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JButton b7, JButton b8, JButton b9)
    {
        b1.setText("-");
        b2.setText("-");
        b3.setText("-");
        b4.setText("-");
        b5.setText("-");
        b6.setText("-");
        b7.setText("-");
        b8.setText("-");
        b9.setText("-");
    }


    /**
     * Check if 3 buttons are X's
     * Date Created: 4/9/22
     * Date Modified: 4/15/22
     */
    public static void checkWin(JButton buttonTL, JButton buttonTM, JButton buttonTR, JButton buttonML, JButton buttonMM, JButton buttonMR, JButton buttonBL, JButton buttonBM, JButton buttonBR)
    {
        String msg = "You win";
        if(buttonTL.getText().equals("X") && buttonTM.getText().equals("X") && buttonTR.getText().equals("X")) //1st Row
        {
            JOptionPane.showMessageDialog(null, msg); //Display win message
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1; //update player balance
            updateMoney();
        }
        else if(buttonTL.getText().equals("X") && buttonML.getText().equals("X") && buttonBL.getText().equals("X")) //1st Column
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonML.getText().equals("X") && buttonMM.getText().equals("X") && buttonMR.getText().equals("X")) //2nd Row
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonTM.getText().equals("X") && buttonMM.getText().equals("X") && buttonBM.getText().equals("X")) //2nd Column
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonBL.getText().equals("X") && buttonBM.getText().equals("X") && buttonBR.getText().equals("X")) //3rd Row
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonTR.getText().equals("X") && buttonMR.getText().equals("X") && buttonBR.getText().equals("X")) //3rd Column
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonTL.getText().equals("X") && buttonMM.getText().equals("X") && buttonBR.getText().equals("X")) //1st Diagonal
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
        else if(buttonBL.getText().equals("X") && buttonMM.getText().equals("X") && buttonTR.getText().equals("X")) //2nd Diagonal
        {
            JOptionPane.showMessageDialog(null, msg);
            win = true;
            run = false;
            PlayerCharacter.wallet = PlayerCharacter.wallet + 1;
            updateMoney();
        }
    }

    /**
     * Updates balances
     * Date Created: 4/19/22
     */
    public static void updateMoney()
    {
        UI.displayMoney.setText(fmt.format(PlayerCharacter.getWallet()));
        UI.displayDebt.setText(fmt.format(PlayerCharacter.getDebt()));

        if(PlayerCharacter.getDebt() > 0)
        {
            if(PlayerCharacter.getDebt() <= PlayerCharacter.getWallet())
                UI.displayDebt.setForeground(Color.green);
            else if(PlayerCharacter.getDebt() > PlayerCharacter.getWallet())
                UI.displayDebt.setForeground(Color.red);
            else
                UI.displayDebt.setForeground(Color.yellow);
        }
    }


    /**
     * Check if 3 buttons are O's
     * Date Created: 4/9/22
     */
    public static void checkLose(JButton buttonTL, JButton buttonTM, JButton buttonTR, JButton buttonML, JButton buttonMM, JButton buttonMR, JButton buttonBL, JButton buttonBM, JButton buttonBR)
    {
        String msg = "You Lose";
        if(buttonTL.getText().equals("O") && buttonTM.getText().equals("O") && buttonTR.getText().equals("O")) //1st Row
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false; //Stop the game
            lose = true; //Set lose to true
        }
        else if(buttonTL.getText().equals("O") && buttonML.getText().equals("O") && buttonBL.getText().equals("O")) //1st Column
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonML.getText().equals("O") && buttonMM.getText().equals("O") && buttonMR.getText().equals("O")) //2nd Row
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonTM.getText().equals("O") && buttonMM.getText().equals("O") && buttonBM.getText().equals("O")) //2nd Column
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonBL.getText().equals("O") && buttonBM.getText().equals("O") && buttonBR.getText().equals("O")) //3rd Row
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonTR.getText().equals("O") && buttonMR.getText().equals("O") && buttonBR.getText().equals("O")) //3rd Column
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonTL.getText().equals("O") && buttonMM.getText().equals("O") && buttonBR.getText().equals("O")) //1st Diagonal
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(buttonBL.getText().equals("O") && buttonMM.getText().equals("O") && buttonTR.getText().equals("O")) //2nd Diagonal
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
        else if(turnCount == 5) //If no more moves
        {
            JOptionPane.showMessageDialog(null, msg);
            run = false;
            lose = true;
        }
    }
}