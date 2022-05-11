package cn.wjb114514.decoration;

/**
 * 装饰者模式
 *
 * 星巴克咖啡：
 * 1.单品咖啡 ： Espresso ShortBlack LongBlack Decaf
 * 2.加调料： Milk Soy chocolate
 * 3.要求扩展咖啡种类时，要求做到扩展性好。
 * 4.用于可以点单品，也可以加调料，可以计算价钱。
 *
 * 传统想法：为单品咖啡和调料 单独设计一个类，是Drink的实现类。 Decaf + Milk / Decaf + Soy / Decaf + Milk + Soy ! 类爆炸！
 *
 *                                  Drink  抽象层
 *                            Espresso ShortBlack LongBlack Decaf
 *                   [Milk,Soy,Chocolate,Milk&Soy,Milk&Chocolate,Milk&Soy,Milk&Soy&Chocolate]
 *                   如果采取继承设计，每一个具体的饮品类都要和调料的全排列进行继承，这是很恐怖的
 *  解决方案：组合/聚合代替继承，即调料和具体的饮品之间，不是继承关系，而是内置关系
 *  把is-a的关系，修正为has-a的关系。
 *
 *  方案二：
 *                      Drink  抽象层
 *  *        Espresso ShortBlack LongBlack Decaf
 *  在具体的饮品类里，设计成员变量为Milk,Soy,Chocolate 并为所有的调料设计 has()方法，判断用户点没点，以及get/set方法 为用户点单或者获取用户点了多少
 *  缺点：具体的饮品类内部臃肿，维护性差，不符合ocp。如果再加一个小料Coconut，就会导致所有的饮品类增加一个成员变量和对应方法。十分复杂！
 *
 *  方案三： 装饰者模式
 *  动态的将新功能附加到对象上，在对象功能扩展方面，它比继承更有弹性，也体现了ocp原则。
 *
 *  举例：送快递
 *  几个角色
 *  主体： 陶瓷，衣服 [被装饰者，也可以看做上例中的Drink子类]
 *  包装： 报纸填充，塑料泡沫，纸板 [装饰者，比如上例中的调料]
 *
 *  ConcreteComponent:具体主体 单品咖啡
 *  Component:定义主体的规范
 *  Decorator : 装饰者 装饰者的内部可以包含被装饰者。
 *  如果ConcreteComponent很多，还可以设计一层抽象层，再提取一层共用部分。[比如Drink 和 具体饮品之间，可以再加一层Coffee，这样就可以把具体饮品的共同点继续抽象]
 *
 *  可以看到：抽象层的泛化 就是分层， 而组合和聚合关系就是包装。 设计模式的灵魂就是分层和包装，前者让功能模块化，分层化，后者让功能具体化，而不引入新的类。
 *  最终版
 *                                  Drink
 *                                  Coffee
 *                      Espresso ShortBlack LongBlack Decaf
 *                                      ↑
 *                                  Decorator(内置了Drink)
 *                               Milk Soy Chocolate
 *
 *             两份巧克力和一份牛奶的LongBlack
 *             装饰者模式的核心：装饰者包含被装饰者，而不是我们想的那样 被装饰者包含装饰者
 *             // 任何组合，都可以通过递归实现：！！
 *             Chocolate {Chocolate {Milk {LongBlack}}}
 */
public class Start {
}
