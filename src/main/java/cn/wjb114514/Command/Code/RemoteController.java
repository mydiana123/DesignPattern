package cn.wjb114514.Command.Code;

// 命令的发布者，内部通过聚合命令对象，命令对象聚合接受者，完成了 发布者 -命令-> 接受者 的模型
public class RemoteController {

    // 开按钮的命令数组
    Command[] onCommands;
    Command[] offCommands;

    // 撤销命令，只能针对最近的一次操作进行记录
    Command undoCommand;

    // 构造器：对按钮初始化工作
    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for(int i = 0; i < 5; i++) {
            // 目前针对开/关按钮的功能，还没绑定具体的命令，初始化为空
            onCommands[i] = offCommands[i] = new NoCommand();
        }
    }

    // 给按钮设置需要的命令
    public void setCommand (int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    // 按下按钮 ==> 遥控器发布一个命令
    // 可以看到：命令发布者 不用管接受者是怎么执行的，这是解耦的，由命令对象完成
    // 而且命令发布者甚至不知道自己发布了一个什么命令。调用者发布命令，实际上是调用了某个封装的命令对象，发布者不知道命令对象的细节，所以说发布者不知道自己发了啥命令，调了啥方法
    // 而命令作用于接受者，一定是要调用接受者的某个方法 [因为我们是oop的，想让接受者执行命令，就要用接受者对象的方法实现]
    // 但是：发布者也不知道接受者对象的方法是啥，因为接受者被命令对象封装了， 而命令对象对发布者是不可见的
    // 所以在命令模式里，发布者一问三不知：1.不知道命令接收者的处理细节，也就是命令发布者不直接调用接受者的方法 2.不知道命令对象是什么，也就是发布者不知道自己发布的命令的具体细节
    // 但是由命令对象的调度，系统却完成了命令的发布，接受和执行。这就很牛逼
    // 命令发布者 只知道自己按下一个按钮，真正执行的命令(light.on()) 是由命令对象[帮助命令发布者]调用接受者的方法[作用于接受者]完成的。
    public void onButtonWasPushed(int no) {
        onCommands[no].execute();
        // 记录此次的操作，用于下一次的撤销
        undoCommand = onCommands[no];
    }
    public void offButtonWasPushed(int no) {
        offCommands[no].execute();
        undoCommand = offCommands[no];
    }
    public void undoButtonWasPushed() {
        if (undoCommand != null) {
            undoCommand.undo();
        }else {
            System.out.println("上一次没有操作~~");
        }
    }

}
