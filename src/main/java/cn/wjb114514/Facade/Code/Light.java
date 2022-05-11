package cn.wjb114514.Facade.Code;

public class Light {
    private Light() {

    }

    private static final Light instance = new Light();

    public static Light getInstance() {
        return instance;
    }
    public void on() {
        System.out.println("open light");
    }
}
