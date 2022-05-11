package cn.wjb114514.Mediator.code;

public abstract class Colleague {
    // 此类内部需要聚合一个 中介者 ，以完成和其他同事类的沟通，又可以互相不暴露信息给对方。
    private Mediator mediator;
    public String name;

    // 初始化同事类时，就聚合一个中介者
    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return this.mediator;
    }

    public abstract void sendMessage(int stateChange);
}
