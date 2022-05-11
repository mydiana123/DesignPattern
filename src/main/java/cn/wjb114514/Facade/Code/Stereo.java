package cn.wjb114514.Facade.Code;

public class Stereo {
    private Stereo() {
    }

    private static final Stereo instance = new Stereo();

    public static Stereo getInstance() {
        return instance;
    }

    public void up() {
        System.out.println("stereo up");
    }

    public void down() {
        System.out.println("stereo down");
    }
}
