package builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 建造者模式示例，肯德基中有很多单品，例如汉堡，可乐等，单品可以组成套餐，此时可以用建造者模式
 * 汉堡用纸盒包装，可乐用瓶子包装。汉堡有素汉堡和肉汉堡，可乐有百事可乐和可口可乐。
 * @Author xtf
 * @Date 2019/8/6 15:41
 */
public class BuilderPattern {
    public static void main(String[] args){
        MealBuilder mb = new MealBuilder();
        Meal meal1 = mb.Meal1();
        System.out.println(meal1.getCost());
        meal1.showItems();

        Meal meal2 = mb.Meal2();
        System.out.println(meal2.getCost());
        meal2.showItems();
    }
}


/**
 * @Description TODO 使用套餐建造者建造套餐对象
 * @Author xtf
 * @Date 2019/8/6 16:13
 */
class MealBuilder {
    /**
     * @Description TODO 套餐一，其中包括蔬菜汉堡和可口可乐
     * @Author xtf
     * @Date 2019/8/6 16:15
     * @Param []
     * @return builder.Meal
     */
    public Meal Meal1() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * @Description TODO 套餐2，其中包括鸡肉汉堡和百事可乐
     * @Author xtf
     * @Date 2019/8/6 16:15
     * @Param []
     * @return builder.Meal
     */
    public Meal Meal2() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}

/**
 * @Description TODO 单品类，每个单品都有名字，包装和价格
 * @Author xtf
 * @Date 2019/8/6 15:43
 */
interface Item {
    public String name();
    public Packing packing();
    public float price();
}

/**
 * @Description TODO 包装类
 * @Author xtf
 * @Date 2019/8/6 15:46
 */
interface Packing {
    public String pack();
}

/**
 * @Description TODO 纸盒类
 * @Author xtf
 * @Date 2019/8/6 15:46
 */
class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Wrapper";
    }
}

/**
 * @Description TODO 瓶子类
 * @Author xtf
 * @Date 2019/8/6 15:47
 */
class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle";
    }
}

/**
 * @Description TODO 实现了特定功能的Item实现类，汉堡类，汉堡用纸盒装
 * @Author xtf
 * @Date 2019/8/6 15:49
 */
abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}

/**
 * @Description TODO 实现了特定功能的Item实现类，可乐类，汉堡用瓶子装
 * @Author xtf
 * @Date 2019/8/6 15:50
 */
abstract class Cola implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}

/**
 * @Description TODO 拓展了Burger类的实体类，素食汉堡类
 * @Author xtf
 * @Date 2019/8/6 15:51
 */
class VegBurger extends Burger {

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

/**
 * @Description TODO 拓展了Burger类的实体类，鸡肉汉堡类
 * @Author xtf
 * @Date 2019/8/6 15:52
 */
class ChickenBurger extends Burger {

    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 45.0f;
    }
}

/**
 * @Description TODO 可乐类的实体类，可口可乐
 * @Author xtf
 * @Date 2019/8/6 15:54
 */
class Coke extends Cola {

    @Override
    public String name() {
        return "Coke Cola";
    }

    @Override
    public float price() {
        return 10.0f;
    }
}

/**
 * @Description TODO 可乐类的实体类，百事可乐
 * @Author xtf
 * @Date 2019/8/6 15:54
 */
class Pepsi extends Cola {

    @Override
    public String name() {
        return "Pepsi Cola";
    }

    @Override
    public float price() {
        return 5.0f;
    }
}

/**
 * @Description TODO 用Meal类表示套餐
 * @Author xtf
 * @Date 2019/8/6 15:59
 */
class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float f = 0.0f;
        for (Item item:items) {
            f += item.price();
        }
        return f;
    }

    public void showItems() {
        for(Item item:items) {
            System.out.println("name:" + item.name() + ",pack:" + item.packing().pack() + ",price:" + item.price());
        }
    }
}
