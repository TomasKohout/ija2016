package ija.ija2016.homework2.model.cards;

/**
 * Created by xblaze31 on 24.3.2017.
 */
public class CardTargetDeck extends CardDeckMethods {
    private Card.Color targetColor;

    public CardTargetDeck(Card.Color color,int size){
        super(size);
        targetColor = color;
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


}
