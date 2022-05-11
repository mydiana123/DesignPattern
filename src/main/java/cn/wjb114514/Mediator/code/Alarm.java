package cn.wjb114514.Mediator.code;

// 具体的同事类
public class Alarm extends Colleague{

    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name,this); // 你中有我我中有你，创建一个具体的同事类时，就把自己注册到了中介者的管理集合，并把中介者传入自己的内部
    }

    public void sendAlarm(int stateChange) {
        sendMessage(stateChange);
    }

    // 此方法 是 中介者和子系统交流的核心方法。
    // 实现逻辑就是，获取当前客户类关联的 中介者，并从中介者那里获取相关信息，就相当于: 同事类 ==发送消息==> 中介者 ==回送消息==> 同事类
    @Override
    public void sendMessage(int stateChange) {

        on();
        this.getMediator().getMessage(stateChange,this.name);
    }

    public void on() {
        System.out.println("闹钟响了 嗡嗡嗡嗡~~~");
    }
}
