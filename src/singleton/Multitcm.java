package singleton;

import java.util.HashMap;
import java.util.Random;

/**
 * @Description TODO 有限多例模式
 * @Author xtf
 * @Date 2019/8/5 16:07
 */
public class Multitcm {
    public static void main(String[] args){
        for(int i = 0; i < 6; i++) {
            Droid droid = Droid.getInstance();
            System.out.println(droid);
        }
    }
}

/**
 * @Description TODO 机器人类，使用有限多例模式，这里创建两个
 * @Author xtf
 * @Date 2019/8/5 16:09
 * @Param
 * @return
 */
class Droid {
    /**
     * 使用一个hashMap来保存有限的多例，每次取出的时候，随机取一个
     **/
    private static HashMap<String, Droid> droids = new HashMap<String, Droid>();

    /**
     * 每个机器人都要有自己的名字
     **/
    private String name;

    /**
     * 使用一个String来存放两个机器人的名字
     **/
    private static final String[] names = {"阿尔法狗", "骂死他"};

    // 直接构造出两个机器人
    static {
        // 第一个机器人是AlphaGo
        Droid alphaGo = new Droid(names[0]);
        droids.put(names[0], alphaGo);

        // 第二个机器人是master
        Droid master = new Droid(names[1]);
        droids.put(names[1], master);
    }

    /**
     * @Description TODO 构造函数私有，防止外部调用，在构造时必须填入名字
     * @Author xtf
     * @Date 2019/8/5 16:14
     * @Param [name]
     * @return
     */
    private Droid(String name) {
        this.name = name;
    }

    /**
     * @Description TODO 随机返回一个机器人
     * @Author xtf
     * @Date 2019/8/5 16:21
     * @Param []
     * @return singleton.Droid
     */
    public static Droid getInstance(){
        Random random = new Random();
        int index = random.nextInt(2);
        return droids.get(names[index]);
    }

    @Override
    public String toString() {
        return "我是：" + this.name;
    }
}
