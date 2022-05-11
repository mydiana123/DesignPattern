package cn.wjb114514.Mediator.code;

public class CoffeeMachine extends Colleague{

    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name,this);
    }

    public void on() {
        System.out.println("咖啡机打开拉~~~");
    }
    public void off() {
        System.out.println("咖啡机关闭拉~~·");
    }
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange,this.name);
    }
}
