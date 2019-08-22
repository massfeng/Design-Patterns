package templatemethod;

/**
 * @Description TODO 模板方法模式
 * 实现调试饮料的功能
 *
 * 冲泡咖啡：
 * 1.把水煮沸
 * 2.用沸水冲泡咖啡
 * 3.把咖啡倒入杯子
 * 4.加糖和牛奶
 *
 * 冲泡茶：
 * 1.把水煮沸
 * 2.用沸水冲泡茶叶
 * 3.把茶倒入被子
 * 4.加柠檬
 *
 * 我们发现，1和3是很像的，4的步骤（加糖和牛奶/加柠檬）本质上都是加调料。
 * 所以在这里，如果我们每个类都一步一步重复写一遍，我们会发现有很多重复代码
 * 所以我们可以使用模板方法模式，找出共性进行封装，提取相同的代码，使系统达到维护和扩展成本很低的状态。
 * @Author xtf
 * @Date 2019/8/19 14:56
 */
public class Drink {
    public static void main(String[] args){
        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();

        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

/**
 * @Description TODO 模板方法
 * @Author xtf
 * @Date 2019/8/19 15:03
 */
abstract class CaffeineBeverage {
    /**
     * 冲泡饮料的算法，不允许被子类修改
     **/
    public final void prepareRecipe() {

        // 算法的具体步骤
        boilWater(); // 烧水
        brew(); // 冲泡
        pourInCup(); // 把饮料倒入杯子
        // 如果要加调料
        if(hook()) {
            addCondiments(); // 加调料
        }
    }

    public abstract void brew();

    public abstract void addCondiments();

    public void boilWater() {
        System.out.println("烧水");
    }

    public void pourInCup() {
        System.out.println("把饮料倒入杯子");
    }

    /**
     * 钩子函数，可以对算法实现做一些控制
     **/
    public boolean hook() {
        return true;
    }

}

/**
 * @Description TODO 咖啡类
 * @Author xtf
 * @Date 2019/8/19 15:12
 */
class Coffee extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("用沸水冲泡咖啡");
    }

    @Override
    public void addCondiments() {
        System.out.println("加糖和牛奶");
    }

    @Override
    public boolean hook() {
        return super.hook();
    }
}

/**
 * @Description TODO 茶类
 * @Author xtf
 * @Date 2019/8/19 15:12
 */
class Tea extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("用沸水浸泡茶叶");
    }

    @Override
    public void addCondiments() {
        System.out.println("加柠檬");
    }

    @Override
    public boolean hook() {
        return super.hook();
    }
}



