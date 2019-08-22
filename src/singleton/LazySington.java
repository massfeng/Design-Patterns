package singleton;

/**
 * @Description TODO 懒汉式单例模式
 * @Author xtf
 * @Date 2019/8/5 11:35
 */
public class LazySington {
    public static void main(String[] args) {
        President instance1 = President.getInstance();
        System.out.println(instance1);
        President instance2 = President.getInstance();
        System.out.println(instance2);
        if(instance1 == instance2) {
            System.out.println("我们是同一个人！");
        } else {
            System.out.println("我们不是同一个人！");
        }
    }
}

/**
 * 美国总统类，因为世界上只能有一个，所以适合用单例模式
 */
class President{
    /**
     * 类初始化的时候单例为null，只有在getInstance的时候才初始化
     * 使用volatile关键字保证线程安全
     */
    private static volatile President instance = null;

    /**
     * 构造函数，防止外界调用，使用private
     */
    private President(){
        System.out.println("产生了一个总统！");
    }

    /**
     * 如果是第一次创建，即instance为null，此时创建一个总统
     * 如果不是第一次创建，直接返回instance
     * 使用synchronized来保证线程安全
     */
    public static synchronized President getInstance() {
        if(instance == null) {
            instance = new President();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "我是美国总统特朗普";
    }
}