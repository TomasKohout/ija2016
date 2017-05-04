package ija.ija2016.model.gui;


/**
 * Created by tom on 2.5.17.
 */
public interface Command {
    boolean execute();
    void undo();
}
