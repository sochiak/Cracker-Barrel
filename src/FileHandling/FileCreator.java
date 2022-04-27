/**
 * --------------------------------------------------------------
 * File Name: FileCreator.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 23, 2022
 */

package FileHandling;

import Main.PlayerCharacter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Fills a text file with player attributes.
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */
public class FileCreator {
    /**
     * Converts player attributes into a string separated by commas
     *
     * Date Created: April 23, 2022
     *
     * @param player
     * @param fileName
     * @param separator
     */
    public void textFileWriter(PlayerCharacter player, String fileName, char separator)
    {
        try(FileWriter myWriter = new FileWriter(fileName))
        {
            //creates empty strings to prevent null pointer exception
            String item1 = " ";
            String item2 = " ";
            String item3 = " ";

            //if the player has items in their inventory it reassigns the string names
            if(player.inventory.size() > 0) {item1 = player.inventory.get(0).getName();}
            if(player.inventory.size() > 1) {item2 = player.inventory.get(1).getName();}
            if(player.inventory.size() > 2) {item3 = player.inventory.get(2).getName();}

            //put contents of each field separated by the separator in a String
            String temp = "" + player.getEnergy() + separator +
                    player.inventory.size() + separator +
                    item1 + separator +
                    item2 + separator +
                    item3 + separator +
                    PlayerCharacter.debt + separator +
                    PlayerCharacter.wallet + separator +
                    player.getLocation()
                    ;
            //write the String to the file
                myWriter.write(temp);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}