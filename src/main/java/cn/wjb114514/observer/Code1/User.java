package cn.wjb114514.observer.Code1;


/**
 * 缺点：每次 接入一个第三方客户端，就要在服务器内部管理一个第三方的对象 ===> 违反ocp原则
 * 缺点2：不能动态的更新天气数据，每次修改都要手动setData() 很麻烦！
 */
public class User {
    public static void main(String[] args) {
        // 用户 持有一个接入方对象
        // 客户端对象
        CurrentCondition currentCondition = new CurrentCondition();

        // 服务器端对象，内部持有一个客户端
        WeatherData weatherData = new WeatherData(currentCondition);
        weatherData.setData(30,150,40);


        System.out.println("风云骤变~~");
        // 每次风云骤变，都要手动setData，硬编码
        weatherData.setData(40,160,80);
    }
}
