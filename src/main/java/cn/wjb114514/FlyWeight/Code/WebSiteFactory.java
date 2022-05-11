package cn.wjb114514.FlyWeight.Code;

import java.util.HashMap;

// 网站工厂类 [根据需求] 返回一个网站
public class WebSiteFactory {
    // 内部聚合WebSite的享元对象。

    // 这里可以看到，ConcreteWebSite对象一定是细粒度[苍蝇质量flyweight]的，要不这个池开销太大， 大对象一般都是懒加载的，你可倒好给人家缓存了，缓存池都装不下了。
    private HashMap<String,ConcreteWebSite> pool = new HashMap<>();

    // 把一些常见的对象放入池里，如果用户需要的网站在池里不存在，就创建一个并放入池中。
    public WebSite getWebSiteCategory(String type) {
        // 没有则创建，并放入池里
        if (!pool.containsKey(type)) {
            pool.put(type,new ConcreteWebSite(type));
        }
        return (WebSite) pool.get(type);
    }

    // 返回当前池中缓存的享元对象数量
    public int getWebSiteCount() {
        return pool.size();
    }


}
