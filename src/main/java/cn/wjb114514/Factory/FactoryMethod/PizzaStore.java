package cn.wjb114514.Factory.FactoryMethod;

import java.util.Scanner;

public class PizzaStore {
    public static void main(String[] args) {
        System.out.println("你想要哪里的pizza?");
        String loc = new Scanner(System.in).nextLine();
        if ("bj".equals(loc)) {
            new BJOrderPizza();
        }else {
            new LDOrderPizza();
        }
    }
}
