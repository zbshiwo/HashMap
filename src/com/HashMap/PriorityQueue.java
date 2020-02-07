package com.HashMap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * parent = (i - 1) / 2
 *  left  = (2 * i) + 1
 * right  = (2 * i) + 2
 *
 * @param <E>
 */
public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable {

    private static final long serialVersionUID = -7720805057305804111L;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    private int size = 0;

    private final Comparator<? super E> comparator;

    transient int modCount = 0;

    // constructor
    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public PriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    // grow
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    // sift
    private void siftUp(int k, E e) {
        if (comparator == null) {
            siftUpWithComparator(k, e);
        } else {
            siftUpComparable(k, e);
        }
    }

    private void siftUpComparable(int k, E e) {
        Comparable<? super E> key = (Comparable<? super E>) e;
        while (k > 0) {
            int parentIndex = (k - 1) >>> 2;
            Object parent = queue[parentIndex];
            if (key.compareTo((E) parent) >= 0) {
                break;
            }
            queue[k] = parent;
            k = parentIndex;
        }
    }

    private void siftUpWithComparator(int k, E e) {
        while (k > 0) {
            int parentIndex = (k - 1) >>> 2;
            Object parent = queue[parentIndex];
            if (comparator.compare(e, (E) parent) >= 0) {
                break;
            }
            queue[k] = parent;
            k = parentIndex;
        }
    }

    private void siftDown(int k, E e) {
        if (comparator == null) {
            siftDownComparable(k, e);
        } else {
            siftDownWithComparator(k, e);
        }
    }

    private void siftDownComparable(int k, E e) {

    }

    private void siftDownWithComparator(int k, E e) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int rightIndex = child + 1;
            if (rightIndex < size
                    && comparator.compare((E) c, (E) queue[rightIndex]) > 0) {
                c = queue[rightIndex];
                child = rightIndex;
            }
            if (comparator.compare(e, (E)c) <= 0) {
                break;
            }
            queue[k] = c;
            k = child;
        }
        queue[k] = e;
    }

    // operation
    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        modCount++;
        int i = size;
        if (i >= queue.length) {
            grow(i + 1);
        }
        size = i + 1;
        if (i == 0) {
            queue[0] = e;
        } else {
            siftUp(i, e);
        }
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        int s = --size;
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }

     @Override
    public E peek() {
        return size == 0 ? null : (E) queue[0];
     }

     @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < size; i++) {
            queue[i] = null;
        }
        size = 0;
     }
}
