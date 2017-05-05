/**
 * Class which invoke methods for transfers between decks and adding them to list. Undo function use that list for
 * doing operations backwards.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which invoke methods for transfers between decks and adding them to list. Undo function use that list for
 * doing operations backwards.
 */
public class Invoker {
    private List<Command> orderList = new ArrayList<Command>();

    /**
     * Method which take order means like type of move. Execute that move and if its valid then push it to the top of array.
     * @param order Move
     */
    public void takeOrder(Command order){
        if(order.execute()) {
            orderList.add(order);
        }
    }

    /**
     * Method which takes move from top of array and doing them with switch source and destination positions.
     */
    public void undo(){
        if (orderList.isEmpty())
            return;
        Command move = orderList.remove(orderList.size()-1);
        move.undo();
    }
}
