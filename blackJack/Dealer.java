import java.util.List;
import java.util.ArrayList;

public class Dealer implements Player {
    private List<Card> cards;
    private boolean turn;

    private static final int CAN_RECEIVE_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer(){
        cards = new ArrayList<>();
    }

    @Override
    public void receiveCard(Card card){
        if(this.isReceiveCard()){
            this.cards.add(card);
            this.showCards();
        }else{
            System.out.println("카드의 총 합이 17 이상입니다. 더 이상 카드를 받을 수 없습니다.");
        }
    }
    private boolean isReceiveCard(){
        return getPointSum() <= CAN_RECEIVE_POINT;
    }
    private int getPointSum(){
        int sum = 0;
        for(Card card : cards){
            sum += card.getDenomination().getPoint();
        }
        return sum;
    }

    @Override
    public void showCards(){
        StringBuilder sb = new StringBuilder();
        sb.append("현재 카드 보유 목록 \n");

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");            
        }
        System.out.println(sb.toString());
    }

    @Override
    public List<Card> openCards(){
        return this.cards;
    }

    @Override
    public void turnOff(){
        this.setTurn(false);
    }

    @Override
    public void turnOn(){
        this.setTurn(true);
    }

    @Override
    public boolean isTurn(){
        return this.turn;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    @Override
    public String getName(){
        return NAME;
    }
}
