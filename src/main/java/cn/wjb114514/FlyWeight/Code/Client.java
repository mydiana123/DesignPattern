package cn.wjb114514.FlyWeight.Code;

public class Client {
    public static void main(String[] args) {
        // 创建一个获取对象的工厂

        WebSiteFactory webSiteFactory = new WebSiteFactory();

        WebSite newsWebSite = webSiteFactory.getWebSiteCategory("新闻");
        User xinLangUser = new User();
        xinLangUser.setName("新浪");
        newsWebSite.use(xinLangUser);

        WebSite blogsWebSite = webSiteFactory.getWebSiteCategory("博客");
        User bilibiliUser = new User();
        bilibiliUser.setName("B站");
        blogsWebSite.use(bilibiliUser);

        // 第二个用户也想搭一个博客网站，其实完全可以管第一个用户要一个，就不脱裤子放屁再自己弄一个了
        WebSite blogsWebSite2 = webSiteFactory.getWebSiteCategory("博客");

        System.out.println(blogsWebSite == blogsWebSite2);
        System.out.println("网站的个数为" + webSiteFactory.getWebSiteCount());
    }
}
