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

    private CardDeck targetDeck1 = factory.createTargetPack(Card.Color.DIAMONDS);
    private CardDeck targetDeck2 = factory.createTargetPack(Card.Color.HEARTS);
    private CardDeck targetDeck3 = factory.createTargetPack(Card.Color.CLUBS);
    private CardDeck targetDeck4 = factory.createTargetPack(Card.Color.SPADES);

    private JLabel[] targetLabel1 = new JLabel[14];
    private JLabel[] targetLabel2 = new JLabel[14];
    private JLabel[] targetLabel3 = new JLabel[14];
    private JLabel[] targetLabel4 = new JLabel[14];

    Configuration actionListener = new Configuration();
    ImageIcon whiteBorder = createImageIcon("../../../../images/border.png");

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

        panelOfAll.setBackground( new Color(12, 121, 5));
        panelOfAll.setSize(FRAME_WIDTH, FRAME_HEIGH);




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

    protected void repaint()
    {
        panelOfAll.removeAll();
        for (int k = workingDeck1.size() - 1, i = 0; k >= 0; k--, i++)
        {
            workingLabel1[k] = workingDeck1.get(i).getJLabel();
            workingLabel1[k].setBounds(10, 300, CARD_WIDTH, CARD_HEIGH);

            workingLabel1[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel1[k]);
        }

        for (int k = workingDeck2.size() - 1, i = 0; k >= 0; k--, i++) {
            workingLabel2[k] = workingDeck2.get(i).getJLabel();
            workingLabel2[k].setBounds(110, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel2[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel2[k]);
        }

        for (int k = workingDeck3.size() - 1, i = 0; k >= 0; k--, i++) {
            workingLabel3[k] = workingDeck3.get(i).getJLabel();
            workingLabel3[k].setBounds(210, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel3[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel3[k]);
        }

        for (int k = workingDeck4.size() - 1, i = 0; k >= 0; k--, i++) {
            workingLabel4[k] = workingDeck4.get(i).getJLabel();
            workingLabel4[k].setBounds(310, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel4[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel4[k]);
        }

        for (int k = workingDeck5.size() - 1, i = 0; k >= 0; k-- , i++) {
            workingLabel5[k] = workingDeck5.get(i).getJLabel();
            workingLabel5[k].setBounds(410, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel5[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel5[k]);
        }

        for (int k = workingDeck6.size() - 1, i = 0; k >= 0; k--, i++) {
            workingLabel6[k] = workingDeck6.get(i).getJLabel();
            workingLabel6[k].setBounds(510, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel6[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel6[k]);
        }

        for (int k = workingDeck7.size() - 1, i = 0; k >= 0; k--, i++) {
            workingLabel7[k] = workingDeck7.get(i).getJLabel();
            workingLabel7[k].setBounds(610, 300 - (k * 10), CARD_WIDTH, CARD_HEIGH);

            workingLabel7[k].addMouseListener(actionListener);
            panelOfAll.add(workingLabel7[k]);
        }
        //SET WORKING PACKS END

        //ADD BORDERS
        addDeckToPanel(mainLabel, mainDeck, 10, 10, "m");
        addStackToPanel(swapLabel, swapDeck, 110, 10, "s");


        addDeckToPanel(targetLabel1, targetDeck1, 310, 10, "t1");
        addDeckToPanel(targetLabel1, targetDeck1, 410, 10, "t2");
        addDeckToPanel(targetLabel1, targetDeck1, 510, 10, "t3");
        addDeckToPanel(targetLabel1, targetDeck1, 610, 10, "t4");

        //ADD BORDERS END

        mainFrame.add(panelOfAll);
        mainFrame.getContentPane().repaint();
    }

    protected void addDeckToPanel(JLabel[] labels, CardDeck deck, int x, int y, String labelName)
    {
        if (deck.size() != 0) {
            for (int k = deck.size() - 1; 0 <= k; k--) {
                labels[k+1] = deck.get(k).getJLabel();
                labels[k+1].setText(deck.get(k).toString() + "-" + labelName);
                labels[k+1].setFont(new Font("Lucida Grande",1,0));
                labels[k+1].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
                labels[k+1].addMouseListener(actionListener);
            }
            this.panelOfAll.add(labels[deck.size()]);
        } else
        {
            labels[0] = new JLabel(whiteBorder);
            labels[0].setText("wb-" + labelName);
            labels[0].setFont(new Font("Lucida Grande",1,0));
            labels[0].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
            labels[0].addMouseListener(actionListener);
            this.panelOfAll.add(labels[0]);
        }

    }

    protected void addStackToPanel( JLabel[] labels, CardStack stack, int x, int y, String labelName)
    {
        if (stack.size() != 0) {
            for (int k = stack.size() - 1; 0 <= k; k--) {
                labels[k+1] = stack.get(k).getJLabel();
                labels[k+1].setText(stack.get(k).toString() + "-" + labelName);
                labels[k+1].setFont(new Font("Lucida Grande",1,0));
                labels[k+1].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
                labels[k+1].addMouseListener(actionListener);
            }
            this.panelOfAll.add(labels[stack.size()]);
        } else {
            labels[0] = new JLabel(whiteBorder);
            labels[0].setText("wb-" + labelName);
            labels[0].setFont(new Font("Lucida Grande",1,0));
            labels[0].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
            labels[0].addMouseListener(actionListener);
            this.panelOfAll.add(labels[0]);
        }
    }

    protected class Configuration extends MouseAdapter{

        private String source = "";
        private String cardNameSource = "";

        private String destination = "";
        private String cardNameDest = "";

        private boolean secondMove = false;

        Component lastComponent;

        @Override
        public void mousePressed(MouseEvent e){
            Component comp_card = e.getComponent();

            if (comp_card instanceof JLabel) {
                clearMove();
                JLabel card = (JLabel) comp_card;

                parse(card.getText());
                System.out.println("mousePressed: Source: " + this.source + " Dest: " + this.destination);


            }
            else
                clearMove();
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (this.lastComponent instanceof JLabel)
            {
                this.secondMove = true;
                JLabel card = (JLabel) this.lastComponent;
                parse(card.getText());

                Card sourceCard =   getFromCardDeck(this.source);
                Card destCard   =   getFromCardDeck(this.destination);

                System.out.println("mouseReleased: Source: " + this.source + " Dest: " + this.destination);

                //funkce mainDeck
                if (this.source.compareTo("m") == 0){
                    if(mainDeck.isEmpty())
                        while(!swapDeck.isEmpty())
                            mainDeck.put(swapDeck.pop().turn());
                    else
                    //   System.out.println("Swap pred: " + swapDeck.size());
                        swapDeck.forcePut(turnCardUp(mainDeck.pop()), "s");
                    //  System.out.println("Swap po: " + swapDeck.size());
                }



                repaint();
                clearMove();

            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            this.lastComponent = e.getComponent();
        }

        protected Card getFromCardDeck(String str)
        {
            switch (str)
            {
                case "w1":
                    return getCard(null, workingDeck1, str);
                case "w2":
                    return getCard(null, workingDeck2, str);
                case "w3":
                    return getCard(null, workingDeck3, str);
                case "w4":
                    return getCard(null, workingDeck4, str);
                case "w5":
                    return getCard(null, workingDeck5, str);
                case "w6":
                    return getCard(null, workingDeck6, str);
                case "w7":
                    return getCard(null, workingDeck7, str);
                case "t1":
                    return getCard(targetDeck1, null, str);
                case "t2":
                    return getCard(targetDeck2, null, str);
                case "t3":
                    return getCard(targetDeck3, null, str);
                case "t4":
                    return getCard(targetDeck4, null, str);
                case "m":
                    return getCard(mainDeck, null, str);
                case "s":
                    return getCard(null, swapDeck, str);
            }
            return null;
        }

        protected Card getCard(CardDeck deck, CardStack stack, String str)
        {
            if (deck != null) {
                for (int i = 0; i < deck.size() - 1; i++)
                    if (str.compareTo(deck.get(i).toString()) == 0)
                        return deck.get(i);
            }
            else
            {
                for (int i = 0; i < stack.size() - 1; i++ )
                    if (str.compareTo(stack.get(i).toString()) == 0)
                        return stack.get(i);
            }


            //We are waiting for you Chuck!
            return null;
        }

        protected void clearMove()
        {
            this.source = "";
            this.cardNameSource = "";
            this.destination = "";
            this.cardNameDest = "";
            this.secondMove = false;
        }

        protected void parse(String labelText)
        {
            boolean flag = true;
            for (char c : labelText.toCharArray())
            {
                if (c == '-')
                {
                    flag = false;
                    continue;
                }

                if (!secondMove) {
                    if (flag)
                        this.cardNameSource += "" + c;
                    else
                        this.source += "" + c;
                }
                else
                {
                    if (flag)
                        this.cardNameDest += "" + c;
                    else
                        this.destination += "" + c;
                }
            }

        }

    }
}