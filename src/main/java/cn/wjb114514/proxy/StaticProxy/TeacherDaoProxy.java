package cn.wjb114514.proxy.StaticProxy;

public class TeacherDaoProxy implements ITeacherDao{

    // 使用接口的静态代理，需要在代理对象里聚合一个目标对象，并通过代理调用目标对象的方法。
    // 这里的目标方法是对代理透明，对用户封闭的，也就是用户无法操作 目标对象，只能通过代理对象访问目标对象
    private ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        // 在这里可以实现 执行目标方法前的方法 [preHandler]
        System.out.println("代理开始");
        target.teach();
        // 在这里可以实现，执行目标方法后的方法 [postHandler]
        System.out.println("代理结束");
    }
}
