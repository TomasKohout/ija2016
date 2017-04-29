package ija.ija2016.model.gui;
import ija.ija2016.model.board.FactoryKlondike;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardDeckMethods;
import ija.ija2016.model.cards.CardStackMethods;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Gui {

    private static final int FRAME_WIDTH = 1280;
    private static final int FRAMW_HEIGH = 720;

    private JFrame mainFrame = new JFrame("Solitaire");
    private Container content;
    private JPanel panelOfAll = new JPanel();

    private GridBagLayout layoutOfBoard = new GridBagLayout();

    private JLabel[] workingDeck1 = new JLabel[2];
    private JLabel[] workingDeck2 = new JLabel[3];
    private JLabel[] workingDeck3 = new JLabel[4];
    private JLabel[] workingDeck4 = new JLabel[5];
    private JLabel[] workingDeck5 = new JLabel[6];
    private JLabel[] workingDeck6 = new JLabel[7];
    private JLabel[] workingDeck7 = new JLabel[8];

    private JLabel[] finalDeckClubs;
    private JLabel[] finalDeckDiamonds;
    private JLabel[] finalDeckHearts;
    private JLabel[] finalDeckSpades;

    private JLabel[] startDeck;
    private JLabel[] swapDeck;

    private FactoryKlondike fKlondike = new FactoryKlondike();

    private CardDeck playDeck = CardDeckMethods.createStandardDeck();

    private JLabel test;
    public Gui()
    {
        setUp();
    }

    public void setMainFrame ()
    {
        mainFrame.setVisible(true);
    }

    private void createFrame()
    {
        mainFrame.setSize(FRAME_WIDTH, FRAMW_HEIGH);
        mainFrame.setBounds(50, 50 ,1240, 700);
    }

    private void setUp()
    {
        createFrame();
        panelOfAll.setLayout(layoutOfBoard);
        panelOfAll.setBackground( new Color(12, 121, 5));
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < 28; i++) {

            //add first card to a first working deck
            if (i == 0) {
                workingDeck1[0] = playDeck.get(i).getJLabel();
                panelOfAll.add(workingDeck1[0]);
            }//add second and third card to a second working deck
            else if (i == 1 || i == 2) {
                for (int k = 0; k < 2; k++) {
                    workingDeck2[k] = playDeck.get(i++).getJLabel();
                    panelOfAll.add(workingDeck2[k]);
                }
            }//add fourth, fifth, sixth card to a third working deck
            else if (i >= 3 && i <= 5) {
                for (int k = 0; k < 3; k++) {
                    workingDeck3[k] = playDeck.get(i++).getJLabel();
                }
            } //add seventh, eight, ninth, tenth card to a fourth working deck
            else if (i >=  6 && i <= 9) {
                for (int k = 0; k < 4; k++) {
                    workingDeck4[k] = playDeck.get(i++).getJLabel();
                }
            }//add eleventh, twelfth, thirteenth, fourthteenth, fifthteenth to a fifth working deck
            else if (i >= 10 && i <= 14)
            {
                for (int k = 0; k < 5; k++) {
                    workingDeck5[k] = playDeck.get(i++).getJLabel();
                }
            }//add sixthteenth, seventeenth, eighteenth, nineteenth, twentieth, twenty-first to a sixth working deck
            else if (i >= 15 && i <= 20)
            {
                for (int k = 0; k < 6; k++) {
                    workingDeck6[k] = playDeck.get(i++).getJLabel();
                }
            }//add twenty-second, twenty-third, twenty-fourht, twenty-fifth, twenty-sixth, twenty-seventh, twenty-eight to a seventh working deck
            else if (i >= 21 && i <= 27)
            {
                for (int k = 0; k < 7; k++) {
                    workingDeck7[k] = playDeck.get(i++).getJLabel();
                }
            }
        }

        panelOfAll.setLayout(new FlowLayout());


        mainFrame.add(panelOfAll);
        mainFrame.getContentPane().validate();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}