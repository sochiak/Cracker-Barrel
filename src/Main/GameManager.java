/**
 * --------------------------------------------------------------
 *   File Name: GameManager.java
 *   Project Name: Final Semester Project
 *   --------------------------------------------------------------
 *   Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 *   Course: CSCI 1260
 *   Creation Date: April 5, 2022
 */

package Main;
import Event.*;
import FileHandling.FileActions;

/**
 * Driver class for Cracker Barrel Simulator
 *
 * Date created: April 5, 2022
 *
 * @author Sophia Herrell
 */

public class GameManager
{
    ActionHandler ah = new ActionHandler(this);
    //because I had different packages they need to be public to access each other
    public UI ui = new UI(this);
    public PlayerCharacter player = new PlayerCharacter(this);
    public ScreenChanger sc = new ScreenChanger(this);
    public MiniGameEvent miniGameEvent = new MiniGameEvent(this);
    public GiftShopEvent giftShopEvent = new GiftShopEvent(this);
    public RegisterEvent registerEvent = new RegisterEvent(this);
    public DiningRoomEvent diningRoomEvent = new DiningRoomEvent(this);
    public KitchenEvent kitchenEvent = new KitchenEvent(this);
    public FileActions fileActions = new FileActions(this);

    //create items/food
    public Item sword = new Item("Sword", 3.00, "A sharp sword");
    public Food cookie = new Food("Cookie", "A gooey chocolate chip cookie", 1, 15, Food.TypeOfFood.GiftShop);
    public Food candy = new Food("Candy","Banana flavored saltwater taffy", .5, 10, Food.TypeOfFood.GiftShop);

    /**
     * Main method that creates GameManager
     *
     * Date Created: April 5, 2022
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new GameManager();
    } //end main()

    /**
     * Constructor for GameManager
     *
     * Date Created: April 5, 2022
     */
    public GameManager()
    {
        player.setDefaults();
        sc.showScreen1();
        sc.updateEnergy(player.getEnergy());
    }
} //end GameManager