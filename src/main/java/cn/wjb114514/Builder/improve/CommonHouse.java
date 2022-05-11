package cn.wjb114514.Builder.improve;

// 具体的建造者实现类
public class CommonHouse extends HouseBuilder{


    @Override
    public void buildBase() {
        System.out.println("普通楼的地基5m");
    }

    @Override
    public void buildWalls() {
        System.out.println("使用水泥砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("使用木屋顶");
    }
}
