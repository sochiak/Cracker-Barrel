package PegGame;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

import Main.UI;
import Main.PlayerCharacter;
/**
 * File name: PegGameModel.java
 * Project name: Five Nights at Cracker Barrel
 * Author: Hunter Jones
 * Date: Created: 3/1/22
 * Date: Modified: 4/24/22
 * Purpose: Logic for Peg Game
 *
 */
class PegGameModel {
    private PegGameView view;
    private boolean[]   pegsPresent = new boolean[15];
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Initializes the peg game model
     * Date created: 4/12/22
     */
    public PegGameModel(PegGameView v)
    {
        view = v;
        for (int i=0; i < pegsPresent.length; i++)
            pegsPresent[i] = true;
    }

    /**
     * Creates an interface so that the pegs will always be properly set
     * Date created: 4/12/22
     */
    interface PegGameView
    {
        void setPeg( int i, boolean state );
    }

    /**
     * Checks if the player has won yet
     * Date created: 4/21/22
     */
    public void checkWin()
    {
        String msg = "You win!!";                                           //sets the message for the dialog box
        int count = 0;                                                      //initializes a counter
        for (int j = 0; j < pegsPresent.length; j++)                        //searches through the entire array
        {
            if(pegsPresent[j] == false)                                     //if the peg is seg to false
            {
                count++;                                                    //increments the counter
            }
        }
        if(count == 14)                                                     //if the counter is equal 14, the player has won
        {
            PegGameSwing.winCondition = true;                               //sets the boolean winConditon to true
            JOptionPane.showMessageDialog(null, msg);        //dialog box telling the player they have won
            PlayerCharacter.wallet = PlayerCharacter.wallet + 5;            //pays the user for winning
            updateMoney();                                                  //updates the balance
            PegGameSwing.getWin();                                          //calls the getWin method
            //System.exit(0);
            //ends the minigame
        }
        if(count < 14)                                                      //if the count is less than 14
        {
            PegGameSwing.winCondition = false;                              //the player has not yet won
        }
    }

    /**
     * Updates the player characters wallet amount
     * Date created: 4/24/22
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
     * Removes pegs from the board
     * Date created: 4/12/22
     */
    public void removePeg( int i )
    {
        pegsPresent[i] = false; //removes the passed in integer from the pegsPresent array
    }

    /**
     * Attempts to move based off user input
     * Date created: 4/12/22
     */
    public void attemptMove(int from, int to)
    {
        from++;
        to++;
        for (int i=0; i < jumpTable.length; i++) {              //for every element of the jumpTable
            if (from == jumpTable[i][0] && to == jumpTable[i][1]  //if from and to are still in the table
                    && pegsPresent[jumpTable[i][0] - 1]       //if "from" position is filled
                    && pegsPresent[jumpTable[i][2] - 1]       //if "jumped" position is filled
                    && !pegsPresent[jumpTable[i][1] - 1])     //if "to" position is vacant
            {
                pegsPresent[jumpTable[i][0] - 1] = false;      //vacate the "from" position
                pegsPresent[jumpTable[i][2] - 1] = false;      //vacate the "jumped" position
                pegsPresent[jumpTable[i][1] - 1] = true;       //fill the "to" position
                view.setPeg(jumpTable[i][0] - 1, false);    //sets the from position to false
                view.setPeg(jumpTable[i][2] - 1, false);    //sets the jumped position to false
                view.setPeg(jumpTable[i][1] - 1, true);     //sets the to position to true
                checkWin();                                    //checks if the user won
                break;                                         //end for loop
            }
        }
    }

    /**
     * Table of all possible moves that can be made
     * Date created: 4/12/22
     */
    public static int[][] jumpTable =
            //{a,b,c}
            //a = "from" position
            //b = "to" position
            //c = "jumped" position
            {
                {1,4,2},{1,6,3},
                {2,7,4},{2,9,5},
                {3,8,5},{3,10,6},
                {4,6,5}, {4,1,2}, {4,11,7}, {4,13,8},
                {5,14,9},{5,12,8},
                {6,4,5}, {6,13,9}, {6,15,10}, {6,1,3},
                {7,2,4},{7,9,8},
                {8,3,5},{8,10,9},
                {9,2,5},{9,7,8},
                {10,8,9},{10,3,6},
                {11,13,12},{11,4,7},
                {12,5,8},{12,14,13},
                {13,11,12},{13,15,14}, {13,6,9}, {13,4,8},
                {14,12,13},{14,5,9},
                {15,13,14},{15,6,10}
            };
}