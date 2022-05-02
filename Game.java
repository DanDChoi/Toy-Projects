import java.util.Scanner;

public class Game {
    public void play(){
        System.out.println("=====black jack======");
        Sacnner sc = new Sacnner(System.in);

        Dealer dealer = new Dealer();
        Gamer gamer = new Gamer();
        Rule rule = new Rule();
        CardDeck cardDeck = new CardDeck();

        playingPhase(sc, cardDeck, gamer);
    }
    private void playingPhase(Scanner sc, CardDeck cardDeck, Gamer gamer){
        String gamerInput;
        while(true){
            System.out.println("카드를 뽑으시겠습니까? 종료를 원하시면 0을 입력하세요");
            gamerInput = sc.nextLine();

            if("0".equals(gamerInput)){
                break;
            }

            Card card = cardDeck.draw();
            gamer.receiveCard(card);
        }
    }
}
