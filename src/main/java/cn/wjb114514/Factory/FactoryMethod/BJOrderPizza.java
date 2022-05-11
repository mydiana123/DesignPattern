package cn.wjb114514.Factory.FactoryMethod;

import cn.wjb114514.Factory.Pizza;

public class BJOrderPizza extends OrderPizza{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("我是北京的披萨");
        Pizza pizza = new CheesePizza();
        pizza.setName("北京的" + orderType + "pizza");
        return pizza;
    }
}

class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("奶酪披萨需要奶酪");
    }
}

class GreekPizza extends  Pizza {

    @Override
    public void prepare() {
        System.out.println("希腊披萨需要希腊！");
    }
}

// 增加一个新类型的披萨
class NewTypePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("新口味的奥利给披萨");
    }
}