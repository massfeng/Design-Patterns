package mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 中介者模式
 * 我们现在有三种数据库，分别是Mysql，Redis，Elasticsearch
 * 其中Mysql作为主数据库，当MySQL添加一条数据时，需要其他两个数据库同步添加数据
 * Redis作为缓存数据库，当Redis添加数据时，不需要其他数据库添加数据
 * Elasticsearch作为大数据数据库，当Elasticsearch添加数据时，只需要往MySQL中添加数据
 * @Author xtf
 * @Date 2019/8/21 15:20
 */
public class DatabaseMediator {
    public static void main(String[] args){
        // 中介者
        AbstractMediator am = new syncMediator();

        // 三个数据库实例
        AbstractDatabase mysql = new MysqlDatabase(am);
        AbstractDatabase redis = new RedisDatabase(am);
        AbstractDatabase elasticsearch = new ElasticsearchDatabase(am);

        // 将三个数据库实例设置到中介者中
        am.setMysqlDatabase((MysqlDatabase)mysql);
        am.setRedisDatabase((RedisDatabase)redis);
        am.setElasticsearchDatabase((ElasticsearchDatabase)elasticsearch);

        System.out.println("-----------mysql添加数据----------");
        mysql.add("1");
        ((MysqlDatabase) mysql).select();
        ((RedisDatabase) redis).select();
        ((ElasticsearchDatabase) elasticsearch).select();

        System.out.println("-----------redis添加数据----------");
        redis.add("2");
        ((MysqlDatabase) mysql).select();
        ((RedisDatabase) redis).select();
        ((ElasticsearchDatabase) elasticsearch).select();

        System.out.println("-----------Elasticsearch添加数据----------");
        elasticsearch.add("3");
        ((MysqlDatabase) mysql).select();
        ((RedisDatabase) redis).select();
        ((ElasticsearchDatabase) elasticsearch).select();
    }
}

/**
 * @Description TODO 抽象同事类，其中维护了一个中介者
 * @Author xtf
 * @Date 2019/8/21 15:24
 */
abstract class AbstractDatabase {
    public static final String MYSQL = "mysql";
    public static final String REDIS = "redis";
    public static final String ELASTICSEARCH = "elasticsearch";

    /**
     * 维护的中介者
     **/
    protected AbstractMediator mediator;

    public AbstractDatabase(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void addData(String data);

    public abstract void add(String data);
}

/**
 * @Description TODO 具体同事类，MySQL数据库
 * @Author xtf
 * @Date 2019/8/21 15:31
 */
class MysqlDatabase extends AbstractDatabase {
    private List<String> dataSet = null;

    public MysqlDatabase(AbstractMediator mediator) {
        super(mediator);
        dataSet = new ArrayList<>();
    }


    @Override
    public void addData(String data) {
        System.out.println("Mysql添加数据：" + data);
        dataSet.add(data);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.MYSQL, data);
    }

    public void select() {
        System.out.println("mysql中的数据：  "  + dataSet.toString());
    }
}

/**
 * @Description TODO 具体同事类，Redis数据库
 * @Author xtf
 * @Date 2019/8/21 15:31
 */
class RedisDatabase extends AbstractDatabase {
    private List<String> dataSet = null;

    public RedisDatabase(AbstractMediator mediator) {
        super(mediator);
        dataSet = new ArrayList<>();
    }


    @Override
    public void addData(String data) {
        System.out.println("Redis添加数据：" + data);
        dataSet.add(data);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.REDIS, data);
    }

    public void select() {
        System.out.println("redis 中的数据 ： " + dataSet.toString());
    }
}

/**
 * @Description TODO 具体同事类，Elasticsearch数据库
 * @Author xtf
 * @Date 2019/8/21 15:31
 */
class ElasticsearchDatabase extends AbstractDatabase {
    private List<String> dataSet = null;

    public ElasticsearchDatabase(AbstractMediator mediator) {
        super(mediator);
        dataSet = new ArrayList<>();
    }


    @Override
    public void addData(String data) {
        System.out.println("ElasticsearchDatabase添加数据：" + data);
        dataSet.add(data);
    }

    @Override
    public void add(String data) {
        addData(data);
        this.mediator.sync(AbstractDatabase.ELASTICSEARCH, data);
    }

    public void select() {
        System.out.println("Elasticsearch中的数据 ： " + dataSet.toString());
    }
}

/**
 * @Description TODO 抽象中介者
 * @Author xtf
 * @Date 2019/8/21 15:33
 */
abstract class AbstractMediator {
    protected MysqlDatabase mysqlDatabase;
    protected RedisDatabase redisDatabase;
    protected ElasticsearchDatabase elasticsearchDatabase;

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public void setRedisDatabase(RedisDatabase redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public void setElasticsearchDatabase(ElasticsearchDatabase elasticsearchDatabase) {
        this.elasticsearchDatabase = elasticsearchDatabase;
    }

    /**
     * @Description TODO 中介者的同步添加方法
     * @Author xtf
     * @Date 2019/8/21 15:34
     * @Param [databaseName, data]
     * @return void
     */
    public abstract void sync(String databaseName, String data);
}

class syncMediator extends AbstractMediator {

    @Override
    public void sync(String databaseName, String data) {
        // 如果是MySQL数据库，往Redis数据库和Elasticsearch数据库中添加数据
        if(AbstractDatabase.MYSQL.equals(databaseName)) {
            this.redisDatabase.addData(data);
            this.elasticsearchDatabase.addData(data);
        } else if(AbstractDatabase.REDIS.equals(databaseName)) {
            // 如果是Redis数据库则什么也不做
        } else if(AbstractDatabase.ELASTICSEARCH.equals(databaseName)) {
            // 如果是Elasticsearch数据库，往mysql数据库中添加数据
            this.mysqlDatabase.addData(data);
        }
    }


}
