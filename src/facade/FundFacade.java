package facade;

/**
 * @Description 外观模式
 * 基金类，基金经理人通过该类作为中间交互者，可以接受投资者的资金，统一对股票、国债、房地产进行购买和赎回操作。
 * 有些人可能炒过股票，但其实大部分人都不太懂，这种没有足够了解证券知识的情况下做股票是很容易亏钱的，
 * 刚开始炒股肯定都会想，如果有个懂行的帮帮手就好，
 * 其实基金就是个好帮手，支付宝里就有许多的基金，
 * 它将投资者分散的资金集中起来，交由专业的经理人进行管理，投资于股票、债券、外汇等领域，
 * 而基金投资的收益归持有者所有，管理机构收取一定比例的托管管理费用。
 *
 * @Author xtf
 * @Date 2019/8/18 11:22
 */
public class FundFacade {
    public static void main(String[] args){
        Fund fund = new Fund();
        fund.buy();
        fund.sell();
    }
}

class Fund {
    private Stock1 stock1;
    private Stock2 stock2;
    private Stock3 stock3;

    public Fund() {
        stock1 = new Stock1();
        stock2 = new Stock2();
        stock3 = new Stock3();
    }

    public void buy() {
        stock1.buy();
        stock2.buy();
        stock3.buy();
    }

    public void sell() {
        stock1.sell();
        stock2.sell();
        stock3.sell();
    }
}

/**
 * @Description TODO 股票1
 * @Author xtf
 * @Date 2019/8/18 11:24
 */
class Stock1 {
    public void buy() {
        System.out.println("股票1买入");
    }

    public void sell() {
        System.out.println("股票1赎回");
    }
}

/**
 * @Description TODO 股票2
 * @Author xtf
 * @Date 2019/8/18 11:24
 */
class Stock2 {
    public void buy() {
        System.out.println("股票2买入");
    }

    public void sell() {
        System.out.println("股票2赎回");
    }
}

/**
 * @Description TODO 股票3
 * @Author xtf
 * @Date 2019/8/18 11:24
 */
class Stock3 {
    public void buy() {
        System.out.println("股票3买入");
    }

    public void sell() {
        System.out.println("股票3赎回");
    }
}