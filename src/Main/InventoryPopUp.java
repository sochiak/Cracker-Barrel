/**
 * --------------------------------------------------------------
 * File Name: InventoryPopUp.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 23, 2022
 */

package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Creates pop up menu to allow user to choose an item to eat
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */
public class InventoryPopUp
{
    GameManager gm;

    private JFrame window;
    private JCheckBox cookieCheck, candyCheck;

    /**
     * Constructor for InventoryPopUp
     *
     * Date Created: April 23, 2022
     *
     * @param gm
     */
    public InventoryPopUp(GameManager gm)
    {
        this.gm = gm;
        createWindow();
        createChecks();
        createButtons();
        window.setVisible(true);
    }

    /**
     * Creates background window for pop up
     *
     * Date Created: April 23, 2022
     */
    public void createWindow()
    {
        window = new JFrame();
        window.setSize(200,200);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(null);
    }

    /**
     * Creates check marks for inventory items
     *
     * Date Created: April 23, 2022
     */
    public void createChecks()
    {
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.PAGE_AXIS));
        checkPanel.setBounds(10,10,180,120);

        cookieCheck = new JCheckBox("Cookie");
        candyCheck = new JCheckBox("Candy");

        //if player inventory contains then
        if(gm.player.checkInventory(gm.candy))
            checkPanel.add(candyCheck);
        if(gm.player.checkInventory(gm.cookie))
            checkPanel.add(cookieCheck);

        window.add(checkPanel);
        checkPanel.setVisible(true);
    }

    /**
     * Creates eat button
     *
     * Date Created: April 23, 2022
     */
    public void createButtons()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(10,130,170,30);

        JButton submit = new JButton("Eat");

        buttonPanel.add(submit);
        window.add(buttonPanel);

        submit.addActionListener(new ButtonClickedListener());
    }

    /**
     * Removes consumed items from inventory
     *
     * Date Created: April 23, 2022
     */
    public void updatePlayerStats()
    {
        if(cookieCheck.isSelected())
        {
            gm.ui.messageText.setText("You ate a cookie and your energy increased.");
            gm.player.increaseEnergy(gm.cookie.getEnergy());
            gm.player.removeItem(gm.cookie);
        }
        if(candyCheck.isSelected())
        {
            gm.ui.messageText.setText("You ate a piece of candy and your energy increased.");
            gm.player.increaseEnergy(gm.candy.getEnergy());
            gm.player.removeItem(gm.candy);
        }
    }

    /**
     * Creates listener for eat button
     *
     * Date Created: April 23, 2022
     */
    private class ButtonClickedListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            updatePlayerStats();
            window.dispose(); //closes window when user eats food
        }
    }
}
