package cn.wjb114514.Command.Code;



// 具体的命令对象，内部需要聚合一个 命令接受者
public class LightOnCommand implements Command {
    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 根据业务逻辑，调用接受者的方法
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
