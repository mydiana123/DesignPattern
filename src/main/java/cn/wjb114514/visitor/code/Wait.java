package cn.wjb114514.visitor.code;

/**
 * 我们新加一个状态
 */
public class Wait extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人待定....");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人待定....");
    }
}
