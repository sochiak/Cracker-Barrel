package Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MemoryGame extends JFrame
{

    private static final long serialVersionUID = 1L;
    //Creating the GUI
    private JPanel panel;
    private JLabel messageLabel;
    private JButton cyanButton;
    private JButton magentaButton;
    private JButton orangeButton;
    //Width + Height of the GUI Window
    private final int WINDOW_WIDTH = 270;
    private final int WINDOW_HEIGHT = 110;
    //Array for the order of colors
    private Integer[] colors = new Integer[11];
    //Variable for random number generator
    private int count = 0;

    public MemoryGame()
    {
        super("Memory Game");

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Title for GUI
        messageLabel = new JLabel("Click The Colors In The Correct Order: ");
        //All the details for the buttons
        cyanButton = new JButton("Cyan");
        cyanButton.setFocusPainted(false);
        cyanButton.setBackground(Color.CYAN);
        cyanButton.setForeground(Color.WHITE);
        magentaButton = new JButton("Magenta");
        magentaButton.setFocusPainted(false);
        magentaButton.setBackground(Color.MAGENTA);
        magentaButton.setForeground(Color.WHITE);
        orangeButton = new JButton("Orange");
        orangeButton.setFocusPainted(false);
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setForeground(Color.WHITE);
        //Used to record the users input
        cyanButton.addActionListener(new cyanButtonListener());
        magentaButton.addActionListener(new magentaButtonListener());
        orangeButton.addActionListener(new orangeButtonListener());
        //Creating the panels for the actual window
        panel = new JPanel();
        panel.add(messageLabel);
        panel.add(cyanButton);
        panel.add(magentaButton);
        panel.add(orangeButton);

        add(panel);
        //Sets it to the middle
        setLocationRelativeTo(null);
        //Shows the window
        setVisible(true);

        //For loop for the random number generator
        for(int i = 0; i < 11; i++)
        {

            Random rand = new Random(); //instance of random class
            int upperbound = 3;
            //generate random values from 0-2
            colors[i] = rand.nextInt(upperbound);
        }
        System.out.println("-----Cyan = 0, Magenta = 1, Orange = 2-----");
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
        }


    }

    //Allows the program to track what the user is doing when they click cyan
    private class cyanButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            cyanButton.setBackground(Color.CYAN);
            if (colors[count] == 0)
            {
                panel.setBackground(Color.GREEN);
            }
            //Lose condition
            else{
                panel.setBackground(Color.RED);
                System.out.println("You lose!");
                //System.exit(0);
            }
            count++;
            //Win condition
            if (count == 11) {
                System.out.println("You win!");
                //System.exit(0);
            }
        }
    }
    //Allows the program to track what the user is doing when they click magenta
    private class magentaButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            magentaButton.setBackground(Color.MAGENTA);
            if (colors[count] == 1)
            {
                panel.setBackground(Color.GREEN);
            }
            //Lose condition
            else{
                panel.setBackground(Color.RED);
                System.out.println("You lose!");
                //System.exit(0);
            }
            count++;
            //Win condition
            if (count == 11) {
                System.out.println("You win!");
                //System.exit(0);
            }
        }
    }
    //Allows the program to track what the user is doing when they click orange
    private class orangeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            orangeButton.setBackground(Color.ORANGE);
            if (colors[count] == 2)
            {
                panel.setBackground(Color.GREEN);
            }
            //Lose condition
            else
            {
                panel.setBackground(Color.RED);
                System.out.println("You lose!");
                //System.exit(0);
            }
            count++;
            //Win condition
            if (count == 11) {
                System.out.println("You win!");
                //System.exit(0);
            }
        }
    }
}