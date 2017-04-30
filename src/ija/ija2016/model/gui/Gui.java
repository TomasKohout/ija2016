package ija.ija2016.model.gui;
import ija.ija2016.model.board.FactoryKlondike;
import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui {

    private static final int FRAME_WIDTH = 720;
    private static final int FRAME_HEIGH = 500;

    private static final int CARD_WIDTH = 80;
    private static final int CARD_HEIGH = 110;


    private JFrame mainFrame = new JFrame("Rébus Solitaire");
    private JPanel panelOfAll = new JPanel(null);

    private FactoryKlondike factory = new FactoryKlondike();

    private CardStack workingDeck1 = factory.createWorkingPack();
    private CardStack workingDeck2 = factory.createWorkingPack();
    private CardStack workingDeck3 = factory.createWorkingPack();
    private CardStack workingDeck4 = factory.createWorkingPack();
    private CardStack workingDeck5 = factory.createWorkingPack();
    private CardStack workingDeck6 = factory.createWorkingPack();
    private CardStack workingDeck7 = factory.createWorkingPack();

    private JLabel[] workingLabel1 = new JLabel[25];
    private JLabel[] workingLabel2 = new JLabel[25];
    private JLabel[] workingLabel3 = new JLabel[25];
    private JLabel[] workingLabel4 = new JLabel[25];
    private JLabel[] workingLabel5 = new JLabel[25];
    private JLabel[] workingLabel6 = new JLabel[25];
    private JLabel[] workingLabel7 = new JLabel[25];

    private JLabel[] mainLabel = new JLabel[53];
    private JLabel[] swapLabel = new JLabel[25];

    private CardStack swapDeck = factory.createWorkingPack();
    private CardDeck mainDeck = factory.createCardDeck();

    private CardDeck diamondTargetDeck = factory.createTargetPack(Card.Color.DIAMONDS);
    private CardDeck heartTargetDeck = factory.createTargetPack(Card.Color.HEARTS);
    private CardDeck clubTargetDeck = factory.createTargetPack(Card.Color.CLUBS);
    private CardDeck spadTargetDeck = factory.createTargetPack(Card.Color.SPADES);

    private JLabel[] targetLabel1 = new JLabel[14];
    private JLabel[] targetLabel2 = new JLabel[14];
    private JLabel[] targetLabel3 = new JLabel[14];
    private JLabel[] targetLabel4 = new JLabel[14];

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
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGH);
        mainFrame.setBounds(50, 50 ,FRAME_WIDTH, FRAME_HEIGH);
    }

    private void setUp()
    {
        createFrame();
        ImageIcon whiteBorder = createImageIcon("../../../../images/border.png");

        panelOfAll.setBackground( new Color(12, 121, 5));
        panelOfAll.setSize(FRAME_WIDTH, FRAME_HEIGH);


        Configuration actionListener = new Configuration();

        //SET WORKING PACKS
        workingDeck1.forcePut(turnCardUp(mainDeck.pop()),"w1");
        workingLabel1[0] = workingDeck1.get(0).getJLabel();
        workingLabel1[0].setBounds(10, 300, CARD_WIDTH, CARD_HEIGH);

        workingLabel1[0].addMouseListener(actionListener);
        panelOfAll.add(workingLabel1[0]);

        for (int k = 1, i = 0; k >= 0; k--, i++) {
            if (k != 1)
                workingDeck2.forcePut(mainDeck.pop(), "w2");
            else
                workingDeck2.forcePut(turnCardUp(mainDeck.pop()),"w2");

            workingLabel2[k] = workingDeck2.get(i).getJLabel();
            workingLabel2[k].setBounds(110, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel2[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel2[k]);
        }

        for (int k = 2, i = 0; k >= 0; k--, i++) {
            if (k != 2)
                workingDeck3.forcePut(mainDeck.pop(), "w3");
            else
                workingDeck3.forcePut(turnCardUp(mainDeck.pop()),"w3");


            workingLabel3[k] = workingDeck3.get(i).getJLabel();
            workingLabel3[k].setBounds(210, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel3[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel3[k]);
        }

        for (int k = 3, i = 0; k >= 0; k--, i++) {
            if (k != 3)
                workingDeck4.forcePut(mainDeck.pop(), "w4");
            else
                workingDeck4.forcePut(turnCardUp(mainDeck.pop()), "w4");

            workingLabel4[k] = workingDeck4.get(i).getJLabel();
            workingLabel4[k].setBounds(310, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel4[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel4[k]);
        }

        for (int k = 4, i = 0; k >= 0; k-- , i++) {
            if (k != 4)
                workingDeck5.forcePut(mainDeck.pop(), "w5");
            else
                workingDeck5.forcePut(turnCardUp(mainDeck.pop()), "w5");
            workingLabel5[k] = workingDeck5.get(i).getJLabel();
            workingLabel5[k].setBounds(410, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel5[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel5[k]);
        }

        for (int k = 5, i = 0; k >= 0; k--, i++) {
            if (k != 5)
                workingDeck6.forcePut(mainDeck.pop(), "w6");
            else
                workingDeck6.forcePut(turnCardUp(mainDeck.pop()), "w6");
            workingLabel6[k] = workingDeck6.get(i).getJLabel();
            workingLabel6[k].setBounds(510, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel6[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel6[k]);
        }

        for (int k = 6, i = 0; k >= 0; k--, i++) {
            if (k != 6)
                workingDeck7.forcePut(mainDeck.pop(), "w7");
            else
                workingDeck7.forcePut(turnCardUp(mainDeck.pop()), "w7");
            workingLabel7[k] = workingDeck7.get(i).getJLabel();
            workingLabel7[k].setBounds(610, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel7[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel7[k]);
        }
        //SET WORKING PACKS END

        //ADD BORDERS
        mainLabel[0] = new JLabel(whiteBorder);
        mainLabel[0].setText("wb-m");
        mainLabel[0].setBounds(10, 10, CARD_WIDTH, CARD_HEIGH);
        for (int k = mainDeck.size()-1; 0 <= k; k--){
            mainLabel[k+1] = mainDeck.get(k).getJLabel();
            mainLabel[k+1].setBounds(10, 10, CARD_WIDTH, CARD_HEIGH);
            mainLabel[k+1].addMouseListener(actionListener);
            mainLabel[k+1].setText(mainDeck.get(k).toString() + "-" + "m");
        }
        panelOfAll.add(mainLabel[mainDeck.size()-1]);

        swapLabel[0] = new JLabel(whiteBorder);
        swapLabel[0].setText("wb-s");
        swapLabel[0].setFont(new Font("Lucida Grande",1,0));
        swapLabel[0].setBounds(110, 10, CARD_WIDTH+5, CARD_HEIGH);
        swapLabel[0].addMouseListener(actionListener);
        panelOfAll.add(swapLabel[0]);

        targetLabel1[0] = new JLabel(whiteBorder);
        targetLabel1[0].setText("wb-t1");
        targetLabel1[0].setFont(new Font("Lucida Grande",1,0));
        targetLabel1[0].setBounds(310, 10, CARD_WIDTH+5, CARD_HEIGH);
        targetLabel1[0].addMouseListener(actionListener);
        panelOfAll.add(targetLabel1[0]);

        targetLabel2[0] = new JLabel(whiteBorder);
        targetLabel2[0].setText("wb-t2");
        targetLabel2[0].setFont(new Font("Lucida Grande",1,0));
        targetLabel2[0].setBounds(410, 10, CARD_WIDTH+5, CARD_HEIGH);
        targetLabel2[0].addMouseListener(actionListener);
        panelOfAll.add(targetLabel2[0]);

        targetLabel3[0] = new JLabel(whiteBorder);
        targetLabel3[0].setText("wb-t3");
        targetLabel3[0].setFont(new Font("Lucida Grande",1,0));
        targetLabel3[0].setBounds(510, 10, CARD_WIDTH+5, CARD_HEIGH);
        targetLabel3[0].addMouseListener(actionListener);
        panelOfAll.add(targetLabel3[0]);

        targetLabel4[0] = new JLabel(whiteBorder);
        targetLabel4[0].setText("wb-t4");
        targetLabel4[0].setFont(new Font("Lucida Grande",1,0));
        targetLabel4[0].setBounds(610, 10, CARD_WIDTH+5, CARD_HEIGH);
        targetLabel4[0].addMouseListener(actionListener);
        panelOfAll.add(targetLabel4[0]);
        //ADD BORDERS END


        mainFrame.add(panelOfAll);
        mainFrame.getContentPane().validate();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private Card turnCardUp (Card card){
        card.turnFaceUp();
        return card;
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    protected class Configuration extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e){
            Component comp_card = e.getComponent();

            if (comp_card instanceof JLabel) {
                JLabel card = (JLabel) comp_card;
                System.out.println(" "+card.getText());
            }
        }

    }
}