package chap1;

import chap1.java7.ApplePredicate;
import chap1.java8.AppleOnePredicate;

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
    //"특정 무게 상관없이 빨간사과를 찾고싶다" || "특정 색깔 상관없이 150g 이상인 사과를 찾고싶다" 라면?
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean useColor){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if((useColor && apple.getColor().equals(color)) //이게 정말 좋은 코드일까? 요구사항에 따라 유연하게 대처 가능? 더 많은 조건이 추가되면?
                || (!useColor && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    //파라미터에 Stirng color, int weight 대신 ApplePredicate 메소드를 넘겨주면 유연하게 대처 가능
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate, Apple compare){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (predicate.test(apple, compare)) {
                result.add(apple);
            }
        }
        return result;
    }

}
