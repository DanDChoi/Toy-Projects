package chap1.java7;

import chap1.Apple;

public interface ApplePredicate {
    boolean test(Apple apple, Apple compare);
}
