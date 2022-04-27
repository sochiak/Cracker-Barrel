/**
 * --------------------------------------------------------------
 * File Name: FileReader.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 23, 2022
 */

package FileHandling;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class that reads a file.
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */
public class FileReader
{
    /**
     * Method that parses a file and saves the contents an array.
     *
     * Date Created: April 23, 2022
     *
     * @param path
     * @return
     */
    public String[] readFile(String path) {
        String[] playerInfo = new String[0];
        try {
            BufferedReader myFileReader = new BufferedReader(new java.io.FileReader(path));
            try {
                //reads 1 line from file
                String line = myFileReader.readLine();
                while (line != null) {
                    //separates data by commas
                    playerInfo = line.split(",");
                    //goes to next entry
                    //there should be only 1 line but if there are multiple lines the last line would be treated
                    // as the most recent save
                    line = myFileReader.readLine();
                } //end while
                myFileReader.close(); //close file
            } //end try
            catch (IOException ex) {
                System.out.println(ex);
            } finally {
                myFileReader.close();
            } //end catch
        } //end try
        catch (IOException ex) {
            System.out.println("File not found!");
        } //end catch
        return playerInfo; //returns string array to be interpreted elsewhere
    } //end readFile
} //end class