package prototype;

/**
 * @Description TODO 原型模式，使用奖状来模拟
 * @Author xtf
 * @Date 2019/8/5 16:39
 */
public class ProtoTypeCitation {
    public static void main(String[] args) throws CloneNotSupportedException{
        // 原型创建
        Citation citation1 = new Citation("张三", "同学：在学校表现良好，被评为三好学生！", "江西农业大学");
        System.out.println(citation1);
        // 克隆出李四
        Citation citation2 = (Citation)citation1.clone();
        citation2.setName("李四");
        System.out.println(citation2);
        // 克隆出王五
        Citation citation3 = (Citation)citation1.clone();
        citation3.setName("王五");
        System.out.println(citation3);
    }
}

/**
 * @Description TODO 奖状类，奖状除了名字以外，其他都一样，所以适合使用原型模式
 * @Author xtf
 * @Date 2019/8/5 16:40
 */
class Citation implements Cloneable {
    private String name;
    private String info;
    private String college;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("奖状克隆成功");
        return super.clone();
    }

    @Override
    public String toString() {
        return this.name + this.info + this.college;
    }
}
