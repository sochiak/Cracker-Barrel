/**
 * --------------------------------------------------------------
 * File Name: Item.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name: Hunter Jones
 * Course: CSCI 1260
 * Creation Date: 3/1/22
 */

package Main;

/**
 * Class that creates the item object.
 *
 * Date Created: 3/1/22
 *
 * @author Hunter Jones and Sophia Herrell
 */

public class Item
{
    //attributes
    private String name;
    private String description;
    private double cost;

    /**
     * Parameterized constructor for item
     *
     * Date Created: 3/1/22
     *
     * @param name
     * @param cost
     * @param description
     */
    public Item(String name, double cost, String description)
    {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    /**
     * Returns name of item
     *
     * Date Created: 3/1/22
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns cost of item.
     *
     * Date Created: 3/1/22
     *
     * @return
     */
    public double getCost() {
        return cost;
    }
} //end Item