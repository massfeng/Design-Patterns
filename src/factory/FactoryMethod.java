package factory;

/**
 * @Description TODO 工厂方法模式，我们有很多的电视生产线，例如海信，TCL电视生产线
 * 每个生产线生产不同的电视，例如海信电视，TCL电视，所以适合用工厂模式
 * @Author xtf
 * @Date 2019/8/6 10:26
 */
public class FactoryMethod {
    public static void main(String[] args){
        TV t;
        AbstractTVFactory atf = new HisenseFactory();
        t = atf.newTV();
        t.watchTV();
    }
}

/**
 * @Description TODO 电视的接口，具有看电视的功能
 * @Author xtf
 * @Date 2019/8/6 10:30
 */
interface TV {
    public void watchTV();
}

/**
 * @Description TODO 海信电视
 * @Author xtf
 * @Date 2019/8/6 10:34
 */
class Hisense implements TV {
    @Override
    public void watchTV() {
        System.out.println("看海信电视");
    }
}

/**
 * @Description TODO TCL电视
 * @Author xtf
 * @Date 2019/8/6 10:34
 */
class TCL implements TV {

    @Override
    public void watchTV() {
        System.out.println("看TCL电视");
    }
}

/**
 * @Description TODO 电视工厂的接口，具有生产电视的功能
 * @Author xtf
 * @Date 2019/8/6 10:31
 */
interface AbstractTVFactory {
    public TV newTV();
}

/**
 * @Description TODO 海信电视工厂，可以生产海信电视
 * @Author xtf
 * @Date 2019/8/6 10:35
 */
class HisenseFactory implements AbstractTVFactory {
    @Override
    public TV newTV() {
        System.out.println("生产了一个Hisense电视");
        return new Hisense();
    }
}

/**
 * @Description TODO TCL电视工厂，可以生产TCL电视
 * @Author xtf
 * @Date 2019/8/6 10:36
 * @Param
 * @return
 */
class TCLFactory implements AbstractTVFactory {

    @Override
    public TV newTV() {
        System.out.println("生产了一个TCL电视");
        return new TCL();
    }
}
