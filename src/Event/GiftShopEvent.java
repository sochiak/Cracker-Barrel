/**
 *   --------------------------------------------------------------
 *   File Name: GiftShopEvent.java
 *   Project Name: Final Semester Project
 *   --------------------------------------------------------------
 *   Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 *   Course: CSCI 1260
 *   Creation Date: April 6, 2022
 */

package Event;

import Main.*;

/**
 * Handles events for the gift shop
 *
 * Date Created: April 6, 2022
 *
 * @author Sophia Herrell
 */
public class GiftShopEvent
{
    GameManager gm;

    /**
     * Constructor for Event02
     *
     * Date Created: April 12, 2022
     *
     * @param gm
     */
    public GiftShopEvent(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Displays description of toy shelf for user.
     *
     * Date Created: April 12, 2022
     */
    public void inspectToyShelf()
    {
        gm.ui.messageText.setText("It's a shelf containing a wide assortment of toys. " +
                "You reach out to touch them and cut yourself on something sharp.");
        gm.player.decreaseEnergy(1);
    }

    /**
     * Adds toy to player inventory, displays confirmation message and toy icon, and detracts appropriate amount
     * from player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void selectToy()
    {
        if(PlayerCharacter.getWallet() >= gm.sword.getCost()) //player can afford sword
        {
            if(gm.player.checkInventory(gm.sword)) //player already has a sword
                gm.ui.messageText.setText("Sorry, that's one of a kind.");
            else
            {
                gm.ui.swordLabel.setVisible(true);
                gm.player.addItem(gm.sword);
                gm.ui.messageText.setText("You acquired a sword.");
                gm.player.loseMoney(gm.sword.getCost());
            } //end else
        } //end of
        else
        {
            gm.ui.messageText.setText("You're too poor. Come back when you've earned some money.");
        } //end else
        gm.player.decreaseEnergy(1);
    }

    /**
     * Removes toy from player inventory, displays confirmation message and hides toy icon, and adds
     * appropriate amount to player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void returnToy()
    {
        if(gm.player.checkInventory(gm.sword))
        {
            gm.ui.swordLabel.setVisible(false);
            gm.player.removeItem(gm.sword);
            gm.ui.messageText.setText("Sword returned.");
            gm.player.earnMoney(gm.sword.getCost());
        }
        else
        {
            gm.ui.messageText.setText("Oh dear, it seems you don't have a sword to return. Are you trying to swindle me?");
        }

        gm.player.decreaseEnergy(1);
    }

    /**
     * Displays description of food shelf for user.
     *
     * Date Created: April 12, 2022
     */
    public void inspectFoodShelf()
    {
        gm.ui.messageText.setText("A shelf of food.");
        gm.player.decreaseEnergy(1);
    }

    /**
     * Adds food to player inventory, displays confirmation message and toy icon, and detracts appropriate amount
     * from player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void selectFood()
    {
        if(gm.player.checkInventory(gm.cookie)) //player already has a sword
            gm.ui.messageText.setText("You can have another cookie when you finish your first one.");
        else
        {
            if(PlayerCharacter.getWallet() >= gm.cookie.getCost()) //player can afford cookie
            {
                gm.ui.cookieLabel.setVisible(true);
                gm.player.addItem(gm.cookie);
                gm.ui.messageText.setText("You purchased a cookie... could I have a bite?");
                gm.player.loseMoney(gm.cookie.getCost());
            } //end of
            else
            {
                gm.ui.messageText.setText("You're too poor. Come back when you've earned some money.");
            } //end else
        }
        gm.player.decreaseEnergy(1);
    }

    /**
     * Removes food from player inventory, displays confirmation message and hides toy icon, and adds
     * appropriate amount to player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void returnFood()
    {
        if(gm.player.checkInventory(gm.cookie))
        {
            gm.ui.cookieLabel.setVisible(false);
            gm.player.removeItem(gm.cookie);
            gm.ui.messageText.setText("Cookie returned.");
            gm.player.earnMoney(gm.cookie.getCost());
        }
        else
        {
            gm.ui.messageText.setText("Erm, you don't have any cookies.");
        }
        gm.player.decreaseEnergy(1);
    }

    /**
     * Displays description of candy shelf for user.
     *
     * Date Created: April 12, 2022
     */
    public void inspectCandyShelf()
    {
        gm.ui.messageText.setText("A shelf of candy.");
        gm.player.decreaseEnergy(1);
    }

    /**
     * Adds candy to player inventory, displays confirmation message and icon, and detracts appropriate amount
     * from player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void selectCandy()
    {
        if(gm.player.checkInventory(gm.candy)) //player already has a sword
            gm.ui.messageText.setText("Didn't you just buy some candy?\n\n...\n\nYou could consider dieting.");
        else
        {
            if(PlayerCharacter.getWallet() >= gm.candy.getCost()) //player can afford cookie
            {
                gm.ui.candyLabel.setVisible(true);
                gm.player.addItem(gm.candy);
                gm.ui.messageText.setText("Candy, heh? Looks tasty. Enjoy.");
                gm.player.loseMoney(gm.candy.getCost());
            } //end of
            else
            {
                gm.ui.messageText.setText("You're too poor. Come back when you've earned some money.");
            } //end else
        }
        gm.player.decreaseEnergy(1);
    }

    /**
     * Removes candy from player inventory, displays confirmation message, hides candy icon, and adds
     * appropriate amount to player wallet.
     *
     * Date Created: April 12, 2022
     */
    public void returnCandy()
    {
        if(gm.player.checkInventory(gm.candy))
        {
            gm.ui.candyLabel.setVisible(false);
            gm.player.removeItem(gm.candy);
            gm.ui.messageText.setText("Candy returned.");
            gm.player.earnMoney(gm.candy.getCost());
        }
        else
        {
            gm.ui.messageText.setText("You want to return your candy? What candy?\n\n...\n" +
                    "\nDo you see the candy in the room with you right now?");
        }
        gm.player.decreaseEnergy(1);
    }
}
