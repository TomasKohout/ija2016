package ija.ija2016.model.cards;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Class represents one card and implements methods from interface Card.
 */
public class CardMethods implements Card, Serializable{
    //Atributy
    private Card.Color color;
    private int value;
    private boolean faceUp;
    private JLabel label;
    private ImageIcon iconTurned;
    private ImageIcon iconUnturned;


    /**
     * Enumeration of colors of card.
     */
    public enum Color{
        CLUBS 		("C"),
        DIAMONDS 	("D"),
        HEARTS 		("H"),
        SPADES 		("S");

        String color;

        Color(String color){
            this.color = color;
        }

        @Override
        public String toString(){
            return this.color;
        }
    }

    /**
     * Create object Card.
     * @param c Color type
     * @param value Value of card
     */
    public CardMethods(Card.Color c, int value){
        this.value = value;
        this.color = c;
        this.faceUp = false;
        label  = new JLabel();
        label.setText(this.toString());
        label.setFont(new Font("Lucida Grande",1,0));
        iconTurned = createImageIcon("../../../../images/" + this.color.toString() + value + ".png");
        iconUnturned = createImageIcon("../../../../images/cardback.png");
    }

    /**
     * Check value of card.
     * @return Value of card.
     */
    public int value(){
        return this.value;
    }

    /**
     * Check that card is faced up.
     * @return True if card is turned face up, else false.
     */
    public boolean isTurnedFaceUp(){
        return this.faceUp;
    }


    /**
     * Turn card face up.
     *
     * @return False if card is already face up, else true.
     */
    public boolean turnFaceUp(){
        if(!this.faceUp){
            this.faceUp = true;
            return true;
        }
        return false;
    }

    /**
     * Turn card.
     * @return Turned card.
     */
    public Card turn(){
        this.faceUp = !this.faceUp;
        return this;
    }

    /**
     * Check color type of card and return color type from enumeration type.
     * @return Color of card.
     */
    public Card.Color color(){
        return this.color;
    }

    /**
     * Check color of card.
     * @return If card is black return true otherwise false.
     */
    public boolean isBlackOrRed(){
        if(this.color == Card.Color.CLUBS || this.color == Card.Color.SPADES) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Compare two cards in color.
     * @param c  Card
     * @return True if colors of cards are similar.
     */
    public  boolean similarColorTo(Card c){
        if(this.isBlackOrRed() == c.isBlackOrRed()){
            return true;
        }else{
            return false;
        }
    }


    /**
     *
     * @param c Card
     * @return Function return zero when card values are same.
     */
    public int compareValue(Card c){
        return this.value - c.value();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object object){
        if(object == null)
            return false;

        if(!Card.class.isAssignableFrom(object.getClass()))
            return false;

        final Card card = (Card) object;

        if(this.hashCode()!=card.hashCode())
            return false;

        return true;

    }

    @Override
    public String toString(){
        String strValue;
        switch(value){
            case 1: strValue = "A"; break;
            case 11: strValue = "J"; break;
            case 12: strValue = "Q"; break;
            case 13: strValue = "K"; break;
            default: strValue = "" + this.value;
        }
        return strValue+"("+this.color.toString()+")";
    }

    @Override
    public JLabel getJLabel()
    {
        if (this.faceUp) {
            this.label.setIcon(iconTurned);
            return label;
        }
        else
        {
            this.label.setIcon(iconUnturned);
            return label;
        }
    }

    /**
     * Create image icon by path.
     * @param path Path to file
     * @return Image or null.
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

}
