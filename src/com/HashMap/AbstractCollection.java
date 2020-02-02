package com.HashMap;

import java.util.Iterator;

public abstract class AbstractCollection<E> implements Collection<E> {

    //TODO 为什么最大数组长度是 Integer.MAX_VALUE - 8
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    protected AbstractCollection() {
    }

    public abstract Iterator<E> iterator();

    public abstract int size();

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断 Collection 中是否包含 object
     * @param object
     * @return
     */
    @Override
    public boolean contains(Object object) {
        Iterator<E> iterator = iterator();

        if (object == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (object.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {

        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * 遍历 Collection ，清除指定的 object
     * @param object
     * @return
     * @see Iterator#remove()
     */
    @Override
    public boolean remove(Object object) {
        Iterator<E> iterator = iterator();

        if (object == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    iterator.remove();
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (object.equals(iterator.next())) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object c : collection) {
            if (!contains(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean modified = false;
        for (E e : collection) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
