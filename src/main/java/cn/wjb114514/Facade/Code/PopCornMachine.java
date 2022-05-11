package cn.wjb114514.Facade.Code;

public class PopCornMachine {


    private PopCornMachine() {
    }

    private static final PopCornMachine instance = new PopCornMachine();

    public static PopCornMachine getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("pop corn on");
    }
    public void off() {
        System.out.println("pop corn off");
    }
    public void pop() {
        System.out.println("pop corn pop");
    }
}
