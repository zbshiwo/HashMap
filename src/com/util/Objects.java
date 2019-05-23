package com.util;

public class Objects<T> {
    private Objects(){
        throw new AssertionError("No java.util.Objects instances for you!");
    }

    /**
     * 检查指定的对象引用是否为空
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T t) {
        if (t == null)
            throw new NullPointerException();
        return t;
    }
}
