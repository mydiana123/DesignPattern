package cn.wjb114514.ChainOfResponsiblity.code;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 职责链模式 在SpringMVC框架。
 *
 *  SpringMVC执行流程
 *  请求 ===> DispatcherServlet == 遍历HandlerMapping集合[主要由配置文件/注解信息 通过反射获取] ==> 寻找到 HandlerMapping,和 HandlerExecutionChain[包括拦截器]
 * HandlerInterceptor调用preHandle()方法 ，遍历HandlerAdapter 获取处理此Controller的具体handler实现子类。 ==处理controller，获取mv对象==>
 * ===> 调用postHandle()方法 ==发生异常的话=> HandlerExceptionResolver ==> 异常处理
 * == 没发生异常 ==> 根据ViewResolver解析上面返回的mv对象 ==视图渲染，render==> view对象
 * ===调用afterCompletion()方法===> 返回response对象。
 */
public class Source {
    public static void main(String[] args) {
        DispatcherServlet
    }
}
