/**
 * --------------------------------------------------------------
 * File Name: RegisterEvent.java
 * Project Name: Final Semester Project
 * --------------------------------------------------------------
 * Creator's Name and Email: Sophia Herrell, herrells@etsu.edu
 * Course: CSCI 1260
 * Creation Date: April 12, 2022
 */

package Event;

import Main.*;
import javax.swing.*;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Class containing methods that ActionHandler routes to concerning the Register.
 *
 * Date Created: April 12, 2022
 *
 * @author Sophia Herrell and Hunter Jones
 */
public class RegisterEvent
{
    GameManager gm;
    private static final NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Constructor for RegisterEvent
     *
     * Date Created: April 12, 2022
     *
     * @param gm
     */
    public RegisterEvent(GameManager gm)
    {
        this.gm = gm;
    }

    /**
     * Allows user to order food
     *
     * Date Created: April 19, 2022
     */
    public void orderMeal()
    {
        SwingUtilities.invokeLater
                (
                        () -> new Menu(gm)
                );
        gm.player.decreaseEnergy(2);
    }

    /**
     * Allows user to pay for food if they have earned enough money
     *
     * Date Created: April 19, 2022
     */
    public void pay()
    {
        if(PlayerCharacter.debt > 0)
        {
            if(PlayerCharacter.wallet >= PlayerCharacter.debt)
            {
                double payment = PlayerCharacter.debt;

                gm.player.ameliorateDebt(payment);
                gm.player.loseMoney(payment);
                gm.player.updateMoney();
                gm.sc.showYouWin();
                gm.ui.messageText.setText("I see you've somehow acquired the funds to escape indentured servitude. " +
                        "I suppose you expect me to congratulate you... next time, you won't be so lucky.");
            }
            else if(PlayerCharacter.wallet < PlayerCharacter.debt)
            {
                double payment = PlayerCharacter.wallet;
                gm.player.ameliorateDebt(payment);
                gm.player.loseMoney(payment);
                gm.ui.messageText.setText("Erm... this isn't everything... you still owe " + fmt.format(PlayerCharacter.debt));
                gm.player.updateMoney();
            }
        }
        else
        {
            gm.ui.messageText.setText("What are you trying to pay for? You haven't ordered anything.");
        }
        gm.player.decreaseEnergy(1);
    }

    /**
     * Hunter Jones
     * Summons the manager and triggers a random response
     *
     * Date Created: April 26, 2022
     */
    public void complain()
    {
        String[] responses = new String[4];
        responses[0] = ("The manager is not currently available, maybe try again later. Or just leave. We don't care.");
        responses[1] = ("*Manager walks over* What seems to be the issue?... oh... that seems like a you problem. Bye!");
        responses[2] = ("*Manager walks over* What seems to be the issue?... oh... sorry for the trouble. Maybe Momma's pancakes will make you happier!");
        responses[3] = ("You rang the bell, trying to complain but no one responded... now that you think about it, you don't remember anything... did they erase your memory?...");
        Random rand = new Random();
        int i = rand.nextInt(4);
        gm.ui.messageText.setText(responses[i]);
    }
}