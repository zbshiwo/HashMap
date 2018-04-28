package com.util;

public class Objects {
    private Objects(){
        throw new AssertionError("No java.util.Objects instances for you!");
    }

    public static boolean equals(Object a, Object b){
        return (a == b) || (a != null && a.equals(b));
    }

    public static void main(String[] args) {
        String json = "{\"a\":\"c\",\"b\":[\"a\",\"b\"]}";
    }
}
