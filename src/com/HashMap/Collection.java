package com.HashMap;

import com.function.Predicate;
import com.util.Objects;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    /**
     * TODO 这里为什么要用 Object 而不是用 E
     * @param object
     * @return
     * @see #remove(Object object)
     */
    boolean contains(Object object);

    /**
     * 这里再定义一下这个接口，没有意义，只是标识实现了 Iterable 接口
     * @return
     */
    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] t);
    
    boolean add(E t);

    boolean remove(Object object);

    // Bulk Operations

    boolean containsAll(Collection<?> collection);

    boolean addAll(Collection<? extends E> collection);

    boolean removeAll(Collection<E> collection);

    boolean retainAll(Collection<E> collection);

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

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
