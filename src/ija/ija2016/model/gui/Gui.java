/**
 * Class implements graphic units, mouse listeners and call move operations of game.
 *
 * @author Tomáš Blažek (xblaze31)
 * @author Tomáš Kohout (xkohou08)
 */


package ija.ija2016.model.gui;

import ija.ija2016.model.board.FactoryKlondike;
import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;
import jdk.nashorn.internal.objects.NativeUint8Array;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.*;

public class Gui implements Serializable{

    private static final int CARD_WIDTH = 80;
    private static final int CARD_HEIGH = 110;

    private int wd1;
    private int wd2;
    private int wd3;
    private int wd4;
    private int wd5;
    private int wd6;
    private int wd7;

    private transient JPanel panelOfAll;

    private transient FactoryKlondike factory;

    private CardStack workingDeck1;
    private CardStack workingDeck2;
    private CardStack workingDeck3;
    private CardStack workingDeck4;
    private CardStack workingDeck5;
    private CardStack workingDeck6;
    private CardStack workingDeck7;

    private transient JLabel[] workingLabel1;
    private transient JLabel[] workingLabel2;
    private transient JLabel[] workingLabel3;
    private transient JLabel[] workingLabel4;
    private transient JLabel[] workingLabel5;
    private transient JLabel[] workingLabel6;
    private transient JLabel[] workingLabel7;

    private transient JLabel[] mainLabel;
    private transient JLabel[] swapLabel;

    private transient JButton resetGame;
    private transient JButton undo;
    private transient JButton save;
    private transient JButton load;
    private transient JButton exitGame;
    private transient JButton hint;

    private CardStack swapDeck;
    private CardDeck mainDeck;

    private CardDeck targetDeck1;
    private CardDeck targetDeck2;
    private CardDeck targetDeck3;
    private CardDeck targetDeck4;

    private transient JLabel[] targetLabel1;
    private transient JLabel[] targetLabel2;
    private transient JLabel[] targetLabel3;
    private transient JLabel[] targetLabel4;

    private transient Font font;

    private transient Configuration actionListener;
    private transient ImageIcon whiteBorder;

    private transient Invoker invoker;

    /**
     * Contructor makes a one game graphic user interface and prepare game to start position.
     *
     * @param panel Panel where is placed game.
     * @param exitGame Button to quit game.
     */
    public Gui(JPanel panel, JButton exitGame)
    {
        invoker = new Invoker();

        this.panelOfAll = panel;

        factory = new FactoryKlondike();
        workingDeck1 = factory.createWorkingPack();
        workingDeck2 = factory.createWorkingPack();
        workingDeck3 = factory.createWorkingPack();
        workingDeck4 = factory.createWorkingPack();
        workingDeck5 = factory.createWorkingPack();
        workingDeck6 = factory.createWorkingPack();
        workingDeck7 = factory.createWorkingPack();

        factory = new FactoryKlondike();
        font = new Font("Lucida Grande",1,0);
        whiteBorder = createImageIcon("/images/border.png");
        actionListener = new Configuration();
        workingLabel1 = new JLabel[25];
        workingLabel2 = new JLabel[25];
        workingLabel3 = new JLabel[25];
        workingLabel4 = new JLabel[25];
        workingLabel5 = new JLabel[25];
        workingLabel6 = new JLabel[25];
        workingLabel7 = new JLabel[25];
        mainLabel = new JLabel[53];
        swapLabel = new JLabel[25];
        swapDeck = factory.createWorkingPack();
        mainDeck = factory.createCardDeck();

        for (int i = 0; i < mainDeck.size(); i++)
        {
            mainDeck.get(i).getJLabel().addMouseListener(actionListener);
            mainDeck.get(i).getJLabel().setFont(this.font);
        }

        targetDeck1 = factory.createTargetPack(Card.Color.DIAMONDS);
        targetDeck2 = factory.createTargetPack(Card.Color.HEARTS);
        targetDeck3 = factory.createTargetPack(Card.Color.CLUBS);
        targetDeck4 = factory.createTargetPack(Card.Color.SPADES);

        targetLabel1 = new JLabel[14];
        targetLabel2 = new JLabel[14];
        targetLabel3 = new JLabel[14];
        targetLabel4 = new JLabel[14];

        undo = new JButton("Undo");
        undo.setFont(new Font("Lucida Grande", 1, 10));
        undo.setBounds(10,0,50,20);
        undo.setMargin(new Insets(0,0,0,0));
        undo.addActionListener(new UndoGame());

        save = new JButton("Save");
        save.setFont(new Font("Lucida Grande", 1, 10));
        save.setBounds(60,0,50,20);
        save.setMargin(new Insets(0,0,0,0));
        save.addActionListener(new SaveGame());

        load = new JButton("Load");
        load.setFont(new Font("Lucida Grande", 1, 10));
        load.setBounds(110,0,50,20);
        load.setMargin(new Insets(0,0,0,0));
        load.addActionListener(new LoadGame());

        this.exitGame = exitGame;

        resetGame = new JButton("Reset");
        resetGame.setFont(new Font("Lucida Grande", 1, 10));
        resetGame.setBounds(210, 0,50,20);
        resetGame.setMargin(new Insets(0,0,0,0));
        resetGame.addActionListener(new ResetGame());

        hint = new JButton("Hint");
        hint.setFont(new Font("Lucida Grande", 1, 10));
        hint.setBounds(260, 0,50,20);
        hint.setMargin(new Insets(0,0,0,0));
        hint.addActionListener(new Hint());


        setUp();
    }

    /**
     * Prepare attributes for game.
     */
    private void setUp()
    {

        //SET WORKING PACKS
        workingDeck1.forcePut(turnCardUp(mainDeck.pop()),"w1");
        workingLabel1[1] = workingDeck1.get(0).getJLabel();
        workingLabel1[1].setBounds(10, 300, CARD_WIDTH, CARD_HEIGH);
        wd1 = workingDeck1.size() -1;

        panelOfAll.add(workingLabel1[1]);

        for (int k = 1; k >= 0; k--)
            workingDeck2.forcePut(mainDeck.pop(), "w2");

        workingDeck2.forcePut(workingDeck2.pop().turn(), "w2");
        wd2 = workingDeck2.size() -1;
        for (int k = 2; k >= 0; k--)
            workingDeck3.forcePut(mainDeck.pop(), "w3");

        workingDeck3.forcePut(workingDeck3.pop().turn(), "w3");

        wd3 = workingDeck3.size() -1;

        for (int k = 3; k >= 0; k--)
            workingDeck4.forcePut(mainDeck.pop(), "w4");

        workingDeck4.forcePut(workingDeck4.pop().turn(), "w4");

        wd4 = workingDeck4.size() - 1;
        for (int k = 4; k >= 0; k--)
            workingDeck5.forcePut(mainDeck.pop(), "w5");

        workingDeck5.forcePut(workingDeck5.pop().turn(), "w5");
        wd5 = workingDeck5.size() -1 ;

        for (int k = 5; k >= 0; k--)
            workingDeck6.forcePut(mainDeck.pop(), "w6");

        workingDeck6.forcePut(workingDeck6.pop().turn(), "w6");
        wd6 = workingDeck6.size() -1;

        for (int k = 6; k >= 0; k--)
            workingDeck7.forcePut(mainDeck.pop(), "w7");

        workingDeck7.forcePut(workingDeck7.pop().turn(), "w7");
        wd7 = workingDeck7.size() - 1;

        setZeroIndexLabel(workingLabel1, 10, 340, "wb-w1");
        setZeroIndexLabel(workingLabel2, 110, 340, "wb-w2");
        setZeroIndexLabel(workingLabel3, 210, 340, "wb-w3");
        setZeroIndexLabel(workingLabel4, 310, 340, "wb-w4");
        setZeroIndexLabel(workingLabel5, 410, 340, "wb-w5");
        setZeroIndexLabel(workingLabel6, 510, 340, "wb-w6");
        setZeroIndexLabel(workingLabel7, 610, 340, "wb-w7");

        setZeroIndexLabel(targetLabel1, 310, 20, "wb-t1");
        setZeroIndexLabel(targetLabel2, 410, 20, "wb-t2");
        setZeroIndexLabel(targetLabel3, 510, 20, "wb-t3");
        setZeroIndexLabel(targetLabel4, 610, 20, "wb-t4");

        setZeroIndexLabel(swapLabel, 110, 20, "wb-s");
        setZeroIndexLabel(mainLabel, 10, 20, "wb-m");
        repaint();

    }

    /**
     * Turn Card card up.
     * @param card Card to be turned.
     * @return Turned Card
     */
    private Card turnCardUp (Card card){
        card.turnFaceUp();
        return card;
    }

    /**
     * Prepare zero index of array of labels
     * @param labels array to be set
     * @param x X coords
     * @param y Y coords
     * @param label Name and position of label;
     */
    private void setZeroIndexLabel (JLabel[] labels, int x, int y, String label)
    {
        labels[0] = new JLabel(whiteBorder);
        labels[0].setText(label);
        labels[0].setFont(this.font);
        labels[0].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
        labels[0].addMouseListener(actionListener);
    }

    /**
     * Create image icon.
     * @param path Path to a image
     * @return Instance of ImageIcon
     */
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Repaint JPanel this.panelOfAll
     */
    protected void repaint()
    {
        panelOfAll.removeAll();
        panelOfAll.add(undo);
        panelOfAll.add(save);
        panelOfAll.add(exitGame);
        panelOfAll.add(load);
        panelOfAll.add(resetGame);
        panelOfAll.add(hint);
            paintWorkingDeck(workingLabel1, workingDeck1, 10, 340);
            wd1 = workingDeck1.size();

            paintWorkingDeck(workingLabel2, workingDeck2, 110, 340);
            wd2 = workingDeck2.size();

            paintWorkingDeck(workingLabel3, workingDeck3, 210, 340);
            wd3 = workingDeck3.size();

            paintWorkingDeck(workingLabel4, workingDeck4, 310, 340);
            wd4 = workingDeck4.size();

            paintWorkingDeck(workingLabel5, workingDeck5, 410, 340);
            wd5 = workingDeck5.size();

            paintWorkingDeck(workingLabel6, workingDeck6, 510, 340);
            wd6 = workingDeck6.size();

            paintWorkingDeck(workingLabel7, workingDeck7, 610, 340);
            wd7 = workingDeck7.size();


        //SET WORKING PACKS END

        //ADD BORDERS
        addDeckToPanel(mainLabel, mainDeck, 10, 20, "m");
        addStackToPanel(swapLabel, swapDeck, 110, 20, "s");

        addDeckToPanel(targetLabel1, targetDeck1, 310,20, "t1");
        addDeckToPanel(targetLabel2, targetDeck2, 410, 20, "t2");
        addDeckToPanel(targetLabel3, targetDeck3, 510, 20, "t3");
        addDeckToPanel(targetLabel4, targetDeck4, 610, 20, "t4");

        //ADD BORDERS END

        panelOfAll.revalidate();
        panelOfAll.repaint();

        Panels.panelOfAll.add(this.panelOfAll);
        Panels.panelOfAll.revalidate();
        Panels.panelOfAll.repaint();

        Panels.mainFrame.add(Panels.panelOfAll);
        Panels.mainFrame.getContentPane().revalidate();
        Panels.mainFrame.getContentPane().repaint();
    }

    /**
     * Add JLbaels from cards in stack to array labels.
     * @param labels Array of JLabels
     * @param stack Stack that contains actual JLabels
     * @param x X coordinates
     * @param y Y coordinates
     */
    protected void paintWorkingDeck(JLabel[] labels, CardStack stack, int x, int y)
    {
        if (stack.isEmpty())
            this.panelOfAll.add(labels[0]);
        else {
            for (int k = 0, i = stack.size() - 1; k <= stack.size() - 1; k++, i--) {
                labels[k+1] = stack.get(i).getJLabel();
                labels[k+1].setBounds(x, y - (k * 15), CARD_WIDTH, CARD_HEIGH);

                this.panelOfAll.add(labels[k+1]);
            }
        }
    }

    /**
     * Add JLabels from cards in deck to array labels.
     * @param labels array of JLabels
     * @param deck CardDeck containing actual cards
     * @param x X Coordinates
     * @param y Y Coordinates
     * @param labelName String that identifies cards and their position
     */
    protected void addDeckToPanel(JLabel[] labels, CardDeck deck, int x, int y, String labelName)
    {

        if (!deck.isEmpty()) {
                labels[1] = deck.get(deck.size()-1).getJLabel();
                labels[1].setText(deck.get(deck.size()-1).toString() + "-" + labelName);
                labels[1].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
            this.panelOfAll.add(labels[1]);
        } else {
            this.panelOfAll.add(labels[0]);
        }

    }

    /**
     * Add JLabels from cards in stack to array labels;
     * @param labels array of JLabels
     * @param stack CardStack containing actual cards
     * @param x X Coordinates
     * @param y Y Coordinates
     * @param labelName String that identifies cards and their position
     */
    protected void addStackToPanel( JLabel[] labels, CardStack stack, int x, int y, String labelName)
    {
        if (stack.size() != 0)
        {
                labels[1] = stack.get(stack.size()-1).getJLabel();
                labels[1].setText(stack.get(stack.size()-1).toString() + "-" + labelName);
                labels[1].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
            this.panelOfAll.add(labels[1]);
        } else {
            this.panelOfAll.add(labels[0]);
        }


    }

    /**
     * Class that implements movement of cards.
     */
    protected class Configuration extends MouseAdapter{
        private Object sourceDeck = null;
        private Object destDeck = null;

        private int secDeck = 1;

        private String source = "";
        private String cardNameSource = "";

        private String destination = "";
        private String cardNameDest = "";

        private boolean secondMove = false;

        Component lastComponent;

        /**
         * First click on JLabel
         * @param e MouseEvent, Component
         */
        @Override
        public void mousePressed(MouseEvent e){
            Component comp_card = e.getComponent();

            if (comp_card instanceof JLabel) {
                clearMove();
                JLabel card = (JLabel) comp_card;

                parse(card.getText());
                //System.out.println("mousePressed: Source: " + this.source + " Dest: " + this.destination);
            }
            else
                clearMove();
        }

        /**
         * Release of mouse.
         * @param e event, Component
         */
        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (this.lastComponent instanceof JLabel)
            {
                this.secondMove = true;
                JLabel card = (JLabel) this.lastComponent;
                parse(card.getText());

                Card sourceCard =   getFromCardDeck(this.cardNameSource, this.source);
                Card destCard   =   getFromCardDeck(this.cardNameDest, this.destination);
                System.out.println("mouseReleased: Source: " + this.source + " Dest: " + this.destination);

                //funkce mainDeck
                if (this.source.compareTo("m") == 0 && this.destination.compareTo("m") == 0){
                    Transfer action = new Transfer(this.source, this.destination, mainDeck, swapDeck, null, null);
                    MainToSwap move = new MainToSwap(action);
                    invoker.takeOrder(move);
                    //  System.out.println("Swap po: " + swapDeck.size());
                }
                else
                {
//                    System.out.println("mouseReleased: Source: " + this.source + " Card: "+ sourceCard.toString() +" Dest: " + this.destination + " Card: " + destCard.toString());

                    if (destination.compareTo(source) != 0 &&
                            source.compareTo("m") != 0)
                        moveCards(sourceCard, destCard);
                }

                repaint();
                clearMove();

                if (targetDeck1.size() == 13 &&
                        targetDeck2.size() == 13 &&
                        targetDeck3.size() == 13 &&
                        targetDeck4.size() == 13) {
                    JOptionPane.showMessageDialog(panelOfAll, "Vyhráli jste!");
                }

            }
        }

        /**
         * Method that get the last Component before release.
         * @param e event
         */
        @Override
        public void mouseEntered(MouseEvent e)
        {
            this.lastComponent = e.getComponent();
            return;
        }

        /**
         * Method for moving cards
         * @param source Source Card
         * @param dest Destination Card
         * @return true/false
         */
        protected boolean moveCards(Card source, Card dest ) {
            if (source == null)
                return false;

            CardStack sStack = null;
            CardStack dStack = null;
            CardTargetDeck sDeck = null;
            CardTargetDeck dDeck = null;
            //CardStack tmp;

            if (this.sourceDeck instanceof CardStack)
                sStack = (CardStack) this.sourceDeck;
            else
                sDeck = (CardTargetDeck) this.sourceDeck;

            if (this.destDeck instanceof CardStack)
                dStack = (CardStack) this.destDeck;
            else
                dDeck = (CardTargetDeck) this.destDeck;

            if (sDeck != null && dStack != null) {
                Transfer action = new Transfer(this.source,  this.destination, sDeck, dStack, source, dest);
                TargetDeckToStack move = new TargetDeckToStack(action);
                invoker.takeOrder(move);

                //targetDeckToStack.setString(this.source, this.destination);
                //targetDeckToStack.execute(sDeck, dStack, source, dest);
            } else if (sStack != null && dStack != null) {
                Transfer action = new Transfer(this.source,  this.destination, sStack, dStack, source, dest);
                StackToStack move = new StackToStack(action);
                invoker.takeOrder(move);

                //stackToStack.setString(this.source, this.destination);
                //stackToStack.execute(sStack, dStack, source, dest);
            } else if (sStack != null && dDeck != null) {
                Transfer action = new Transfer(this.source,  this.destination, sStack, dDeck, source, dest);
                StackToTargetDeck move = new StackToTargetDeck(action);
                invoker.takeOrder(move);

                //stackToTargetDeck.setString(this.source,this.destination);
                //stackToTargetDeck.execute(sStack, dDeck,source,dest);
            }


            return false;
        }

        /**
         * Returns Card on CardStack/CardDeck
         * @param str Name of Card
         * @param switchString Name of CardStack/CardDeck
         * @return Instance of Card of null (null is never reacher)
         */
        protected Card getFromCardDeck(String str, String switchString)
        {
            switch (switchString)
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

        /**
         * This method go through specific stack/deck and return Card.
         * @param deck CardDeck
         * @param stack CardStack
         * @param str Name of card
         * @return Instance of Card
         */
        protected Card getCard(CardDeck deck, CardStack stack, String str)
        {
            if (deck != null) {
                if (this.secDeck == 1)
                    this.sourceDeck = deck;
                else
                    this.destDeck = deck;

                this.secDeck = 0;
                for (int i = 0; i < deck.size(); i++) {
                    if (str.compareTo(deck.get(i).toString()) == 0)
                        return deck.get(i);
                }
            }
            else
            {
                if (this.secDeck == 1)
                    this.sourceDeck = stack;
                else
                    this.destDeck = stack;

                this.secDeck = 0;
                for (int i = 0; i < stack.size(); i++ ) {
                    if (str.compareTo(stack.get(i).toString()) == 0)
                        return stack.get(i);
                }

            }


            //We are waiting for you Chuck!
            return null;
        }

        /**
         * Wipe attributes necessary for movement.
         */
        protected void clearMove()
        {
            this.source = "";
            this.cardNameSource = "";
            this.destination = "";
            this.cardNameDest = "";
            this.secondMove = false;
            this.sourceDeck = null;
            this.destDeck = null;
            this.secDeck = 1;
        }

        /**
         * This method sets attribute destination, source, cardNameSource and cardNameDest.
         * @param labelText text from cards JLabel
         */
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

    /**
     * Class for handling event applied to undo button.
     */
    protected class UndoGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            invoker.undo();
            repaint();
        }
    }

    /**
     * Class for handling event applied to save button.
     */
    protected class SaveGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            String str =System.getProperty("user.dir") + "/examples";
            System.out.println(str);
            chooser.setCurrentDirectory(new File(str));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Rebus Solitaire","rebus");
            chooser.setFileFilter(filter);
            int ret = chooser.showSaveDialog(panelOfAll);
            if (ret == JFileChooser.APPROVE_OPTION )
            {
                BufferedReader br = null;

                try {
                    FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile() + ".rebus");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(mainDeck);
                    out.writeObject(swapDeck);

                    out.writeObject(targetDeck1);
                    out.writeObject(targetDeck2);
                    out.writeObject(targetDeck3);
                    out.writeObject(targetDeck4);

                    out.writeObject(workingDeck1);
                    out.writeObject(workingDeck2);
                    out.writeObject(workingDeck3);
                    out.writeObject(workingDeck4);
                    out.writeObject(workingDeck5);
                    out.writeObject(workingDeck6);
                    out.writeObject(workingDeck7);

                    out.close();
                    fileOut.close();

                }
                catch (IOException i)
                {
                    JOptionPane.showMessageDialog(panelOfAll, "Takový soubor již existuje!");
                }
            }


        }
    }

    /**
     * Class that handle event applied to load button.
     */
    protected class LoadGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainDeck = null;
            swapDeck = null;

            targetDeck1 = null;
            targetDeck2 = null;
            targetDeck3 = null;
            targetDeck4 = null;

            workingDeck1 = null;
            workingDeck2 = null;
            workingDeck3 = null;
            workingDeck4 = null;
            workingDeck5 = null;
            workingDeck6 = null;
            workingDeck7 = null;
            File s = null;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Rebus Solitaire","rebus");
            String str =System.getProperty("user.dir") + "/examples";
            chooser.setCurrentDirectory(new File(str));
            chooser.setFileFilter(filter);
            int ret = chooser.showOpenDialog(panelOfAll);
            if (ret == JFileChooser.APPROVE_OPTION)
            {
                s = chooser.getSelectedFile();
                try {
                    FileInputStream fileIn = new FileInputStream(s);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    mainDeck = (CardDeck) in.readObject();

                    addListenerDeck(mainDeck);

                    swapDeck = (CardStack) in.readObject();
                    addListenerStack(swapDeck);

                    targetDeck1 = (CardDeck) in.readObject();
                    addListenerDeck(targetDeck1);

                    targetDeck2 = (CardDeck) in.readObject();
                    addListenerDeck(targetDeck2);
                    targetDeck3 = (CardDeck) in.readObject();
                    addListenerDeck(targetDeck3);
                    targetDeck4 = (CardDeck) in.readObject();
                    addListenerDeck(targetDeck4);

                    workingDeck1 = (CardStack) in.readObject();
                    addListenerStack(workingDeck1);
                    workingDeck2 = (CardStack) in.readObject();
                    addListenerStack(workingDeck2);
                    workingDeck3 = (CardStack) in.readObject();
                    addListenerStack(workingDeck3);
                    workingDeck4 = (CardStack) in.readObject();
                    addListenerStack(workingDeck4);
                    workingDeck5 = (CardStack) in.readObject();
                    addListenerStack(workingDeck5);
                    workingDeck6 = (CardStack) in.readObject();
                    addListenerStack(workingDeck6);
                    workingDeck7 = (CardStack) in.readObject();
                    addListenerStack(workingDeck7);

                    repaint();
                }
                catch (IOException i)
                {
                    JOptionPane.showMessageDialog(panelOfAll, "Takový soubor neexistuje!");
                }
                catch (ClassNotFoundException c)
                {
                    JOptionPane.showMessageDialog(panelOfAll, "Nastala vnitřní chyba...");
                }
            }



        }
    }

    /**
     * Class that handle event applied to reset button.
     */
    protected class ResetGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prepareForNewGame();
            setUp();
            repaint();
        }
    }

    /**
     * Class that handle event applied to hint button.
     */
    protected class Hint implements ActionListener{

        private int start = 0;
        private int start2 = 0;
        private int end = 7;
        private boolean pruchod = true;
        private boolean pruchod2 = true;
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(panelOfAll, findHint());
        }

        /**
         * Method for getting workingDeck by int i
         * @param i number of deck
         * @return Instance of CardStack or null (never should happend)
         */
        private CardStack getWorkingDeck(int i)
        {
            i++;
            switch (i)
            {
                case 1: return workingDeck1;
                case 2: return workingDeck2;
                case 3: return workingDeck3;
                case 4: return workingDeck4;
                case 5: return workingDeck5;
                case 6: return workingDeck6;
                case 7: return workingDeck7;
            }

            return null;
        }

        /**
         * Method for comparing workingDeck with workingDeck
         * @param from CardStack one of workingDecks
         * @return String if match has been found, otherwise empty String
         */
        private String workWithWorkStack(CardStack from)
        {
            Card card = null;
            for (int g = 0; g < from.size(); g++)
            {
                if (from.get(g).isTurnedFaceUp())
                {
                    card = from.get(g);
                    break;
                }
            }
            if (card == null)
                return "";
            for (int k = 0; k < end ; k++) {

                if (getWorkingDeck(k).isEmpty())
                    continue;

                if (card.value() + 1 == getWorkingDeck(k).get().value() && !card.similarColorTo(getWorkingDeck(k).get()) && card.isTurnedFaceUp() && getWorkingDeck(k).get().isTurnedFaceUp()) {
                    return card.getJLabel().getText() + " To: " + getWorkingDeck(k).get().getJLabel().getText();
                }

                if(getWorkingDeck(k).isEmpty() && card.value() == 13)
                    return card.toString() + " To: Empty Place";
            }

            return "";
        }

        /**
         * Method for find hint.
         * @return String with hint or "Hint not found" like.
         */
        private String findHint()
        {
            if (start == 7)
                start = 0;
            if (start2 == 7)
                start2 = 0;
            String ret = "";
            pruchod = true;

            while (start < end )
            {
                start++;
                CardStack from = getWorkingDeck(start - 1);

                if (from.isEmpty())
                    continue;
                switch (start-1)
                {
                    case 0:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;

                    case 1:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;

                    case 2:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;

                    case 3:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;
                    case 4:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;
                    case 5:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;
                    case 6:
                        ret = workWithWorkStack(from);
                        if (!ret.isEmpty())
                            return ret;
                        break;
                }

                if (pruchod)
                {
                    start = 0;
                    pruchod = false;
                }


            }
            if (!swapDeck.isEmpty()) {
                for (int i = 0; i < 11; i++) {
                    if (!mainTarget(i).isEmpty())
                        return mainTarget(i);
                }
            }
            pruchod2 = true;
            String tmp = "";
            while (start2 < end)
            {
                start2++;
                switch (start2 - 1){
                    case 0:
                        tmp = workWithTarget(workingDeck1);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 1:
                        tmp = workWithTarget(workingDeck2);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 2:
                        tmp = workWithTarget(workingDeck3);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 3:
                        tmp = workWithTarget(workingDeck4);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 4:
                        tmp = workWithTarget(workingDeck5);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 5:
                        tmp = workWithTarget(workingDeck6);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                    case 6:
                        tmp = workWithTarget(workingDeck7);
                        if (!tmp.isEmpty())
                            return tmp;
                        break;
                }

                if (pruchod2)
                {
                    start2 = 0;
                    pruchod2 = false;
                }
            }


            return "Žádné tahy nejsou možné";

        }
    }

    /**
     * Compare workingDeck wiht TargetDecks
     * @param stack wrokingDeck
     * @return String with hint or empty String
     */
    private String workWithTarget(CardStack stack)
    {
        if (stack.isEmpty())
            return "";

        for (int i = 0; i < 4; i++)
        {
            switch (i)
            {
                case 0:
                    if (targetDeck1.isEmpty() && stack.get().value() == 1)
                        return stack.get().toString() + " To: Target Deck";
                    else if (targetDeck1.isEmpty())
                        return "";

                    if(stack.get().value() - 1 == targetDeck1.get().value() && stack.get().color() == targetDeck1.get().color())
                        return stack.get().toString() + " To: " + targetDeck1.get().toString();

                    break;
                case 1:
                    if (targetDeck2.isEmpty() && stack.get().value() == 1)
                        return stack.get().toString() + " To: Target Deck";
                    else if (targetDeck2.isEmpty())
                        return "";

                    if(stack.get().value() - 1 == targetDeck2.get().value() && stack.get().color() == targetDeck2.get().color())
                        return stack.get().toString() + " To: " + targetDeck2.get().toString();

                    break;
                case 2:
                    if (targetDeck3.isEmpty() && stack.get().value() == 1)
                        return stack.get().toString() + " To: Target Deck";
                    else if (targetDeck3.isEmpty())
                        return "";

                    if(stack.get().value() - 1 == targetDeck3.get().value() && stack.get().color() == targetDeck3.get().color())
                        return stack.get().toString() + " To: " + targetDeck3.get().toString();

                    break;

                case 3:
                    if (targetDeck4.isEmpty() && stack.get().value() == 1)
                        return stack.get().toString() + " To: Target Deck";
                    else if (targetDeck4.isEmpty())
                        return "";

                    if(stack.get().value() - 1 == targetDeck4.get().value() && stack.get().color() == targetDeck4.get().color())
                        return stack.get().toString() + " To: " + targetDeck4.get().toString();

                    break;
            }
        }
        return "";
    }

    /**
     * Compare swapDeck with targetDecks and workingDecks
     * @param i which deck/stack should be compared to swapDeck
     * @return String with hint or empty String
     */
    private String mainTarget (int i)
    {
        switch (i)
        {
            case 0:
                if (swapDeck.get().value() == 1 && targetDeck1.isEmpty())
                    return swapDeck.get().toString() + " To: Target Deck";
                else if (targetDeck1.isEmpty())
                    return "";

                if (swapDeck.get().value() - 1 == targetDeck1.get().value() && swapDeck.get().color() == targetDeck1.get().color())
                    return swapDeck.get().toString() + " To: " + targetDeck1.get().toString();
                return "";
            case 1:
                if (swapDeck.get().value() == 1 && targetDeck2.isEmpty())
                    return swapDeck.get().toString() + " To: Target Deck";
                else if (targetDeck2.isEmpty())
                    return "";

                if (swapDeck.get().value() - 1 == targetDeck2.get().value() && swapDeck.get().color() == targetDeck2.get().color())
                    return swapDeck.get().toString() + " To: " + targetDeck2.get().toString();
                return "";
            case 2:
                if (swapDeck.get().value() == 1 && targetDeck3.isEmpty())
                    return swapDeck.get().toString() + " To: Target Deck";
                else if (targetDeck3.isEmpty())
                    return "";

                if (swapDeck.get().value() - 1 == targetDeck3.get().value() && swapDeck.get().color() == targetDeck3.get().color())
                    return swapDeck.get().toString() + " To: " + targetDeck3.get().toString();
                return "";
            case 3:
                if (swapDeck.get().value() == 1 && targetDeck4.isEmpty())
                    return swapDeck.get().toString() + " To: Target Deck";
                else if (targetDeck4.isEmpty())
                    return "";

                if (swapDeck.get().value() - 1 == targetDeck4.get().value() && swapDeck.get().color() == targetDeck4.get().color())
                    return swapDeck.get().toString() + " To: " + targetDeck4.get().toString();
                return "";

            case 4:
                if (swapDeck.get().value() == 13 && workingDeck1.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck1.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck1.get().value() && !swapDeck.get().similarColorTo(workingDeck1.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck1.get().toString();
                return "";
            case 5:
                if (swapDeck.get().value() == 13 && workingDeck2.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck2.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck2.get().value() && !swapDeck.get().similarColorTo(workingDeck2.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck2.get().toString();
                return "";
            case 6:
                if (swapDeck.get().value() == 13 && workingDeck3.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck3.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck3.get().value() && !swapDeck.get().similarColorTo(workingDeck3.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck3.get().toString();
                return "";
            case 7:
                if (swapDeck.get().value() == 13 && workingDeck4.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck4.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck4.get().value() && !swapDeck.get().similarColorTo(workingDeck4.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck4.get().toString();
                return "";
            case 8:
                if (swapDeck.get().value() == 13 && workingDeck5.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck5.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck5.get().value() && !swapDeck.get().similarColorTo(workingDeck5.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck5.get().toString();
                return "";
            case 9:
                if (swapDeck.get().value() == 13 && workingDeck6.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck6.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck6.get().value() && !swapDeck.get().similarColorTo(workingDeck6.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck6.get().toString();
                return "";
            case 10:
                if (swapDeck.get().value() == 13 && workingDeck7.isEmpty())
                    return swapDeck.get().toString() + " To: Working Deck";
                else if (workingDeck7.isEmpty())
                    return "";

                if (swapDeck.get().value() + 1 == workingDeck7.get().value() && !swapDeck.get().similarColorTo(workingDeck7.get()))
                    return swapDeck.get().toString() + " To: " + workingDeck7.get().toString();
                return "";
        }

        return "";
    }

    /**
     * Add actionListener to Cards in stack.
     * @param stack CardStack
     */
    private void addListenerStack(CardStack stack)
    {
        for (int k = 0; k < stack.size(); k++)
            stack.get(k).getJLabel().addMouseListener(actionListener);
    }

    /**
     * Add actionListener to Cards in deck
     * @param deck CardDeck
     */
    private void addListenerDeck(CardDeck deck)
    {
        for (int k = 0; k < deck.size(); k++)
            deck.get(k).getJLabel().addMouseListener(actionListener);
    }


    /**
     * Prepare stacks/decks for new game
     */
    private void prepareForNewGame()
    {
        targetDeck1 = null;
        targetDeck2 = null;
        targetDeck3 = null;
        targetDeck4 = null;

        targetDeck1 = factory.createTargetPack(Card.Color.DIAMONDS);
        targetDeck2 = factory.createTargetPack(Card.Color.HEARTS);
        targetDeck3 = factory.createTargetPack(Card.Color.CLUBS);
        targetDeck4 = factory.createTargetPack(Card.Color.SPADES);

        workingDeck1 = null;
        workingDeck2 = null;
        workingDeck3 = null;
        workingDeck4 = null;
        workingDeck5 = null;
        workingDeck6 = null;
        workingDeck7 = null;

        workingDeck1 = factory.createWorkingPack();
        workingDeck2 = factory.createWorkingPack();
        workingDeck3 = factory.createWorkingPack();
        workingDeck4 = factory.createWorkingPack();
        workingDeck5 = factory.createWorkingPack();
        workingDeck6 = factory.createWorkingPack();
        workingDeck7 = factory.createWorkingPack();

        mainDeck = null;
        swapDeck = null;

        swapDeck = factory.createWorkingPack();
        mainDeck = factory.createCardDeck();

        for (int i = 0; i < mainDeck.size(); i++)
        {
            mainDeck.get(i).getJLabel().addMouseListener(this.actionListener);
            mainDeck.get(i).getJLabel().setFont(this.font);
        }
    }

}