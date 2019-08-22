package visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description TODO 访问者模式
 * 艺术公司利用“铜”可以设计出铜像，利用“纸”可以画出图画；
 * 造币公司利用“铜”可以印出铜币，利用“纸”可以印出纸币
 * 对“铜”和“纸”这两种元素，两个公司的处理方法不同，所以该实例用访问者模式来实现比较适合。
 * @Author xtf
 * @Date 2019/8/21 17:39
 */
public class CompanyDemo {
    public static void main(String[] args){
        // 创造材料集，添加材料
        SetMaterial sm = new SetMaterial();
        sm.add(new Paper());
        sm.add(new Cuprum());

        // 让公司去访问材料集
        Company c1 = new ArtCompany();
        sm.accept(c1);

        Company c2 = new Mint();
        sm.accept(c2);
    }
}

/**
 * @Description TODO 抽象访问者：公司
 * @Author xtf
 * @Date 2019/8/21 17:41
 */
interface Company {
    public String create(Paper page);
    public String create(Cuprum cuprum);
}

/**
 * @Description TODO 具体访问者 艺术公司
 * @Author xtf
 * @Date 2019/8/21 17:47
 */
class ArtCompany implements Company {

    @Override
    public String create(Paper page) {
        return "清明上河图";
    }

    @Override
    public String create(Cuprum cuprum) {
        return "朱熹铜像";
    }
}

/**
 * @Description TODO 具体访问者 造币公司
 * @Author xtf
 * @Date 2019/8/21 17:48
 */
class Mint implements Company {

    @Override
    public String create(Paper page) {
        return "纸币";
    }

    @Override
    public String create(Cuprum cuprum) {
        return "铜币";
    }
}

/**
 * @Description TODO 抽象元素 材料
 * @Author xtf
 * @Date 2019/8/21 17:42
 */
interface Material {
    public String accept(Company visitor);
}

/**
 * @Description TODO 具体元素 纸类
 * @Author xtf
 * @Date 2019/8/21 17:44
 */
class Paper implements Material {
    @Override
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}

/**
 * @Description TODO 具体元素 铜类
 * @Author xtf
 * @Date 2019/8/21 17:45
 */
class Cuprum implements Material {
    @Override
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}

/**
 * @Description TODO 材料集
 * @Author xtf
 * @Date 2019/8/21 17:50
 */
class SetMaterial {
    /**
     * 内部维护了一个List来保存材料
     **/
    private List<Material> list = new ArrayList<>();

    /**
     * 传入一个公司，让公司去访问这些材料
     **/
    public void accept(Company company) {
        Iterator<Material> i = list.iterator();
        while(i.hasNext()) {
            System.out.println(i.next().accept(company));
        }
    }

    public void add(Material material) {
        list.add(material);
    }

    public void remove(Material material) {
        list.remove(material);
    }
}
