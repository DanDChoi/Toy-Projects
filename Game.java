import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Game {

    public void play(){
        System.out.println("=====black jack======");
        Scanner sc = new Scanner(System.in);

        List<Players> players = Arrays.asList(new Gamer(), new Dealer());
        Rule rule = new Rule();
        CardDeck cardDeck = new CardDeck();

        initPhase(cardDeck, players);
        playingPhase(sc, cardDeck, gamer); 
    }

    private void playingPhase(Scanner sc, CardDeck cardDeck, List<Player> players){
        while(true){
            boolean isAllPlayerTurnOff = receiveCardAllPlayers(sc, cardDeck, players);
            
            if(isAllPlayerTurnOff){
                break;
            }
        }
    }

    private boolean receiveCardAllPlayers(Scanner sc, CardDeck cardDeck, List<Player> players){
        boolean isAllPlayerTurnOff = true;

        for(Player player : players){
            if(isReceiveCard(sc)){
                Card card = cardDeck.draw();
                player.receiveCard(card);
                isAllPlayerTurnOff = false;
            }else{
                isAllPlayerTurnOff = true;
            }
        }
        return isAllPlayerTurnOff;
    }

    private boolean isReceiveCard(Scanner sc){
        System.out.println("카드를 뽑으시겠습니까? 종료를 원하시면 0을 입력하세요");
        return !STOP_RECEIVE_CARD.equals(sc.nextLine());
    }

    private static final int INIT_RECEIVE_CARD_COUNT = 2;
    private void initPhase(CardDeck cardDeck, List<Player> players){
        System.out.println("처음 2장의 카드를 각자 뽑겠습니다");
        for(int i=0; i<INIT_RECEIVE_CARD_COUNT; i++){
            for(Player player : players){
                Card card = cardDeck.draw();
                player.receiveCard(card);
            }
        }
    }
}
