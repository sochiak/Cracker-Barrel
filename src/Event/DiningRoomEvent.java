/**
 * --------------------------------------------------------------
 * File Name: DiningRoomEvent.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 16, 2022
 */
package Event;

import Main.GameManager;
import Main.InventoryPopUp;
import PegGame.PegGameSwing;
import javax.swing.*;
import java.util.Random;

/**
 * Class that handles events in the dining room.
 *
 * Date Created: April 16, 2022
 *
 * @author Sophia Herrell and Hunter Jones
 */
public class DiningRoomEvent
{
    GameManager gm;

    /**
     * Constructor for DiningRoomEvent
     *
     * Date Created: April 16, 2022
     *
     * @param gm
     */
    public DiningRoomEvent(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Invokes PegGame
     *
     * Date Created: April 16, 2022;
     */
    public void playPegGame()
    {
        SwingUtilities.invokeLater
        (
                PegGameSwing::new
        );
    }

    /**
     * Hunter Jones
     * Allows player to interact with NPC
     *
     * Date Created: April 26, 2022
     */
    public void converse()
    {
        String[] responses = new String[4];
        responses[0] = "Hmmmmm, some of Momma's pancakes sound delicious right about now!";
        responses[1] = "Have you played this peg game? I can't figure out how to beat it!!";
        responses[2] = "Did you see that odd door in the other room? I wonder where it leads...";
        responses[3] = "Back in my day, we had to walk up hill in the snow to school... both ways!!";
        Random rand = new Random();
        int i = rand.nextInt(4);
        gm.ui.messageText.setText(responses[i]);
    }

    /**
     * Allows player to eat snack from inventory and gain energy.
     *
     * Date Created: April 16, 2022
     */
    public void consumeInventory()
    {
        SwingUtilities.invokeLater
                (
                        () -> new InventoryPopUp(gm)
                );
        gm.player.decreaseEnergy(1);
    }
}
