package cn.wjb114514.observer.Code1;

/**
 * 天气预报设计需求：普通模式1 使用推送方式完成
 * 观察者模式 其实描述了一个 主体 和 多个观察者的 一对多关系。
 *
 * 主体掌握着数据信息，而观察者依赖主体获取数据信息。 比如主体就是奶站，观察者就是用户，观察者依赖奶站提供的信息 ==> 奶
 *
 * 而在这个模型里，主体什么时候有数据信息/数据信息会不会发生变化，对观察者而言就很重要了。比如用户需要时刻关注奶站有没有奶 。
 *
 * 于是 观察者模式提供了一个 从主体==>观察者 的数据分派模型。
 *
 * 也就是观察者获取数据 是由主体推送而来的，观察者不需要主动的更新数据，而是由主体进行数据的更新。比如奶站问题，用户无需每隔一段时间就去奶站取奶。
 *
 * 而是等到奶站有奶了，奶站派送奶员给用户送奶
 *
 * 这是不是和事件机制很像~~ ==> 如果发生了事件，事件发生方则派相关的处理方法进行处理，而如果事件不发生，相关的处理方法不会定时的去询问是否有事件发生，
 *
 * 这里事件的发生者，和事件的处理者就是观察者模型。在java的对事件的监听-回调机制里，就用到了观察者模式，其中主体负责监听事件，分派给相关回调函数[观察者]。
 *
 *
 *
 * 针对这样的设计，观察者模式提出了
 *
 * 1.主体内部聚合观察者对象集合
 *
 * 2.主体提供对观察者对象集合的操作方法 [比如注册，删除，通知所有]
 *
 * 3.主体内部实现了相关事件的分派方法 因为主体的数据是容易变化的，因此主体内部需要针对 [是否有数据发生了变化，数据变化后的新数据是什么]等情况，做出相关的方法
 */
public class Start {
}
