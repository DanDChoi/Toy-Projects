import java.util.List;

public class ApplicationTest {
    
    @Test
    public void test 카드패턴비교 (){
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = cardDeck.getCards();
        assertThat(cards.get(0).getPattern(), is(Card.Pattern.SPADE));
        assertThat(cards.get(13).getPattern(), is(Card.Pattern.HEART));
    }
    
}
