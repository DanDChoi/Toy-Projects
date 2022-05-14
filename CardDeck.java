import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> cards;
    private static final String[] PATTERNS = {"spade", "heart", "diamond", "club"};
    private static final int CARD_COUNT = 13;

    public Stack<Card> getCards(){
        return cards;
    }

    public CardDeck() {
        cards = this.generateCards();
        Collections.shuffle(this.cards); 

    }
    private Stack<Card> generateCards(){
        Stack<Card> cards = new Stack<>();

        for(Card.Pattern pattern : Card.Pattern.values()){
            for(Card.Denomination denomination : Card.Denomination.values()){
                Card card = new Card(pattern, denomination);
                cards.push(card);
            }
        }
        return cards;
    }

    public Card draw(){
        return this.cards.pop();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }
        return toString();
    }
}
