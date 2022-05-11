package cn.wjb114514.observer.Code2;

// 相比传统方法，观察者模式把 对客户端的操作 使用集合对象管理，并对外提供了操作内部客户端的方法
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
