package ija.ija2016.model.gui;

import javafx.scene.layout.Pane;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
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

    private JButton exitGame1;
    private JButton exitGame2;
    private JButton exitGame3;
    private JButton exitGame4;

    private JButton newGame;

    private Gui gameLogic1;
    private Gui gameLogic2;
    private Gui gameLogic3;
    private Gui gameLogic4;

    public Panels()
    {
        mainFrame = new JFrame("Rébus Solitaire");
        panelOfAll = new JPanel(null);
        panelOfAll.setBackground( new Color(12, 121, 5));
        panelOfAll.setSize(FRAME_WIDTH * 2, FRAME_HEIGH * 2);

        mainFrame.setLayout(null);

        mainFrame.setResizable(false);
        setSingleSize();
          mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newGame = new JButton("New Game");
        newGame.setFont(new Font("Lucida Grande", 1, 10));
        newGame.setBounds(260,0,80,20);
        newGame.setMargin(new Insets(0,0,0,0));
        newGame.addActionListener(new NewGameButton());

        game1 = new JPanel(null);
        game1.setSize(FRAME_WIDTH, FRAME_HEIGH);
        game1.setBackground(new Color(12,121,5));
        game1.setLocation(0,0);
        exitGame1 = createButton();
        game1.add(exitGame1);
        mainFrame.add(newGame);
        panelOfAll.add(game1);
        gameLogic1 = new Gui(game1, exitGame1);

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
                game1 = createPanel();
                game1.setSize(FRAME_WIDTH, FRAME_HEIGH);
                panelOfAll.add(game1);
                gameLogic1 = new Gui(game1, exitGame1);
            }
            else if (game2 == null)
            {
                game2 = createPanel();
                game2.setLocation(FRAME_WIDTH,0);
                exitGame2 = createButton();
                game1.add(exitGame2);
                panelOfAll.add(game2);
                gameLogic2 = new Gui(game2, exitGame2);
            }
            else if (game3 == null) {

                game3 = createPanel();
                game3.setLocation(0, FRAME_HEIGH);
                exitGame3 = createButton();
                game3.add(exitGame3);
                panelOfAll.add(game3);
                gameLogic3 = new Gui(game3, exitGame3);
            }
            else if (game4 == null)
            {
                game4 = createPanel();
                game4.setLocation(FRAME_WIDTH,FRAME_HEIGH);
                exitGame4 = createButton();
                game1.add(exitGame4);
                panelOfAll.add(game4);
                gameLogic4 = new Gui(game4, exitGame4);
            }
            setSize();
            repaint();
        }
    }
    private void setDoubleSize()
    {
        panelOfAll.setSize(FRAME_WIDTH * 2, FRAME_HEIGH * 2);
        mainFrame.setBounds(50, 50 ,FRAME_WIDTH*2, FRAME_HEIGH * 2);
        repaint();
    }

    private void setSingleSize()
    {
        panelOfAll.setSize(FRAME_WIDTH, FRAME_HEIGH);
        mainFrame.setBounds(50, 50 ,FRAME_WIDTH, FRAME_HEIGH);
        repaint();

    }
    private void setSize()
    {
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    if(game1 == null)
                        count++;
                    break;
                case 1:
                    if (game2 == null)
                        count++;
                    break;
                case 2:
                    if (game3  == null)
                        count++;
                    break;
                case 3:
                    if (game4 == null)
                        count++;
                    break;
            }
        }

        if (count == 3 && game1 != null)
            setSingleSize();
        else if (game1 == null && game2 == null && game3 == null && game4 == null)
            setSingleSize();
        else
            setDoubleSize();

    }
    private JButton createButton()
    {
        JButton button;
        button = new JButton("Exit");
        button.setFont(new Font("Lucida Grande", 1, 10));
        button.setBounds(160,0,50,20);
        button.setMargin(new Insets(0,0,0,0));
        button.addActionListener(new ExitGame());
        return button;
    }

    private JPanel createPanel()
    {
        JPanel panel;
        panel = new JPanel(null);
        panel.setSize(FRAME_WIDTH, FRAME_HEIGH);
        panel.setBackground(new Color(12,121,5));
        return panel;
    }
    protected class ExitGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object tmp = e.getSource();
            if (tmp instanceof JButton)
            {
                System.out.println("in");
                if(exitGame1 == (JButton)tmp)
                {
                    panelOfAll.remove(game1);
                        game1 = null;
                        gameLogic1 = null;
                }
                else if (exitGame2 == (JButton)tmp)
                {
                    panelOfAll.remove(game2);

                        game2 = null;
                        gameLogic2 = null;

                }
                else if (exitGame3 == (JButton)tmp) {
                    panelOfAll.remove(game3);

                        game3 = null;
                        gameLogic3 = null;

                }
                else if (exitGame4 == (JButton)tmp)
                {
                    panelOfAll.remove(game4);
                    game4 = null;
                    gameLogic4 = null;
                }

                setSize();
                repaint();
            }

        }

    }
}
