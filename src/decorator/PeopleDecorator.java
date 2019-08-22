package decorator;

/**
 * @Description TODO
 * 人有吃饭的功能，但是我们需要拓展人吃牛排和吃面条的功能的时候怎么办呢，这个时候我们可以使用装饰者模式
 * @Author xtf
 * @Date 2019/8/18 10:18
 */
public class PeopleDecorator {
    public static void main(String[] args){
        ExpensiveFood ef1 = new ExpensiveFood(new NormalPerson());
        ef1.eat();

        ExpensiveFood ef2 = new ExpensiveFood(new NormalPerson2());
        ef2.eat();
    }
}

/**
 * @Description TODO 人定义为抽象类，有一个吃东西的方法
 * @Author xtf
 * @Date 2019/8/18 10:22
 */
interface Person {
    public void eat();
}

/**
 * @Description TODO 普通人可以吃饭
 * @Author xtf
 * @Date 2019/8/18 10:23
 */
class NormalPerson implements Person {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }
}

class NormalPerson2 implements Person {
    @Override
    public void eat() {
        System.out.println("喝水");
    }
}
/**
 * @Description TODO 这里定义一个装饰者的抽象类，保持一个对Person的引用，可以方便调用具体的被装饰者的方法，方便对其进行扩展
 * @Author xtf
 * @Date 2019/8/18 10:24
 */
class PeopleFood extends NormalPerson {
    private Person person;

    public PeopleFood(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        person.eat();
    }
}

/**
 * @Description TODO 具体装饰类
 * @Author xtf
 * @Date 2019/8/18 10:51
 */
class ExpensiveFood extends PeopleFood {
    public ExpensiveFood(Person person) {
        super(person);
    }

    @Override
    public void eat() {
        super.eat();
        eatSteak();
        drinkRedWine();
    }

    public void eatSteak() {
        System.out.println("吃牛排");
    }

    public void drinkRedWine() {
        System.out.println("喝拉菲");
    }
}

/**
 * @Description TODO 具体装饰类
 * @Author xtf
 * @Date 2019/8/18 10:53
 */
class CheapFood extends PeopleFood {
    public CheapFood(Person person) {
        super(person);
    }

    @Override
    public void eat() {
        super.eat();
        eatNoodles();
    }

    public void eatNoodles() {
        System.out.println("吃面条");
    }
}