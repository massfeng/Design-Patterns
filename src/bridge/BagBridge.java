package bridge;

/**
 * @Description TODO 桥接模式
 * 包包有很多种，有双肩包，单肩包，也有很多种颜色，红色、蓝色等
 * 这个时候如果单纯用继承，非常麻烦，有很多种组合方式
 * 所以这时候我们可以用桥接模式
 * @Author xtf
 * @Date 2019/8/8 15:39
 */
public class BagBridge {
    public static void main(String[] args){
        Color color;
        Bag bag;
        color = new Yellow();
        bag = new BackPack();
        bag.setColor(color);
        System.out.println(bag.getName());
    }
}

/**
 * @Description TODO 包的抽象类
 * @Author xtf
 * @Date 2019/8/8 15:44
 */
abstract class Bag {
    protected Color color;
    public void setColor(Color color) {
        this.color = color;
    }
    public abstract String getName();
}

/**
 * @Description TODO 颜色的接口
 * @Author xtf
 * @Date 2019/8/8 15:42
 */
interface Color {
    public String getColor();
}

/**
 * @Description TODO 黄色
 * @Author xtf
 * @Date 2019/8/8 15:45
 */
class Yellow implements Color {
    @Override
    public String getColor() {
        return "yellow";
    }
}

/**
 * @Description TODO 红色
 * @Author xtf
 * @Date 2019/8/8 15:48
 */
class Red implements Color {
    @Override
    public String getColor() {
        return "red";
    }
}

/**
 * @Description TODO 双肩包
 * @Author xtf
 * @Date 2019/8/8 15:48
 */
class BackPack extends Bag {
    @Override
    public String getName() {
        return color.getColor() + "BackPack";
    }
}

/**
 * @Description TODO 单肩包
 * @Author xtf
 * @Date 2019/8/8 15:48
 */
class SingPack extends Bag {

    @Override
    public String getName() {
        return color.getColor() + "SingPack";
    }
}

