package PegGame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * File name: PegGameSwing.java
 * Project name: Five Nights at Cracker Barrel
 * Author: Hunter Jones
 * Date: Created: 3/1/22
 * Date: Modified: 4/24/22
 * Purpose: GUI and check feature for Peg Game
 *
 */
public class PegGameSwing extends Canvas implements MouseListener, PegGameModel.PegGameView {
    private int peg = -2;                                       //initially set to -2 to indicate that the first peg is about to be selected
    private static boolean[] pegsPresent = new boolean[15];     //initializes the pegsPresent array
    private PegGameModel model;                                 //creates the PegGameModel
    public static boolean winCondition = false;                 //creates the winCondition variable

    /**
     * The driver of the game
     * Date created: 4/12/22
     */
    public static void main(String[] args ) {
        new PegGameSwing();
    }

    /**
     * The GUI window that displays the peg game
     * Date created: 4/12/22
     */
    //creates the playing surface
    public PegGameSwing() {
        for (int i = 0; i < pegsPresent.length; i++)
            pegsPresent[i] = true;

        JFrame frame = new JFrame("Peg Game");                  //title for the GUI window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       //dispose on close
        JButton checkButton = new JButton("Check");             //creates a check button
        checkButton.addActionListener(new CheckButtonListener());   //check button listener
        frame.add(checkButton, BorderLayout.SOUTH);                 //moves checkbutton to the bottom
        setBackground(Color.white);                                 //sets background color to white
        addMouseListener(this);                                  //adds a mouse listener to detect when a peg is selected
        frame.getContentPane().add(this);                           //adds the PegGameSwing object to the JFrame, utilizes Canvas
        frame.setSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));       //sets the frame size to the width and height of the elements added
        frame.setVisible(true);                                    //sets the frame to be visible

        model = new PegGameModel(this);                         //creates the PegGame model
    }

    /**
     * Sets the peg at the inputted index to the inputted state
     * Date created: 4/12/22
     */
    public void setPeg( int i, boolean state )
    {
        pegsPresent[i] = state;
    }

    /**
     * Colors all of the "pegs". Default to blue, red if selected, white if false
     * Date created: 4/12/22
     */
    public void paint( Graphics g )
    {
        for (int i=0; i < pegsPresent.length; i++)
            drawPeg( i, g );
    }

    /**
     * Returns the win condition
     * Date created: 4/12/22
     */
    public static boolean getWin(){return winCondition;}

    /**
     * Check button listener that verifies if a user has won or not
     * Date created: 4/23/22
     */
    public class CheckButtonListener implements ActionListener
    {
        boolean movesLeft = false;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //comparing {i,j,k} to jumpTable[l]
            for(int i = 1; i < 15; i++)                                                                 //for loop that counts through for the from element
            {
                if(pegsPresent[i] == true)                                                              //if that number is still on the peg board
                {
                    for (int j = 1; j < 15; j++)                                                        //for loop that counts through for the to element
                    {
                        if(pegsPresent[j] == false)                                                     //if that number is NOT on the peg board
                        {
                            for (int k = 1; k < 15; k++)                                                //for loop that counts through for the jumped element
                            {
                                if(pegsPresent[k] == true)                                              //if that number is on the peg board
                                {
                                    int[] test = {i, j, k};                                             //creates an integer array from the elements if they are all valid
                                    for (int l = 0; l < PegGameModel.jumpTable.length; l++)             //loops through for different elements in the jump table
                                    {
                                        int[] compare = PegGameModel.jumpTable[l];                      //creates an array out of the element from jump table
                                        if (Arrays.equals(test,compare))                                //compares the two arrays
                                        {
                                            movesLeft = true;                                           //if they match, set moves left to true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int count = 0;                                                      //initializes a counter
            for (int j = 0; j < pegsPresent.length; j++)                        //searches through the entire array
            {
                if(pegsPresent[j] == false)                                     //if the peg is seg to false
                {
                    count++;                                                    //increments the counter
                }
            }
            if(count == 14)
            {
                JOptionPane.showMessageDialog(null, "You win!!!");
            }

            if(movesLeft == true)                                                                       //if there are still moves left to make
            {
                JOptionPane.showMessageDialog(null, "There are still moves left to make.");     //Tell the user there are still moves to make and let them keep playing
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No remaining moves, you lose!");           //Tell the user there are no remaining moves
                //System.exit(0);
                // end the game
                getWin();                                                                                             //get the winCondition
            }
        }
    }

    /**
     * Mouse pressed event listener
     * Date created: 4/12/22
     */
    public void mousePressed( MouseEvent e ) {
        int x = e.getX();           //getX gets the x position from the event
        int y = e.getY();           //getY gets the y position from the event
        x = x / XPOS;               //divides by XPOS to find the X value attached
        if (x > 9) x = 9;           //9 could not normally be found, so an if was needed
        y = y / YPOS;               //divide by YPOS to find the Y value attached
        if (y > 4) y = 4;           //4 could not normally be found, so an if was needed
        int i = cells[y][x];        //stores the number value associated with the exact cell selected
        Graphics g = getGraphics();

        if (peg == -2) {            //initial state - select starting hole
            pegsPresent[i] = false;      //removes the first selected peg from the array
            drawPeg(i, g);               //calls draw peg, and since i cannot be -2, it turns the peg white
            model.removePeg(i);          //removes the peg from the model
            peg = -1;               //changes the value of peg, so it moves down the if statement
        } else if (peg == -1) {     //select source peg
            peg = i;                //sets peg to i so that it changes to red
            drawPeg(i, g);               //passes the int value to draw
        } else if (peg == i) {      // unselect source peg
            peg = -1;               //if the same peg is selected again, changes it to -1 so the color is reset
            drawPeg(i, g);               //passes i to draw
        } else {                         //select destination hole
            model.attemptMove(peg, i );   //if a destination is selected, it attempts to move
            peg = -1;                     //sets the peg to -1 to clear it
            repaint();                   //updates the graphic
        }
    }
    public void mouseEntered(MouseEvent e) { }      //default mouse event
    public void mouseExited(MouseEvent e) { }       //default mouse event
    public void mouseClicked(MouseEvent e) { }      //default mouse event
    public void mouseReleased(MouseEvent e) { }     //default mouse event

    /**
     * Colors the pegs based on the current status
     * Date created: 4/12/22
     */
    private void drawPeg( int i, Graphics g ) {
        if (pegsPresent[i])
        {
            if (i == peg)                       //if the peg is selected
                g.setColor(Color.red);
            else
                g.setColor(Color.blue);         //if the peg is on the board
                g.fillOval(X[i], Y[i], SIZE, SIZE);
                g.setColor(Color.white);
        }
        else                                    //when the peg is removed
        {
            g.setColor( Color.white );
            g.fillOval( X[i], Y[i], SIZE, SIZE );
            g.setColor( Color.blue );
        }
        g.drawString( "" + (i+1), X[i]+XS[i/9], Y[i]+YS );
    }

    private static final int XPOS = 15;  //XPOS and YPOS are dimensions multiplied by something in each for a unique location
    private static final int YPOS = 30;
    private static final int SIZE = 23;  //size creates the standard size for each number
    private static final int MARG = 15;  //MARG creates the margin from the border of the window
    private static final int WIN_WIDTH = 210;   //creates a standard window width
    private static final int WIN_HEIGHT = 225;  //creates a standard window height
    private static final int[] XS = {9, 5}; //int array used for drawing the string
    private static final int YS = 16;     //the y value for where the numbers are put onto the button

    //X creates an array of the X positions for every element
    private static final int[] X = {
            4*XPOS+MARG,                                                        //row 1
            3*XPOS+MARG, 5*XPOS+MARG,                                           //row 2
            2*XPOS+MARG, 4*XPOS+MARG, 6*XPOS+MARG,                              //row 3
            1*XPOS+MARG, 3*XPOS+MARG, 5*XPOS+MARG, 7*XPOS+MARG,                 //row 4
            0*XPOS+MARG, 2*XPOS+MARG, 4*XPOS+MARG, 6*XPOS+MARG, 8*XPOS+MARG };  //row 5

    //Y creates an array of the Y positions for every element
    private static final int[] Y = {
            0*YPOS+MARG,                                                        //row 1
            1*YPOS+MARG, 1*YPOS+MARG,                                           //row 2
            2*YPOS+MARG, 2*YPOS+MARG, 2*YPOS+MARG,                              //row 3
            3*YPOS+MARG, 3*YPOS+MARG, 3*YPOS+MARG, 3*YPOS+MARG,                 //row 4
            4*YPOS+MARG, 4*YPOS+MARG, 4*YPOS+MARG, 4*YPOS+MARG, 4*YPOS+MARG };  //row 5

    //cells creates a 2d array of numeric values tied to cell created
    private static final int[][] cells = {
            {0,0,0,0,0,0,0,0,0,0},                                              //row 1
            {1,1,1,1,1,2,2,2,2,2},                                              //row 2
            {3,3,3,3,4,4,5,5,5,5},                                              //row 3
            {6,6,6,7,7,8,8,9,9,9},                                              //row 4
            {10,10,11,11,12,12,13,13,14,14} };                                  //row 5
}