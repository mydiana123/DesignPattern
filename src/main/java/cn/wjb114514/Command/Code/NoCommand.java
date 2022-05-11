package cn.wjb114514.Command.Code;

// 此类为空操作，作用是对空操作的判断。
// 没有任何命令 ==> 空执行 用于初始化每个按钮。 当调用空命令时，对象什么事情都不做，这是一种设计模式：省略对空的判断。
public class NoCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
