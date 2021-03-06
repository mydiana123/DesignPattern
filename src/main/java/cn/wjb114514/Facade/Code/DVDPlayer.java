package cn.wjb114514.Facade.Code;

public class DVDPlayer {


    // 使用单例模式

    private DVDPlayer() {}
    private static DVDPlayer instance = new DVDPlayer();

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("dvd open");
    }

    public void off() {
        System.out.println("dvd off");
    }

    public void play() {
        System.out.println("dvd is playing");
    }

    public void pause() {
        System.out.println("dvd is pause");
    }


}
