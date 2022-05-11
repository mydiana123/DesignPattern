package cn.wjb114514.Mediator.code;

public abstract class Mediator {

    // 把同事类 加入中介者对象管理的集合里
    public abstract void register(String colName, Colleague colleague);

    // 接受具体同事类发来的消息
    public abstract void getMessage(int stateChange,String colleagueName);
    public abstract void sendMessage();
}
