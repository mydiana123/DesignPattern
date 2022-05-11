package cn.wjb114514.Command.Code;

// 创建命令接口，其后的具体命令都基于此接口实现 ：此接口提供了 命令对象操作接受者对象的规范
public interface Command {
    void execute(); // 执行某个动作/操作
    void undo();    // 撤销某个动作/操作

}
