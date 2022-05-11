package cn.wjb114514.visitor.code;

public class Fail extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人评价为 差~~");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人评价为 差~~");
    }
}
