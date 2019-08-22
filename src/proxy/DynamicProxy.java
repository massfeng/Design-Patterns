package proxy;

import java.lang.reflect.Proxy;

/**
 * @Description TODO 动态代理，ISinger和Singer请看同包下的StaticProxy.java
 * @Author xtf
 * @Date 2019/8/6 17:04
 */
public class DynamicProxy {
    public static void main(String[] args){
        Singer target = new Singer();
        ISinger proxy = (ISinger)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (o, method, objects) -> {
                    System.out.println("向观众问好");
                    Object returnVal = method.invoke(target, objects);
                    System.out.println("向观众告别");
                    return returnVal;
                }
        );
        proxy.sing();
    }
}
