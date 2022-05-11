package cn.wjb114514.Adapter.ObjectAdapter.springmvc;

// 多种Controller 的实现
public interface Controller {

}

class HttpController implements Controller {
    public void doHttpHandler() {
        System.out.println("处理http...");
    }
}

class SimpleController implements Controller {
    public void doSimpleHandler() {
        System.out.println("处理简单controller...");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationHandler() {
        System.out.println("处理注解...");
    }
}