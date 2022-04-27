package Checkers;

/**
 * ---------------------------------------------------------------------------
 * File name: CheckersDriver.java
 * Author: Charlie Shahan
 * Purpose: Play Checkers game 
 */

import javax.swing.SwingUtilities;


public class CheckersDriver
{
    public static void main (String[] args)
    {
        
            SwingUtilities.invokeLater
            (
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        new Checkers();   
                    }
                }
            ); 
    }  
}

