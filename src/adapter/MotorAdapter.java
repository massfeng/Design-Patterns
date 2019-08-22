package adapter;

/**
 * @Description TODO
 * 新能源汽车的发动机有电能发动机（Electric Motor）和光能发动机（Optical Motor）等，
 * 各种发动机的驱动方法不同，例如，电能发动机的驱动方法 electricDrive() 是用电能驱动，
 * 而光能发动机的驱动方法 opticalDrive() 是用光能驱动
 * 客户端希望用统一的发动机驱动方法 drive() 访问这两种发动机，所以必须定义一个统一的目标接口 Motor，
 * 然后再定义电能适配器（Electric Adapter）和光能适配器（Optical Adapter）去适配这两种发动机。
 * 适配器有两种模式，一种是类适配器，一种是对象适配器
 * 类适配器既继承又实现接口
 * 对象适配器只实现接口，而具体对象在构造函数时注入
 * @Author xtf
 * @Date 2019/8/8 14:48
 */
public class MotorAdapter {
    public static void main(String[] args){
        Moter motor = new OpticalDrive2();
        motor.drive();
    }
}

/**
 * @Description TODO 发动机接口
 * @Author xtf
 * @Date 2019/8/8 14:50
 */
interface Moter {
    public void drive();
}

/**
 * @Description TODO 电能发动机实现类
 * @Author xtf
 * @Date 2019/8/8 14:51
 */
class ElectricMoter {
    public void electricDrive() {
        System.out.println("电动发动机");
    }
}

/**
 * @Description TODO 光能发动机实现类
 * @Author xtf
 * @Date 2019/8/8 14:52
 */
class OpticalMotor {
    public void opticalDrive() {
        System.out.println("光能发动机");
    }
}

/**
 * @Description TODO 电能驱动 对象适配器模式
 * @Author xtf
 * @Date 2019/8/8 14:54
 */
class ElectricDrive1 implements Moter {
    private ElectricMoter em;
    public ElectricDrive1() {
        em = new ElectricMoter();
    }
    @Override
    public void drive() {
        em.electricDrive();
    }
}

/**
 * @Description TODO 电能驱动 类适配器模式
 * @Author xtf
 * @Date 2019/8/8 14:55
 */
class ElectricDrive2 extends ElectricMoter implements Moter {
    @Override
    public void drive() {
        super.electricDrive();
    }
}

/**
 * @Description TODO 光能启动 对象适配器模式
 * @Author xtf
 * @Date 2019/8/8 14:56
 */
class OpticalDrive1 implements Moter {
    private OpticalMotor om;
    public OpticalDrive1() {
        om = new OpticalMotor();
    }
    @Override
    public void drive() {
        om.opticalDrive();
    }
}

/**
 * @Description TODO  光能驱动 类适配器模式
 * @Author xtf
 * @Date 2019/8/8 14:57
 */
class OpticalDrive2 extends OpticalMotor implements Moter {
    @Override
    public void drive() {
        super.opticalDrive();
    }
}