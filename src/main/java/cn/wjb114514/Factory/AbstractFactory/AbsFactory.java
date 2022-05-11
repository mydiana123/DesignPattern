package cn.wjb114514.Factory.AbstractFactory;

/**
 * 抽象工厂模式的抽象层。可以生产工厂给创建对象的类使用
 * 普通工厂模式，在实体对象和创建实体的对象之间加了一层工厂层
 * 抽象工厂模式进一步在 普通工厂层和创建实体的对象之间又加了一层抽象层
 *
 *                   创建实体类的类
 *                  抽象工厂的实现子类
 *                     抽象工厂层
 *                     实体类
 */
public interface AbsFactory {
    // 此方法由工厂子类实现(这里体现了方法工厂模式)
    Pizza createPizza(String orderPizza);
}
