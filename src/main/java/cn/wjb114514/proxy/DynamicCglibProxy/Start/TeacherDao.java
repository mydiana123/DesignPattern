package cn.wjb114514.proxy.DynamicCglibProxy.Start;

public class TeacherDao {
    public void teach() {
        System.out.println("我是cglib的目标对象，不需要实现接口*--- 老师授课ing~~");
    }
}
