package cn.wjb114514.observer.Code2;

public class Client {
    public static void main(String[] args) {
        CurrentCondition currentCondition = new CurrentCondition();
        BaiduCurrentCondition baiduCurrentCondition = new BaiduCurrentCondition();
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(currentCondition);
        weatherData.registerObserver(baiduCurrentCondition);

        // 发生了天气变化事件，立即调用回调函数[notifyObserver]
        weatherData.setData(50,12,45);

    }
}
