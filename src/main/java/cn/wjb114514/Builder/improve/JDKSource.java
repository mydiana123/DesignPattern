package cn.wjb114514.Builder.improve;

/**
 * StringBuilder继承于AbstractStringBuilder
 * abstract class AbstractStringBuilder implements Appendable, CharSequence
 * alt+7查看类的方法
 * Appendable：定义了多个append方法(抽象方法)，等待子类实现。
 * 同理CharSequence 也是抽象建造者。定义了charAt()，length()等常用方法
 * AbstractStringBuilder实现了上面两个接口的方法，已经是一个建造者了，但是不能实例化
 * StringBuilder:既充当了Director，又充当了建造者
 *
 * 注意事项：
 * 1.把产品本身和产品创造过程进行解耦
 * 2.各个建造者之间是抽象建造者的实现子类，也就是兄弟之间没有过多的耦合，互相完成自己的业务
 * 3.可以更加细致的控制产品创建过程，把一个极大对象分为多部进行创建
 * 4.增加新的建造者无需修改源类库代码，遵循了ocp，即增加实例，但不修改原本
 *
 * 工厂模式：
 * 建造者模式：
 * 工厂模式和设计者模式其实很相像，两者都用来创建对象，用SQLSessionFactory的例子可以很好的描述二者的区别和联系
 * 首先回顾下工厂模式：我们使用工厂模式，可以直接获得实例对象，而不用考虑实例对象是如何被创建的。
 * 也就是说，创建对象的类，通过聚合工厂对象，完成了对象的创建，而其他人无需了解对象是怎么创建的 ，也就是别人只需要调用创建对象的类，不用知道工厂是怎么创建的。
 * 工厂模式用于创建对象，只适用于小型对象。比如pizza这种，创建一个对象是轻量级的，常用的，经常产生并销毁的
 * 比如SqlSessionFactory的静态方法openSession，可以直接通过工厂创建出对象，而不经过创建对象的类。
 * 我们引入一个创建对象的类，主要是对从工厂里获取的实例对象进行一些业务逻辑的处理。
 * 于是，在Mybatis框架里，直接使用SqlSession sqlSession = sqlSessionFactory.openSession(); 就可以获取到SqlSession对象
 * 我们简单分析一段工厂实现类创建实例对象的方法
 * 可以看到，底层封装了一个提供sqlSession的方法，当然这个方法对外是隐藏的，
 * 也就是说我们在openSession的时候，甚至不知道我们其实用到了这个方法
 * 其中configuration对象是private final Configuration configuration;
 * private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
 *  2     Transaction tx = null;
 *  3     try {
 *           // 获取环境对象，这个环境对象就配置了很多全局信息
 *  4       final Environment environment = configuration.getEnvironment();
 *  5       final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
 *  6       tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
 *  7       final Executor executor = configuration.newExecutor(tx, execType);
 *  8       return new DefaultSqlSession(configuration, executor, autoCommit);
 *  9     } catch (Exception e) {
 * 10       closeTransaction(tx); // may have fetched a connection so lets call close()
 * 11       throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
 * 12     } finally {
 * 13       ErrorContext.instance().reset();
 * 14     }
 * 15   }
 *
 * 再看建造者模式
 * SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
 * 我们获取SQLSessionFactory对象其实是通过了一个建造者SqlSessionFactoryBuilder()的build方法，这简直是建造者模式的典型
 *
 * public class SqlSessionFactoryBuilder {
 *  2
 *  3   public SqlSessionFactory build(Reader reader) {
 *  4     return build(reader, null, null);
 *  5   }
 *  6
 *  7   public SqlSessionFactory build(Reader reader, String environment) {
 *  8     return build(reader, environment, null);
 *  9   }
 * 10
 * 11   public SqlSessionFactory build(Reader reader, Properties properties) {
 * 12     return build(reader, null, properties);
 * 13   }
 * 14
 * 15   public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
 * 16     try {
 *          // 解析配置文件，获取相关的属性
 * 17       XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
 * 18       return build(parser.parse());
 * 19     } catch (Exception e) {
 * 20       throw ExceptionFactory.wrapException("Error building SqlSession.", e);
 * 21     } finally {
 * 22       ErrorContext.instance().reset();
 * 23       try {
 * 24         reader.close();
 * 25       } catch (IOException e) {
 * 26         // Intentionally ignore. Prefer previous error.
 * 27       }
 * 28     }
 * 29   }
 * 30
 * 31   public SqlSessionFactory build(InputStream inputStream) {
 * 32     return build(inputStream, null, null);
 * 33   }
 * 34
 * 35   public SqlSessionFactory build(InputStream inputStream, String environment) {
 * 36     return build(inputStream, environment, null);
 * 37   }
 * 38
 * 39   public SqlSessionFactory build(InputStream inputStream, Properties properties) {
 * 40     return build(inputStream, null, properties);
 * 41   }
 * 42
 * 43   public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
 * 44     try {
 * 45       XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
 * 46       return build(parser.parse());
 * 47     } catch (Exception e) {
 * 48       throw ExceptionFactory.wrapException("Error building SqlSession.", e);
 * 49     } finally {
 * 50       ErrorContext.instance().reset();
 * 51       try {
 * 52         inputStream.close();
 * 53       } catch (IOException e) {
 * 54         // Intentionally ignore. Prefer previous error.
 * 55       }
 * 56     }
 * 57   }
 * 58
 * 59   public SqlSessionFactory build(Configuration config) {
 * 60     return new DefaultSqlSessionFactory(config);
 * 61   }
 * 62
 * 63 }
 * 其中最为关键的解析配置文件的源码如下，可以看到，此解析方法就是建造者模式
 * 相比于工厂模式创建对象，建造者模式创建的对象太过于复杂，以至于遵循“一点一点的把对象给创建起来”
 * 这里有很多老熟人，比如mappers，settings，properties属性，都是mybatis的配置文件里的xml节点。
 * 我们依据一个庞大的xml文档树构建一个对象，使用建造者模式是再好不过的了。
 * private void parseConfiguration(XNode root) {
 *     try {
 *         //issue #117 read properties first
 *         // Configuration#
 *         propertiesElement(root.evalNode("properties"));
 *         Properties settings = settingsAsProperties(root.evalNode("settings"));
 *         loadCustomVfs(settings);
 *         typeAliasesElement(root.evalNode("typeAliases"));
 *         pluginElement(root.evalNode("plugins"));
 *         objectFactoryElement(root.evalNode("objectFactory"));
 *         objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
 *         reflectorFactoryElement(root.evalNode("reflectorFactory"));
 *         settingsElement(settings);
 *         // read it after objectFactory and objectWrapperFactory issue #631
 *         environmentsElement(root.evalNode("environments"));
 *         databaseIdProviderElement(root.evalNode("databaseIdProvider"));
 *         typeHandlerElement(root.evalNode("typeHandlers"));
 *         mapperElement(root.evalNode("mappers"));
 *     } catch (Exception e) {
 *         throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
 *     }
 * }
 *
 */
public class JDKSource {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello,world");
        System.out.println(sb);
    }
}
