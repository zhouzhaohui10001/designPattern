package cn.cy.singletonPattern;

/**
 * Created by zhouzhaohui on 2017/8/10.
 */
//饿汉式 线程安全
//类加载是就实例化 达不到lazy loading 效果
class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {};
    public static Singleton getInstance() {
        return instance;
    }
}
//属于上边一种的变种
class StaticBlockSingleton {
    private static StaticBlockSingleton instance;
    static {
        instance = new StaticBlockSingleton();
    }
    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
//饿汉式
//第一种 线程不安全 多线程下无法工作
class Singleton1 {
    private static Singleton1 instance ;
    private Singleton1() {};
    public static  Singleton1 getInstance() {
        if(instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

}
//第二种 线程安全 但是效率比较低 99%情况下是不需要同步
class  Singleton2 {
    private static  Singleton2 instance;
    private Singleton2() {};
    public static synchronized Singleton2 getInstance() {
        if(instance==null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
//第三种 双重校验 效率高 同步发生少 jdk1.5以后生效
class  Singleton3 {
    private static  Singleton3 instance;
    private Singleton3() {};
    public static Singleton3 getInstance() {
        if(instance==null) {
            synchronized (Singleton3.class) {
                if(instance==null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
//第四种 静态内部类
class Singleton4 {
    private static class Singletonholder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4() {};
    public static Singleton4 getInstance() {
        return Singletonholder.INSTANCE;
    }
}
/*
解释下静态内部类为什么是线程安全的懒加载模式
这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，
它跟饿汉的两个示例不同的是（很细微的差别）：饿汉的两个示例方式是只要Singleton类被装载了，
那么instance就会被实例化（没有达到lazy loading效果），而这种方式是Singleton类被装载了，
instance不一定被初始化。因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，
才会显示装载SingletonHolder类，从而实例化instance。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，
另外一方面，我不希望在Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比饿汉的两个示例式就显得很合理。
 */
//第五种 枚举
 enum Singleton5 {
    INSTANCE;
    public void whateverMethod() {
    }
}
/*
这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，
而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
 */
public class SingletonPattern {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}
