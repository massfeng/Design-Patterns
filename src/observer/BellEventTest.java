package observer;

import java.util.*;

/**
 * @Description TODO 观察者模式
 * 模拟打上课铃和打下课铃同学和老师的行为
 * @Author xtf
 * @Date 2019/8/21 10:56
 */
public class BellEventTest {
    public static void main(String[] args){
        // 新建事件源
        BellEventSource bes = new BellEventSource();
        // 注册老师监听器
        bes.addPersonListener(new TeacherEventListener());
        // 注册学生监听器
        bes.addPersonListener(new StudentEventListener());
        // 打上课铃
        bes.ring(true);
        // 打下课铃
        bes.ring(false);
    }
}

/**
 * @Description TODO 铃声事件类，用于封装事件源和一些事件相关的参数
 * EventObject 是JDK中封装的一个事件类
 * @Author xtf
 * @Date 2019/8/21 10:59
 */
class RingEvent extends EventObject {
    /**
     * true表示上课铃，false表示下课铃
     **/
    private boolean sound; 

    public RingEvent(Object source, boolean sound) {
        super(source);
        this.sound = sound;
    }

    public boolean getSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }
}

/**
 * @Description TODO 目标类。事件源，铃
 * @Author xtf
 * @Date 2019/8/21 11:10
 */
class BellEventSource {
    /**
     * 铃事件源内部维护的监听器容器
     **/
    private List<BellEventListener> listener;

    public BellEventSource() {
        listener = new ArrayList<>();
    }

    /**
     * @Description TODO 给事件源绑定监听器
     * @Author xtf
     * @Date 2019/8/21 11:13
     * @Param [person]
     * @return void
     */
    public void addPersonListener(BellEventListener person) {
        listener.add(person);
    }

    /**
     * @Description TODO 敲钟，当铃声发生变化时触发事件
     * @Author xtf
     * @Date 2019/8/21 11:26
     * @Param [sound]
     * @return void
     */
    public void ring(boolean sound) {
        System.out.println(sound?"上课铃":"下课铃" + "响");
        RingEvent re = new RingEvent(this, sound);
        notifies(re);
    }

    /**
     * @Description TODO 当事件发生时,通知绑定在该事件源上的所有监听器做出反应（调用事件处理方法）
     * @Author xtf
     * @Date 2019/8/21 11:31
     * @Param [e]
     * @return void
     */
    public void notifies(RingEvent e) {
        BellEventListener b = null;
        Iterator<BellEventListener> i = listener.iterator();
        while(i.hasNext()) {
            b = i.next();
            b.hellBell(e);
        }
    }
}

/**
 * @Description TODO 抽象观察者类：铃声事件监听器
 * @Author xtf
 * @Date 2019/8/21 11:07
 */
interface BellEventListener extends EventListener {
    /**
     * @Description TODO 听到铃声
     * @Author xtf
     * @Date 2019/8/21 11:26
     * @Param [e]
     * @return void
     */
    public void hellBell(RingEvent e);
}

/**
 * @Description TODO 具体观察者类，老师事件监听器
 * @Author xtf
 * @Date 2019/8/21 11:09
 */
class TeacherEventListener implements BellEventListener {
    @Override
    public void hellBell(RingEvent e) {
        if(e.getSound()) {
            System.out.println("老师上课了...");
        } else {
            System.out.println("老师下课了...");
        }
    }
}

/**
 * @Description TODO 具体观察者类，学生事件监听器
 * @Author xtf
 * @Date 2019/8/21 11:09
 */
class StudentEventListener implements BellEventListener {

    @Override
    public void hellBell(RingEvent e) {
        if(e.getSound()) {
            System.out.println("同学上课了...");
        } else {
            System.out.println("同学下课了...");
        }
    }
}