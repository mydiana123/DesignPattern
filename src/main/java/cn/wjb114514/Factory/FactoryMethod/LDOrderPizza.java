package cn.wjb114514.Factory.FactoryMethod;

import cn.wjb114514.Factory.Pizza;

public class LDOrderPizza extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("i am London pizza");
        Pizza pizza = new CheesePizza();
        pizza.setName("London" + orderType + "pizza");
        return pizza;
    }
}
