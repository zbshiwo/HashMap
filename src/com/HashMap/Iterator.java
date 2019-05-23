package com.HashMap;

import com.function.Consumer;
import com.util.Objects;

public interface Iterator<T> {
    boolean hasNext();

    T next();

    /**
     * TODO
     * what is it used for?
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * TODO
     * what is it used for?
     */
    default void forEachRemaining(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        while (hasNext()) {
            action.accept(next());
        }
    }
}
