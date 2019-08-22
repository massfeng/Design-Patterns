package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 迭代器模式
 * 模拟List中的迭代器
 * @Author xtf
 * @Date 2019/8/21 16:55
 */
public class ListDemo {
    public static void main(String[] args){
        AbstractXtfList ag=new XtfList();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("数组的内容有：");
        Iterator it=ag.getIterator();
        while(it.hasNext())
        {
            Object ob=it.next();
            System.out.print(ob.toString()+"\t");
        }
        Object ob=it.first();
        System.out.println("\nFirst："+ob.toString());
    }
}

/**
 * @Description TODO 抽象数组
 * @Author xtf
 * @Date 2019/8/21 16:57
 */
interface AbstractXtfList {
    public void add(Object o);
    public void remove(Object o);
    public Iterator getIterator();
}

/**
 * @Description TODO 具体数组实现类
 * @Author xtf
 * @Date 2019/8/21 16:58
 */
class XtfList implements AbstractXtfList {

    private List<Object> list = new ArrayList<>();
    @Override
    public void add(Object o) {
        list.add(o);
    }

    @Override
    public void remove(Object o) {
        list.remove(o);
    }

    @Override
    public Iterator getIterator() {
        return (new ConcreteIterator(list));
    }
}

/**
 * @Description TODO 抽象迭代器
 * @Author xtf
 * @Date 2019/8/21 16:57
 */
interface Iterator {
    Object first();
    Object next();
    boolean hasNext();
}

/**
 * @Description TODO 具体迭代器实现类
 * @Author xtf
 * @Date 2019/8/21 17:05
 */
class ConcreteIterator implements Iterator {
    /**
     *
     **/
    private List<Object> list = null;
    private int index = -1;

    ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        index = 0;
        return list.get(index);
    }

    @Override
    public Object next() {
        if(hasNext()) {
            return list.get(++index);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if(index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }
}