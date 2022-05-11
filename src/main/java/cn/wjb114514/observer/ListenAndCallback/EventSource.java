package cn.wjb114514.observer.ListenAndCallback;


import java.util.Vector;

public class EventSource {
    //监听器列表，监听器的注册则加入此列表
    // 此监听器的列表管理 各个监听器的实现子类， 监听器的实现子类专一性的监听某一事件
    // 比如监听点击事件的监听器实现子类/监听按键事件的... 所以点击事件和按键事件都分配了唯一的监听器
    private Vector<EventListener> ListenerList = new Vector<>();

    //注册监听器：这就是观察者模式 对观察者集合的管理方法
    // 这里体现了接口编程，即我们注册监听器时，可以传入一个接口实现子类，也就是匿名内部类，这样监听器集合里就管理了 各个功能的监听器实现子类
    public void addCloseListener(EventListener eventListener){
        System.out.println("事件源关注closeWindows事件");
        ListenerList.add(eventListener);
    }
    public void addOpenListener(EventListener eventListener){
        System.out.println("事件源关注openWindows事件");
        ListenerList.add(eventListener);
    }

    //撤销注册
    public void removeListener(EventListener eventListener){
        ListenerList.remove(eventListener);
    }
    //接受外部事件
    public void notifyListenerEvents(EventObject event){
        for(EventListener eventListener:ListenerList){
            eventListener.handleEvent(event);
        }
    }
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.addCloseListener((event)->{
            event.doEvent();
            if(event.getSource().equals("closeWindows")){
                System.out.println("监听器监听到并执行doClose");
            }
            else
            {
                System.out.println("监听器没有监听到closeWindows");
            }
        });

        eventSource.addOpenListener((event)->{
            event.doEvent();
            if(event.getSource().equals("openWindows")){
                System.out.println("监听器监听到并执行doOpen");
            }
            else
            {
                System.out.println("监听器没有监听到openWindows");
            }
        });
        eventSource.notifyListenerEvents(new EventObject("openWindows"));
        eventSource.notifyListenerEvents(new EventObject("closeWindows"));
    }
}