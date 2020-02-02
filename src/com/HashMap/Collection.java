package com.HashMap;

import com.function.Predicate;
import com.util.Objects;


import java.util.Iterator;

public interface Collection<E> extends java.lang.Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] t);
    
    boolean add(E e);

    boolean remove(Object o);

    // Bulk Operations

    boolean containsAll(Collection<?> collection);

    boolean addAll(Collection<? extends E> collection);

    boolean removeAll(Collection<?> collection);

    boolean retainAll(Collection<?> collection);

    void clear();

    boolean equals(Object object);

    int hashCode();

    //default method

    /**
     * 如果存在符合 filter 的，remove 第一个符合的
     * @param filter
     * @return
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
}
