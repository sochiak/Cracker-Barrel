/**
 * --------------------------------------------------------------
 * File Name: UI.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 5, 2022
 */

package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that creates backgrounds and objects
 *
 * Date Created: April 5, 2022
 *
 * @author Sophia Herrell
 */

public class UI
{
    //This folder contains the images used in the game
    static final String FILE_PATH = "/Users/sophiaherrell/IdeaProjects/CrackerBarrelSim/src/res/";

    GameManager gm;
    JFrame window;

    //background
    public JTextArea messageText;
    public JPanel[] bgPanel = new JPanel[10];
    public JPanel[] doorPanel = new JPanel[10];
    public JLabel[] bgLabel = new JLabel[10];
    public JPanel clickable;

    //player stats
    public static JTextArea displayMoney;
    public static JTextArea displayDebt;
    public JPanel[] energyPanelLayered = new JPanel[5];
    public JLabel[] energyLabelLayered = new JLabel[5];
    public JPanel inventoryPanel;
    public JLabel swordLabel, cookieLabel, candyLabel;

    //question mark and file upload
    public JPanel[] buttonPanels = new JPanel[3];
    public JButton[] miscButtons = new JButton[3];

    /**
     * Constructor for user interface
     *
     * Date Created: April 5, 2022
     *
     * @param gm
     */
    public UI(GameManager gm)
    {
        this.gm = gm;
        createMainField();
        createPlayerEnergy();
        generateScreen();
        createPlayerInventory();
        window.setVisible(true);
    } //end UI()

    /**
     * Method to create background window and text box for UI
     *
     * Date Created: April 5, 2022
     */
    public void createMainField()
    {
        //create background
        window = new JFrame(); //instantiate window
        window.setSize(725,675);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        //create text directions / output
        messageText = new JTextArea("You awake and find yourself in the parking lot. As you gaze across the " +
                "vast expanse of asphalt and metal, you see only two options before you...\nPress the left arrow " +
                "to go back where you came from, or press the right arrow to venture inside.");
        messageText.setBounds(25, 440,675, 115);
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.white);
        messageText.setEditable(false); //cannot edit text -- only used for display
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        window.add(messageText); //adds message text to main window
    } //end CreateMainField()

    /**
     * Method to create background with image icon for each individual room
     *
     * Date Created: April 5, 2022
     *
     * @param bgNum
     * @param objFile
     */
    public void createBackground(int bgNum, String objFile)
    {
        bgPanel[bgNum] = new JPanel(); //initialize
        bgPanel[bgNum].setBounds(25,25,675,400); //size of background image
        bgPanel[bgNum].setBackground(Color.black);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);
        //will be used to display background image
        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 675, 400);
        //creates image icon and adds it to the panel
        ImageIcon bgIcon = new ImageIcon(FILE_PATH + objFile);
        bgLabel[bgNum].setIcon(bgIcon);
        bgPanel[bgNum].add(bgLabel[bgNum]);

        //creates door button background
        doorPanel[bgNum] = new JPanel();
        doorPanel[bgNum].setBounds(25,575,80,50);
        doorPanel[bgNum].setBackground(Color.black);
        doorPanel[bgNum].setLayout(null);
        window.add(doorPanel[bgNum]);
    } //end createBackground();

    /**
     * Method to create clickable object and action listener
     *
     * Date Created: April 6, 2022
     *
     * @param objx
     * @param objy
     * @param objWidth
     * @param objHeight
     * @param objFile
     * @param bgObj
     * @param choice1
     * @param choice2
     * @param choice3
     * @param choice1command
     * @param choice2command
     * @param choice3command
     */
    public void createObject(int optionsNum, int objx, int objy, int objWidth, int objHeight, String objFile, int bgObj, String choice1, String choice2, String choice3, String choice1command, String choice2command, String choice3command)
    {
        //create pop up menu
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem[] menuItem = new JMenuItem[4];

        //add pop up menu items - if() allows custom menu length
        //if instead of if-else because an if else would terminate after the first if was met
        //did not use switch because it would have required repeat code
        if(optionsNum > 0)
        {
            menuItem[1] = new JMenuItem(choice1);
            menuItem[1].addActionListener(gm.ah);
            menuItem[1].setActionCommand(choice1command);
            popMenu.add(menuItem[1]);
        }
        if(optionsNum > 1)
        {
            menuItem[2] = new JMenuItem(choice2);
            menuItem[2].addActionListener(gm.ah);
            menuItem[2].setActionCommand(choice2command);
            popMenu.add(menuItem[2]);
        }
        if(optionsNum > 2)
        {
            menuItem[3] = new JMenuItem(choice3);
            menuItem[3].addActionListener(gm.ah);
            menuItem[3].setActionCommand(choice3command);
            popMenu.add(menuItem[3]);
        }

        //create object
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx,objy,objWidth, objHeight);
        //set object image
        ImageIcon objectIcon = new ImageIcon(FILE_PATH + objFile);
        objectLabel.setIcon(objectIcon);
        //implement obj listener
        objectLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e)
            {
                //if you press right mouse button on object label
                if(SwingUtilities.isRightMouseButton(e))
                {
                    popMenu.show(objectLabel, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add to background
        bgPanel[bgObj].add(objectLabel);
    }

    /**
     * Method to create arrow to move between backgrounds
     *
     * Date Created: April 7, 2022
     *
     * @param num
     * @param fileName
     * @param command
     */
    public void createDoor(int num, String fileName, String command)
    {
        //create buttons
        ImageIcon doorIcon = new ImageIcon(FILE_PATH + fileName);
        JButton doorButton = new JButton();

        if (fileName.equals("left.png"))
            doorButton.setBounds(-10, 0, 50, 50);
        else
            doorButton.setBounds(40, 0, 50, 50);

        doorButton.setBackground(null);
        doorButton.setBorderPainted(false);
        doorButton.setContentAreaFilled(false);
        doorButton.setFocusPainted(false);
        doorButton.setIcon(doorIcon);

        //add action listener
        doorButton.addActionListener(gm.ah);
        doorButton.setActionCommand(command);

        //add to background panel
        doorPanel[num].add(doorButton);
    }

    /**
     * Creates battery icons, labels, and panels to depict player energy.
     *
     * Date Created: April 12, 2022
     */
    public void createPlayerEnergy()
    {
        //create labels and panels
        for(int i = 0; i < 5; i++)
        {
            energyLabelLayered[i] = new JLabel();
            energyPanelLayered[i] = new JPanel();
        }

        //set panels
        for(int i = 0; i < 5; i++)
        {
            energyPanelLayered[i].setBounds(637, 565, 75, 75);
            energyPanelLayered[i].setBackground(Color.black);
            window.add(energyPanelLayered[i]);
        }

        //creates icons from battery images
        ImageIcon battery0 = new ImageIcon(FILE_PATH + "battery-0.png");
        ImageIcon battery25 = new ImageIcon(FILE_PATH + "battery-25.png");
        ImageIcon battery50 = new ImageIcon(FILE_PATH + "battery-50.png");
        ImageIcon battery75 = new ImageIcon(FILE_PATH + "battery-75.png");
        ImageIcon battery100 = new ImageIcon(FILE_PATH + "battery-100.png");

        //add icons to labels
        energyLabelLayered[0].setIcon(battery0);
        energyLabelLayered[1].setIcon(battery25);
        energyLabelLayered[2].setIcon(battery50);
        energyLabelLayered[3].setIcon(battery75);
        energyLabelLayered[4].setIcon(battery100);

        //add label to panel
        for(int i = 0; i < 5; i++)
        {
            energyPanelLayered[i].add(energyLabelLayered[i]);
        }
    }

    /**
     * Creates text area to display player's money.
     *
     * Date Created: April 12, 2022
     */
    public void createWallet()
    {
        displayMoney = new JTextArea("$0.00");
        displayMoney.setBounds(120, 573, 100, 25);
        //125, 565, 100, 27
        displayMoney.setBackground(Color.black);
        displayMoney.setForeground(Color.white);
        displayMoney.setEditable(false); //cannot edit text -- only used for display
        displayMoney.setLineWrap(true);
        displayMoney.setWrapStyleWord(true);
        displayMoney.setFont(new Font("Monospaced", Font.PLAIN, 23));
        window.add(displayMoney); //adds message text to main window
    }

    /**
     * Creates text area to display debt to Cracker Barrel
     *
     * Date Created: April 20, 2022
     */
    public void createDebt()
    {
        displayDebt = new JTextArea("$0.00");
        displayDebt.setBounds(120, 603, 100, 25);
        //125, 565, 100, 27
        displayDebt.setBackground(Color.black);
        displayDebt.setForeground(Color.white);
        displayDebt.setEditable(false); //cannot edit text -- only used for display
        displayDebt.setLineWrap(true);
        displayDebt.setWrapStyleWord(true);
        displayDebt.setFont(new Font("Monospaced", Font.PLAIN, 23));
        window.add(displayDebt); //adds message text to main window
    }

    /**
     * Creates panel and item labels to display the contents of the player's inventory.
     *
     * Date Created: April 12, 2022
     */
    public void createPlayerInventory()
    {
        //create inventory panel
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(225, 565, 250, 75);
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        window.add(inventoryPanel);

        //create item labels
        //weapon
        swordLabel = new JLabel();
        ImageIcon swordIcon = new ImageIcon(FILE_PATH + "gladius.png");
        Image image6 = swordIcon.getImage().getScaledInstance(75, 70, Image.SCALE_DEFAULT); //resizes image
        swordIcon = new ImageIcon(image6);
        swordLabel.setIcon(swordIcon);
        inventoryPanel.add(swordLabel);

        //cookie
        cookieLabel = new JLabel();
        ImageIcon cookieIcon = new ImageIcon(FILE_PATH + "cookie.png");
        Image image7 = cookieIcon.getImage().getScaledInstance(75, 70, Image.SCALE_DEFAULT); //resizes image
        cookieIcon = new ImageIcon(image7);
        cookieLabel.setIcon(cookieIcon);
        inventoryPanel.add(cookieLabel);

        //candy
        candyLabel = new JLabel();
        ImageIcon candyIcon = new ImageIcon(FILE_PATH + "candy.png");
        Image image8 = candyIcon.getImage().getScaledInstance(75, 70, Image.SCALE_DEFAULT); //resizes image
        candyIcon = new ImageIcon(image8);
        candyLabel.setIcon(candyIcon);
        inventoryPanel.add(candyLabel);
    }

    /**
     * Creates a transparent clickable box, so I can click sections of
     * images and not just objects
     *
     * Date Created: April 19, 2022
     *
     * @param optionsNum
     * @param bgNum
     * @param objx
     * @param objy
     * @param objWidth
     * @param objHeight
     * @param option1
     * @param option1command
     * @param option2
     * @param option2command
     * @param option3
     * @param option3command
     */
    public void createClickableArea(int optionsNum, int bgNum, int objx, int objy, int objWidth, int objHeight, String option1, String option1command, String option2, String option2command, String option3, String option3command)
    {
        clickable = new JPanel();

        //create pop up menu
        JPopupMenu popMenuClickable = new JPopupMenu();
        JMenuItem[] menuOption = new JMenuItem[4];

        //add pop up menu items - if allows custom menu length
        //if instead of if-else because an if else would terminate after the first if was met
        //did not use switch because it would have required repeat code
        if(optionsNum > 0)
        {
            menuOption[1] = new JMenuItem(option1);
            menuOption[1].addActionListener(gm.ah);
            menuOption[1].setActionCommand(option1command);
            popMenuClickable.add(menuOption[1]);
        }
        if(optionsNum > 1)
        {
            menuOption[2] = new JMenuItem(option2);
            menuOption[2].addActionListener(gm.ah);
            menuOption[2].setActionCommand(option2command);
            popMenuClickable.add(menuOption[2]);
        }
        if(optionsNum > 2)
        {
            menuOption[3] = new JMenuItem(option3);
            menuOption[3].addActionListener(gm.ah);
            menuOption[3].setActionCommand(option3command);
            popMenuClickable.add(menuOption[3]);
        }

        //create object
        JLabel clickableAreaLabel = new JLabel();
        clickableAreaLabel.setBounds(objx,objy,objWidth, objHeight);

        //implement obj listener
        clickableAreaLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e)
            {
                //if you press right mouse button on object label
                if(SwingUtilities.isRightMouseButton(e))
                {
                    popMenuClickable.show(clickableAreaLabel, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add to background
        bgPanel[bgNum].add(clickableAreaLabel);
    }

    /**
     * Creates question mark button, save game button, and upload save file button
     *
     * Date Created: April 22, 2022
     *
     * @param buttonNum
     * @param x
     * @param y
     * @param width
     * @param height
     * @param fileName
     * @param actionCommand
     */
    public void createMiscButtons(int buttonNum, int x, int y, int width, int height, String fileName, String actionCommand)
    {
        buttonPanels[buttonNum] = new JPanel();
        buttonPanels[buttonNum].setBounds(x, y, width, height);
        buttonPanels[buttonNum].setBackground(Color.black);
        window.add(buttonPanels[buttonNum]);

        //create button + image icon
        ImageIcon buttonIcon = new ImageIcon(FILE_PATH + fileName);
        miscButtons[buttonNum] = new JButton();

        miscButtons[buttonNum].setBackground(null);
        miscButtons[buttonNum].setBorderPainted(false);
        miscButtons[buttonNum].setContentAreaFilled(false);
        miscButtons[buttonNum].setFocusPainted(false);
        miscButtons[buttonNum].setIcon(buttonIcon);

        //add action listener
        miscButtons[buttonNum].addActionListener(gm.ah);
        miscButtons[buttonNum].setActionCommand(actionCommand);

        //add to background panel
        buttonPanels[buttonNum].add(miscButtons[buttonNum]);
    }

    /**
     * Uses above methods to generate backgrounds and objects
     *
     * Date Created: April 5, 2022
     */
    public void generateScreen()
    {
        //screen 1: outside
        createBackground(1, "signPalette.png");
        createDoor(1, "left.png", "leave");
        createDoor(1,"right.png", "goScreen2");
        bgPanel[1].add(bgLabel[1]);

        //screen 2: gift shop
        createBackground(2, "giftShop.png");
        createObject(2,0,25,244, 300, "arcadeDoor.png", 2,
                "Investigate door", "Enter", "",
                "inspectArcDoor", "goScreen4", "");
        createObject(3,220, 149, 167, 200, "toyShelf.png", 2,
                "Investigate shelf", "Buy sword", "Return sword",
                "inspectToyShelf", "selectToy", "returnToy");
        createObject(3,410, 50, 290, 300, "foodShelf.png", 2,
                "Investigate shelf", "Buy cookie", "Return cookie",
                "inspectFoodShelf", "selectFood", "returnFood");
        createObject(3,228, 15, 150, 150, "candyShelf.png", 2,
                "Investigate shelf", "Buy candy", "Return candy",
                "inspectCandyShelf", "selectCandy", "returnCandy");

        createDoor(2,"left.png", "goScreen5");
        createDoor(2,"right.png", "goScreen3");
        bgPanel[2].add(bgLabel[2]);

        //screen 3: register
        createBackground(3, "register.png");
        createObject(1,450,275, 75, 56, "bell.png", 3,
                "Ask for the manager", "", "", "complain", "", "");
        createClickableArea(2,3,30,80,300,300,"Order Food",
                "orderMeal","Pay", "pay","","");
        createDoor(3,"left.png", "goScreen2");
        bgPanel[3].add(bgLabel[3]);

        //screen 4: mini game area
        createBackground(4, "minigameArea.png");
        createObject(1,400, 155, 250, 250, "memory.png", 4,
                "Test your memory", " ", " ",
                "playMemoryGm", " ", " ");
        createObject(1,180, 160, 250, 250, "tictactoe.png", 4,
                "Play tic tac toe", " ", " ",
                "playTicTacToe", " ", " ");
        createObject(1,-40, 155, 250, 250, "checkers.png", 4,
                "Play checkers", " ", " ",
                "playCheckers", " ", " ");
        createDoor(4, "left.png", "goScreen2");
        bgPanel[4].add(bgLabel[4]);

        //screen 5: dining room
        createBackground(5, "diningRoom.png");
        createDoor(5,"left.png", "goScreen6");
        createDoor(5,"right.png", "goScreen2");
        createClickableArea(3, 5,0,250,300,200, "Play peg game",
                "playPegGame","Make conversation", "converse",
                "Eat something", "consumeInventory");
        createClickableArea(3, 5,420,250,300,200, "Play peg game",
                "playPegGame","Make conversation", "converse",
                "Eat something", "consumeInventory");
        bgPanel[5].add(bgLabel[5]);

        //screen 6: kitchen
        createBackground(6, "kitchen.png");
        createDoor(6,"left.png", "goScreen5");
        createClickableArea(1,6,130,140,350,100,"Wash dishes",
                "washDishes","","","","");
        bgPanel[6].add(bgLabel[6]);

        //screen 7: game over screen
        createBackground(7, "gameOver.png");
        bgPanel[7].add(bgLabel[7]);

        //screen 8: game over win screen
        createBackground(8, "youWin.png");
        bgPanel[8].add(bgLabel[8]);

        //money display
        createWallet();
        createDebt();

        //question mark
        createMiscButtons(0, 590, 558, 40, 82, "question.png", "question");
        //save file
        createMiscButtons(1, 515, 565, 60, 75, "saveFile.png", "saveFile");
        //upload file
        createMiscButtons(2, 575, 565, 60, 75, "fileUpload.png", "uploadFile");
    }
} //end UI