package cn.wjb114514.Factory;

/**
 * 相当于一个客户端，发出订购
 */
public class PizzaStore {
    public static void main(String[] args) {
//        OrderPizza orderPizza = new OrderPizza();
        SimpleFactory simpleFactory = new SimpleFactory();
        OrderPizzaPlus orderPizzaPlus = new OrderPizzaPlus(simpleFactory);
    }
}
