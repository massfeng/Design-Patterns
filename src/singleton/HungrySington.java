package singleton;

/**
 * @Description TODO 饿汉式单例模式
 * @Author xtf
 * @Date 2019/8/5 11:51
 */
public class HungrySington {
    public static void main(String[] args){
        BaJie instance1 = BaJie.getInstance();
        System.out.println(instance1);
        BaJie instance2 = BaJie.getInstance();
        System.out.println(instance2);
        if(instance1 == instance2){
            System.out.println("我们是同一个八戒哦！");
        } else {
            System.out.println("我们是不同的八戒哦！");
        }
    }
}

/**
 * @Description TODO 八戒只有一个，所以可以用单例模式
 * @Author xtf
 * @Date 2019/8/5 11:54
 */
class BaJie {
    /**
     * 饿汉式单例模式，在类初始化的时候就已经创建完成
     **/
    public static BaJie instance = new BaJie();

    /**
     * 构造函数，防止外界调用，使用private
     **/
    private BaJie(){
        System.out.println("创建了一个八戒");
    }

    /**
     * @Description TODO 直接返回这个单例，因为在类初始化的时候已经构造完成
     * @Author xtf
     * @Date 2019/8/5 12:07
     * @Param []
     * @return singleton.BaJie
     */
    public static BaJie getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "我是八戒";
    }
}
