/**
 * Main Class witch creates main window panel, where is everything about game displayed.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */


package ija.ija2016.model.main;

import ija.ija2016.model.gui.Panels;

public class Main {
    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        try {
            Panels window = new Panels();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}