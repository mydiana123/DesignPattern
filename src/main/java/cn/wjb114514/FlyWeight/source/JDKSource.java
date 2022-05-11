package cn.wjb114514.FlyWeight.source;


/**
 * Integer的享元模式，和传统的享元模式不同点在于，Integer的享元模式是先把对象创建好了，而不是 没有则创建，即用户手动放入。
 * 因为Integer也没有什么不确定性，所以直接创建好就行。
 * 而且使用valueOf要比new效率高（前提是Integer的值要在low和high之间）
 *     public static Integer valueOf(int i) {
 *         if (i >= IntegerCache.low[-128] && i <= IntegerCache.high[如果不在jdk参数里指定，默认赋值为127])
 *             return IntegerCache.cache[i + (-IntegerCache.low)];
 *         return new Integer(i);
 *     }
 *
 *     static final Integer cache[];
 *
 *     static {
 *             // high value may be configured by property
 *             int h = 127;
 *             // 可以指定jvm参数来指定此high的值。默认赋值为127
 *             String integerCacheHighPropValue =
 *                 sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
 *             if (integerCacheHighPropValue != null) {
 *                 try {
 *                     int i = parseInt(integerCacheHighPropValue);
 *                     i = Math.max(i, 127);
 *                     // Maximum array size is Integer.MAX_VALUE
 *                     // 最大值不超过Integer.MAX_VALUE - (-low) -1。即high和low的和不超过Integer.MAX_VALUE
 *                     h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
 *                 } catch( NumberFormatException nfe) {
 *                     // If the property cannot be parsed into an int, ignore it.
 *                 }
 *             }
 *             high = h;
 *
 *             cache = new Integer[(high - low) + 1];
 *             int j = low;
 *             // 初始化cache数组，此时 从low到high的所有Integer都被缓存了。
 *             也就是说，如果我们获取 [low,high]的任意一个Integer对象， 都是相同的对象。
 *             所以cache就是一个缓存池，而且缓存的都是不变的，细粒度的小对象~~~
 *             for(int k = 0; k < cache.length; k++)
 *                 cache[k] = new Integer(j++);
 *
 *             // range [-128, 127] must be interned (JLS7 5.1.7)
 *             assert IntegerCache.high >= 127;
 *         }
 *
 */
public class JDKSource {
    public static void main(String[] args) {
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);

        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);
        System.out.println(x == y);
        System.out.println(x == z);
        System.out.println(w == x);
        System.out.println(w == y);

        // 为什么valueOf返回的实例是同一对象
        // false
        // true
        // false
        // false

        Integer i1 = Integer.valueOf(114514);
        Integer i2 = Integer.valueOf(114514);
        System.out.println(i1 == i2); // false
    }
}
