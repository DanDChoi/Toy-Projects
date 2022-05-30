package chap1.java7;

import chap1.Apple;

public class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean test (Apple apple, Apple compare){
        return apple.getColor().equals(compare.getColor());
    }
}
