package cn.wjb114514.Factory;

/**
 * 把创建pizza 的细节放入简单工厂
 * 所有创建pizza 的相关类，都依赖此简单工厂
 * 可以认为就是在 Pizza的实体和Pizza的创建类中间加了一个缓冲层
 * 这样Pizza和OrderPizza 的关系由多对多 变成了多对一对多。减少了代码的修改量
 */
public class SimpleFactory {
    // 根据orderType返回对应的Pizza对象
    public Pizza createPizza(String orderType) {
        System.out.println("使用简单工厂模式");
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName(orderType + "pizza");
        }else if("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName(orderType + "pizza");
        }
        // 违反开闭原则的代码！使用简单工厂，可以把违反ocp的代码缩小在一个类里
        else if ("newType".equals(orderType)) {
            pizza = new NewTypePizza();
            pizza.setName(orderType + "pizza");
        }
        return pizza;
    }

    public static Pizza createPizza2(String orderType) {
        System.out.println("使用简单工厂模式");
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName(orderType + "pizza");
        }else if("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName(orderType + "pizza");
        }
        // 违反开闭原则的代码！使用简单工厂，可以把违反ocp的代码缩小在一个类里
        else if ("newType".equals(orderType)) {
            pizza = new NewTypePizza();
            pizza.setName(orderType + "pizza");
        }
        return pizza;
    }
}
