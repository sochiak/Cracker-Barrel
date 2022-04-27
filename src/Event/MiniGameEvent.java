/**
 *   --------------------------------------------------------------
 *   File Name: MiniGameEvent.java
 *   Project Name: Final Semester Project
 *   --------------------------------------------------------------
 *   Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 *   Course: CSCI 1260
 *   Creation Date: April 6, 2022
 */

package Event;

import Main.GameManager;
import javax.swing.*;
import Memory.*;
import TicTacToe.*;
import Checkers.*;

/**
 * Handles events for the mini-game arena
 *
 * Date Created: April 6, 2022
 *
 * @author Sophia Herrell
 */
public class MiniGameEvent
{
    GameManager gm;

    /**
     * Constructor for Event01
     *
     * Date Created: April 6, 2022
     *
     */
    public MiniGameEvent(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Invokes MemoryGame
     *
     * Date Created: April 6, 2022
     */
    public void playMemoryGm() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        gm.ui.messageText.setText("Testing memory...");
        //The default User Interface look and feel on OS X does not allow you to change the background color of
        //buttons since OS X buttons in native applications generally have a white background. This makes it look
        //the way it would look on a Windows PC
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeLater(
                MemoryGame::new
        );
        gm.player.decreaseEnergy(7);
    }

    /**
     * Invokes TicTacToe
     *
     * Date Created: April 6, 2022
     */
    public void playTicTacToe()
    {
        gm.ui.messageText.setText("Playing tic tac toe...");
        SwingUtilities.invokeLater
        (
            () -> new TicTacToeWindow()
        );
        gm.player.decreaseEnergy(5);
    }

    /**
     * Invokes Checkers
     *
     * Date Created: April 6, 2022
     */
    public void playCheckers() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeLater
                (
                        () -> new Checkers()
                );
        gm.player.decreaseEnergy(10);
    }

} //end class