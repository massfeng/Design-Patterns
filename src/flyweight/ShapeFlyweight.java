package flyweight;

import java.util.HashMap;

/**
 * @Description TODO
 * 享元模式
 * 享元模式最出名的应用就是池应用，例如Java的String常量池
 * 我们将创建一个 Shape 接口和实现了 Shape 接口的实体类 Circle。下一步是定义工厂类 ShapeFactory。
 * ShapeFactory 有一个 Circle 的 HashMap，其中键名为 Circle 对象的颜色。
 * 无论何时接收到请求，都会创建一个特定颜色的圆。
 * ShapeFactory 检查它的 HashMap 中的 circle 对象，
 * 如果找到 Circle 对象，则返回该对象，
 * 否则将创建一个存储在 hashmap 中以备后续使用的新对象，并把该对象返回到客户端。
 * @Author xtf
 * @Date 2019/8/18 17:32
 */
public class ShapeFlyweight {
    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args){
        for(int i=0; i < 20; ++i) {
            Circle circle =
                    (Circle)ShapeFactory.getShape(getRandomColor());

            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}

/**
 * @Description TODO 定义图形的接口
 * @Author xtf
 * @Date 2019/8/18 17:41
 */
interface Shape {
    public void draw();
}

/**
 * @Description TODO 圆类，图形的实体类
 * @Author xtf
 * @Date 2019/8/18 17:42
 */
class Circle implements Shape {
    private int x;
    private int y;
    private int radius;
    private String color;

    @Override
    public void draw() {
        System.out.println("Circle{" +
                "x=" + x +
                ", y=" + y +
                ", redius=" + radius +
                ", color='" + color + '\'' +
                '}');
    }

    Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}

/**
 * @Description TODO 工厂类，生产基于颜色的对象
 * @Author xtf
 * @Date 2019/8/18 17:43
 */
class ShapeFactory {
    // 工厂维护了一个hashmap，能够通过颜色获取对象
    public static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

    /**
     * @Description TODO 通过颜色从hashmap中拿对象，如果该颜色已经存在（或者说已经被拿过），则返回hashmap中的对象
     * 如果该颜色不存在，则创建并返回一个该颜色的圆，并把这个圆放到hashmap中去
     * @Author xtf
     * @Date 2019/8/18 17:48
     * @Param [color]
     * @return flyweight.Shape
     */
    public static Shape getShape(String color) {
        Circle circle = (Circle)circleMap.get(color);
        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("创建了一个新圆：" + color);
        }
        return circle;
    }
}
