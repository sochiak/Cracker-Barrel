/**
 * --------------------------------------------------------------
 * File Name: KitchenEvent.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 20, 2022
 */

package Event;

import Main.GameManager;

/**
 * Handles events for the kitchen
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */
public class KitchenEvent
{
    GameManager gm;

    /**
     * Constructor for KitchenEvent
     *
     * Date Created: April 20, 2022
     *
     * @param gm
     */
    public KitchenEvent(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Decreases player energy and increase player money for washing dishes.
     *
     * Date Created: April 20, 2022
     */
    public void washDishes()
    {
        gm.ui.messageText.setText("You hear a gruff voice behind you... 'Hey, thanks for pitching in, kid. " +
                "Have a dollar.'");
        gm.player.decreaseEnergy(7);
        gm.player.earnMoney(1);
    }
}