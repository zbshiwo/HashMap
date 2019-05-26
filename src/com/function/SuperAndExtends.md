## JAVA 泛型中 extends 和 super 的区别
`<? super Class>` 是指 “下界通配符（Lower Bounds Wildcards）  
`<? extends Class>` 是指 “上界通配符（Upper Bounds Wildcards）

这里有五个类和一个测试类  
```java
class Food {}

class Fruit extends Food{}

class Meat extends Food{}

class Apple extends Fruit{}

class GreenApple extends Apple{}

class RedApple extends Apple{}
```
```java
public class SuperAndExtend<T> {
    T t;

    public SuperAndExtend(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        //频繁往外读取内容的，适合用上界Extends  生产者使用extends
        //经常往里插入的，适合用下界Super        消费者使用super
        SuperAndExtend<? extends Fruit> superAndExtend = new SuperAndExtend<>(new Fruit());

        //可以 get 数据，但是不可以 set，生产者：只 get
        Object object = superAndExtend.getT();
        Fruit fruit = superAndExtend.getT();

        //无法确定里面是 Apple 还是 RedApple 还是 GreenApple
        //Apple apple = superAndExtend.getT();
        //superAndExtend.setT(new Apple());
        //superAndExtend.setT(new RedApple());
        //superAndExtend.setT(new GreenApple());

        SuperAndExtend<? super Fruit> superAndExtend1 = new SuperAndExtend<>(new Object());

        //可以 set 数据，但是不可以 get，消费者：只 set
        superAndExtend1.setT(new Fruit());
        superAndExtend1.setT(new Apple());
        superAndExtend1.setT(new GreenApple());
        superAndExtend1.setT(new RedApple());

        //无法确定里面是 Fruit 还是 Food 还是 Object，所以无法 get
        //Object object1 = superAndExtend1.getT();
        //Food food = superAndExtend1.getT();
        //Fruit fruit1 = superAndExtend1.getT();
    }
}
```
## 结论
```java
class Test<T> {
     T t;
     T get() {
         return t;
     }
     void set(T t) {
         this.t = t;
     }
}
```
当使用 `<? extends Test>` 方法时，可以使用 `get` 方法，返回值限定为 `T`，而我们的限定条件是 `Test` 或者它的子类 == Predicate  
当使用 `<? super Test>` 方法时，可以使用 `set` 方法，方法参数限定为 `T`，我们可以 `set` 它的子类 == Consumer  


