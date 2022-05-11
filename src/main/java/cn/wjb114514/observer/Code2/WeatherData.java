package cn.wjb114514.observer.Code2;

import cn.wjb114514.observer.Code1.CurrentCondition;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private float temperature;
    private float pressure;
    private float humidity;

    private List<Observer> observers;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    // 用于管理内部聚合的观察者对象的三个方法
    @Override
    public void registerObserver(Observer o) {
        // 添加一个观察者
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        // 移除一个观察者
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(int i= 0; i < observers.size(); i++) {
            observers.get(i).update(getTemperature(),getPressure(),getHumidity());
        }
    }

    // 当有数据更新时：就向客户端推送，并更新客户端的数据
    public void dataChange() {
        // 调用客户端的更新方法，实现数据的推送并更新
       notifyObserver();
    }

    // 数据有更新时，就调用: 先更新自己的，再把自己的数据推送给客户端
    public void setData(float temperature, float pressure,float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
