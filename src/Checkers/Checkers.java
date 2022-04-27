package Checkers;

/**
 * File Name: Checkers.java
 * Project Name: CrackerBarrel.java
 * Author: Charlie Shahan
 * Date: Created: 4/22/2022
 * Date: modified
 * Purpose: Play Checkers
 */

import javax.swing.*;

import Main.PlayerCharacter;
import Main.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkers extends JFrame
{
    //Gui Attributes
    private final int WINDOW_WIDTH = 700; //Window width
    private final int WINDOW_HEIGHT = 700; //Window height
    private final int ROW_SIZE = 8; //Number of rows
    private final int COLUMN_SIZE = 8; //Number of columns

    private JPanel gamePanel;
    private JPanel textPanel;
    private JPanel submitPanel;
    private JButton submitButton;
    private JButton [][] spots;
    private JTextField textField;


    //Porgram Attribute
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance(); ///Used for UI display 
    
    //Game Attributes
    private final int NUM_PIECES = 24;
    
    private ArrayList<RedPiece> redPieces;
    private ArrayList<BlackPiece> blackPieces;
    private boolean spotRed [][];
    private boolean spotBlack [][];
    private boolean spotKing [][];
    private int spotSelected [];

    public Checkers()
    {
        super("Checkers"); //Set Title

        setBounds(150, 150, WINDOW_WIDTH, WINDOW_HEIGHT); //Set Size
        setLayout(null); //null window layout

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Set Close Operation

        initComponents();
        
        createPieces();  //create 
        createGameBoard(); //create game board

        //Add action Listener to button
        submitButton.addActionListener(new submitButtonListener());

        addComponents();

        setLocationRelativeTo(null); //Center on screen

        setVisible(true); //show window

    }

    /**
     * Initialize Game Components
     */
    public void initComponents()
    {
        gamePanel = new JPanel(); //Create new game panel
        gamePanel.setLayout(new GridLayout(ROW_SIZE,COLUMN_SIZE)); //Set gridlayout
        gamePanel.setBounds(100, 100, 550, 550); //Set Location

        textPanel = new JPanel(); //Create text panel;
        textPanel.setBounds(10, 10, 100, 100); //Set Location

        submitPanel = new JPanel(); //Create panel for submit button
        submitPanel.setBounds(10, 40, 100, 100); //Set location

        textField = new JTextField(5); //Create text field;

        submitButton = new JButton("Submit"); //Create submit button

        spots = new JButton[ROW_SIZE][COLUMN_SIZE]; //create button array;

        redPieces = new ArrayList<RedPiece>(); //create red pieces array - only used to keep track of number of pieces on the board
        blackPieces = new ArrayList<BlackPiece>(); //create black pieces array
        spotRed = new boolean[ROW_SIZE][COLUMN_SIZE]; //Create boolean array that has every board spot which might contain a red piece
        spotBlack = new boolean[ROW_SIZE][COLUMN_SIZE]; //Create boolean array that has every board spot whihc might contain a black piece
        spotKing = new boolean[ROW_SIZE][COLUMN_SIZE]; //Created another boolean array to keep track of what pieces are kings
        spotSelected = new int[2]; //Will be used for moving pieces

    }

    /**
     * Add components to panels and window
     */
    public void addComponents()
    {
        //Add components to Panels
        textPanel.add(textField);
        submitPanel.add(submitButton);

        //Add panels to window
        add(submitPanel);
        add(textPanel);
        add(gamePanel); 
    }
    
    /**
     * Create game pieces
     */
    public void createPieces()
    {
        for(int i = 0; i < NUM_PIECES/2; i++) //12 Pieces on each side 
        {
            blackPieces.add(new BlackPiece()); //black
            redPieces.add(new RedPiece()); //red
        }
    }


    /**
     * Create gameboard 
     */
    public void createGameBoard()
    {
        String rows [] = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"}; //String Array of row letter names
        String text = ""; //varaible to store text
        for(int row = 0; row < ROW_SIZE; row++) //iterate through rows
        {
            for(int col = 0; col < COLUMN_SIZE; col++) //iterate through columns
            {
                text = rows[row] + col; //get row letter + col number
                spots[row][col] = new JButton(text); //make button
                spots[row][col].setForeground(Color.WHITE); //Set text color
                spots[row][col].setBackground(Color.ORANGE); //Set background color
                gamePanel.add(spots[row][col]); //add to panel 
            }
        }
        placeBlackPieces(); //place black pieces on board
        placeRedPieces(); //place red pieces on board
    }

    /**
     * Place Black Pieces on board
     */
    public void placeBlackPieces()
    {
        for(int row = 0; row < 3; row++) //First three rows
        {
            if(row%2 == 0) //If even
            {
                for(int c = 0; c < COLUMN_SIZE; c++) //Iterate through columns
                {       
                    if(c%2 == 0) //If even column
                    {
                        spots[row][c].setBackground(Color.decode(blackPieces.get(0).getColor())); //Set background of piece to black
                        spotBlack[row][c] = true;
                    }
                }
            }
            else //If row is odd
            {
                for(int c = 0; c < COLUMN_SIZE;c++)
                {
                    if(c%2 == 1) //On odd column
                    {
                        spots[row][c].setBackground(Color.decode(blackPieces.get(0).getColor())); 
                        spotBlack[row][c] = true;
                    }
                }
            }
        
        }
             
    }
    
    /**
     * Place red pieces on board 
     */
    public void placeRedPieces()
    {
        for(int row = ROW_SIZE-3; row < ROW_SIZE; row++) //Last three rows
        {
            if(row%2 == 0) //If even
            {
                for(int c = 0; c < COLUMN_SIZE; c++)
                {       
                    if(c%2 == 0) //If odd
                    {
                        spots[row][c].setBackground(Color.decode(redPieces.get(0).getColor()));
                        spotRed[row][c] = true;
                    }
                }
            }
            else
            {
                for(int c = 0; c < COLUMN_SIZE;c++)
                {
                    if(c%2 == 1) 
                    {
                        spots[row][c].setBackground(Color.decode(redPieces.get(0).getColor())); 
                        spotRed[row][c] = true;
                    }
                }
            }
        
        }
             
    }

    /**
     * Submit button action listener 
     * Takes the string passed into the text field and selects and selects to button to move which shows the possible movement 
     * Or moves the button 
     */
    private class submitButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {   
            String input; //text field input
            input = textField.getText(); //get text field input

            Pattern pattern= Pattern.compile("[A-H][0-7]"); //Pattern to match (board spots)
            Matcher matcher = pattern.matcher(input); //Test if user input matches a board spot

            if(matcher.matches()) //If the pattern matches select the board spot or move the previous selected piece
            {
                String [][] stringArray = create2dString(input);
                if(selectedRedPiece(stringArray) == true) //Checks if the player selected a red piece
                {
                    updateBoard(); 
                    showSelectedPiece(stringArray); //Highlights selected piece yellow
                    showPossibleRedMoves(stringArray); //Highlight possible squares green
                }
                else if(selectedBlackPiece(stringArray) == true) //Check if the user selected a black piece
                {
                    updateBoard();
                    showSelectedPiece(stringArray);
                    showPossibleBlackMoves(stringArray);
                }
                else if(selectedMove(stringArray) == true)  //Check if the user selected a move
                {
                    move(stringArray);
                    makeKing();
                    updateBoard(); //update board state;
                }

            }
            else //display error message 
            {
                JOptionPane.showMessageDialog(null, "Not a valid board spot");
            }

            checkRedWin();
            checkBlackWin();
        }

        //See if Black wins
        public void checkRedWin()
        {
            if(redPieces.size() == 0) 
            {
                JOptionPane.showMessageDialog(null, "Black Wins");
                PlayerCharacter.wallet = PlayerCharacter.wallet + 8; //update player balance
                updateMoney();
            }
        }

        //See if red wins
        public void checkBlackWin()
        {
            if(blackPieces.size() == 0)  
            {
                JOptionPane.showMessageDialog(null, "Red Wins"); 
            }
        }

        //Create 2 string from string input
        public String[][] create2dString(String input)
        {
            String [][] stringArray = new String[1][1];
            for(int i = 0; i < 1; i++)
            {
                for(int j = 0; j < 1; j++)
                {
                    stringArray[i][j] = input;
                }
            }
            return stringArray;
        }

        //Return true if the user selected a red piece
        public boolean selectedRedPiece(String[][] input) 
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spotRed[row][col] == true)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        //Return true if the user selected a black piece
        public boolean selectedBlackPiece(String[][] input) 
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spotBlack[row][col] == true)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        //Return true if user selected a moving square 
        public boolean selectedMove(String[][] input)
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.GREEN))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        //Highlights selected piece yellow
        public void showSelectedPiece(String[][] input)
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spotRed[row][col] == true)
                    {
                        spots[row][col].setBackground(Color.YELLOW);
                    }
                    else if(spots[row][col].getText().equals(input[0][0]) && spotBlack[row][col] == true)
                    {
                        spots[row][col].setBackground(Color.YELLOW);
                    }
                }
            }
        }

        //Update the board 
        public void updateBoard()
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spotBlack[row][col] == true)
                    {
                        spots[row][col].setBackground(Color.decode(blackPieces.get(0).getColor()));
                    }
                    else if(spotRed[row][col] == true)
                    {
                        spots[row][col].setBackground(Color.decode(redPieces.get(0).getColor()));
                    }
                    else
                    {
                        spots[row][col].setBackground(Color.ORANGE);
                    }
                }
            }
        }

        //Mark possible red movement squares with green
        public void showPossibleRedMoves(String[][] input)
        {
            String message = "exception in possible red moves";
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.YELLOW) && spotKing[row][col] != true)
                    {
                        try 
                        {
                            if(spotRed[row-1][col+1] != true && spotBlack[row-1][col+1] != true)  //Piece to the right diagonal
                            {
                                spots[row-1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }     
                        try
                        {
                            if(spotRed[row-1][col-1] != true && spotBlack[row-1][col-1] != true)  //Piece to the left diagonal
                            {
                                spots[row-1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }   
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotBlack[row-1][col+1] == true)  //Show possible jump of black piece to the right
                            {
                                spots[row-2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }   
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }  
                        try
                        {
                            if(spotBlack[row-1][col-1] == true)  //Show possible jump of black piece to the left
                            {
                                spots[row-2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }   
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }     
                    }
                    //If the piece is a king
                    if(spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.YELLOW) && spotKing[row][col] == true)
                    {
                        try
                        {
                            if(spotRed[row-1][col+1] != true && spotBlack[row-1][col+1] != true)  //Spot to the right diagonal
                            {
                                spots[row-1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }  
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotBlack[row-1][col-1] == true)  //Show possible jump of black piece to the backleft
                            {
                                spots[row-2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotBlack[row-1][col+1] == true)  //Show possible jump of black piece to the backright
                            {
                                spots[row+2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotRed[row+1][col-1] != true && spotBlack[row+1][col-1] != true)  //Spot to the backleft diagonal
                            {
                                spots[row+1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotRed[row+1][col+1] != true && spotBlack[row+1][col+1] != true)  //Spot to the backright diagonal
                            {
                                spots[row+1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotBlack[row-1][col-1] == true)  //Show possible jump of black piece to the left
                            {
                                spots[row-2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;

                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotBlack[row-1][col+1] == true)  //Show possible jump of black piece to the right
                            {
                                spots[row-2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            } 
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                        try
                        {
                            if(spotRed[row-1][col-1] != true && spotBlack[row-1][col-1] != true)  //Spot to the left diagonal
                            {
                                spots[row-1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(message);
                        }
                    }
                }
            }
        }

        //Mark possible black movement squares with green
        public void showPossibleBlackMoves(String[][] input)
        {
            String msg = "Exception in black moves";
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.YELLOW) && spotKing[row][col] == false)
                    {
                        try
                        {
                            if(spotRed[row+1][col+1] != true && spotBlack[row+1][col+1] != true)  //Spot to the right diagonal
                            {
                                spots[row+1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col-1] != true && spotBlack[row+1][col-1] != true)  //Spot to the left diagonal
                            {
                                spots[row+1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col+1] == true)  //Show possible jump of red piece to the right
                            {
                                spots[row+2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col-1] == true)  //Show possible jump of red piece to the left
                            {
                                spots[row+2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;

                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                    }
                    //If king is selected
                    if(spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.YELLOW) && spotKing[row][col] == true)
                    {
                        try
                        {
                            if(spotRed[row+1][col+1] != true && spotBlack[row+1][col+1] != true)  //Spot to the right diagonal
                            {
                                spots[row+1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col-1] != true && spotBlack[row+1][col-1] != true)  //Spot to the left diagonal
                            {
                                spots[row+1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col+1] == true)  //Show possible jump of red piece to the right
                            {
                                spots[row+2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row+1][col-1] == true)  //Show possible jump of red piece to the left
                            {
                                spots[row+2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;

                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row-1][col+1] != true && spotBlack[row-1][col+1] != true) //Spot to the backright diagonal
                            {
                                spots[row-1][col+1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row-1][col-1] != true && spotBlack[row-1][col-1] != true) //Spot to the backleft diagonal
                            {
                                spots[row-1][col-1].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row-1][col-1] == true) //Possbile jump of red piece to the backright diagonal
                            {
                                spots[row-2][col-2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                        try
                        {
                            if(spotRed[row-1][col+1] == true) //Possbile jump of red piece to the backright diagonal
                            {
                                spots[row-2][col+2].setBackground(Color.GREEN); 
                                spotSelected[0] = row;
                                spotSelected[1] = col;
                            }
                        }
                        catch(Exception ex)
                        {
                            System.out.println(msg);
                        }
                    }
                }

            }
        }

        
        //Move piece 
        public void move(String[][] input)
        {
            for(int row = 0; row < ROW_SIZE; row++)
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    try
                    {
                        if(spotRed[spotSelected[0]][spotSelected[1]] == true && spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.GREEN)) //Moving red piece
                        {
                            redPieces.get(0).move();
                            spotRed[row][col] = true; //Move red piece
                            spotRed[spotSelected[0]][spotSelected[1]] = false; //Clear previous read piece
                            if(spotBlack[row+1][col+1] == true) //Jumping a black piece to the left
                            {
                                spotBlack[row+1][col+1] = false;
                                blackPieces.remove(0);
                            }
                            if(spotBlack[row+1][col-1] == true) //Jumpling a black piece to the right
                            {
                                spotBlack[row+1][col-1] = false;
                                blackPieces.remove(0);
                            }
                            if(spotKing[spotSelected[0]][spotSelected[1]] == true) 
                            {
                                spotKing[row][col] = true; //Move king 
                                spotKing[spotSelected[0]][spotSelected[1]] = false; //Clear king spot
                            }
                        }
                        else if(spotBlack[spotSelected[0]][spotSelected[1]] == true && spots[row][col].getText().equals(input[0][0]) && spots[row][col].getBackground().equals(Color.GREEN)) //moving black piece
                        {
                            spotBlack[row][col] = true;
                            spotBlack[spotSelected[0]][spotSelected[1]] = false;
                            if(spotRed[row-1][col-1] == true) //Jumping a red piece to the left
                            {
                                spotRed[row-1][col-1] = false;
                                redPieces.remove(0);
                            }
                            else if(spotRed[row-1][col+1] == true) //Jumping a red piece to the right
                            {
                                spotRed[row-1][col+1] = false;
                                redPieces.remove(0);
                            }
                            if(spotKing[spotSelected[0]][spotSelected[1]] == true) 
                            {
                                spotKing[row][col] = true; //Move king 
                                spotKing[spotSelected[0]][spotSelected[1]] = false; //Clear king spot
                            }
                            spotRed[spotSelected[0]][spotSelected[1]] = false; //Clear previous read piece
                        } 
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                }
            }
            
        }

        //Make King
        public void makeKing()
        {
            for(int row = 0; row < 1; row++) //Make any red kings
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spotRed[row][col] == true)
                    {
                        spotKing[row][col] = true;
                        System.out.println(spotKing[row][col]);
                    }
                }
            }

            for(int row = ROW_SIZE-1; row < ROW_SIZE; row++) //Make any black kings
            {
                for(int col = 0; col < COLUMN_SIZE; col++)
                {
                    if(spotBlack[row][col] == true)
                    {
                        spotKing[row][col] = true;
                        System.out.println(spotKing[row][col]);
                    }
                }
            }
        
        }

    /** 
     * Updates balances
     * Author Sophia Herrell
     * Date Created: 4/19/22
     */
    public void updateMoney()
    {
        UI.displayMoney.setText(fmt.format(PlayerCharacter.getWallet()));
        UI.displayDebt.setText(fmt.format(PlayerCharacter.getDebt()));

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

    }

}
