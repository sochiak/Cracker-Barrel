/**
 * --------------------------------------------------------------
 * File Name: FileActions.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 23, 2022
 */

package FileHandling;

import Main.GameManager;
import Main.PlayerCharacter;
import javax.swing.*;
import java.io.File;

/**
 * Class that utilizes the FileCreator and FileReader classes.
 * I made a separate class for everything that interacts with other parts of the game like UI and player.
 *
 * Date Created: April 23, 2022
 *
 * @author Sophia Herrell
 */
public class FileActions
{
    //This is where the window that allows you to select a file will open by default.
    public static final String FILE_PATH = "/Users/sophiaherrell/IdeaProjects/CrackerBarrelSim/src/FileHandling/";

    GameManager gm;

    final JFileChooser fileChooser = new JFileChooser();
    public FileReader fileReader = new FileReader();
    public String[] stats;

    /**
     * Constructor for FileActions.
     *
     * Date Created: April 23, 2022
     *
     * @param gm
     */
    public FileActions(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Creates file chooser and allows player to select a save file to read.
     *
     * Date Created: April 23, 2022
     *
     * @return
     */
    public String[] readFile()
    {
        fileChooser.setCurrentDirectory(new File(FILE_PATH)); //determines where the file chooser opens initially
        int response = fileChooser.showSaveDialog((null));
        if(response == JFileChooser.APPROVE_OPTION){ //if user selects file, pass into the filer reader
            stats = fileReader.readFile(fileChooser.getSelectedFile().getAbsolutePath());
        }
        return stats; //return array of player info
    }

    /**
     * Creates the FileCreator and passes an empty file in
     *
     * Date Created: April 23, 2022
     */
    public void writeFile()
    {
        FileCreator textWriter = new FileCreator();
        textWriter.textFileWriter(gm.player, FILE_PATH + "NewSave", ',' );
    }

    /**
     * Uses the data extracted in readFile() to set player attributes to
     * those in the save file.
     *
     * Date Created: April 23, 2022
     *
     * @param stats
     */
    public void createSavedGame(String [] stats)
    {
        int energy = Integer.parseInt(stats[0]);
        int itemNum = Integer.parseInt(stats[1]);
        String item1Name = stats[2];
        String item2Name = stats[3];
        String item3Name = stats[4];
        double debt = Double.parseDouble(stats[5]);
        double wallet = Double.parseDouble(stats[6]);
        PlayerCharacter.Location location = PlayerCharacter.Location.valueOf(stats[7]);

        gm.player.setStats(energy, itemNum, item1Name, item2Name, item3Name, debt, wallet, location);
    }
}