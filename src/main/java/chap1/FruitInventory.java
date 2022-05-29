package chap1;

import java.util.ArrayList;
import java.util.List;

public class FruitInventory {

    //녹색 사과를 찾고싶다!
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    //만약 빨간색사과도 찾고싶다면?
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){ //(1)파라미터에 color를 받아
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if(apple.getColor().equals(color)){ // (2)비교조건에 사용
                result.add(apple);
            }
        }
        return result;
    }
}
