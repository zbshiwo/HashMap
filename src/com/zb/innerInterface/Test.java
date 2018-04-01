package com.zb.innerInterface;

public class Test implements OutterInterface{
    public static void main(String[] args) {
        String str = null;

        System.out.println(new Test().hashCode());
        System.out.println(new Test().hashCode());
    }

    @Override
    public void clear() {
        System.out.println();
    }
    class Entry implements OutterInterface.Entry{
        @Override
        public int getKey() {
            return 0;
        }
    }
}
