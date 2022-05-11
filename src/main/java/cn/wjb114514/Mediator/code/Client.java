package cn.wjb114514.Mediator.code;

public class Client {
    public static void main(String[] args) {
        // 创建中介者
        Mediator concreteMediator = new ConcreteMediator();

        // new的时候，同事类就把自己扔到了 中介者的集合，并注册了一个中介者，换句话说， 每new一个同事类，就注册了 同事类-中介者的关联
        Alarm alarm = new Alarm(concreteMediator, "Alarm");
        CoffeeMachine coffeeMachine = new CoffeeMachine(concreteMediator, "CoffeeMachine");

        /*
        闹钟响了 嗡嗡嗡嗡~~~
        咖啡机打开拉~~~
        可以看到，用户不知道 闹钟打开后的处理细节，而闹钟只负责 闹钟响的职责，咖啡机只负责 打开咖啡机的职责
        也就是 按照原来的设计，要想完成 闹钟响了就打开咖啡机的功能，闹钟对象必须要内聚一个咖啡机，然后调用其方法
        这样子系统之间耦合度大，如果闹钟一响要求打开咖啡机 电视机 电报机 电话机 电热水器 ...
        难道 闹钟还要聚合这么多对象吗？
        现在 只需要把这些对象，包括闹钟对象交给中介者管理，闹钟只需 按照中介者办事
        即只需要把自己的事件跟中介者说一声==> 闹钟跟中介者说"我响了"，然后中介者就把业务逻辑处理好了，之后把结果返回了。就这么简单，
        这么做虽然完成了系统的灵活性，但导致中介者类及其臃肿。
         */
        alarm.sendMessage(0);

    }
}
