package cn.wjb114514.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ObserverSource {
    public static void main(String[] args) {

    }
}

//public interface Observer {
//    /**
//     * This method is called whenever the observed object is changed. An
//     * application calls an <tt>Observable</tt> object's
//     * <code>notifyObservers</code> method to have all the object's
//     * observers notified of the change.
//     *
//     * @param   o     the observable object.
//     * @param   arg   an argument passed to the <code>notifyObservers</code>
//     *                 method.
//     */
//    void update(Observable o, Object arg);
//}

// 此类就是Subject类。但是此类并没有明确的实现某个Subject接口，总之观察者模式的实现并没有那么死，思想大于实现，实现灵活。
// 相当于此类把 针对观察者集合的操作以及监听属性变化的核心方法已经实现好了
// 其他的Subject类只需要继承，而不需要重写这些核心方法，因为父类已经实现了
//public class Observable {
//    private boolean changed = false;
//    private Vector<Observer> obs;
//
//    // 用于管理Observers的集合
//    public Observable() {
//        obs = new Vector<>();
//    }
//
    // 用于操作Observers集合的方法
//    public synchronized void addObserver(Observer o) {
//        if (o == null)
//            throw new NullPointerException();
//        if (!obs.contains(o)) {
//            obs.addElement(o);
//        }
//    }
//
//
//    public synchronized void deleteObserver(Observer o) {
//        obs.removeElement(o);
//    }
//
//    public void notifyObservers() {
//        notifyObservers(null);
//    }
//
//    public void notifyObservers(Object arg) {
//
//        Object[] arrLocal;
//
//        synchronized (this) {
//            if (!changed)
//                return;
//            arrLocal = obs.toArray();
//            clearChanged();
//        }
//        // 向所有客户端进行推送的方法
//        for (int i = arrLocal.length-1; i>=0; i--)
//            ((Observer)arrLocal[i]).update(this, arg);
//    }
//
//
//    public synchronized void deleteObservers() {
//        obs.removeAllElements();
//    }
// --------------------------------------------------
// 下面的类就是在监听Observer 的事件情况，Observer观察者模式和java的监听-回调机制应该密不可分
//    protected synchronized void setChanged() {
//        changed = true;
//    }
//
//    protected synchronized void clearChanged() {
//        changed = false;
//    }
//
//    public synchronized boolean hasChanged() {
//        return changed;
//    }
//
//    public synchronized int countObservers() {
//        return obs.size();
//    }
//}
