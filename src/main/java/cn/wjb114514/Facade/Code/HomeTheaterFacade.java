package cn.wjb114514.Facade.Code;


// 影院的外观类[页面类]
public class HomeTheaterFacade {

    // 内部聚合/组合各个子系统对象
    private Light light;
    private DVDPlayer dvdPlayer;
    private Stereo stereo;
    private Screen screen;
    private PopCornMachine popCornMachine;
    private  Projector projector;

    public HomeTheaterFacade() {
        this.light = Light.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
        this.stereo = Stereo.getInstance();
        this.screen = Screen.getInstance();
        this.popCornMachine = PopCornMachine.getInstance();
        this.projector = Projector.getInstance();
    }

    // 准备工作
    public void ready() {
        popCornMachine.on();
        popCornMachine.pop();

        screen.show();
        projector.on();

        stereo.up();

        dvdPlayer.on();
        light.on();
    }

    // 开始播放
    public void play() {
        dvdPlayer.play();
    }

    // 暂停
    public void pause() {
        dvdPlayer.pause();
    }

    // 关闭工作
    public void end() {
        popCornMachine.off();
        dvdPlayer.off();
        stereo.down();
    }

}
