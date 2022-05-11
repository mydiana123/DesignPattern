package cn.wjb114514.decoration;

// 此装饰者本质也是一个饮品，相当于加了调料的饮品，因此其需要继承Drink
public class Decorator extends Drink{

    // 此装饰者内部聚合了另一个饮品 就好像加了Milk的LongBlack实际上内部是聚合了LongBlack的。
    // 这就是套娃和递归，内部聚合自己。层层包装。
    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        // 自己的价格加上 内部对象的价格。
        // 我们用装饰者包装了被装饰者，这样每次加一份小料，就包装一层，super.getPrice()是自己的价格，也就是小料的价格。obj.cost()就是之前的饮品价格。
        return super.getPrice() + obj.cost();
    }
    @Override
    public String getDes() {
        // obj.getDes():被装饰者的描述信息
        return super.des + " " + super.getPrice() + "&&" + obj.getDes();
    }
}
