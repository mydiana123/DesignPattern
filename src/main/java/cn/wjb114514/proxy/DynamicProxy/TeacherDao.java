package cn.wjb114514.proxy.DynamicProxy;

public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("老师正在授课中");
    }
}
