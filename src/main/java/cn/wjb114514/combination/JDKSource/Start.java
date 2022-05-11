package cn.wjb114514.combination.JDKSource;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap中体现了组合模式
 *
 * public interface Map<K,V> (Component:定义了所有操作和构成map组织的统一规范：put/putAll/remove/removeAll)
 * public abstract class AbstractMap<K,V> implements Map<K,V> (仍然是Component，针对Map做了一层细化)
 * 因为Map太抽象了，所以实现起来比较麻烦，设计者在Map和实现层之间加了一层抽象层，此抽象层提供了一些方法的默认实现
 *   public V put(K key, V value) {
 *         throw new UnsupportedOperationException();
 *     }
 * public class HashMap<K,V> extends AbstractMap<K,V>
 *     implements Map<K,V>, Cloneable, Serializable
 * HashMap类似我们的College或者University：如果把一个HashMap看做树状结构的节点，他显然既可以操作别人，又可以被操作 [Composite]
 *
 * 谁是Leaf?
 * 此Node只提供了操作自己的方法，不存在操作子元素的方法， 类似于叶子结点。 也就是我们的Department
 * static class Node<K,V> implements Map.Entry<K,V> {
 *         final int hash;
 *         final K key;
 *         V value;
 *         Node<K,V> next;
 *
 *         Node(int hash, K key, V value, Node<K,V> next) {
 *             this.hash = hash;
 *             this.key = key;
 *             this.value = value;
 *             this.next = next;
 *         }
 *
 *         public final K getKey()        { return key; }
 *         public final V getValue()      { return value; }
 *         public final String toString() { return key + "=" + value; }
 *
 *         public final int hashCode() {
 *             return Objects.hashCode(key) ^ Objects.hashCode(value);
 *         }
 *
 *         public final V setValue(V newValue) {
 *             V oldValue = value;
 *             value = newValue;
 *             return oldValue;
 *         }
 *
 *         public final boolean equals(Object o) {
 *             if (o == this)
 *                 return true;
 *             if (o instanceof Map.Entry) {
 *                 Map.Entry<?,?> e = (Map.Entry<?,?>)o;
 *                 if (Objects.equals(key, e.getKey()) &&
 *                     Objects.equals(value, e.getValue()))
 *                     return true;
 *             }
 *             return false;
 *         }
 *     }
 *
 *                          Node 和 HashMap都可以看做一个Composite 不过HashMap是中间构件，也就是非叶子结点，而Node就是Leaf叶子结点
 *                          二者都实现Map/AbstractMap接口，可以看做都遵循了树形结构结点规范Component
 *                                HashMap
 *                        /put   | putAll     \put
 *                      Node     HashMap      Node
 *                              /   |   \
 *                            Node Node Node
 *
 * 注意事项：
 * 1.客户端面对包含关系，无需考虑谁包含谁，反正Component提供了统一的操作关系，即整体-部分的操作关系
 *
 * 2.具有较强的扩展性，符合ocp。 当修改组织层次时，客户端的代码仍然可以保持不变，修改的是内部的对象关系
 * 3.方便创建出复杂的层次结构，客户端不理会组合里面的组成细节，容易添加节点或者叶子创建出复杂的树形结构（网页里连续下拉条就是这样的）
 * 比如文章类型就满足组合模式，
 *                      前端         后端
 *                     / | \
 *                  html css js
 *                          / | \
 *                       jquery vue react ...
 * 4.[要求遍历组织结构，或者处理的对象含有树状结构] 非常适合使用组合模式
 *
 * 5.需要较高抽象性 ==> 如果叶子和节点之间的差异性较大， 无法被统一的抽象出来，不适合使用！
 */
public class Start {
//    HashMap
}
