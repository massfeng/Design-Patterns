package factory;

/**
 * @Description TODO 抽象工厂模式
 * @Author xtf
 * @Date 2019/8/6 11:43
 */
public class AbstractFactory {
    public static void main(String[] args){
        Farm f = new NanChangFarm();
        Animal animal = f.newAnimal();
        animal.eat();
        Plant plant = f.newPlant();
        plant.eat();
    }
}

/**
 * @Description TODO 动物接口，动物有吃的功能
 * @Author xtf
 * @Date 2019/8/6 11:47
 */
interface Animal {
    public void eat();
}

/**
 * @Description TODO 马的实现类
 * @Author xtf
 * @Date 2019/8/6 11:49
 */
class Horse implements Animal{

    @Override
    public void eat() {
        System.out.println("吃马");
    }
}

/**
 * @Description TODO 牛的实现类
 * @Author xtf
 * @Date 2019/8/6 11:49
 */
class Cattle implements Animal {

    @Override
    public void eat() {
        System.out.println("吃牛");
    }
}

/**
 * @Description TODO 植物的接口，有吃的功能
 * @Author xtf
 * @Date 2019/8/6 11:49
 */
interface Plant {
    public void eat();
}

/**
 * @Description TODO 水果类
 * @Author xtf
 * @Date 2019/8/6 11:51
 */
class Fruitage  implements Plant {

    @Override
    public void eat() {
        System.out.println("吃水果");
    }
}

/**
 * @Description TODO
 * @Author xtf
 * @Date 2019/8/6 11:51
 */
class Vegetables implements Plant{

    @Override
    public void eat() {
        System.out.println("吃蔬菜");
    }
}

/**
 * @Description TODO 农场的工厂类接口
 * @Author xtf
 * @Date 2019/8/6 11:52
 */
interface Farm {
    /**
     * @Description TODO 可以生产动物
     * @Author xtf
     * @Date 2019/8/6 11:53
     * @Param []
     * @return Animal
     */
    public Animal newAnimal();
    /**
     * @Description TODO 可以生产职务
     * @Author xtf
     * @Date 2019/8/6 11:53
     * @Param []
     * @return Plant
     */
    public Plant newPlant();
}

/**
 * @Description TODO 南昌农场，可以生产马和水果
 * @Author xtf
 * @Date 2019/8/6 11:55
 */
class NanChangFarm implements Farm {
    @Override
    public Animal newAnimal() {
        return new Horse();
    }

    @Override
    public Plant newPlant() {
        return new Fruitage();
    }
}

/**
 * @Description TODO 樟树农场，可以生产牛和蔬菜
 * @Author xtf
 * @Date 2019/8/6 11:56
 * @Param
 * @return
 */
class ZhangShuFarm implements Farm {

    @Override
    public Animal newAnimal() {
        return new Cattle();
    }

    @Override
    public Plant newPlant() {
        return new Vegetables();
    }
}
