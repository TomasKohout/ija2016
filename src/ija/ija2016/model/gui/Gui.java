package ija.ija2016.model.gui;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import ija.ija2016.model.board.FactoryKlondike;
import ija.ija2016.model.cards.Card;
import ija.ija2016.model.cards.CardDeck;
import ija.ija2016.model.cards.CardStack;
import ija.ija2016.model.cards.CardTargetDeck;


import javax.swing.*;
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

    private transient StackToStack stackToStack;
    private transient StackToTargetDeck stackToTargetDeck;
    private transient TargetDeckToStack targetDeckToStack;
    private transient MainToSwap mainToSwap;

    Invoker invoker;

    public Gui(JPanel panel, JButton exitGame)
    {
        invoker = new Invoker();
        //mainToSwap = new MainToSwap();
        //targetDeckToStack = new TargetDeckToStack();
        //stackToTargetDeck = new StackToTargetDeck();
        //stackToStack = new StackToStack();

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
        whiteBorder = createImageIcon("../../../../images/border.png");
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


        setUp();
    }


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

        setZeroIndexLabel(workingLabel1, 10, 310, "wb-w1");
        setZeroIndexLabel(workingLabel2, 110, 310, "wb-w2");
        setZeroIndexLabel(workingLabel3, 210, 310, "wb-w3");
        setZeroIndexLabel(workingLabel4, 310, 310, "wb-w4");
        setZeroIndexLabel(workingLabel5, 410, 310, "wb-w5");
        setZeroIndexLabel(workingLabel6, 510, 310, "wb-w6");
        setZeroIndexLabel(workingLabel7, 610, 310, "wb-w7");

        setZeroIndexLabel(targetLabel1, 310, 20, "wb-t1");
        setZeroIndexLabel(targetLabel2, 410, 20, "wb-t2");
        setZeroIndexLabel(targetLabel3, 510, 20, "wb-t3");
        setZeroIndexLabel(targetLabel4, 610, 20, "wb-t4");

        setZeroIndexLabel(swapLabel, 110, 20, "wb-s");
        setZeroIndexLabel(mainLabel, 10, 20, "wb-m");
        repaint();

    }

    private Card turnCardUp (Card card){
        card.turnFaceUp();
        return card;
    }

    private void setZeroIndexLabel (JLabel[] labels, int x, int y, String label)
    {
        labels[0] = new JLabel(whiteBorder);
        labels[0].setText(label);
        labels[0].setFont(this.font);
        labels[0].setBounds(x, y, CARD_WIDTH+5, CARD_HEIGH);
        labels[0].addMouseListener(actionListener);
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
        panelOfAll.add(undo);
        panelOfAll.add(save);
        panelOfAll.add(exitGame);
        panelOfAll.add(load);
            paintWorkingDeck(workingLabel1, workingDeck1, 10, 310);
            wd1 = workingDeck1.size();

            paintWorkingDeck(workingLabel2, workingDeck2, 110, 310);
            wd2 = workingDeck2.size();

            paintWorkingDeck(workingLabel3, workingDeck3, 210, 310);
            wd3 = workingDeck3.size();

            paintWorkingDeck(workingLabel4, workingDeck4, 310, 310);
            wd4 = workingDeck4.size();

            paintWorkingDeck(workingLabel5, workingDeck5, 410, 310);
            wd5 = workingDeck5.size();

            paintWorkingDeck(workingLabel6, workingDeck6, 510, 310);
            wd6 = workingDeck6.size();

            paintWorkingDeck(workingLabel7, workingDeck7, 610, 310);
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
                if (targetDeck1.size() == 13 &&
                        targetDeck2.size() == 13 &&
                        targetDeck3.size() == 13 &&
                        targetDeck4.size() == 13)
                JOptionPane.showMessageDialog(panelOfAll, "Vyhráli jste!");
                repaint();
                clearMove();

            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            this.lastComponent = e.getComponent();
            return;
        }

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
                Transfer action = new Transfer(this.source,  this.destination, sDeck, sStack, source, dest);
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

    protected class UndoGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            invoker.undo();
            repaint();
        }
    }

    protected class SaveGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) JOptionPane.showInputDialog(null);

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("save/" + s + ".rebus"));
                if (br.readLine() != null) {
                    JOptionPane.showMessageDialog(panelOfAll, "Takový soubor již existuje!");
                    return;
                }
            } catch (FileNotFoundException e1) {

            } catch (IOException e1) {

            }

            try {
                FileOutputStream fileOut = new FileOutputStream("save/" + s + ".rebus");
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
                System.out.println("Saved to " + s );

            }
            catch (IOException i)
            {
                JOptionPane.showMessageDialog(panelOfAll, "Takový soubor již existuje!");
            }

        }
    }

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
            String s = (String) JOptionPane.showInputDialog(null);
            try {
                FileInputStream fileIn = new FileInputStream("save/"+s + ".rebus");
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
    private void addListenerStack(CardStack stack)
    {
        for (int k = 0; k < stack.size(); k++)
            stack.get(k).getJLabel().addMouseListener(actionListener);
    }

    private void addListenerDeck(CardDeck deck)
    {
        for (int k = 0; k < deck.size(); k++)
            deck.get(k).getJLabel().addMouseListener(actionListener);
    }



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
        workingLabel1 = new JLabel[25];
        workingLabel2 = new JLabel[25];
        workingLabel3 = new JLabel[25];
        workingLabel4 = new JLabel[25];
        workingLabel5 = new JLabel[25];
        workingLabel6 = new JLabel[25];
        workingLabel7 = new JLabel[25];
        mainLabel = new JLabel[53];
        swapLabel = new JLabel[25];
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
        actionListener = null;
        actionListener = new Configuration();
        for (int i = 0; i < mainDeck.size(); i++)
        {
            mainDeck.get(i).getJLabel().addMouseListener(this.actionListener);
            mainDeck.get(i).getJLabel().setFont(this.font);
        }
    }

}