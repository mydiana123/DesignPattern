package cn.wjb114514.Command.Code;

/**
 * 原始操作[不使用命令模式]：
 * 发布者.发布命令 ==> 接受者.执行命令()
 * 在这种模式下，发布者和接受者耦合，发布者知道自己发布了什么命令，也知道接受者执行命令的逻辑
 *
 * 使用命令模式：
 * 发布者.发布命令() =发布命令=> 生成命令对象[对发布者不透明] =命令对象把命令作用在自己内部的接受者=> 接受者.执行命令()[对发布者不透明]
 * 由于命令对象对发布者不透明
 * 所以发布者对命令本身 和 接受者如何执行命令 都不了解
 *
 * 好处： 遵守ocp原则。
 * 命令发布者由于 不关注 命令本身 和 接受者如何执行命令，所以我们增加一个命令不需要修改命令发布者的代码
 * 只需要在外部注册一个 新的命令对象给命令发布者。反正命令发布者 不管你这个命令是啥，你给我注册了我就能用。
 * 同理，我们也可以增加一个命令接受者，由于命令接受者和命令发布者不直接关联，而是由命令对象管理，所以增加一万个命令接收者，都和命令发布者无关
 * 因为命令发布者 不关注命令对象如何实现...
 *
 */
public class Client {
    public static void main(String[] args) {
        // 创建命令接受者: 命令作用于接受者，体现在接受者调用相关方法，因为oop~
        // 比如开灯命令作用在 接受者上，接受者就要用 receiver.onLight(); 方法，来完成命令的接受
        LightReceiver commandReceiver = new LightReceiver();

        // 创建开关命令: 目前：命令对象和 接收对象被绑定了
        LightOnCommand lightOnCommand = new LightOnCommand(commandReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(commandReceiver);

        RemoteController remoteController = new RemoteController();
        // 给按钮安排功能
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);
        remoteController.onButtonWasPushed(0);
        remoteController.undoButtonWasPushed();
    }
}
