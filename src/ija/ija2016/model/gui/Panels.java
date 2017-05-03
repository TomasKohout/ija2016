package ija.ija2016.model.gui;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

/**
 * Created by tom on 3.5.17.
 */
public class Panels {
    private static final int FRAME_WIDTH = 720;
    private static final int FRAME_HEIGH = 500;

    public static JFrame mainFrame;
    public static JPanel panelOfAll;
    private JPanel game1;
    private JPanel game2;
    private JPanel game3;
    private JPanel game4;

    private JButton newGame;

    private Gui gameLogic1;
    private Gui gameLogic2;
    private Gui gameLogic3;
    private Gui gameLogic4;

    private GridBagConstraints gbc;

    public Panels()
    {
        mainFrame = new JFrame("Rébus Solitaire");
        panelOfAll = new JPanel(null);
        panelOfAll.setBackground( new Color(12, 121, 5));
        panelOfAll.setSize(FRAME_WIDTH * 2, FRAME_HEIGH * 2);
        mainFrame.setLayout(null);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGH);
        mainFrame.setResizable(false);
        mainFrame.setBounds(50, 50 ,FRAME_WIDTH*2, FRAME_HEIGH * 2);
        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newGame = new JButton("New Game");
        newGame.setFont(new Font("Lucida Grande", 1, 10));
        newGame.setBounds(200,10,80,30);
        newGame.setMargin(new Insets(0,0,0,0));
        newGame.addActionListener(new NewGameButton());

        game1 = new JPanel(null);
        game1.setSize(FRAME_WIDTH, FRAME_HEIGH);
        game1.setBackground(new Color(12,121,5));
        game1.setLocation(0,0);
        mainFrame.add(newGame);
        panelOfAll.add(game1);
        gameLogic1 = new Gui(game1);

    }

    public static void repaint()
    {
        Panels.mainFrame.add(Panels.panelOfAll);
        Panels.mainFrame.getContentPane().revalidate();
        Panels.mainFrame.getContentPane().repaint();
    }
    protected class NewGameButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game1 == null)
            {
                game1 = new JPanel(null);
                game1.setSize(FRAME_WIDTH, FRAME_HEIGH);
                game1.setBackground(new Color(12,121,5));
                panelOfAll.add(game1);
                gameLogic1 = new Gui(game1);
                Panels.mainFrame.add(Panels.panelOfAll);
                Panels.mainFrame.getContentPane().revalidate();
                Panels.mainFrame.getContentPane().repaint();
            }
            else if (game2 == null)
            {
                game2 = new JPanel(null);
                game2.setSize(FRAME_WIDTH, FRAME_HEIGH);
                game2.setBackground(new Color(12,121,5));
                game2.setLocation(FRAME_WIDTH,0);
                panelOfAll.add(game2);
                gameLogic2 = new Gui(game2);
                Panels.mainFrame.add(Panels.panelOfAll);
                Panels.mainFrame.getContentPane().revalidate();
                Panels.mainFrame.getContentPane().repaint();
            }
            else if (game3 == null)
            {
                game3 = new JPanel(null);
                game3.setSize(FRAME_WIDTH, FRAME_HEIGH);
                game3.setBackground(new Color(12,121,5));
                game3.setLocation(0, FRAME_HEIGH);
                panelOfAll.add(game3);
                gameLogic3 = new Gui(game3);
                Panels.mainFrame.add(Panels.panelOfAll);
                Panels.mainFrame.getContentPane().revalidate();
                Panels.mainFrame.getContentPane().repaint();
            }
            else if (game4 == null)
            {
                game4 = new JPanel(null);
                game4.setSize(FRAME_WIDTH, FRAME_HEIGH);
                game4.setBackground(new Color(12,121,5));
                game4.setLocation(FRAME_WIDTH,FRAME_HEIGH);
                panelOfAll.add(game4);
                gameLogic4 = new Gui(game4);
                Panels.mainFrame.add(Panels.panelOfAll);
                Panels.mainFrame.getContentPane().revalidate();
                Panels.mainFrame.getContentPane().repaint();
            }
        }
    }
}
