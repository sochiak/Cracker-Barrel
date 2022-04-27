/**
 * --------------------------------------------------------------
 * File Name: ScreenChanger.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 6, 2022
 */

package Main;

/**
 * Class that changes the game backgrounds as the player moves from room to room
 *
 * Date Created: April 6, 2022
 *
 * @author Sophia Herrell
 */

public class ScreenChanger
{
    GameManager gm;

    /**
     * Constructor for ScreenChanger
     *
     * Date Created: April 6, 2022
     *
     * @param gm
     */
    public ScreenChanger(GameManager gm)
    {
        this.gm = gm;
    } //end constructor

    /**
     * Displays outside background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen1()
    {
        makeAllInvisible();
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.doorPanel[1].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(false);
        gm.ui.buttonPanels[2].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.Outside;
    }

    /**
     * Displays gift shop background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen2()
    {
        makeAllInvisible();
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.doorPanel[2].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.GiftShop;
    }

    /**
     * Displays register background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen3()
    {
        makeAllInvisible();
        gm.ui.bgPanel[3].setVisible(true);
        gm.ui.doorPanel[3].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.Register;
    }

    /**
     * Displays mini-game arena background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen4()
    {
        makeAllInvisible();
        gm.ui.bgPanel[4].setVisible(true);
        gm.ui.doorPanel[4].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.MiniGames;
    }

    /**
     * Displays dining room background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen5()
    {
        makeAllInvisible();
        gm.ui.bgPanel[5].setVisible(true);
        gm.ui.doorPanel[5].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.DiningRoom;
    }

    /**
     * Displays kitchen background
     *
     * Date Created: April 6, 2022
     */
    public void showScreen6()
    {
        makeAllInvisible();
        gm.ui.bgPanel[6].setVisible(true);
        gm.ui.doorPanel[6].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(true);
        gm.player.decreaseEnergy(1);
        gm.player.location = PlayerCharacter.Location.Kitchen;
    }

    /**
     * Displays game over screen and hides all player controls
     *
     * Date Created: April 19, 2022
     */
    public void showGameOver()
    {
        makeAllInvisible();
        gm.ui.bgPanel[7].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(false);
        hideEnergy();
        UI.displayDebt.setVisible(false);
        UI.displayMoney.setVisible(false);
    }

    /**
     * Displays the "you win" screen and hides all player controls except save game.
     *
     * Date Created: April 23, 2022
     */
    public void showYouWin(){
        makeAllInvisible();
        gm.ui.bgPanel[8].setVisible(true);
        gm.ui.buttonPanels[0].setVisible(false);
        hideEnergy();
        UI.displayDebt.setVisible(false);
        UI.displayMoney.setVisible(false);
    }

    /**
     * Displays energy icon corresponding with value passed it
     *
     * Date Created: April 19, 2022
     */
    public void updateEnergy(int level)
    {
        hideEnergy();
        if(level <= 0)
        {
            //show 0 energy at 0
            gm.ui.energyPanelLayered[0].setVisible(true);
        }
        else if(level > 0 && level <= 25)
        {
            //show 25 energy at 1-25
            gm.ui.energyPanelLayered[1].setVisible(true);
        }
        else if(level > 25 && level <= 50)
        {
            //show 50 energy 26-50
            gm.ui.energyPanelLayered[2].setVisible(true);
        }
        else if(level > 50 && level <= 75)
        {
            //show 75 energy at 51-75
            gm.ui.energyPanelLayered[3].setVisible(true);
        }
        else if(level > 75 && level <= 100)
        {
            //show 100 energy at 76-100
            gm.ui.energyPanelLayered[4].setVisible(true);
        }
        else
            gm.ui.messageText.setText("Error updating energy");

        if(gm.player.getEnergy() < 0)
        {
            gm.ui.messageText.setText("Your blood sugar dropped dangerously low and you had a hypoglycemic seizure. " +
                    "A bystander called an ambulance and you now owe $50,000 in medical debt. You will never be" +
                    " able to pay for your food and are banned from Cracker Barrel.");
            showGameOver();
        }
    } //end updateEnergy()

    /**
     * Hides background panels and arrow buttons
     *
     * Date Created: April 19, 2022
     */
    public void makeAllInvisible()
    {
        for(int i = 1; i < 9; i++)
        {
            gm.ui.bgPanel[i].setVisible(false);
        }
        for(int i = 1; i < 7; i++)
        {
            gm.ui.doorPanel[i].setVisible(false);
        }
        gm.ui.buttonPanels[2].setVisible(false);
    }

    /**
     * Hides all energy icons
     *
     * Date Created: April 19, 2022
     */
    public void hideEnergy()
    {
        for(int i = 0; i<5; i++)
        {
            gm.ui.energyPanelLayered[i].setVisible(false);
        }
    }
} //end class