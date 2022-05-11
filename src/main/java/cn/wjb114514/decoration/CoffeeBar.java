package cn.wjb114514.decoration;

// 调用装饰者模式的客户端
public class CoffeeBar {

    public static void main(String[] args) {
        // 1.点一份LongBlack
        Drink order = new LongBlack();
        System.out.println(order.getDes());
        System.out.println(order.cost());


        // 2.加入一份牛奶 装饰者 包含被装饰者
        // milk -> decorator -> Drink
        Drink milkLongBlack = new Milk(order);
        System.out.println(milkLongBlack.getDes());
        System.out.println(milkLongBlack.cost());

        // 3.再加入一份巧克力
        // 巧克力装饰者内部包装了加了Milk的LongBlack
        Drink chocolateMilkLongBlack = new Chocolate(milkLongBlack);
        System.out.println(chocolateMilkLongBlack.getDes());
        System.out.println(chocolateMilkLongBlack.cost());

        // 4.再加入一份巧克力
        Drink chocolateChocolateMilkLongBlack = new Chocolate(chocolateMilkLongBlack);
        System.out.println(chocolateChocolateMilkLongBlack.getDes());
        System.out.println(chocolateChocolateMilkLongBlack.cost());
    }
}
