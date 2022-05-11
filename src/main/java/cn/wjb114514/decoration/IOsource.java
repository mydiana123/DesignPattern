package cn.wjb114514.decoration;

import java.io.*;

/**
 * IO流运用装饰者模式
 *
 *
 */
public class IOsource {
    public static void main(String[] args) throws FileNotFoundException {
        new BufferedInputStream(new FileInputStream("test"));
    }
}

// 装饰者模式主要是为了避免类爆炸的问题，
// 因为输入流实在是太多了，CipherInputStream,FastInputStream ...
// 如果我们为这些 输入流[处理流]都实现一个新功能[包装流]，类似于给咖啡加牛奶，那就需要 CipherInputStreamPlus ...
// 好几个类，造成类爆炸，因此采取装饰者模式，用一个装饰者以及其子类，完成对 处理流的包装。
// 此FilterInputStream[装饰者] 下面有很多实现子类，类似我们的具体调料。
// public
// class FilterInputStream extends InputStream {
//    /**
//     * The input stream to be filtered.
//     */
      // 如果发现一个类，即继承了类A，内部又聚合了类A，就可能是装饰者模式
      // 此FilterInputStream可以对同为输入流的 流对象进行包装，获得包装后的流对象
      // 装饰者内部聚合了被装饰者。
//    protected volatile InputStream in;
//
//    /**
//     * Creates a <code>FilterInputStream</code>
//     * by assigning the  argument <code>in</code>
//     * to the field <code>this.in</code> so as
//     * to remember it for later use.
//     *
//     * @param   in   the underlying input stream, or <code>null</code> if
//     *          this instance is to be created without an underlying stream.
//     */
      // 使用构造器聚合了其他的输入流
//    protected FilterInputStream(InputStream in) {
//        this.in = in;
//    }