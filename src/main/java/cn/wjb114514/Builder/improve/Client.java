package cn.wjb114514.Builder.improve;


/**
 * 原本：产品类内部耦合了创建产品类的过程。
 * 对于用户而言，用户只关心产品的属性，不需要知道产品制作流程。
 * 比如用户买苹果手机，他不想知道苹果手机的CPU是怎么做到，他只想知道苹果手机的CPU是几核的。
 *
 * 这样我们把创建产品的过程单独拎出来，由于不同产品创造过程有些许不同，我们使用抽象编程，完成接口-实现类的方式，把创造过程抽象出来
 * 使用一个监督者Director，通过监督者实现产品的生产，监督者内部聚合了建造者，建造者内部组合了产品，层层包装，用户最后直接获得了产品
 * 用户->使用监督者->监督者使用建造者->建造者使用产品->用户获得产品！
 * 整体上看，用户直接访问监督者就获得了产品，不需要任何关于建造者的细节。也就是用户不关心产品的制作过程
 */
public class Client {
    public static void main(String[] args) {
        // 普通房子建造者
        CommonHouse commonHouse = new CommonHouse();
        // 普通房子监督者
        Director director = new Director(commonHouse);
        director.constructHouse();
        // 高楼建造者
        HighHouse highHouse = new HighHouse();
        director.houseBuilder = highHouse;
        director.constructHouse();

    }
}
