package chainofresponsibility;

/**
 * @Description TODO 责任链模式
 * 学生请假
 * 如果小于2天，需要班主任（ClassAdviser）批准
 * 小于7天，需要系主任（DepartmentHead）批准
 * 小于10天，需要院长（Dean）批准
 * 其他情况不允许批准
 * @Author xtf
 * @Date 2019/8/20 10:48
 */
public class StudentLeave {
    public static void main(String[] args){
        //组装责任链
        Leader leader1 = new ClassAdviser();
        Leader leader2 = new DepartmentHead();
        Leader leader3 = new Dean();
        leader1.setNext(leader2);
        leader2.setNext(leader3);

        leader1.handleRequest(9);
    }
}

/**
 * @Description TODO 领导抽象类（Handle）
 * @Author xtf
 * @Date 2019/8/20 10:52
 */
abstract class Leader {
    private Leader next;

    public void setNext(Leader next) {
        this.next = next;
    }

    public Leader getNext() {
        return next;
    }

    public abstract void handleRequest(int leaveDays);
}

/**
 * @Description TODO 班主任类，可以处理请假天数少于两天的请求
 * @Author xtf
 * @Date 2019/8/20 10:59
 */
class ClassAdviser extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        // 如果请假天数小于两天，则班主任批准请假
        if(leaveDays <= 2) {
            System.out.println("班主任批准请假！");
        } else {    //如果请假天数大于两天，让下一个责任链处理
            // 如果有下一个责任链有人处理，则让下一个责任链处理
            if(getNext() != null) {
                getNext().handleRequest(leaveDays);
            } else {    //如果下一个责任链为空，则输出无人处理
                System.out.println("请假天数太多，无人批准该假条");
            }
        }
    }
}

/**
 * @Description TODO 系主任类，允许批准七天以下的假条
 * @Author xtf
 * @Date 2019/8/20 11:02
 */
class DepartmentHead extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        // 如果请假天数小于七天，则班主任批准请假
        if(leaveDays <= 7) {
            System.out.println("系主任批准请假！");
        } else {    //如果请假天数大于七天，让下一个责任链处理
            // 如果有下一个责任链有人处理，则让下一个责任链处理
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            } else {    //如果下一个责任链为空，则输出无人处理
                System.out.println("请假天数太多，无人批准该假条");
            }
        }
    }
}

/**
 * @Description TODO 院长类，允许处理10天以下的假条
 * @Author xtf
 * @Date 2019/8/20 11:03
 */
class Dean extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        // 如果请假天数小于十天，则班主任批准请假
        if(leaveDays <= 10) {
            System.out.println("院长批准请假！");
        } else {    //如果请假天数大于十天，让下一个责任链处理
            // 如果有下一个责任链有人处理，则让下一个责任链处理
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            } else {    //如果下一个责任链为空，则输出无人处理
                System.out.println("请假天数太多，无人批准该假条");
            }
        }
    }
}
