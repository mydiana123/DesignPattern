package cn.wjb114514.observer.ListenAndCallback;

// 这是一个函数式接口。定义了处理事件的逻辑
public interface EventListener extends java.util.EventListener {
    //事件处理
    public void handleEvent(EventObject event);
}