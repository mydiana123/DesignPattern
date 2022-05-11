package cn.wjb114514.Builder;

/**
 * 建造者模式
 *
 * 需求
 * 1.需要建房子：流程为 打桩 砌墙 封顶
 * 2.房子的种类不一样，建造过程基本一致
 */
public class Start {
}

/*
Mybatis源码里的建造者模式：主要用于分层，解耦的创建较大对象

注：其他和建造者模式无关的代码已经省去

BaseBuilder:建造者的基类，内部聚合了产品对象Configuration。

建造者模式创建的产品对象都是大对象，这里的Configuration显然是一个大对象

XMLConfigBuilder：建造者的实现子类，用于完成具体对象的创建

可以看到其子类的parse方法相当于可以返回创建好的产品，其实等价于重写了父类的getConfiguration()方法。

之后那个parseConfiguration()方法，就是建造者模式的典型案例了

parseConfiguration()方法内部调用的所有方法，都在XMLConfigBuilder类里实现了，虽然没有按照父类的定义，但是思想大于实现，因为BaseBuilder在庞大的设计体系里，也不能说每一个Builder的实现子类都必须要按照BaseBuilder定义的方法来做。在这个方法里，就好比我们建房子，打地基，砌墙，盖屋顶，这些一系列有条理的方法，他也使用递归方式读取文档树，通过各种方法解析文档树节点，有条有理的完成了Configuration对象的创建。其实这个XMLConfigBuilder既充当了建造者实现类的功能，又充当了监督者的功能，因为他把建造方法也给实现了，同时还返回了产品...

最后，在SqlSessionFactoryBuilder类里，调用了parse方法，SqlSessionFactoryBuilder就是我们的客户了， 他只负责获取产品，也就是configuration对象，而不去关注组装configuration对象的细节。

public class SqlSessionFactoryBuilder {

  public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
    try {
      XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
      return build(parser.parse());
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        reader.close();
      } catch (IOException e) {
        // Intentionally ignore. Prefer previous error.
      }
    }
  }
public abstract class BaseBuilder {
  protected final Configuration configuration;

  public BaseBuilder(Configuration configuration) {
    this.configuration = configuration;
  }

  public Configuration getConfiguration() {
    return configuration;
  }
public class XMLConfigBuilder extends BaseBuilder {

  private boolean parsed;
  private final XPathParser parser;
  private String environment;
  private final ReflectorFactory localReflectorFactory = new DefaultReflectorFactory();


  private XMLConfigBuilder(XPathParser parser, String environment, Properties props) {
    super(new Configuration());
    ErrorContext.instance().resource("SQL Mapper Configuration");
    this.configuration.setVariables(props);
    this.parsed = false;
    this.environment = environment;
    this.parser = parser;
  }

  public Configuration parse() {
    if (parsed) {
      throw new BuilderException("Each XMLConfigBuilder can only be used once.");
    }
    parsed = true;
    parseConfiguration(parser.evalNode("/configuration"));
    return configuration;
  }

  private void parseConfiguration(XNode root) {
    try {
      // issue #117 read properties first
      propertiesElement(root.evalNode("properties"));
      Properties settings = settingsAsProperties(root.evalNode("settings"));
      loadCustomVfs(settings);
      loadCustomLogImpl(settings);
      typeAliasesElement(root.evalNode("typeAliases"));
      pluginElement(root.evalNode("plugins"));
      objectFactoryElement(root.evalNode("objectFactory"));
      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
      reflectorFactoryElement(root.evalNode("reflectorFactory"));
      settingsElement(settings);
      // read it after objectFactory and objectWrapperFactory issue #631
      environmentsElement(root.evalNode("environments"));
      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
      typeHandlerElement(root.evalNode("typeHandlers"));
      mapperElement(root.evalNode("mappers"));
    } catch (Exception e) {
      throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
    }
  }
 */