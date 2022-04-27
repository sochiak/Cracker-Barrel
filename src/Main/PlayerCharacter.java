/**
 * --------------------------------------------------------------
 * File Name: PlayerCharacter.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 12, 2022
 */

package Main;

import java.awt.*;
import java.util.ArrayList;
import java.text.NumberFormat;

/**
 * Class that represents the player character
 *
 * Date Created: April 12, 2022
 *
 * @author Sophia Herrell
 */
public class PlayerCharacter
{
    GameManager gm;
    public int maxEnergy;
    public int energy;
    public ArrayList<Item> inventory = new ArrayList<>();
    public Location location;
    public static double wallet;
    public static double debt;

    private static NumberFormat fmt = NumberFormat.getCurrencyInstance();

    public enum Location
    {
        Outside,
        GiftShop,
        Register,
        MiniGames,
        DiningRoom,
        Kitchen
    }

    /**
     * Constructor for player character
     *
     * Date Created: April 12, 2022
     *
     * @param gm
     */
    public PlayerCharacter(GameManager gm)
    {
        this.gm = gm;
    } //end constructor

    /**
     * Sets default states for player character
     *
     * Date Created: April 12, 2022
     */
    public void setDefaults()
    {
        maxEnergy = 100;
        energy = 100;
        wallet = 0.00;
        debt = 0.00;
        inventory.clear();
        location = Location.Outside;

        gm.ui.swordLabel.setVisible(false);
        gm.ui.cookieLabel.setVisible(false);
        gm.ui.candyLabel.setVisible(false);

        updateMoney();
    } //end setDefaults

    /**
     * Creates player stats. For use in file upload.
     *
     * Date Created: April 23, 2022
     *
     *
     * @param energy
     * @param itemNum
     * @param item1Name
     * @param item2Name
     * @param item3Name
     * @param debt
     * @param wallet
     * @param location
     */
    public void setStats(int energy, int itemNum, String item1Name, String item2Name, String item3Name, double debt,
                         double wallet, Location location)
    {
        this.energy = energy;
        PlayerCharacter.wallet = wallet;
        PlayerCharacter.debt = debt;

        if(itemNum > 0)
        {
            if(item1Name.equalsIgnoreCase(gm.sword.getName())){gm.player.addItem(gm.sword);}
            if(item1Name.equalsIgnoreCase(gm.cookie.getName())){gm.player.addItem(gm.cookie);}
            if(item1Name.equalsIgnoreCase(gm.candy.getName())){gm.player.addItem(gm.candy);}
        }
        if(itemNum > 1)
        {
            if(item2Name.equalsIgnoreCase(gm.sword.getName())){gm.player.addItem(gm.sword);}
            if(item2Name.equalsIgnoreCase(gm.cookie.getName())){gm.player.addItem(gm.cookie);}
            if(item2Name.equalsIgnoreCase(gm.candy.getName())){gm.player.addItem(gm.candy);}
        }
        if(itemNum > 2)
        {
            if(item3Name.equalsIgnoreCase(gm.sword.getName())){gm.player.addItem(gm.sword);}
            if(item3Name.equalsIgnoreCase(gm.cookie.getName())){gm.player.addItem(gm.cookie);}
            if(item3Name.equalsIgnoreCase(gm.candy.getName())){gm.player.addItem(gm.candy);}
        }

        switch(location)
        {
            case GiftShop: gm.sc.showScreen2(); break;
            case Register: gm.sc.showScreen3(); break;
            case MiniGames: gm.sc.showScreen4(); break;
            case DiningRoom: gm.sc.showScreen5(); break;
            case Kitchen: gm.sc.showScreen6(); break;
            default: gm.sc.showScreen1(); break;
        }

        updateMoney();
        gm.sc.updateEnergy(energy);
    }

    /**
     * Adds an item to the player's inventory.
     *
     * Date Created: April 12, 2022
     *
     * @param item
     */
    public void addItem(Item item)
    {
        inventory.add(item);
        if(item == gm.candy){gm.ui.candyLabel.setVisible(true);}
        if(item == gm.cookie){gm.ui.cookieLabel.setVisible(true);}
        if(item == gm.sword){gm.ui.swordLabel.setVisible(true);}
    }

    /**
     * Removes an item from the player's inventory.
     *
     * Date Created: April 12, 2022
     *
     * @param item
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
        if(item == gm.candy){gm.ui.candyLabel.setVisible(false);}
        if(item == gm.cookie){gm.ui.cookieLabel.setVisible(false);}
        if(item == gm.sword){gm.ui.swordLabel.setVisible(false);}
    }

    /**
     * Adds money to the player's wallet.
     *
     * Date Created: April 12, 2022
     *
     * @param amount
     */
    public void earnMoney(double amount)
    {
        wallet += amount;
        updateMoney();
    }

    /**
     * Detracts money from the player's wallet.
     *
     * Date Created: April 12, 2022
     *
     * @param amount
     */
    public void loseMoney(double amount)
    {
        wallet -= amount;
        updateMoney();
    }

    /**
     * Adds to player's debt
     *
     * Date Created: April 19, 2022
     *
     * @param amount
     */
    public void accrueDebt(double amount)
    {
        debt += amount;
        updateMoney();
    }

    /**
     * Detracts from player's debt
     *
     * Date Created: April 19, 2022
     *
     * @param amount
     */
    public void ameliorateDebt(double amount)
    {
        debt -= amount;
        updateMoney();
    }

    /**
     * Increases player energy if energy is not already greater than 100
     *
     * Date Created: April 19, 2022
     *
     * @param amount
     */
    public void increaseEnergy(int amount)
    {
        if(energy < maxEnergy)
            energy += amount;
        gm.sc.updateEnergy(energy);
    }

    /**
     * Decreases player energy if energy is not already 0 or less
     *
     * Date Created: April 19, 2022
     *
     * @param amount
     */
    public void decreaseEnergy(int amount)
    {
        if(energy > 0)
            energy -= amount;
        gm.sc.updateEnergy(energy);
    }

    /**
     * Checks player's inventory for an item and returns true if the player's inventory contains the item.
     *
     * Date Created: April 12, 2022
     *
     * @param item
     * @return
     */
    public Boolean checkInventory(Item item)
    {
        boolean itemPresent;
        itemPresent = inventory.contains(item);
        return itemPresent;
    }

    /**
     * Returns the amount of money in the player's wallet.
     *
     * Date Created: April 12, 2022
     *
     * @return
     */
    public static double getWallet() {
        return wallet;
    }

    /**
     * Returns the player's energy level
     *
     * Date Created: April 12, 2022
     *
     * @return
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Returns the player's debt
     *
     * Date Created: April 19, 2022
     * @return
     */
    public static double getDebt() {
        return debt;
    }

    /**
     * Updates debt and wallet displays to current value; changes debt to red if greater than wallet
     * or green if less than wallet
     *
     * Date Created: April 19, 2022
     */
    public void updateMoney()
    {
        UI.displayMoney.setText(fmt.format(wallet));
        UI.displayDebt.setText(fmt.format(debt));

        //if the player has more money than debt, it turns green; otherwise, it turns red
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
     * Returns player location name as a string.
     *
     * Date Created: April 23, 2022
     *
     * @return
     */
    public String getLocation()
    {
        return location.name();
    }
} // end PlayerCharacter