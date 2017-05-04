package ija.ija2016.model.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 3.5.2017.
 */
public class Invoker {
    private List<Command> orderList = new ArrayList<Command>();

    public void takeOrder(Command order){
        if(order.execute()) {
            orderList.add(order);
        }
    }

    public void undo(){
        if (orderList.isEmpty())
            return;
        Command move = orderList.remove(orderList.size()-1);
        move.undo();
    }
}
