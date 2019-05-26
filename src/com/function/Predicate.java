package com.function;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
