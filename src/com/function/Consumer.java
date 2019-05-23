package com.function;

import com.util.Objects;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> consume) {
        Objects.requireNonNull(consume);
        return (T t) -> {
            accept(t);
            consume.accept(t);
        };
    }
}

//TODO add readme about Consumer