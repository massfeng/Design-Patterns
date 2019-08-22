package command;

import java.util.List;

/**
 * @Description TODO 命令模式
 * 将“请求”封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。
 * 命令模式也支持可撤销的操作。命令模式通过这种封装的方式实现将客户端和接收端解耦。
 * 本例子是呼叫小爱同学帮你开灯的例子，你对小爱同学说：小爱同学帮我打开灯，然后小爱同学让灯自己打开了。
 * @Author xtf
 * @Date 2019/8/19 16:47
 */
public class XiaoAiStudent {
    public static void main(String[] args){
        // 创建小爱同学
        XiaoAi xa = new XiaoAi();
        //创建具体的接收命令者，即电灯
        Light light = new Light();
        //命令小爱同学开灯
        System.out.println("小爱同学开灯！");
        Command command1 = new LightOnCommand(light);
        xa.setCommand(command1);
        xa.doCommand();
        //命令小爱同学关灯
        System.out.println("小爱同学关灯！");
        Command command2 = new LightOffCommand(light);
        xa.setCommand(command2);
        xa.doCommand();
    }
}

/**
 * @Description TODO 命令接口
 * @Author xtf
 * @Date 2019/8/19 16:49
 */
interface Command {
    public void execute();
}

/**
 * @Description TODO 具体的命令类，传入一个灯对象，自己调用自己的开灯操作
 * @Author xtf
 * @Date 2019/8/19 16:52
 */
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOn();
    }
}

/**
 * @Description TODO 具体的命令类，传入一个灯对象，自己调用自己的关灯操作
 * @Author xtf
 * @Date 2019/8/19 16:53
 */
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOff();
    }
}
/**
 * @Description TODO 具体的电灯类，命令接受者Receiver
 * @Author xtf
 * @Date 2019/8/19 16:51
 */
class Light {
    public void lightOn() {
        System.out.println("灯灯开了");
    }

    public void lightOff() {
        System.out.println("灯灯关了");
    }
}

/**
 * @Description TODO 传递命令的对象invoker，即小爱同学
 * 传递一个命令给小爱同学，小爱同学对其作出命令
 * @Author xtf
 * @Date 2019/8/19 16:54
 */
class XiaoAi {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void doCommand() {
        command.execute();
    }
}

