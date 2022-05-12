public class Card {
    private Pattern pattern;
    private String denomination;
    private int point;

    public Card(Pattern pattern, int index){
        this.pattern = pattern;
        this.denomination = this.numberToDenomination(index);
        this.point = this.numberToPoint(index);
    }
    private String numberToDenomination(int number){
        if(number == 1){
            return "A";
        }else if(number == 11){
            return "J";
        }else if(number == 12){
            return "Q";
        }else if(number == 13){
            return "K";
        }
        return String.valueOf(number);
    }

    private int numberToPoint(int number){
        if(number >= 11){
            return 10;
        }
        return number;
    }

    public Pattern getPattern(){
        return pattern;
    }
    public void setPattern(Pattern pattern){
        this.pattern = pattern;
    }
    public String getDenomination(){
        return denomination;
    }
    public void setDenomination(String denomination){
        this.denomination = denomination;
    }

    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point = point;
    }

    @Override
    public String toString(){
        return "Card{" + "pattern='" + pattern + ", demnomination='" + denomination +"'}";
    }

    public enum Pattern{
        SPADE("spade"),
        HEART("heart"),
        DIAMOND("diamond"),
        CLUB("club");

        private String value;

        Pattern() {}
        Pattern(String value){
            this.value = value;
        }
    }
}
