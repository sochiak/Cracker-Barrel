/**
 * --------------------------------------------------------------
 * File Name: Menu.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 23, 2022
 */

package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

/**
 * Creates menu for player to order meal from
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */

public class Menu
{
    GameManager gm;

    public double total;

    public static final double 	MEALCOST = 10.00, SIDECOST = 4.00, DESSERTCOST = 3.00, DRINKCOST = 1.00, TIP = 0.2;
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance();
    private JFrame window;
    private JPanel container;
    private JButton submitButton, cancelButton;
    private JLabel totalAmtLbl;
    private JLabel subtotalAmtLbl;
    private JLabel tipAmtLbl;
    private JCheckBox coffeeCheck, milkCheck, sweetTeaCheck, juiceCheck;
    private JCheckBox chickenDumplingsCheck, troutCheck, friedChickenCheck, sirloinCheck, grandmaCheck;
    private JCheckBox macCheck, potatoesCheck, hashbrownCheck, carrotsCheck, friesCheck, applesCheck;
    private JCheckBox beignetsCheck, cobblerCheck, pieCheck;

    /**
     * Constructor for menu
     *
     * Date Created: April 23, 2022
     *
     * @param gm
     */
    public Menu(GameManager gm)
    {
        this.gm = gm;
        createWindow();
        calculate();
        addListeners();
        window.setVisible(true);
    }

    /**
     * Creates background window for Menu
     *
     * Date Created: April 23, 2022
     */
    public void createWindow()
    {
        //create background
        window = new JFrame(); //instantiate window
        window.setSize(300,500);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(null);

        createScrollable();
        createButtons();
        createTotals();
    }

    /**
     * Creates scrollable panel to hold menu
     *
     * Date Created: April 23, 2022
     */
    public void createScrollable()
    {
        //create scrollable panel to hold menu
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setBackground(Color.pink);

        //create scroller
        JScrollPane scrollablePanel = new JScrollPane(container);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollablePanel.setBounds(15,15,250,350);
        scrollablePanel.setVisible(true);

        window.add(scrollablePanel);

        createDrinkMenu();
        createDinnerMenu();
        createSideMenu();
        createDessertMenu();
    }

    /**
     * Creates submit and cancel buttons
     *
     * Date Created: April 23, 2022
     */
    public void createButtons()
    {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout());
        buttonsPanel.setBounds(15,375,120,85);

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        buttonsPanel.add(submitButton);
        buttonsPanel.add(cancelButton);

        window.add(buttonsPanel);
    }

    /**
     * Creates subtotal, tip, and total fields
     *
     * Date Created: April 23, 2022
     */
    public void createTotals()
    {
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(3, 2));
        totalPanel.setBounds(150,375,135,85);

        JLabel subtotalLbl = new JLabel();
        subtotalLbl.setText("Subtotal: ");
        subtotalAmtLbl = new JLabel();

        JLabel tipLbl = new JLabel();
        tipLbl.setText("Tip: ");
        tipAmtLbl = new JLabel();

        JLabel totalLbl = new JLabel();
        totalLbl.setText("Total: ");
        totalAmtLbl = new JLabel();

        totalPanel.add(subtotalLbl);
        totalPanel.add(subtotalAmtLbl);

        totalPanel.add(tipLbl);
        totalPanel.add(tipAmtLbl);

        totalPanel.add(totalLbl);
        totalPanel.add(totalAmtLbl);

        window.add(totalPanel);
    }

    /**
     * Creates drink label and check boxes
     *
     * Date Created: April 23, 2022
     */
    public void createDrinkMenu()
    {
        JPanel checkDrinkPanel = new JPanel(new GridLayout(4, 1));
        JLabel drinksLbl = new JLabel("Drinks");

        //initialize drinks check box
        coffeeCheck = new JCheckBox("Coffee");
        milkCheck = new JCheckBox("Milk");
        sweetTeaCheck = new JCheckBox("Sweet Tea");
        juiceCheck = new JCheckBox("Juice");

        //add checks to panel
        checkDrinkPanel.add(coffeeCheck);
        checkDrinkPanel.add(milkCheck);
        checkDrinkPanel.add(sweetTeaCheck);
        checkDrinkPanel.add(juiceCheck);

        //add sub panel to panel
        checkDrinkPanel.setVisible(true);
        container.add(drinksLbl);
        container.add(checkDrinkPanel);
    }

    /**
     * Creates dinner label and check boxes
     *
     * Date Created: April 23, 2022
     */
    public void createDinnerMenu()
    {
        JPanel checkDinnerPanel = new JPanel(new GridLayout(5, 1));
        JLabel dinnerLbl = new JLabel("Meals");

        //initialize meals check box
        chickenDumplingsCheck = new JCheckBox("Chicken and Dumplings");
        troutCheck = new JCheckBox("Grilled Rainbow Trout");
        friedChickenCheck = new JCheckBox("Fried Chicken Dinner");
        sirloinCheck = new JCheckBox("6oz Sirloin Steak");
        grandmaCheck = new JCheckBox("Grandma's Sampler (Pancakes)");

        //add checks to panel
        checkDinnerPanel.add(chickenDumplingsCheck);
        checkDinnerPanel.add(troutCheck);
        checkDinnerPanel.add(friedChickenCheck);
        checkDinnerPanel.add(sirloinCheck);
        checkDinnerPanel.add(grandmaCheck);

        //add sub panel to panel
        checkDinnerPanel.setVisible(true);
        container.add(dinnerLbl);
        container.add(checkDinnerPanel);
    }

    /**
     * Creates sides label and check boxes
     *
     * Date Created: April 23, 2022
     */
    public void createSideMenu()
    {
        JPanel checkSidePanel = new JPanel(new GridLayout(6, 1));
        JLabel sideLbl = new JLabel("Sides");

        //initialize sides check box
        macCheck = new JCheckBox("Macaroni and Cheese");
        potatoesCheck = new JCheckBox("Mashed Potatoes");
        hashbrownCheck = new JCheckBox("Hashbrown Casserole");
        carrotsCheck = new JCheckBox("Sweet baby carrots");
        friesCheck = new JCheckBox("French Fries");
        applesCheck = new JCheckBox("Southern Fried Applies");

        //add checks to panel
        checkSidePanel.add(macCheck);
        checkSidePanel.add(potatoesCheck);
        checkSidePanel.add(hashbrownCheck);
        checkSidePanel.add(carrotsCheck);
        checkSidePanel.add(friesCheck);
        checkSidePanel.add(applesCheck);

        //add sub panel to panel
        checkSidePanel.setVisible(true);
        container.add(sideLbl);
        container.add(checkSidePanel);
    }

    /**
     * Creates dessert label and check boxes
     *
     * Date Created: April 23, 2022
     */
    public void createDessertMenu()
    {
        JPanel checkDessertPanel = new JPanel(new GridLayout(3, 1));
        JLabel dessertLbl = new JLabel("Desserts");

        //initialize desserts check box
        beignetsCheck = new JCheckBox("Biscuit Beignets");
        cobblerCheck = new JCheckBox("Peach Cobbler");
        pieCheck = new JCheckBox("Apple Pie");

        //add checks to panel
        checkDessertPanel.add(beignetsCheck);
        checkDessertPanel.add(cobblerCheck);
        checkDessertPanel.add(pieCheck);

        //add sub panel to panel
        checkDessertPanel.setVisible(true);
        container.add(dessertLbl);
        container.add(checkDessertPanel);
    }

    /**
     * Calculates payment
     *
     * Date Created: April 23, 2022
     */
    public void calculate()
    {
        double subtotal = 0;
        double tip;

        //add 1.00 for each drink
        if(coffeeCheck.isSelected()) {
            subtotal += DRINKCOST;}
        if(milkCheck.isSelected()) {
            subtotal += DRINKCOST;}
        if(sweetTeaCheck.isSelected()) {
            subtotal += DRINKCOST;}
        if(juiceCheck.isSelected()) {
            subtotal += DRINKCOST;}
        //add 10.00 for each meal
        if(chickenDumplingsCheck.isSelected()) {
            subtotal += MEALCOST;}
        if(troutCheck.isSelected()) {
            subtotal += MEALCOST;}
        if(friedChickenCheck.isSelected()) {
            subtotal += MEALCOST;}
        if(sirloinCheck.isSelected()) {
            subtotal += MEALCOST;}
        if(grandmaCheck.isSelected()) {
            subtotal += MEALCOST;}
        //add 4.00 for each side
        if(macCheck.isSelected()) {
            subtotal += SIDECOST;}
        if(potatoesCheck.isSelected()) {
            subtotal += SIDECOST;}
        if(hashbrownCheck.isSelected()) {
            subtotal += SIDECOST;}
        if(carrotsCheck.isSelected()) {
            subtotal += SIDECOST;}
        if(friesCheck.isSelected()) {
            subtotal += SIDECOST;}
        if(applesCheck.isSelected()) {
            subtotal += SIDECOST;}
        //add 3.00 for each dessert
        if(beignetsCheck.isSelected()) {
            subtotal += DESSERTCOST;}
        if(cobblerCheck.isSelected()) {
            subtotal += DESSERTCOST;}
        if(pieCheck.isSelected()) {
            subtotal += DESSERTCOST;}

        tip = subtotal * TIP;
        total = tip + subtotal;

        //update labels
        subtotalAmtLbl.setText(fmt.format(subtotal));
        tipAmtLbl.setText(fmt.format(tip));
        totalAmtLbl.setText(fmt.format(total));
    }

    /**
     * Sets player debt to order total price
     *
     * Date Created: April 23, 2022
     */
    public void updatePlayerMoney()
    {
        gm.player.accrueDebt(total);
        gm.player.updateMoney();
    }

    /**
     * Adds action listeners to buttons and check boxes
     *
     * Date Created: April 23, 2022
     */
    private void addListeners()
    {
        // Register listeners for events from buttons, checkboxes, and text fields.

        submitButton.addActionListener(new ButtonClickedListener());
        cancelButton.addActionListener(new ButtonClickedListener());

        coffeeCheck.addActionListener(new TotalChangesListener());
        milkCheck.addActionListener(new TotalChangesListener());
        sweetTeaCheck.addActionListener(new TotalChangesListener());
        juiceCheck.addActionListener(new TotalChangesListener());
        chickenDumplingsCheck.addActionListener(new TotalChangesListener());
        troutCheck.addActionListener(new TotalChangesListener());
        friedChickenCheck.addActionListener(new TotalChangesListener());
        sirloinCheck.addActionListener(new TotalChangesListener());
        grandmaCheck.addActionListener(new TotalChangesListener());
        macCheck.addActionListener(new TotalChangesListener());
        potatoesCheck.addActionListener(new TotalChangesListener());
        hashbrownCheck.addActionListener(new TotalChangesListener());
        carrotsCheck.addActionListener(new TotalChangesListener());
        friesCheck.addActionListener(new TotalChangesListener());
        applesCheck.addActionListener(new TotalChangesListener());
        beignetsCheck.addActionListener(new TotalChangesListener());
        cobblerCheck.addActionListener(new TotalChangesListener());
        pieCheck.addActionListener(new TotalChangesListener());

    }

    /**
     * Recalculates total whenever an action is performed
     *
     * Date Created: April 23, 2022
     */
    private class TotalChangesListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            calculate();
        }
    }

    /**
     * Contains action listeners for Menu
     *
     * Date Created: April 23, 2022
     *
     * @author Sophia Herrell
     */
    private class ButtonClickedListener implements ActionListener
    {
        /**
         * Handles button clicks
         *
         * Date Created: April 23, 2022
         */
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (e.getSource() == submitButton)
            {
                JOptionPane.showMessageDialog(null, "Thank you for your order",
                        "Thank you", JOptionPane.INFORMATION_MESSAGE);
                updatePlayerMoney();
            }
            if (e.getSource() == cancelButton)
            {
                JOptionPane.showMessageDialog(null, "Order canceled", "Order Canceled",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            reset();
        }

        /**
         * Unchecks boxes
         *
         * Date Created: April 23, 2022
         */
        private void reset ( )
        {
            coffeeCheck.setSelected(false);
            milkCheck.setSelected(false);
            sweetTeaCheck.setSelected(false);
            juiceCheck.setSelected(false);
            chickenDumplingsCheck.setSelected(false);
            troutCheck.setSelected(false);
            friedChickenCheck.setSelected(false);
            sirloinCheck.setSelected(false);
            grandmaCheck.setSelected(false);
            macCheck.setSelected(false);
            potatoesCheck.setSelected(false);
            hashbrownCheck.setSelected(false);
            carrotsCheck.setSelected(false);
            friesCheck.setSelected(false);
            applesCheck.setSelected(false);
            beignetsCheck.setSelected(false);
            cobblerCheck.setSelected(false);
            pieCheck.setSelected(false);

            calculate();
        }
    } //end action listener class
} //end class