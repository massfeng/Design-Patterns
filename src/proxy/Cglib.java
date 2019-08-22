package proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description TODO 使用Cglib代理，因为Spring已经集成了Cglib所以直接用spring-core的jar包
 * 使用静态代理或者动态代理时，目标对象必须实现一个或多个接口，而当对象不实现接口时，可以使用Cglib
 * @Author xtf
 * @Date 2019/8/6 17:24
 */
public class Cglib {
    public static void main(String[] args){
        singer singer = new singer();
        singer proxy = (singer)new ProxyFactory(singer).getProxyInstance();
        proxy.sing();
    }
}

/**
 * @Description TODO 目标对象，可以不实现接口
 * @Author xtf
 * @Date 2019/8/6 17:27
 */
class singer {
    public void sing() {
        System.out.println("唱一首歌");
    }
}

class ProxyFactory implements MethodInterceptor {
    // 维护目标对象
    private Object target;

    // 传入目标对象
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("向观众问好");
        // 执行目标对象的方法
        Object returnVal = method.invoke(target, objects);
        System.out.println("向观众告别");
        return returnVal;
    }
}

