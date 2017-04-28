package ija.ija2016.model.cards;

/**
 * Created by xblaze31 on 24.3.2017.
 */
public class CardMethods implements Card{
    //Atributy
    private Card.Color color;
    private int value;
    private boolean faceUp;

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

    public CardMethods(Card.Color c, int value){
        this.value = value;
        this.color = c;
        this.faceUp = false;
    }

    public int value(){
        return this.value;
    }

    public boolean isTurnedFaceUp(){
        return this.faceUp;
    }

    public boolean turnFaceUp(){
        if(!this.faceUp){
            this.faceUp = true;
            return true;
        }
        return false;
    }

    public Card.Color color(){
        return this.color;
    }

    /**
     * @return False if Red, True if Black
     */
    public boolean isBlackOrRed(){
        if(this.color == Card.Color.CLUBS || this.color == Card.Color.SPADES) {
            return true;
        }else {
            return false;
        }
    }

    public  boolean similarColorTo(Card c){
        if(this.isBlackOrRed() == c.isBlackOrRed()){
            return true;
        }else{
            return false;
        }
    }

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

}
