package cn.wjb114514.observer.Code1;

// 气象局的核心数据对象，相当于服务器端
public class WeatherData {
    // 存储最新的天气情况信息
    private float temperature;
    private float pressure;
    private float humidity;

    // 内部维护客户端，用来维护
    private CurrentCondition currentCondition;

    // 当有数据更新时：就向客户端推送，并更新客户端的数据
    public void dataChange() {
        // 调用客户端的更新方法，实现数据的推送并更新
        currentCondition.update(getTemperature(),getPressure(),getHumidity());
    }

    // 数据有更新时，就调用: 先更新自己的，再把自己的数据推送给客户端
    public void setData(float temperature, float pressure,float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        dataChange();
    }

    public WeatherData(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
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
