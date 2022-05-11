package cn.wjb114514.Factory.AbstractFactory;

public class LDFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderPizza) {
        Pizza pizza = null;
        if ("cheese".equals(orderPizza)) {
            pizza = new CheesePizza();
            pizza.setName("London奶酪口味披萨");
        }else if("Greek".equalsIgnoreCase(orderPizza)) {
            pizza = new GreekPizza();
            pizza.setName("London希腊口味披萨！");
        }

        return pizza;
    }
}
