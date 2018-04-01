package com.zb.AbstractMap_containsValue;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class Test extends AbstractMap{
    public static void main(String[] args) {
        Test test = new Test();
        test.put(null, null);
        test.put(null, null);
        test.put(null, null);
        test.containsValue(null);
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
