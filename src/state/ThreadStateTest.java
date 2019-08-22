package state;

/**
 * @Description TODO 状态模式
 * 模拟线程工作，线程有五种状态
 * 新建--(start)-->就绪--(获取CPU时间)-->运行--(stop)-->死亡
 *                   \                    \
 *                 resume()           suspend()
 *                   \                    \
 *                   <--------阻塞---------<
 *
 * 每个状态遇到相应的方法调用或者事件触发时会跳转到另一种状态
 * @Author xtf
 * @Date 2019/8/20 11:45
 */
public class ThreadStateTest {
    public static void main(String[] args){
        ThreadContext tc = new ThreadContext();
        tc.start();
        tc.getCPU();
        tc.suspend();
        tc.resume();
        tc.getCPU();
        tc.stop();
    }
}

class ThreadContext {
    private ThreadState state;
    public ThreadContext() {
        state = new New();
    }

    public void setState(ThreadState state)
    {
        this.state=state;
    }
    public ThreadState getState()
    {
        return state;
    }
    public void start()
    {
        ((New) state).start(this);
    }
    public void getCPU()
    {
        ((Runnable) state).getCPU(this);
    }
    public void suspend()
    {
        ((Running) state).suspend(this);
    }
    public void stop()
    {
        ((Running) state).stop(this);
    }
    public void resume()
    {
        ((Blocked) state).resume(this);
    }
}

/**
 * @Description TODO 线程状态的抽象类
 * @Author xtf
 * @Date 2019/8/20 11:51
 */
abstract class ThreadState {
    /**
     * 状态名
     **/
    protected String stateName;
}

/**
 * @Description TODO 具体状态类：新建状态
 * @Author xtf
 * @Date 2019/8/20 11:59
 */
class New extends ThreadState {
    public New() {
        stateName = "新建状态";
        System.out.println("当前线程处于：新建状态");
    }

    public void start(ThreadContext hj)
    {
        System.out.print("调用start()方法-->");
        if("新建状态".equals(stateName)) {
            hj.setState(new Runnable());
        } else {
            System.out.println("当前线程不是新建状态，不能调用start()方法.");
        }
    }
}

/**
 * @Description TODO 具体状态类：就绪状态
 * @Author xtf
 * @Date 2019/8/20 11:59
 */
class Runnable extends ThreadState {
    public Runnable() {
        stateName = "就绪状态";
        System.out.println("当前线程处于：运行状态");
    }

    public void getCPU(ThreadContext hj) {
        System.out.println("获取CPU时间-->");
        if("就绪状态".equals(stateName)) {
            hj.setState(new Running());
        } else {
            System.out.println("当前线程不是就绪状态，不能获取CPU.");
        }
    }
}

/**
 * @Description TODO 具体状态类：运行状态
 * @Author xtf
 * @Date 2019/8/20 11:58
 */
class Running extends ThreadState
{
    public Running()
    {
        stateName="运行状态";
        System.out.println("当前线程处于：运行状态.");
    }
    public void suspend(ThreadContext hj)
    {
        System.out.print("调用suspend()方法-->");
        if(stateName.equals("运行状态"))
        {
            hj.setState(new Blocked());
        }
        else
        {
            System.out.println("当前线程不是运行状态，不能调用suspend()方法.");
        }
    }
    public void stop(ThreadContext hj)
    {
        System.out.print("调用stop()方法-->");
        if(stateName.equals("运行状态"))
        {
            hj.setState(new Dead());
        }
        else
        {
            System.out.println("当前线程不是运行状态，不能调用stop()方法.");
        }
    }
}
/**
 * @Description TODO 具体状态类：阻塞状态
 * @Author xtf
 * @Date 2019/8/20 11:58
 */
class Blocked extends ThreadState
{
    public Blocked()
    {
        stateName="阻塞状态";
        System.out.println("当前线程处于：阻塞状态.");
    }
    public void resume(ThreadContext hj)
    {
        System.out.print("调用resume()方法-->");
        if(stateName.equals("阻塞状态"))
        {
            hj.setState(new Runnable());
        }
        else
        {
            System.out.println("当前线程不是阻塞状态，不能调用resume()方法.");
        }
    }
}
/**
 * @Description TODO 具体状态类：死亡状态
 * @Author xtf
 * @Date 2019/8/20 11:59
 */
class Dead extends ThreadState
{
    public Dead()
    {
        stateName="死亡状态";
        System.out.println("当前线程处于：死亡状态.");
    }
}