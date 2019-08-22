package strategy;

/**
 * @Description TODO 策略模式
 * 定义一个个算法，把他们封装起来，并使他们可以相互替换
 * 我们这里定义三个算法，加减乘，使用策略模式使他们可以互相替换
 * @Author xtf
 * @Date 2019/8/19 15:58
 */
public class Math {
    public static void main(String[] args){
        Context c1 = new Context(new Add());
        System.out.println(c1.executeStrategy(1,2));

        Context c2 = new Context(new Sub());
        System.out.println(c2.executeStrategy(1,2));

        Context c3 = new Context(new Mul());
        System.out.println(c3.executeStrategy(1,2));
    }
}

/**
 * @Description TODO 面向接口编程，使他们实现相同的接口
 * @Author xtf
 * @Date 2019/8/19 16:00
 */
interface Strategy {
    public int doOperation(int num1, int num2);
}

/**
 * @Description TODO 加法策略
 * @Author xtf
 * @Date 2019/8/19 16:01
 */
class Add implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

/**
 * @Description TODO 减法策略
 * @Author xtf
 * @Date 2019/8/19 16:01
 */
class Sub implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * @Description TODO 乘法策略
 * @Author xtf
 * @Date 2019/8/19 16:02
 */
class Mul implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

/**
 * @Description TODO Context来查看改变策略时的行为变化
 * @Author xtf
 * @Date 2019/8/19 16:03
 */
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}