package cn.wjb114514.Factory.FactoryMethod;

/**
 * 根据需求，工厂的创建实例的方法由子类实现！
 * 可以做到只增加实例，不修改原有代码的效果
 */
public abstract class SimpleFactory {
    public abstract void createPizza(String orderType);


}
