import java.beans.Transient;
import java.util.List;

public class ApplicationTest {
    private CardDeck cardDeck;
    private List<Card> cards;

    @Before
    public void setUp(){
        CardDeck cardDeck = new CardDeck();
        cards = cardDeck.getCards();
    }
    
    @Test
    public void test 카드패턴비교 (){
        assertThat(cards.get(0).getPattern(), is(Card.Pattern.SPADE));
        assertThat(cards.get(13).getPattern(), is(Card.Pattern.HEART));
    }

    @Test
    public void test 카드끗수비교(){
        assertThat(cards.get(0).getDenomination(), is(Card.Denomination.ACE));
        assertThat(cards.get(0).getDenomination().getPoint(), is(1));
    }    
}
