package com.HashMap;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HashMap<K, V> extends AbstractMap<K, V>
    implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    /**
     * 默认的初始容量为16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 默认的最大容量为2的30次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认的加载因子为0.75
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 实际的加载因子
     */
    final float loadFactor;

    transient int modCount;

    /**
     * HashMap的阈值，用于判断是否需要调整HashMap的容量（threshold = 容量*加载因子）
     */
    int threshold;

    /**
     * HashMap的大小，他是HashMap保存键值对的数量
     */
    transient int size;

    /**
     * 为提高效率，将Node数组中的链表最大个数设置为8个，超过8个就变成红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;

    transient int modCount;

    transient Set<Map.Entry<K,V>> entrySet;

    transient Node<K, V>[] table;

    /**
     * HashMap的无参构造函数
     */
    public HashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * HashMap的带初始化容量大小参数构造函数
     * @param initialCapacity: HashMap的容量大小
     */
    public HashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * HashMap 带加载因子和初始化容量大小参数的构造函数
     * @param initialCapacity: HashMap容量大小
     * @param loadFactor: HashMap加载因子
     */
    public HashMap(int initialCapacity, float loadFactor){
        if (initialCapacity < 0)
            throw new  IllegalArgumentException("Illegal initial capacity: " +
                                                initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor < 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                                loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     *当指定initialCapacity实例化HashMap时，这个方法会找到大于等于initialCapacity
     * 的最小的2的幂（initialCapacity如果是2的幂的话，返回的是它本身）
     * 问题：为什么HashMap的大小必须是2的幂？
     * @param cap
     * @return
     * 拓展：1、 >> 按二进制形式把所有的数字向右移动对应的位数，低位舍弃，高位的空位补符号位，
     *              即正数补0，负数补1，右移n位就相当于除以2的n次方
     *       2、 >>>按二进制形式把所有的数字向右移动对应的位数，低位舍弃，高位的空位补0，
     *              对于正数来说和带符号右移相同，对于负数来说不同。
     *       3、 << 按二进制形式把所有的数字向左移动对应的位数，高位舍弃，低位的空位补0，
     *              在没有溢出的情况下，左移n为就相当于乘以2的n次方
     */
    static final int tableSizeFor(int cap) {
        //为什么要对cap进行减1操作，为了防止cap已经是2的幂，这样经过运算返回值会是cap的2倍
        int n = cap - 1;
        //这里只讨论n > 0的情况，因为n < 0的话，右移之后还是0
        //因为n不等于0，所以n的二进制表示总会有一位为1，这时只考虑最高位的1，通过右移1位，
        //再进行或运算，会产生类似000011xxxxxx的数字
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        //以上同理可得，经过运算后会产生一个n的二进制数的最高位到最低位全是1的数字，（10 = 00001010  =>  00001111 = 15）
        //n的最大值为 1111 1111 1111 1111 1111 1111 1111 1111
        //加1之后会得到2的幂
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * HashMap中的到hash值的算法
     * @param key
     * @return
     * 拓展：位运算符 ^
     * 进行'异或'运算，仅当两个操作数不同一时候。对应的输出结果才为1，否则为0
     * 任何数 ^ 0 都为它本身，任何数 ^ 1 都相当于取反
     */
    static final int hash(Object key) {
        int h;
        //
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
<<<<<<< HEAD:HashMap.java
=======
    }

    @Override
    public Set<Entry<k, v>> entrySet() {
        return null;
>>>>>>> f560ee88cdb00ad0ff93d954a228b738c30888f0:src/HashMap.java
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K, V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final String toString() { return key + "=" + value; }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final boolean equals(Object o){
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 返回HashMap中的键值对的数量
     * @return: HashMap中的键值对的数量
     */
    public int size() {
        return size;
    }

    /**
     * 返回HashMap是否为空
     * @return: HashMap是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;

        //如果数组为空或者长度为0则创建
        if ((tab = table) == null || (n = table.length) == 0)
            n = (tab = resize()).length;
        //计算index，如果为空就新建节点并加入到数组之中
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = new Node(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            //如果将同一个键值对放入两次，第二次无效
            if (p.hash == hash && ((k = p.key) == key) || (key != null && key.equals(k)))
                e = p;
            // 如果最顶层的Node已经为红黑树，则...
            else if (...)
            //不是红黑树节点，则加入到链表的最后，超过8个变为红黑树
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = new Node(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1)
                            //
                        break;
                    }
                    if (p.hash == hash && ((k = p.key) == key) || (key != null && key.equals(k)))
                        break;
                    p = e;
                }
            }
            //为啥要写e != null，e为空即
            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
    }






}
