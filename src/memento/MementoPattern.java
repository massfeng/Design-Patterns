package memento;

/**
 * @Description TODO 备忘录模式
 * @Author xtf
 * @Date 2019/8/22 12:17
 */
public class MementoPattern {
    public static void main(String[] args){
        // 发起者实例
        Originator or = new Originator();
        // 管理者实例
        Caretaker ca = new Caretaker();

        // 当前状态
        or.setState("State1");
        System.out.println("当前状态为：" + or.getState());

        // 管理者保存当前状态
        ca.setMemento(or.createMemento());
        System.out.println("管理者保存了当前状态：" + ca.getMemento().getState());

        // 改变当前状态
        or.setState("State2");
        System.out.println("当前状态为：" + or.getState());

        // 恢复之前管理者保存的状态
        or.restoreMemento(ca.getMemento());
        System.out.println("当前状态为：" + or.getState());

    }
}

/**
 * @Description TODO 备忘录，具体类，其中使用一个String存储数据
 * 负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
 * @Author xtf
 * @Date 2019/8/22 12:19
 */
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * @Description TODO 发起人
 * 记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能
 * 实现其他业务功能，它可以访问备忘录里的所有信息。
 * @Author xtf
 * @Date 2019/8/22 12:20
 */
class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 创建新备忘录
     **/
    public Memento createMemento() {
        return new Memento(state);
    }

    /**
     * 恢复备忘录
     **/
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}

/**
 * @Description TODO 管理者
 * 对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
 * @Author xtf
 * @Date 2019/8/22 12:26
 */
class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
