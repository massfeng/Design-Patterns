package adapter;

/**
 * @Description TODO
 * 我们要访问的接口A中不存在我们需要的方法，但是在接口B中我们发现了该方法
 * 所以这时我们需要一个适配器p来帮助我们在访问A时同时访问到B中的我们需要的方法
 * 以USB口和PS2口为例，我们要访问的接口是PS2口，但是不存在我们需要的方法(在USB接口中)
 * 我们又不能直接修改PS2接口的内容，所以我们创建一个适配器来帮助我们适配USB口
 * 适配器分为两种，一种是类适配器，一种是对象适配器
 * @Author xtf
 * @Date 2019/8/8 15:04
 */
public class UsbDemo {
    public static void main(String[] args){
        Adpter1 adpter1 = new Adpter1();
        adpter1.ps2();

        Adpter2 adpter2 = new Adpter2(new Usber());
        adpter2.ps2();
    }
}

/**
 * @Description TODO ps2的接口（我们直接访问的接口）
 * @Author xtf
 * @Date 2019/8/8 15:07
 */
interface Ps2 {
    public void ps2();
}

/**
 * @Description TODO usb的接口（我们需要该接口中的方法）
 * @Author xtf
 * @Date 2019/8/8 15:07
 */
interface Usb {
    public void usb();
}

/**
 * @Description TODO usb口的实现类
 * @Author xtf
 * @Date 2019/8/8 15:08
 */
class Usber implements Usb {
    @Override
    public void usb() {
        System.out.println("我是usb口中的方法");
    }
}

/**
 * @Description TODO 类适配器
 * @Author xtf
 * @Date 2019/8/8 15:11
 */
class Adpter1 extends Usber implements Ps2 {
    @Override
    public void ps2() {
        super.usb();
    }
}

/**
 * @Description TODO 对象适配器
 * @Author xtf
 * @Date 2019/8/8 15:12
 */
class Adpter2 implements Ps2 {
    private Usb usb;
    public Adpter2(Usb usb) {
        this.usb = usb;
    }
    @Override
    public void ps2() {
        usb.usb();
    }
}