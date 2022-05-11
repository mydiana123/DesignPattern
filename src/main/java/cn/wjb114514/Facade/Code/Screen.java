package cn.wjb114514.Facade.Code;

public class Screen {
    private Screen() {}
    private static final Screen instance = new Screen();

    public static Screen getInstance() {
        return instance;
    }

    public void show() {
        System.out.println("screen is showing");
    }


}
