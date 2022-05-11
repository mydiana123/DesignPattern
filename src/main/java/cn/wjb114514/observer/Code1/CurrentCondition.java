package cn.wjb114514.observer.Code1;

// 使用到气象局天气数据的网站[可能是气象局本身的网站，或者使用接口的第三方网站]
public class CurrentCondition {
    // 显示当前天气情况
    private float temperature;
    private float pressure;
    private float humidity;

    // 更新天气情况，由weatherData调用，相当于气象局把最新数据推送给 使用其的网站，完成实时更新。
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println("***Today mTemperature" + temperature +  "***");
        System.out.println("***Today mPressure" + pressure + "***");
        System.out.println("***Today mHumidity" + humidity +  "***");
    }

}
