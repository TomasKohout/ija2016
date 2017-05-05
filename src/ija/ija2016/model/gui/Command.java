/**
 * Interface which declares methods for execute and undo moves from Command design pattern.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */

package ija.ija2016.model.gui;


/**
 * Interface which declares methods for execute and undo moves from Command design pattern.
 */
public interface Command {
    boolean execute();
    void undo();
}
