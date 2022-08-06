package chap1.java7;

import chap1.Apple;

public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test (Apple apple, Apple compare){
        return apple.getWeight() > compare.getWeight(); // compare에 150g이 포함
    }
}
