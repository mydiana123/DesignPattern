package cn.wjb114514.Factory.AbstractFactory;

// 具体的工厂子类
public class BJFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderPizza) {
        Pizza pizza = null;
        if ("cheese".equals(orderPizza)) {
            pizza = new CheesePizza();
            pizza.setName("北京奶酪口味披萨");
        }else if("Greek".equalsIgnoreCase(orderPizza)) {
            pizza = new GreekPizza();
            pizza.setName("北京希腊口味披萨！");
        }

        return pizza;
    }
}
