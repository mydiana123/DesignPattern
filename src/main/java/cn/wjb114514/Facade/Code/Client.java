package cn.wjb114514.Facade.Code;

// 统一调用 界面类提供的方法
// 外观类通过内聚子系统，实现了复杂的业务逻辑调用，而且可以调度子系统内部依赖关系
public class Client {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.end();
    }
}
