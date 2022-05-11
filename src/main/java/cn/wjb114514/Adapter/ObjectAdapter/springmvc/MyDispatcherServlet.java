package cn.wjb114514.Adapter.ObjectAdapter.springmvc;

import java.util.ArrayList;
import java.util.List;

/**
 * DispatcherServlet里核心组件getHandler()就使用了适配器模式
 * 即为每一个controller分配一个adapter。adapter负责调用controller的处理方法。
 * adapter通过参数传入到controller，是adapter的直接朋友
 * 相比 把adapter聚合到controller有一定好处。
 * 并且实现了adapter和controller的分离。
 * 具体怎么体现的适配器模式呢
 *
 * 这个和传统的适配器模式又不太一样，
 * 所以实际开发里很多设计模式都是很灵活的。
 * 我们认为适配器描述了 输入--适配器-->输出-->用户 的规范，比如变压器就是一种适配器  220V -变压器-> 5V --> 用户
 * 可以认为我们的HandlerAdapter就是目标接口，他提供了适配器的输出规范
 * 输出就是adapter的handle()方法，其具体实现要看adapter当前支持的controller是哪一个
 * 输入就是controller对象。controller对象输入到adapter里，经过adapter的handle()方法输出。
 * 实际上adapter.handle()就是对controller的处理方法的封装
 * 看上去好像没有必要，直接获取到controller 的运行类型，然后调用controller处理方法。最后输出不好吗？
 * 如果真按照上面那么做，每次遇到一个controller，都要判断他是哪个类型的，究竟是处理哪方面的controller，判断方式就是
 * if(controller instanceof A)
 * else if( controller instanceof B)
 * ...
 * 一堆if-else。那隔几天我加一个F类型的Controller，就要对这一串if-else再来修改。
 * 设计模式就体现在了 这些细枝末节的地方。
 *
 * 说实话，SpringMVC里使用的适配器模式 几乎没太体现适配器模式的原始目的，仅仅为了遵守ocp，不用太多的if-else判断，使得编码优雅。
 *
 * Controller HandlerAdapter[support handle] DispatcherServlet[getHandler()]
 *
 * 适配器模式的精髓就是 输入-适配器->输出
 *
 * 在这里就体现为  Controller -HandlerAdapter->adapter.handle()
 *
 * 看似好像脱裤子放屁，实际上很重要。减少了if-else 的硬编码
 */
public class MyDispatcherServlet {
    List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public static void main(String[] args) {
        new MyDispatcherServlet().doDispatch();
    }

    public MyDispatcherServlet() {

        // 这步可以通过反射获取当前类路径下的所有实现子类，清除硬编码，完成解耦合。
        // 把支持的adapter加入到集合里
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispatch() {
        // 模拟springMVC从request取出handler对象的过程
        // SpringMVC可以从handlerMapping里取出当前请求对应的handler。我们实现不了，就先写死
        AnnotationController controller = new AnnotationController();
        HandlerAdapter handler = getHandler(controller);

        // 使用对应的adapter处理对应的controller
        handler.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        // 遍历所有支持的适配器，看看哪个可以处理当前的controller
        for (HandlerAdapter ha : handlerAdapters) {
            if (ha.support(controller)) {
                return ha;
            }
        }
        return null;
    }
}
