/**
 *   --------------------------------------------------------------
 *   File Name: ActionHandler.java
 *   Project Name: Final Semester Project
 *   --------------------------------------------------------------
 *   Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 *   Course: CSCI 1260
 *   Creation Date: April 6, 2022
 */

package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Handles events for the UI class; routes to Event01 and ScreenChanger
 *
 * Date Created: April 6, 2022
 *
 * @author Sophia Herrell
 */
public class ActionHandler implements ActionListener
{
    GameManager gm;
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public String[] stats;

    public ActionHandler(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Uses a switch to route actions
     *
     * Date Created: April 6, 2022
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String choice = e.getActionCommand();

        switch(choice)
        {
            //outside
            case "leave":
                gm.ui.messageText.setText("Taking the coward's way out, eh? Well, Congratulations... you have " +
                        "evaded Cracker Barrel and won the game... for now.");
                gm.sc.showGameOver();
                break;

            //change screen
            case "goScreen1": //outside
                gm.sc.showScreen1();
                break;
            case "goScreen2":
                gm.sc.showScreen2();
                gm.ui.messageText.setText("The colors, shapes, and noises overwhelm you. Somewhere distant, " +
                        "you can smell the faint aroma of mac and cheese. There's a strange door and a " +
                        "number of wares to peruse.");
                break;
            case "goScreen3":
                gm.sc.showScreen3();
                gm.ui.messageText.setText("This looks like the place to buy food. Why not? You" +
                        " are hungry, after all.");
                break;
            case "goScreen4":
                gm.sc.showScreen4();
                gm.ui.messageText.setText("Vintage arcade games? What are these doing in Cracker Barrel? " +
                        "It looks like someone was playing checkers.");
                break;
            case "goScreen5":
                gm.sc.showScreen5();
                gm.ui.messageText.setText("The scent of mac and cheese grows stronger and intermingles with an intoxicating " +
                        "variety of aromas. It clings to your clothing and penetrates your subconscious. " +
                        "All around you, the elderly feast on signature dishes.");
                break;
            case "goScreen6":
                gm.sc.showScreen6();
                gm.ui.messageText.setText("The clattering and shouting of ex-convicts causes you to become dizzy. " +
                        "You're surrounded on all sides by counter tops, ingredients, and utensils. What was that " +
                        "scuttering in the corner of your eye?");
                break;

            //gift shop
            case "inspectToyShelf": gm.giftShopEvent.inspectToyShelf(); break;
            case "selectToy": gm.giftShopEvent.selectToy(); break;
            case "returnToy": gm.giftShopEvent.returnToy(); break;
            case "inspectFoodShelf": gm.giftShopEvent.inspectFoodShelf(); break;
            case "selectFood": gm.giftShopEvent.selectFood(); break;
            case "returnFood": gm.giftShopEvent.returnFood(); break;
            case "inspectCandyShelf": gm.giftShopEvent.inspectCandyShelf(); break;
            case "selectCandy": gm.giftShopEvent.selectCandy(); break;
            case "returnCandy": gm.giftShopEvent.returnCandy(); break;

            //mini games
            case "inspectArcDoor": gm.ui.messageText.setText("You peak inside the cracked door and see flashing " +
                    "lights... could there be a way to make some cash inside?"); break;
            case "playMemoryGm":
                try {
                    gm.miniGameEvent.playMemoryGm();
                } catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case "playTicTacToe": gm.miniGameEvent.playTicTacToe(); break;
            case "playCheckers":
                try {
                    gm.miniGameEvent.playCheckers();
                } catch (UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException | InstantiationException ex) {
                    ex.printStackTrace();
                }
                break;
            //register
            case "orderMeal":
                gm.registerEvent.orderMeal();
                break;
            case "pay":
                gm.registerEvent.pay();
                break;
            case "complain":
                gm.registerEvent.complain();
                break;

            //dining room
            case "playPegGame": gm.diningRoomEvent.playPegGame(); break;
            case "converse": gm.diningRoomEvent.converse(); break;
            case "consumeInventory":
                if(gm.player.inventory.size() > 0)
                    gm.diningRoomEvent.consumeInventory();
                else
                    gm.ui.messageText.setText("You don't have any food...");
                break;

            //buttons
            case "question": gm.ui.messageText.setText("Confused, are you? Unsurprising... I am SICK and TIRED of " +
                    "kids thinking they can waltz into Cracker Barrel and leave without ordering, or, worse, without" +
                    " PAYING. It is RUDE and DISRESPECTFUL. If you want to leave, you will order your food, sit down," +
                    " have a nice meal, pay, and tip generously."); break;

            case "saveFile":
                gm.ui.messageText.setText("Game saved.");
                gm.fileActions.writeFile();
                break;
            case "uploadFile":
                gm.ui.messageText.setText("Oh, hello. Back again?");
                stats = gm.fileActions.readFile();
                gm.fileActions.createSavedGame(stats);
                break;

            //kitchen
            case "washDishes":
                gm.ui.messageText.setText("Washing dishes...");
                executorService.schedule(gm.kitchenEvent::washDishes, 4, TimeUnit.SECONDS);
                break;

        } //end switch
    } //end action performed
} //end ActionHandler