package cn.wjb114514.templateMethod.improve;

public class PureSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        // 不加配料的纯豆浆，空实现
    }

    @Override
    boolean customerWantCondiment() {
        return false;
    }
}
