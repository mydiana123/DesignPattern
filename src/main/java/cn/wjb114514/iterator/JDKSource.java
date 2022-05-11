package cn.wjb114514.iterator;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class JDKSource {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("jack");
        Iterator<String> iter = a.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}

/*

角色分析：

ArrayList:聚合对象，内部有一个
transient Object[] elementData; // non-private to simplify nested class access
对外提供了一个提供迭代器的方法：
public Iterator<E> iterator() {
        return new Itr();
    }
注意：传统的迭代器模式，聚合对象获取迭代器时，迭代器对象需要获取到聚合对象的 内部对象集合[通过参数传递]
但是，在本例中，由于迭代器对象和聚合对象属于 内部类-外部类的关系，因此迭代器对象可以直接操作到 内部对象集合，就无需使用参数传递了。
其实对于ArrayList这种不太会被修改的源码，设计成这样可以保证系统的稳定性。因为我们无法重写ArrayList对应的迭代器。
同理，对于jdk的集合类源码，往往聚合对象和迭代器对象，都是采取迭代器对应的类是聚合对象的内部类。
这样可以无需把 聚合对象内部的数据传给迭代器。

private class Itr implements Iterator<E> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = modCount; // 如果发生了线程安全问题，预期的修改次数和 统计到的修改次数不符，会爆 ConcurrentModificationException

    Itr() {}

    public boolean hasNext() {
        return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
        // 如果在删除时发生了 数组越界，很有可能是发生了线程安全问题...
            throw new ConcurrentModificationException();
        }
    }

    // 此方法用于对集合的每个元素进行特定操作
    @Override
    @SuppressWarnings("unchecked")
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        final int size = ArrayList.this.size;
        int i = cursor;
        if (i >= size) {
            return;
        }
        final Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length) {
            throw new ConcurrentModificationException();
        }
        while (i != size && modCount == expectedModCount) {
            consumer.accept((E) elementData[i++]);
        }
        // update once at end of iteration to reduce heap write traffic
        cursor = i;
        lastRet = i - 1;
        checkForComodification();
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
 */