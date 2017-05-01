package ija.ija2016.model.cards;

/**
 * Created by xblaze31 on 24.3.2017.
 */
public class CardTargetDeck extends CardDeckMethods {
    private Card.Color targetColor;

    @Override
    public Card pop(){
        if(this.size() > 0) {
            if (this.size() == 1)
                this.targetColor = null;
            return this.s.pop();
        }
        else
            return null;
    }

    public CardTargetDeck(Card.Color color,int size){
        super(size);
    }

    public boolean put(Card card){
        if(this.size > this.size()){
            if(this.targetColor == card.color()){
                if(this.size()+1  == (card.value())){
                    this.s.push(card);
                    return true;
                }
            }
        }
        return false;
    }

    public void setColor(Card.Color color)
    {
        this.targetColor = color;
    }

    public boolean getColor()
    {
        return this.targetColor == null;
    }


}
