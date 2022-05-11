package cn.wjb114514.Adapter;

public class Client {
    public static void main(String[] args) {
        System.out.println("====类适配器模式====");
        Phone phone = new Phone();
        // 被适配者对用户是隔离的。用户需要依赖适配器(我们以耳机线为例，耳机线是圆口，而插头是Type-C口)
        // 于是我们依赖适配器(耳机转换线)，得到了目标输出(圆口)。但是对于Type-C口，实际上我们是不关心的。
        // 缺点：1.适配器需要继承 被适配者，耦合性增加。 2.适配器需要用到被适配者的方法，维护成本增加
        // 优点：由于继承了被适配者，可以按需重写被适配者的方法，提高灵活性
        phone.charging(new VoltageAdapter());
    }
}
