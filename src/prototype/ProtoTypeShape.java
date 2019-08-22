package prototype;

import com.sun.corba.se.impl.encoding.CDROutputStream_1_0;

import java.util.HashMap;

/**
 * @Description TODO 带原型管理器的原型模式
 * @Author xtf
 * @Date 2019/8/5 17:06
 */
public class ProtoTypeShape {
    public static void main(String[] args){
        ProtoTypeManager prm = new ProtoTypeManager();
        Circle c1 = (Circle)prm.getShape("Circle");
        c1.setR(3);
        c1.countArea();
        Square s1 = (Square)prm.getShape("Square");
        s1.setL(4);
        s1.countArea();
    }
}

/**
 * @Description TODO 图形接口，有两个实现类，圆和正方形
 * @Author xtf
 * @Date 2019/8/5 17:07
 */
interface Shape extends Cloneable {
    public Object clone();
    public void countArea();
}

/**
 * @Description TODO 圆类，实现了Shape接口
 * @Author xtf
 * @Date 2019/8/5 17:12
 */
class Circle implements Shape {
    /**
     * 半径
     **/
    private float r;

    Circle(float r) {
        this.r = r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getR() {
        return r;
    }
    @Override
    public Object clone() {
        Circle c = null;
        try {
            c = (Circle)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝圆形失败");
        }
        return c;
    }

    @Override
    public void countArea() {
        System.out.println("圆的面积是：" + 3.14 * r * r);
    }
}

/**
 * @Description TODO 正方形
 * @Author xtf
 * @Date 2019/8/5 17:23
 */
class Square implements Shape {
    /**
     * 正方形的边长
     **/
    private float l;
    Square(float l) {
        this.l = l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getL() {
        return l;
    }

    @Override
    public Object clone() {
        Square s = null;
        try {
            s = (Square)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝正方形失败");
        }
        return s;
    }

    @Override
    public void countArea() {
        System.out.println("正方形的面积是：" + l * l);
    }
}

/**
 * @Description TODO 原型模式管理器
 * @Author xtf
 * @Date 2019/8/5 17:26
 */
class ProtoTypeManager {
    private HashMap<String, Shape> map = new HashMap<String, Shape>();

    /**
     * 在构建出原型模式管理器时，往里面加入圆形和正方形的原型
     **/
    ProtoTypeManager() {
        map.put("Circle", new Circle(2));
        map.put("Square", new Square(3));
    }

    public void addShape(String key, Shape shape) {
        map.put(key, shape);
    }

    /**
     * 在取出时，取出原型模式管理器中的克隆
     **/
    public Shape getShape(String key){
        Shape shape = map.get(key);
        return (Shape)shape.clone();
    }
}