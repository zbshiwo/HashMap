package com.HashMap;

import com.function.Consumer;
import com.util.Objects;


public interface Iterable<T> {
    Iterator<T> iterator();

//    default void forEach(Consumer<? super T> consumer) {
//        Objects.requireNonNull(consumer);
//        for (Iterator<T> iterator = this.iterator(); iterator.hasNext();) {
//            T t = iterator.next();
//            consumer.accept(t);
//        }
//    }

    /**
     * java 8 实现的接口的 default 方法，for 方法会被编译器解释为上面的方法
     * @param consumer
     */
    default void forEach(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
//        for (T t : this) {
//            consumer.accept(t);
//        }
    }

}
