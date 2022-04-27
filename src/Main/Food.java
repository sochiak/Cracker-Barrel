/**
 * --------------------------------------------------------------
 * File Name: Food.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 13, 2022
 */

package Main;

/**
 * Creates the Food object.
 *
 * Date Created: April 13, 2022
 *
 * @author Sophia Herrell
 */
public class Food extends Item
{
    //attributes
    int energy;
    TypeOfFood foodType;

    enum TypeOfFood
    {
        GiftShop,
        Drink,
        Kitchen,
    }

    /**
     * Parameterized constructor for food
     *
     * Date Created: April 13, 2022
     *
     * @param name
     * @param description
     * @param price
     * @param energy
     * @param foodType
     */
    public Food(String name, String description, double price, int energy, TypeOfFood foodType)
    {
        super(name, price, description);
        this.energy = energy;
        this.foodType = foodType;
    }

    /**
     * Returns amount energy gained by eating food.
     *
     * Date Created: April 13, 2022
     *
     * @return
     */
    public int getEnergy() {
        return energy;
    }
}