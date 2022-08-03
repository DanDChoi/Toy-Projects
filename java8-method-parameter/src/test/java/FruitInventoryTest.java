import chap1.Apple;
import chap1.FruitInventory;

import java.util.ArrayList;
import java.util.List;

public class FruitInventoryTest {

    public void 익명클래스 () {
        List<Apple> inventory = new ArrayList<>();

        FruitInventory.filterApples(inventory,
                (apple, compare) -> apple.getWeight() > compare.getWeight(),
                Apple.builder()
                        .weight(150)
                        .build());

    }
}