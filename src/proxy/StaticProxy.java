package proxy;

/**
 * @Description TODO 静态代理
 * 有个歌手singer，有一个方法sing()，可以唱一首歌，但是我们想在唱歌的基础上，加入和观众问好和告别观众的功能，怎么办呢？
 * 这个时候我们就要采用代理模式。代理模式简单来说就是在不改变源码的情况下，实现对目标对象的功能扩展
 * @Author xtf
 * @Date 2019/8/6 16:55
 */
public class StaticProxy {
    public static void main(String[] args){
        SingerProxy sp = new SingerProxy(new Singer());
        sp.sing();
    }
}

/**
 * @Description TODO 唱歌的接口，包含了一个唱歌方法
 * @Author xtf
 * @Date 2019/8/6 16:58
 */
interface ISinger {
    public void sing();
}

/**
 * @Description TODO 歌手对象，他的sing()方法只能唱一首歌
 * @Author xtf
 * @Date 2019/8/6 16:59
 */
class Singer implements ISinger {
    @Override
    public void sing() {
        System.out.println("唱了一首歌");
    }
}

/**
 * @Description TODO 静态代理对象，在之前的歌手唱歌的基础上加入了向观众问好和向观众告别的功能
 * @Author xtf
 * @Date 2019/8/6 17:02
 */
class SingerProxy implements ISinger {
    private ISinger target;
    SingerProxy(ISinger target) {
        this.target = target;
    }

    @Override
    public void sing() {
        System.out.println("向观众问好");
        target.sing();
        System.out.println("向观众告别");
    }
}