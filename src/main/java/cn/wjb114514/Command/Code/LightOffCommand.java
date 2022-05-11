package cn.wjb114514.Command.Code;

public class LightOffCommand implements Command{
    LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 根据业务逻辑，调用接受者的方法
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
