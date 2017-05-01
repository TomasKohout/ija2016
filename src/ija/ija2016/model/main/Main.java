package ija.ija2016.model.main;
import javax.swing.*;

import ija.ija2016.model.gui.Gui;

public class Main {
    public static void main(String[] args) {
        try {
            Gui window = new Gui();
            window.setMainFrame();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}